package com.atguigu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObject;
import com.atguigu.service.OssService;
import com.atguigu.utils.OssConstantsUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.xmlbeans.XmlOptions;
import org.joda.time.DateTime;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar2Oss(MultipartFile file) {

         String endPoint = OssConstantsUtil.END_POINT;
         String accesskeyId = OssConstantsUtil.ACCESSKEY_ID;
         String accessKeySecret = OssConstantsUtil.ACCESS_KEY_SECRET;
         String bucketName = OssConstantsUtil.BUCKET_NAME;

        //创建OssClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accesskeyId, accessKeySecret);

        //判断bucket是否存在，不存在就创建
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建bucket
            ossClient.createBucket(bucketName);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        //上传文件流
        try {
            InputStream inputStream = file.getInputStream();

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名(包含扩展名)
            String original = file.getOriginalFilename();
            //获取随机数作为fileName
            String fileName = UUID.randomUUID().toString();
            //获取文件的扩展名
            String fileType = original.substring(original.lastIndexOf("."));
            //通过使用随机数和扩展名拼接成新的文件名
            String newName = fileName + fileType;
            //通过日期创建文件夹
            String fileUrl =  filePath + "/" + newName;
            //调用oss方法实现上传
            //第一个参数：Bucket名称
            //第二个参数：上传到oss的文件路径和文件名称   /aa/bb1.jpg
            //第三个参数：上传文件输入流
            ossClient.putObject(bucketName,fileUrl,inputStream);
            //  https://edu1129.oss-cn-beijing.aliyuncs.com/8470181.jpg
            String url = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ossClient.shutdown();
        }


    }

    @Override
    public String downLoadFile2Oss() {
        String endPoint = OssConstantsUtil.END_POINT;
        String accesskeyId = OssConstantsUtil.ACCESSKEY_ID;
        String accessKeySecret = OssConstantsUtil.ACCESS_KEY_SECRET;
        String bucketName = OssConstantsUtil.BUCKET_NAME;
        String objectName1 = "1.docx";
        String objectName2 = "2.docx";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accesskeyId, accessKeySecret);

// ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject1 = ossClient.getObject(bucketName, objectName1);

// 读取文件内容。
        InputStream reader1 = ossObject1.getObjectContent();
// ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject2 = ossClient.getObject(bucketName, objectName2);

// 读取文件内容。
        InputStream reader2 = ossObject2.getObjectContent();

        File newFile = new File("D:\\word\\merge3.doc");
        List<InputStream> srcfile = new ArrayList<>();
        srcfile.add(reader2);
        srcfile.add(reader1);

        try {
            OutputStream dest = new FileOutputStream(newFile);
            ArrayList<XWPFDocument> documentList = new ArrayList<>();
            XWPFDocument doc = null;
            for (int i = 0; i < srcfile.size(); i++) {
                OPCPackage open = OPCPackage.open(srcfile.get(i));
                XWPFDocument document = new XWPFDocument(open);
                documentList.add(document);
            }
            for (int i = 0; i < documentList.size(); i++) {
                doc = documentList.get(0);
                if(i != 0){
                    documentList.get(i).createParagraph().setPageBreak(true);
                    appendBody(doc,documentList.get(i));
                }
            }
            doc.createParagraph().setPageBreak(true);
            doc.write(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ossClient.shutdown();

        return null;
    }

    public static void appendBody(XWPFDocument src, XWPFDocument append) throws Exception {
        CTBody src1Body = src.getDocument().getBody();
        CTBody src2Body = append.getDocument().getBody();

        List<XWPFPictureData> allPictures = append.getAllPictures();
        // 记录图片合并前及合并后的ID
        Map<String,String> map = new HashMap();
        for (XWPFPictureData picture : allPictures) {
            String before = append.getRelationId(picture);
            //将原文档中的图片加入到目标文档中
            String after = src.addPictureData(picture.getData(), Document.PICTURE_TYPE_PNG);
            map.put(before, after);
        }

        appendBody(src1Body, src2Body,map);

    }

    private static void appendBody(CTBody src, CTBody append, Map<String,String> map) throws Exception {
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);

        String srcString = src.xmlText();
        String prefix = srcString.substring(0,srcString.indexOf(">")+1);
        String mainPart = srcString.substring(srcString.indexOf(">")+1,srcString.lastIndexOf("<"));
        String sufix = srcString.substring( srcString.lastIndexOf("<") );
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));

        if (map != null && !map.isEmpty()) {
            //对xml字符串中图片ID进行替换
            for (Map.Entry<String, String> set : map.entrySet()) {
                addPart = addPart.replace(set.getKey(), set.getValue());
            }
        }
        //将两个文档的xml内容进行拼接
        CTBody makeBody = CTBody.Factory.parse(prefix+mainPart+addPart+sufix);

        src.set(makeBody);
    }

}

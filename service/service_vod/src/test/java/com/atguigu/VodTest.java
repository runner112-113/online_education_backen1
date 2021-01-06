package com.atguigu;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VodTest {


    public static void main(String[] args) throws ClientException {
        //根据视频id获取视频播放地址
        //获取初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GCBBKjxYE8WW5JCdV8v", "9lNfEyWdQjk4Zvz28FtxqjdCSbIHmZ");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象里面设置视频id
        request.setVideoId("9fdaaaafb27742139632d4b1607a5703");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");


    }
}



package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvatar2Oss(MultipartFile file);

    String downLoadFile2Oss();
}

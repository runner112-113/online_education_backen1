package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFile2Oss(MultipartFile file);

    String downLoadFile2Oss();
}

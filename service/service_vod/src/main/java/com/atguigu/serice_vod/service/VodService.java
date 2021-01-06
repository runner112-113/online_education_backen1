package com.atguigu.serice_vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideo(MultipartFile file);

    boolean deleteVideo(String videoId);
}

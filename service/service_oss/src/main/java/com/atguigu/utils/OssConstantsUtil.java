package com.atguigu.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssConstantsUtil implements InitializingBean {

    @Value("${aliyun.accesskeyId}")
    private String accesskeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.endPoint}")
    private String endPoint;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public static String END_POINT;
    public static String BUCKET_NAME;
    public static String ACCESS_KEY_SECRET;
    public static String ACCESSKEY_ID;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        BUCKET_NAME = bucketName;
        ACCESS_KEY_SECRET = accessKeySecret;
        ACCESSKEY_ID = accesskeyId;
    }
}

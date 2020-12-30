package com.atguigu.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类，读取配置文件application.properties中的配置
 * 用spring的InitializingBean的afterPropertiesSet来初始化配置信息，
 * 这个方法将在所有的属性被初始化后调用。
 */
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

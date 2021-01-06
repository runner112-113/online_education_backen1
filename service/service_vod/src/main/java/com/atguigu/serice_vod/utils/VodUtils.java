package com.atguigu.serice_vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VodUtils implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String accessKeyIdStr;

    @Value("${aliyun.vod.file.keysecret}")
    private String accessKeySecretStr;

    public static String accessKeyId;
    public static String accessKeySecret;

    //这个方法将在所有的属性被初始化后调用。
    @Override
    public void afterPropertiesSet() throws Exception {
        accessKeyId = accessKeyIdStr;
        accessKeySecret = accessKeySecretStr;
    }
}

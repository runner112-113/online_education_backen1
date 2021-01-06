package com.atguigu;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VodConfig {

    @Bean
    public DefaultAcsClient client() {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, "LTAI4GCBBKjxYE8WW5JCdV8v", "9lNfEyWdQjk4Zvz28FtxqjdCSbIHmZ");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}

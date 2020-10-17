package com.music;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName HelloApplication
 * @Description TODO
 * @Author xiep
 * @Date 2019/5/16 9:22
 **/

@SpringBootApplication
@EnableTransactionManagement
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(DataSize.parse("10240MB"));
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("10240MB"));
        return factory.createMultipartConfig();
    }


    @Bean
    public OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }


}

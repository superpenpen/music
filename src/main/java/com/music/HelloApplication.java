package com.music;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName HelloApplication
 * @Description TODO
 * @Author xiep
 * @Date 2019/5/16 9:22
 **/

@SpringBootApplication
@EnableTransactionManagement
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}

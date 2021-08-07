package com.im_first;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.im_first.mapper")
@ComponentScan(basePackages = {"com.im_first","n3r.idworker"})
public class ImFirstApplication {

    public static void main(String[] args) {
        initLogRecord.initLog();
        SpringApplication.run(ImFirstApplication.class, args);
    }

}

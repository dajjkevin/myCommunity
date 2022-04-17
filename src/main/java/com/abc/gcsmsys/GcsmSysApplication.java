package com.abc.gcsmsys;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

// @EnableOpenApi  用来启动打开Swagger
@EnableOpenApi
@SpringBootApplication
@EnableScheduling
@MapperScan("com.abc.gcsmsys.mapper")
@ServletComponentScan
public class GcsmSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcsmSysApplication.class, args);
    }

}

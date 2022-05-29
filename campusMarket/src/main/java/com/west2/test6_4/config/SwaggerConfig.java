package com.west2.test6_4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TAG_1 = "用户商品操作类_用户权限";
    public static final String TAG_2 = "商品管理类_用户权限";
    public static final String TAG_3 = "管理员操作类_需管理员权限";
    public static final String TAG_4 = "基本注册类_无需权限验证";
    public static final String TAG_5 = "订单相关类_用户权限";
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    Contact contact = new Contact("HuaQi", "", "");

    private ApiInfo apiInfo() {
        return new ApiInfo("west2_6 Api文档", "Api 文档", "1.0", "urn:tos", contact, "", "", new ArrayList());

    }
}

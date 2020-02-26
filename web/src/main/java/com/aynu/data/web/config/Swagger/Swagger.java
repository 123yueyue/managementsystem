package com.aynu.data.web.config.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Configuration
@EnableSwagger2
public class Swagger {
    // http://localhost:8080/swagger-ui.html
    // Swagger2默认将所有的Controller中的RequestMapping方法都会暴露，
    // 然而在实际开发中，我们并不一定需要把所有API都提现在文档中查看，这种情况下，使用注解
    // @ApiIgnore来解决，如果应用在Controller范围上，则当前Controller中的所有方法都会被忽略，
    // 如果应用在方法上，则对应用的方法忽略暴露API

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.aynu.data.web.core")).paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("com.aynu.data").description("实践积分管理系统API文档")
                .termsOfServiceUrl("http://blog.csdn.net/je_ge").contact("张玥").version("1.0").build();
    }

}



package com.quantchi.swaggerConfig;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swaggers {

  public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.quantchi";

  @Bean
  public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE)).paths(PathSelectors.any()).build();
    return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Dmp数据分析平台").description("业务数据处理项目接口说明")
        .termsOfServiceUrl("http://localhost:8081/search/").contact("杭州量知数据科技有限公司").version("1.0")
        .build();
  }

}

package com.yotaku.exchanger.exchangerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class ExchangerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangerApiApplication.class, args);
    }

    @Bean
    public Docket swaggerExchangeApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("conversion-api-1.0")
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.yotaku.exchanger.exchangerapi.web.rest.controller"))
                .paths(PathSelectors.regex("/api/conversion/v1.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Exchanger API")
                .description("Simple foreign exchange API")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0")
                .build();
    }
}

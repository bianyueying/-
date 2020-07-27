package bml.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 月影
 * Date 2020/2/15 17:01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置了Swagger2的Bean实例
     * http://localhost:8080/swagger-ui.html
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("月影")
                .select()
                .apis(RequestHandlerSelectors.basePackage("bml.controller"))
                .build();
    }

    /**
     * 配置Swagger2信息
     */
    private ApiInfo apiInfo(){
        Contact contact = new Contact("月影", "https://www.bianmenglei.xyz", "2966566116@qq.com");
        return new ApiInfo(
                "毕设开发文档",
                "我会拿回往日的荣耀",
                "V1.0",
                "https://www.bianmenglei.xyz",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

}

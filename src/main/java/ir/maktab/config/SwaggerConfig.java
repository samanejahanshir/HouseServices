package ir.maktab.config;

import com.google.common.base.Predicates;
import com.wordnik.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"ir.maktab.web"})
public class SwaggerConfig extends WebMvcConfigurerAdapter {
   /* @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }*/

   /* private ApiInfo apiInfo() {
        Contact contact = new  Contact ("Xiao Ming", "http://www.cnblogs.com/getupmorning/", "zhaoming0018@126.com"); //  Equivalent to postcards
        return new ApiInfoBuilder()
                .title("Front API Interface") //  title
                .description ("API Interface") //  description
                .contact(contact)
                .version("1.1.0") //  version
                .build();
    }*/
   @Bean
   public Docket api() {
       // @formatter:off
       //Register the controllers to swagger
       //Also it is configuring the Swagger Docket
       return new Docket(DocumentationType.SWAGGER_2).select()
               // .apis(RequestHandlerSelectors.any())
               .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
               // .paths(PathSelectors.any())
               // .paths(PathSelectors.ant("/swagger2-demo"))
               .build();
       // @formatter:on
   }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

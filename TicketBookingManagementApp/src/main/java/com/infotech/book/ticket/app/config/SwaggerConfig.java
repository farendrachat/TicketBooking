package com.infotech.book.ticket.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        .select()                                  
        .apis(RequestHandlerSelectors.any())              
        .paths(PathSelectors.any())                          
        .build();
        /*
         * Above config : It takes care of all rest points used throughout the application.
         * Below config: It is restricted to a specific package with specific end points.
         */
        
        /*  .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.infotech.book.ticket.app.controller"))              
          .paths(regex("/api/tickets.*"))                          
          .build();  */                                         
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/TicketBookingManagementApp/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/TicketBookingManagementApp/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/TicketBookingManagementApp/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/TicketBookingManagementApp/swagger-resources", "/swagger-resources");
    }
    
    /*
     * This is mandatory to show in UI
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/TicketBookingManagementApp/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/TicketBookingManagementApp/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

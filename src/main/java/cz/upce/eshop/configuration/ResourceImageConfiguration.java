package cz.upce.eshop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ResourceImageConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagesDir = "file:D:/Dokumenty/Skola/UPCE/Magisterské studium/1. Prvák/NNPIA (Programování internetových aplikací)/Cvičení/5/eshop/images/";
        registry.addResourceHandler("/images/**").addResourceLocations(imagesDir);
    }
}

package com.edmp.api.board.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
//        .allowedOrigins("http://101.101.218.129", "http://127.0.0.1:3000", "http://localhost:3000")
    	.allowedOrigins("*")
//        .allowCredentials(true)
        .allowedMethods(
            	HttpMethod.GET.name(),
            	// HttpMethod.HEAD.name(),
            	HttpMethod.POST.name(),
            	HttpMethod.PUT.name(),
            	HttpMethod.DELETE.name(),
            	HttpMethod.PATCH.name());
    }
}


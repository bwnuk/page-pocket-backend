package com.cracow.spring;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//adding MVC functionality
@Configuration
// If we want to enable xml configurations for message-converter:
// @ImportResource("classpath:WEB-INF/api-servlet.xml")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<>( new ShallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/page-pocket-backend/*");
        filterRegistrationBean.setName("etagFilter");
        return filterRegistrationBean;
    }

}
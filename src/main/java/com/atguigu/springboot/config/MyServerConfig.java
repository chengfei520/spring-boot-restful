package com.atguigu.springboot.config;

import com.atguigu.springboot.filter.Myfilter;
import com.atguigu.springboot.listener.MyListener;
import com.atguigu.springboot.servlet.Myservlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

@Configuration
public class MyServerConfig {
    //注册三大组件
    @Bean
    public ServletRegistrationBean<Myservlet> myServlet(){
        return new ServletRegistrationBean<Myservlet>(new Myservlet(),"/myServlet");
    }
    @Bean
    public FilterRegistrationBean<Myfilter> filterRegistrationBean(){
        FilterRegistrationBean<Myfilter> filterRegistrationBean= new FilterRegistrationBean<Myfilter>();
        filterRegistrationBean.setFilter(new Myfilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myServlet"));
        return filterRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean<MyListener> myListenerServletContextListener(){
        return new ServletListenerRegistrationBean<MyListener>(new MyListener());
    }
    //配置嵌入式的servlet容器
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>(){
            @Override
            public void customize(ConfigurableWebServerFactory factory){
                factory.setPort(8083);
            }
        };
    }

}

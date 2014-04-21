/*
 * Copyright
 */

package com.recursiveknowledge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Dustin Sweigart <dustin@swigg.net>
 */
@Configuration
@EnableWebMvc
@Import({JtwigConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

    @Bean
    public ChapterController chapterController() {
        return new ChapterController();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

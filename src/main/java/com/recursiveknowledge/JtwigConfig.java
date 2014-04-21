/*
 * Copyright
 */

package com.recursiveknowledge;

import com.lyncode.jtwig.mvc.JtwigView;
import com.lyncode.jtwig.mvc.JtwigViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Class Description
 *
 * @author Dustin Sweigart <dustin@swigg.net>
 */
public class JtwigConfig {
    @Bean
    public ViewResolver urlBasedViewResolver() {
        JtwigViewResolver viewResolver = new JtwigViewResolver();
        viewResolver.setViewClass(JtwigView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".twig");
        viewResolver.addFunctionPackages("com.recursiveknowledge");

        return viewResolver;
    }
}

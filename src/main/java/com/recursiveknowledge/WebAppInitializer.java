/*
 * Copyright
 */

package com.recursiveknowledge;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.Filter;

/**
 * @author Dustin Sweigart <dustin@swigg.net>
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MainConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        UrlRewriteFilter urlRewriteFilter = new UrlRewriteFilter();

        return new Filter[]{urlRewriteFilter};
    }
}

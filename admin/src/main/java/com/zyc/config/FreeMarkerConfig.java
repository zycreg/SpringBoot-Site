package com.zyc.config;

import com.zyc.directive.PaginationDirective;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangyuancheng
 * @create 2017-11-07 下午4:27
 * @descripition FreeMarker配置类
 */
@Component
public class FreeMarkerConfig {
    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private PaginationDirective paginationDirective;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("pagination", paginationDirective);
    }
}

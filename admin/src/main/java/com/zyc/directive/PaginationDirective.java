package com.zyc.directive;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @author zhangyuancheng
 * @create 2017-11-07 下午4:18
 * @descripition 分页标签
 */
@Component
public class PaginationDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        String url = params.get("url").toString();
        env.setVariable("url", getBeansWrapper().wrap(url));
        env.include("/public/pagination.html","UTF-8",true);
    }

    public static BeansWrapper getBeansWrapper(){
        BeansWrapper beansWrapper =
                new BeansWrapperBuilder(Configuration.getVersion()).build();
        return beansWrapper;
    }

}

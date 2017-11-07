package com.zyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.autoconfigure.web.ErrorController;

/**
 * @author zhangyuancheng
 * @create 2017-11-07 下午9:41
 * @descripition
 */
@Controller
@RequestMapping(value = "/error/")
public class ExceptionController implements ErrorController {

    /**
     * @return
     */
    @RequestMapping(produces = "text/html",value = "404")
    public String errorHtml404() {
        return "error/404";
    }

    /**
     * @return
     */
    @RequestMapping(produces = "text/html",value = "500")
    public String errorHtml500() {
        return "error/500";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}

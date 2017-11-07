package com.zyc.interceptor;

import com.zyc.global.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangyuancheng
 * @create 2017-11-02 下午7:51
 * @descripition 登录状态拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    private static final String[] EXCLUDE_URLS ={"/login","/login_do","/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("进入了拦截器。。。。。。。");

        String pMeunId = request.getParameter("pMenuId");
        String subMenuId = request.getParameter("subMenuId");
        request.getSession().setAttribute("pMeunId",pMeunId);
        request.getSession().setAttribute("subMenuId",subMenuId);

        String requestUri = request.getRequestURI();
        for(String x:EXCLUDE_URLS){
            if(requestUri.indexOf(x)!=-1){
                return super.preHandle(request, response, handler);
            }
        }
        //登录判断
        if(request.getSession().getAttribute(Constants.CONSOLE_ADMIN_USER)==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        //User user = (User)request.getSession().getAttribute(Constants.CONSOLE_ADMIN_USER);
        //UserCache.setUser(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}

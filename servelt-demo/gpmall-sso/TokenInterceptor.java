package com.gupaoedu.sso.intercept;

import com.gupaoedu.sso.constants.GpmallWebConstant;
import com.gupaoedu.sso.controller.Anonymous;
import com.gupaoedu.sso.controller.BaseController;
import com.gupaoedu.sso.utils.CookieUtil;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.CheckAuthRequest;
import com.gupaoedu.user.dto.CheckAuthResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: LIH
 * @Date: 2020/7/10 13:11
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN = "access_token";

    @Autowired
    private IUserCoreService userCoreService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        if (!(bean instanceof BaseController)) {
            throw new RuntimeException("must extend BaseController");
        }
        if (isAnonymous(handlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        String token = CookieUtil.getCookieValue(request, ACCESS_TOKEN);
        System.out.println("==TokenInterceptor==token:" + token);
        boolean isAjax = CookieUtil.isAjax(request);
        if (StringUtils.isEmpty(token)) {
            if (isAjax) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("{\"code\":\"-1\",\"msg\":\"error\"}");
                return false;
            }
            response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
            return false;
        }
        CheckAuthRequest checkAuthRequest = new CheckAuthRequest();
        checkAuthRequest.setToken(token);
        CheckAuthResponse checkAuthResponse = userCoreService.validToken(checkAuthRequest);
        if ("000000".equals(checkAuthResponse.getCode())) {
            BaseController baseController = (BaseController) bean;
            baseController.setUid(checkAuthResponse.getUid());
            return super.preHandle(request, response, handler);
        }
        if (isAjax) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            response.getWriter().write(objectMapper.writeValueAsString(checkAuthResponse));
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":\"" +
                    checkAuthResponse.getCode() + "\",\"msg\":\"" +
                    checkAuthResponse.getMsg() + "\"}");
            return false;
        }
        response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
        return false;
    }

    private boolean isAnonymous(HandlerMethod handlerMethod) {
        Object bean = handlerMethod.getBean();
        Class<?> clazz = bean.getClass();
        if (clazz.isAnnotationPresent(Anonymous.class)) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(Anonymous.class)) {
            return true;
        }
        return false;
    }
}

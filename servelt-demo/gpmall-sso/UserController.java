package com.gupaoedu.sso.controller;

import com.gupaoedu.sso.constants.GpmallWebConstant;
import com.gupaoedu.sso.controller.support.ResponseData;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: LIH
 * @Date: 2020/7/8 17:40
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private IUserCoreService userCoreService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Anonymous
    @GetMapping("test")
    public String doTest() {
        kafkaTemplate.send("test","Hello LIH");
        return "success";
    }


    @PostMapping("/login")
    @Anonymous
    public ResponseData doLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                                HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        UserLoginResponse userLoginResponse = userCoreService.login(request);
        response.addHeader("Set-Cookie",
                "access_token=" + userLoginResponse.getToken() + ";Path=/;HttpOnly");

        responseData.setCode(userLoginResponse.getCode());
        responseData.setMsg(userLoginResponse.getMsg());
        responseData.setData(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
        return responseData;
    }

    @GetMapping("/test")
    public String test() {
        return "success" + this.getUid();
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> clazz = UserController.class;
        Method doLogin = clazz.getMethod("doLogin", String.class, String.class, HttpServletResponse.class);
        /*Annotation[][] parameterAnnotations = doLogin.getParameterAnnotations();
//        System.out.println(Arrays.deepToString(parameterAnnotations));
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            for (int j = 0; j < parameterAnnotation.length; j++) {
                Annotation annotation = parameterAnnotation[j];
//                System.out.println(annotation);
                if (annotation instanceof RequestParam) {
                    String value = ((RequestParam) annotation).value();
                    System.out.println(value);
                }
            }
        }*/
        Class<?>[] parameterTypes = doLogin.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            System.out.println(parameterType);
            if (parameterType == HttpServletRequest.class ||
                    parameterType == HttpServletResponse.class) {
                System.out.println(parameterType.getSimpleName() + "----" + i);
            }
        }
    }
}

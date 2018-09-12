package com.quantchi.authority.controller;

import com.quantchi.common.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/12 10:35
 * @Version 1.0.0
 **/

@Controller
@RequestMapping("api")
public class LoginController {

    /**
     * @api {post} /api/login 用户登录
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleRole
     * @apiName login
     * @apiGroup LoginController
     * @apiParam {String} [username] 用户名
     * @apiParam {String} [password] 密码
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  登录成功或者错误信息
     */

    @ResponseBody
    @RequestMapping(value = "/login", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody Map<String, Object> requestMap){
        String username = (String) requestMap.get("username");
        String password = (String) requestMap.get("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login(token);

        }catch (UnknownAccountException e) {
            e.printStackTrace();
            return JsonResult.errorJson("login failed! ... UnknownAccount");
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return JsonResult.errorJson("login failed! ... IncorrectCredentials");
        }catch(AuthenticationException e) {
            e.printStackTrace();
        }

        if(currentUser.isAuthenticated()) {
            return JsonResult.successJson("login success! ... ");
        }else {
            return JsonResult.errorJson("login failed! ... ");
        }
    }

}

package com.lloyvet.sys.controller;

import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.service.UserService;
import com.lloyvet.sys.utils.WebUtils;
import com.lloyvet.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/main/login";
    }
    /**
     * 登录方法
     */
    @RequestMapping("login")
    public String login(UserVo userVo, Model model){
        User user = userService.login(userVo);
        if(null!=user){
            //放进session
            WebUtils.getHttpSession().setAttribute("user",user);
            //记录登录日志向sys_login_log里面插入数据
            return "system/main/index";
        }else {
            model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
            return "system/main/login";
        }
    }
}

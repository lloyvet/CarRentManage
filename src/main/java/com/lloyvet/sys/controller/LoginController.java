package com.lloyvet.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.service.LogInfoService;
import com.lloyvet.sys.service.UserService;
import com.lloyvet.sys.utils.WebUtils;
import com.lloyvet.sys.vo.LogInfoVo;
import com.lloyvet.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogInfoService logInfoService;
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
        String code = (String)WebUtils.getHttpSession().getAttribute("code");
        if(userVo.getCode().equalsIgnoreCase(code)){
            User user = userService.login(userVo);
            if(null!=user){
                //放进session
                WebUtils.getHttpSession().setAttribute("user",user);
                //记录登录日志向sys_login_log里面插入数据
                LogInfoVo logInfoVo = new LogInfoVo();
                logInfoVo.setStartTime(new Date());
                logInfoVo.setLoginname(user.getRealname()+"-"+user.getLoginname());
                logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                logInfoService.addLogInfo(logInfoVo);
                return "system/main/index";
            }else {
                model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        } else {
            model.addAttribute("error", SysConstast.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }
    }
    /**
     * 得到登录验证码
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,5);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }
}

package com.lloyvet.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("sys")
public class SysController {

    /**
     * 跳转菜单管理
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager(){
        return "system/menu/menuManager";
    }
    /**
     * 菜单管理的左页面
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }
    /**
     * 菜单管理的右页面
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }
    /**
     * 跳转角色管理页面
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager(){
        return "system/role/roleManager";
    }
    /**
     * 跳转用户管理页面
     */
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }
    /**
     * 跳转日志管理
     */
    @RequestMapping("toLogInfoManager")
    public String toLogInfoManager(){
       return "system/logInfo/logInfoManager";
    }
    /**
     * 跳转公告管理页面
     */
    @RequestMapping("toNewsManager")
    public String toNewsManager(){
        return "/system/news/newsManager";
    }
}

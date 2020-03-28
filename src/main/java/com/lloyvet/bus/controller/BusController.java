package com.lloyvet.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务跳转管理器
 */
@Controller
@RequestMapping("bus")
public class BusController {

    /**
     * 跳转到用户管理页面
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到车辆管理页面
     */
    @RequestMapping("toCarManager")
    public String toCarManager(){
        return "business/car/carManager";
    }
    /**
     * 跳转车辆出租页面
     */
    @RequestMapping("toRentCarManager")
    public String toRentCarManager(){
        return "business/rent/rentCarManager";
    }
    /**
     * 跳转汽车出租单页面
     */
    @RequestMapping("toRentManager")
    public String toRentManager(){
        return "business/rent/rentManager";
    }
    /**
     * 跳转汽车入库页面
     */
    @RequestMapping("toCheckManager")
    public String toCheckManager(){
        return "business/check/checkCarManager";
    }
    /**
     * 跳转出租单
     */
    @RequestMapping("toCheck")
    public String toCheck(){
        return "business/check/checkManager";
    }
}

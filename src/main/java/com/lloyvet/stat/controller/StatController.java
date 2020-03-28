package com.lloyvet.stat.controller;

import com.lloyvet.stat.domain.BaseEntity;
import com.lloyvet.stat.service.StatService;
import com.lloyvet.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析
 */
@Controller
@RequestMapping("stat")
public class StatController {

    @Autowired
    private StatService statService;
    /**
     * 跳转客户地区统计页面
     */
    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }
    /**
     * 跳转销售员统计页面
     */
    @RequestMapping("toOpernameYearGradeStat")
    public String toOpernameYearGradeStat(){
        return "stat/opernameYearGradeStat";
    }
    /**
     * 跳转公司统计页面
     */
    @RequestMapping("toCompanyYearGradeStat")
    public String toCompanyYearGradeStat(){
        return "stat/companyYearGradeStat";
    }
    @ResponseBody
    @RequestMapping("loadCustomerAreaStatJosn")
    public List<BaseEntity> loadCustomerAreaStatJosn(){
        return statService.loadCustomerAreaStatJosn();
    }
    /**
     * 业务员销售额
     */
    @ResponseBody
    @RequestMapping("loadOpernameYearGradeStat")
    public Map<String,Object> loadOpernameYearGradeStat(String year){
        List<BaseEntity> entities = statService.loadOpernameYearGradeStat(year);
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        Map<String,Object> res = new HashMap<>();
        for (BaseEntity entity : entities) {
            names.add(entity.getName());
            values.add(entity.getValue());
        }
        res.put("name",names);
        res.put("value",values);
        return res;
    }
    /**
     * 公司年度销售额
     */
    @ResponseBody
    @RequestMapping("loadCompanyYearGradeStat")
    public List<Double> loadCompanyYearGradeStat(String year){
        List<Double> list = statService.loadCompanyYearGradeStat(year);
        List<Double> res = new ArrayList<>();
        for (Double aDouble : list) {
            if(aDouble==null)
                res.add(0.0);
            else
                res.add(aDouble);
        }
        return res;
    }
}

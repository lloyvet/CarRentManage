package com.lloyvet.bus.controller;

import com.lloyvet.bus.domain.Check;
import com.lloyvet.bus.domain.Rent;
import com.lloyvet.bus.service.CheckService;
import com.lloyvet.bus.service.RentService;
import com.lloyvet.bus.vo.CheckVo;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 检查单管理控制器
 */
@RestController
@RequestMapping("check")
public class CheckController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CheckService checkService;
    /**
     * 根据汽车出租单号查询出租信息
     */
    @RequestMapping("checkRentExist")
    public Rent ckeckRentExist(String rentid){
        Rent rent = rentService.queryRentBtRentId(rentid);
        return rent;
    }
    /**
     * 加载表单数据
     */
    @RequestMapping("initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){
        return checkService.initCheckFormData(rentid);
    }
    /**
     * 保存更新后表单数据
     */
    @RequestMapping("saveCheck")
    public ResultObj saveCheck(CheckVo checkVo){
        try{
            checkVo.setCreatetime(new Date());
            checkService.saveCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 加载出租单
     */
    @RequestMapping("loadAllCheck")
    public DataGridView loadAllCheck(CheckVo checkVo){
        return checkService.queryAllCheck(checkVo);
    }
    /**
     * 更新
     */
    @RequestMapping("updateCheck")
    public ResultObj updateCheck(CheckVo checkVo){
        try{
            checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}

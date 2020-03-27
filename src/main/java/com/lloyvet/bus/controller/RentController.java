package com.lloyvet.bus.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.bus.domain.Customer;
import com.lloyvet.bus.service.CustomerService;
import com.lloyvet.bus.service.RentService;
import com.lloyvet.bus.vo.RentVo;
import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.RandomUtils;
import com.lloyvet.sys.utils.ResultObj;
import com.lloyvet.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("rent")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private CustomerService customerService;
    /**
     * 检查身份证号是否存在
     */
    @RequestMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(RentVo rentVo){
        Customer customer = customerService.queryCustomerByIdentity(rentVo.getIdentity());
        if(null!=customer){
            return ResultObj.STATUS_TRUE;
        }else{
            return ResultObj.STATUS_FALSE;
        }
    }
    /**
     * 初始化添加出租单的表单数据
     */
    @RequestMapping("initRentFrom")
    public RentVo initRentFrom(RentVo rentVo){
        //生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
        //设置起租时间
        rentVo.setBegindate(new Date());
        //设置操作员
        User user = (User)WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }
    /**
     * 保存修改的出租单
     */
    @RequestMapping("saveRent")
    public ResultObj saveRent(RentVo rentVo){
        try{
            //设置创建时间
            rentVo.setCreatetime(new Date());
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
            rentService.saveRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 查询所有出租
     */
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo){
        Page<Object> page = PageHelper.startPage(rentVo.getPage(),rentVo.getLimit());
        List<RentVo> rentVos = rentService.loadAllRent(rentVo);
        return new DataGridView(page.getTotal(),rentVos);
    }
    /**
     * 修改
     */
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo){
        try{
            rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除出租
     */
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(RentVo rentVo){
        try{
            rentService.deleteRent(rentVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

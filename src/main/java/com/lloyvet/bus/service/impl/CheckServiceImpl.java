package com.lloyvet.bus.service.impl;

import com.lloyvet.bus.domain.Car;
import com.lloyvet.bus.domain.Check;
import com.lloyvet.bus.domain.Customer;
import com.lloyvet.bus.domain.Rent;
import com.lloyvet.bus.mapper.CarMapper;
import com.lloyvet.bus.mapper.CheckMapper;
import com.lloyvet.bus.mapper.CustomerMapper;
import com.lloyvet.bus.mapper.RentMapper;
import com.lloyvet.bus.service.CheckService;
import com.lloyvet.bus.vo.CheckVo;
import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.utils.RandomUtils;
import com.lloyvet.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;
    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        Rent rent = rentMapper.selectByPrimaryKey(rentid);
        //查询客户
        Customer customer = customerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        Car car = carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user = (User)WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        Map<String,Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);
        return map;
    }

    @Override
    public void saveCheck(CheckVo checkVo) {
        checkMapper.updateByPrimaryKeySelective(checkVo);
        //更新出租状态
        Rent rent = rentMapper.selectByPrimaryKey(checkVo.getRentid());
        rent.setRentflag(SysConstast.RENT_BACK_TRUE);
        rentMapper.updateByPrimaryKeySelective(rent);
        //更新汽车状态
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
    }
}

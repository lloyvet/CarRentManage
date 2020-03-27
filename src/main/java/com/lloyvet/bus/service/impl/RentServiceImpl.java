package com.lloyvet.bus.service.impl;

import com.lloyvet.bus.domain.Car;
import com.lloyvet.bus.domain.Rent;
import com.lloyvet.bus.mapper.CarMapper;
import com.lloyvet.bus.mapper.RentMapper;
import com.lloyvet.bus.service.RentService;
import com.lloyvet.bus.vo.RentVo;
import com.lloyvet.sys.constast.SysConstast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public void saveRent(RentVo rentVo) {
        rentMapper.insertSelective(rentVo);
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public List<RentVo> loadAllRent(RentVo rentVo) {
        return rentMapper.queryAllRent(rentVo);
    }

    @Override
    public void deleteRent(RentVo rentVo) {
        //更改汽车状态
        Rent rent = rentMapper.selectByPrimaryKey(rentVo.getRentid());
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
        rentMapper.deleteByPrimaryKey(rentVo.getRentid());
    }

    @Override
    public void updateRent(RentVo rentVo) {
        rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    @Override
    public Rent queryRentBtRentId(String rendid) {
        return rentMapper.selectByPrimaryKey(rendid);
    }

}

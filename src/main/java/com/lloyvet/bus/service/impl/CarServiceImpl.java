package com.lloyvet.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.bus.domain.Car;
import com.lloyvet.bus.mapper.CarMapper;
import com.lloyvet.bus.service.CarService;
import com.lloyvet.bus.vo.CarVo;
import com.lloyvet.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;
    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        Page<Object> page = PageHelper.startPage(carVo.getPage(),carVo.getLimit());
        List<Car> cars = carMapper.queryAllCar(carVo);
        return new DataGridView(page.getTotal(),cars);
    }

    @Override
    public void addCar(CarVo carVo) {
        carVo.setCreatetime(new Date());
        carMapper.insert(carVo);
    }

    @Override
    public void updateCar(CarVo carVo) {
        carMapper.updateByPrimaryKeySelective(carVo);
    }

    @Override
    public void deleteCar(String carnumber) {
        carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber : carnumbers) {
            deleteCar(carnumber);
        }
    }
}

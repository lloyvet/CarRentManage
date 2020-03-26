package com.lloyvet.bus.service;

import com.lloyvet.bus.vo.CarVo;
import com.lloyvet.sys.utils.DataGridView;


/**
 * 车辆管理服务接口
 */


public interface CarService {

    /**
     * 查询所有
     * @param carVo
     * @return
     */
    DataGridView queryAllCar(CarVo carVo);

    /**
     * 添加车辆
     * @param carVo
     */
    void addCar(CarVo carVo);

    /**
     * 修改车辆
     * @param carVo
     */
    void updateCar(CarVo carVo);
    /**
     * 根据id删除车辆
     * @return
     */
    void deleteCar(String carnumber);

    /**
     * 批量删除车辆
     */
    void deleteBatchCar(String[] carnumbers);

}

package com.lloyvet.bus.controller;

import com.lloyvet.bus.domain.Car;
import com.lloyvet.bus.service.CarService;
import com.lloyvet.bus.vo.CarVo;
import com.lloyvet.sys.constast.SysConstast;
import com.lloyvet.sys.utils.AppFileUtils;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 加载所有车辆
     * @param carVo
     * @return
     */
    @RequestMapping("loadAllCar")
    public DataGridView loadAllCar(CarVo carVo){
        return carService.queryAllCar(carVo);
    }

    /**
     * 添加车辆
     * @param carVo
     * @return
     */
    @RequestMapping("addCar")
    public ResultObj addCar(CarVo carVo){
        try {
            carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改车辆
     * @param carVo
     * @return
     */
    @RequestMapping("updateCar")
    public ResultObj updateCar(CarVo carVo){
        try {
            String carimg = carVo.getCarimg();
            if(!carimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)){
                //不是以temp结尾并且不是默认图片
                if(!carimg.equals(SysConstast.DEFAULT_CAR_IMG)){
                    String path = AppFileUtils.updateFileName(carVo.getCarimg(),SysConstast.FILE_UPLOAD_TEMP);
                    carVo.setCarimg(path);
                    //把原来的删除
                    Car car = carService.queryCarByCarNumber(carVo.getCarnumber());
                    AppFileUtils.removeFileByPath(car.getCarimg());
                }

            }
            carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除车辆
     * @param carVo
     * @return
     */
    @RequestMapping("deleteCar")
    public ResultObj deleteCar(CarVo carVo){
        try {
            carService.deleteCar(carVo.getCarnumber());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除车辆
     * @param carVo
     * @return
     */
    @RequestMapping("deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo){
        try {
            carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

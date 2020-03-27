package com.lloyvet.bus.service;

import com.lloyvet.bus.domain.Rent;
import com.lloyvet.bus.vo.RentVo;

import java.util.List;
import java.util.Map;

public interface RentService {
    void saveRent(RentVo rentVo);

    List<RentVo> loadAllRent(RentVo rentVo);

    void deleteRent(RentVo rentVo);

    void updateRent(RentVo rentVo);

    Rent queryRentBtRentId(String rendid);

}

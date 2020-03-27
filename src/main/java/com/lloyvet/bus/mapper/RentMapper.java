package com.lloyvet.bus.mapper;

import com.lloyvet.bus.domain.Rent;
import com.lloyvet.bus.domain.RentExample;
import java.util.List;

import com.lloyvet.bus.vo.RentVo;
import org.apache.ibatis.annotations.Param;

public interface RentMapper {
    long countByExample(RentExample example);

    int deleteByExample(RentExample example);

    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    List<Rent> selectByExample(RentExample example);

    Rent selectByPrimaryKey(String rentid);

    int updateByExampleSelective(@Param("record") Rent record, @Param("example") RentExample example);

    int updateByExample(@Param("record") Rent record, @Param("example") RentExample example);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);

    List<RentVo> queryAllRent(RentVo rentVo);
}
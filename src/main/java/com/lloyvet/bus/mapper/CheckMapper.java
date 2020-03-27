package com.lloyvet.bus.mapper;

import com.lloyvet.bus.domain.Check;
import com.lloyvet.bus.domain.CheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CheckMapper {
    long countByExample(CheckExample example);

    int deleteByExample(CheckExample example);

    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    List<Check> selectByExample(CheckExample example);

    Check selectByPrimaryKey(String checkid);

    int updateByExampleSelective(@Param("record") Check record, @Param("example") CheckExample example);

    int updateByExample(@Param("record") Check record, @Param("example") CheckExample example);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);
}
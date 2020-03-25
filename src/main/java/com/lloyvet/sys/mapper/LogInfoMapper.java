package com.lloyvet.sys.mapper;

import com.lloyvet.sys.domain.LogInfo;
import com.lloyvet.sys.domain.LogInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogInfoMapper {
    long countByExample(LogInfoExample example);

    int deleteByExample(LogInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    List<LogInfo> selectByExample(LogInfoExample example);

    LogInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogInfo record, @Param("example") LogInfoExample example);

    int updateByExample(@Param("record") LogInfo record, @Param("example") LogInfoExample example);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);
    /**
     * 查询日志
     */
    List<LogInfo> queryAllLogInfo(LogInfo logInfo);
}
package com.lloyvet.sys.service;

import com.lloyvet.sys.domain.LogInfo;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.LogInfoVo;

import java.util.List;

/**
 * 日志管理服务接口
 */


public interface LogInfoService {

    /**
     * 查询所有
     * @param logInfoVo
     * @return
     */
    DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    /**
     * 添加日志
     * @param logInfoVo
     */
    void addLogInfo(LogInfoVo logInfoVo);

    /**
     * 根据id删除日志
     * @param logInfoid
     * @return
     */
    void deleteLogInfo(Integer logInfoid);

    /**
     * 批量删除日志
     * @param ids
     */
    void deleteBatchLogInfo(Integer[] ids);

}

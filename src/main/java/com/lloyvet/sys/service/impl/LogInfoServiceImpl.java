package com.lloyvet.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.sys.domain.LogInfo;
import com.lloyvet.sys.mapper.LogInfoMapper;
import com.lloyvet.sys.service.LogInfoService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page = PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> logInfos = logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridView(page.getTotal(),logInfos);
    }

    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        logInfoMapper.insertSelective(logInfoVo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoid) {
        logInfoMapper.deleteByPrimaryKey(logInfoid);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer id : ids) {
            deleteLogInfo(id);
        }
    }
}

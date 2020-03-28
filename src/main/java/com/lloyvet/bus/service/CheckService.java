package com.lloyvet.bus.service;

import com.lloyvet.bus.domain.Check;
import com.lloyvet.bus.vo.CheckVo;
import com.lloyvet.sys.utils.DataGridView;

import java.util.Map;

/**
 * 检查单管理服务
 */
public interface CheckService {
    Map<String, Object> initCheckFormData(String rentid);

    void saveCheck(CheckVo checkVo);


    DataGridView queryAllCheck(CheckVo checkVo);

    void updateCheck(CheckVo checkVo);
}

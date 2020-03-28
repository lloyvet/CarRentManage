package com.lloyvet.stat.service;

import com.lloyvet.stat.domain.BaseEntity;

import java.util.List;

public interface StatService {
    /**
     * 查询客户地区数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatJosn();

    /**
     * 业务员年度销售数据
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStat(String year);

    List<Double> loadCompanyYearGradeStat(String year);
}

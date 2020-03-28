package com.lloyvet.stat.service.impl;

import com.lloyvet.stat.domain.BaseEntity;
import com.lloyvet.stat.mapper.StatMapper;
import com.lloyvet.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatMapper statMapper;
    @Override
    public List<BaseEntity> loadCustomerAreaStatJosn() {
        return statMapper.queryCustomerAreaStat();
    }

    @Override
    public List<BaseEntity> loadOpernameYearGradeStat(String year) {
        return statMapper.queryOpernameYearGradeStat(year);
    }

    @Override
    public List<Double> loadCompanyYearGradeStat(String year) {
        return statMapper.queryCompanyYearGradeStat(year);
    }
}

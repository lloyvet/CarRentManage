package com.lloyvet.stat.mapper;

import com.lloyvet.stat.domain.BaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatMapper {
    public List<BaseEntity> queryCustomerAreaStat();

    List<BaseEntity> queryOpernameYearGradeStat(String year);

    List<Double> queryCompanyYearGradeStat(String year);
}

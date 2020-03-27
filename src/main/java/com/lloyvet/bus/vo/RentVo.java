package com.lloyvet.bus.vo;

import com.lloyvet.bus.domain.Rent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RentVo extends Rent {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //用于接收多个角色id
    private String[] ids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

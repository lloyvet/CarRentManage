package com.lloyvet.sys.vo;

import com.lloyvet.sys.domain.LogInfo;
import com.lloyvet.sys.domain.News;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class NewsVo extends News {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private Integer[] ids;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndtTime() {
        return endTime;
    }

    public void setEndtTime(Date endtTime) {
        this.endTime = endtTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
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

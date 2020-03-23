package com.lloyvet.sys.utils;

/**
 * 封装数据对象
 */
public class DataGridView {

    private Integer code = 0;
    private String msh = "";
    private Long count;
    private Object data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridView() {
    }

    public DataGridView(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsh() {
        return msh;
    }

    public void setMsh(String msh) {
        this.msh = msh;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

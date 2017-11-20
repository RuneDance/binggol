package com.binggol.utils;

import java.util.List;

/**
 * 结果集
 */
public class SPResult {
    //接收游标返回值
    private List rsList;
    //返回状态码
    private Integer retCode;
    //返回信息
    private String msg;

    public List getRsList() {
        return rsList;
    }

    public void setRsList(List rsList) {
        this.rsList = rsList;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

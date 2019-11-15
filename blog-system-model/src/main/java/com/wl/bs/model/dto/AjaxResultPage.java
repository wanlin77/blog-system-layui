package com.wl.bs.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页表格数据返回
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/11/7 19:40
 */
public class AjaxResultPage<T> implements Serializable {
    // 状态码
    private int code;

    // 提示消息
    private String msg;

    // 总条数
    private long count;

    // 表格数据
    private List<T> data;

    public int getCode() {
        return code;
    }

    public AjaxResultPage setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxResultPage setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public long getCount() {
        return count;
    }

    public AjaxResultPage setCount(long count) {
        this.count = count;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public AjaxResultPage setData(List<T> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "AjaxResultPage{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}

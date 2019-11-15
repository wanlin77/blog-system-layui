package com.wl.bs.common.util;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页工具类
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/20 19:35
 */
public class PageResult implements Serializable {
    // 总记录数
    private long totalNum;

    // 每页记录数
    private int pageSize;

    // 总页数
    private int totalPage;

    // 当前页数
    private int currentPage;

    // 列表数据
    private List<?> list;

    public PageResult(List<?> list, long totalNum, int pageSize, int currentPage) {
        this.list = list;
        this.totalNum = totalNum;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = (int) Math.ceil((double) totalNum / pageSize);
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}

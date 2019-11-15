package com.wl.bs.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @description: 分页查询[带条件]输入映射
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/11/7 20:51
 */
public class AjaxPutPage<T> {
    // 当前页码
    private Integer page;

    // 每页显示
    private Integer limit;

    // 从多少开始
    private Integer start;

    // 条件类
    private T condition;

    public Integer getPage() {
        return page;
    }

    public AjaxPutPage setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public AjaxPutPage setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public AjaxPutPage setStart(Integer start) {
        this.start = start;
        return this;
    }

    public T getCondition() {
        return condition;
    }

    public AjaxPutPage setCondition(T condition) {
        this.condition = condition;
        return this;
    }

    public Page<T> putPageToPage() {
        Page<T> page = new Page<>();
        page.setCurrent(this.page);
        page.setSize(this.limit);
        return page;
    }

    @Override
    public String toString() {
        return "AjaxPutPage{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", condition=" + condition +
                '}';
    }
}

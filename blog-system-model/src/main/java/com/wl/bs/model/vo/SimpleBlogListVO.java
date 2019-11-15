package com.wl.bs.model.vo;

/**
 * @description: 文章展示vo
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/20 19:54
 */
public class SimpleBlogListVO {
    // 博客id
    private Long blogId;

    // 博客标题
    private String blogTitle;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
}

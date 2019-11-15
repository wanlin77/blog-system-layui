package com.wl.bs.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wl.bs.common.constants.BlogStatusConstants;
import com.wl.bs.common.util.PageResult;
import com.wl.bs.model.entity.blog.BlogInfo;
import com.wl.bs.service.blog.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 博客controller
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/20 19:18
 */
@Controller
public class MyBlogController {
    public static String theme = "amaze";

    @Autowired
    private IBlogInfoService iBlogInfoService;

    @Autowired
    private IBlogTagService iBlogTagService;

    @Autowired
    private IBlogConfigService iBlogConfigService;

    @Autowired
    private IBlogTagRelationService iBlogTagRelationService;

    @Autowired
    private IBlogCommentService iBlogCommentService;

    @Autowired
    private IBlogLinkService iBlogLinkService;

    /**
     * @description: 博客首页
     * @param request
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/10/20 19:22
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        return this.page(request, 1);
    }

    // 博客分页
    @GetMapping({"/page/{pageNum}"})
    public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
        Page<BlogInfo> page = new Page<>(pageNum, 8);
        iBlogInfoService.page(page, new QueryWrapper<BlogInfo>()
                .lambda()
                .eq(BlogInfo::getBlogStatus, BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted, BlogStatusConstants.ZERO)
                .orderByDesc(BlogInfo::getCreateTime));
        PageResult blogPageResult = new PageResult(page.getRecords(), page.getTotal(), 8, pageNum);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("newBlogs", iBlogInfoService.listSimpleBlogListVOsForLatestOf(5));
        request.setAttribute("hotBlogs", iBlogInfoService.listSimpleBlogListVOsForMostHitsOf(5));
        request.setAttribute("hotTags", iBlogTagService.getBlogTagCountForIndex());
        request.setAttribute("pageNum", "首页");
        request.setAttribute("configurations", iBlogConfigService.getAllConfigs());
        return "blog/" + theme + "/index";
    }

}

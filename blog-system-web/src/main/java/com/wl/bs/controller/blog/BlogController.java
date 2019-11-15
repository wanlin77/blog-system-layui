package com.wl.bs.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wl.bs.common.constants.HttpStatusConstants;
import com.wl.bs.common.dto.Result;
import com.wl.bs.common.util.ResultGenerator;
import com.wl.bs.model.dto.AjaxPutPage;
import com.wl.bs.model.dto.AjaxResultPage;
import com.wl.bs.model.entity.blog.BlogInfo;
import com.wl.bs.model.entity.blog.BlogTagRelation;
import com.wl.bs.service.blog.IBlogInfoService;
import com.wl.bs.service.blog.IBlogTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @description: 博客
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/7 4:25
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    IBlogInfoService iBlogInfoService;
    @Autowired
    IBlogTagRelationService iBlogTagRelationService;

    /**
     * @description: 跳转博客编辑页面
     * @param blogId
     * @param model
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/11/8 19:58
     */
    @GetMapping("/v1/blog/edit")
    public String gotoBlogEdit(@RequestParam(required = false) Long blogId, Model model) {
        if (blogId != null) {
            BlogInfo blogInfo = iBlogInfoService.getById(blogId);
            QueryWrapper<BlogTagRelation> queryWrapper = new QueryWrapper<>();
            List<BlogTagRelation> blogTagRelationList = iBlogTagRelationService.list(
                    new QueryWrapper<BlogTagRelation>().lambda().eq(BlogTagRelation::getBlogId, blogId)
            );
            List<Long> tags = null;
            if (!CollectionUtils.isEmpty(blogTagRelationList)) {
                tags = blogTagRelationList.stream()
                        .map(blogTagRelation -> blogTagRelation.getRelationId())
                        .collect(Collectors.toList());
            }
            model.addAttribute("blogTags", tags);
            model.addAttribute("blogInfo", blogInfo);
        }
        return "adminLayui/blog-edit";
    }

    /**
     * @description: 跳转博客列表页面
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/11/8 19:59
     */
    @GetMapping("/v1/blog")
    public String gotoBlogList() {
        return "adminLayui/blog-list";
    }

    /**
     * @description: 文章分页列表
     * @param ajaxPutPage 分页参数
     * @param condition 筛选条件
     * @return: com.wl.bs.model.dto.AjaxResultPage<com.wl.bs.model.entity.blog.BlogInfo>
     * @author: wanlin
     * @createtime: 2019/11/8 19:57
     */
    @ResponseBody
    @GetMapping("v1/blog/list")
    public AjaxResultPage<BlogInfo> listBlogs(AjaxPutPage<BlogInfo> ajaxPutPage, BlogInfo condition) {
        AjaxResultPage<BlogInfo> result = new AjaxResultPage<>();
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>(condition);
        queryWrapper.lambda().orderByDesc(BlogInfo::getUpdateTime);
        Page<BlogInfo> page = ajaxPutPage.putPageToPage();
        iBlogInfoService.page(page, queryWrapper);
        result.setData(page.getRecords());
        result.setCount(page.getTotal());

        return result;
    }

    // 修改博客状态相关信息
    @ResponseBody
    @PostMapping("v1/blog/blogStatus")
    public Result updateBlogStatus(BlogInfo blogInfo) {
        blogInfo.setUpdateTime(new Timestamp(new Date().getTime()));
        boolean flag = iBlogInfoService.updateById(blogInfo);
        if (flag) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

}

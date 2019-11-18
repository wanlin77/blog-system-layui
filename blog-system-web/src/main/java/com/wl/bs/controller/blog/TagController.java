package com.wl.bs.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wl.bs.common.constants.BlogStatusConstants;
import com.wl.bs.common.constants.HttpStatusConstants;
import com.wl.bs.common.dto.Result;
import com.wl.bs.common.util.ResultGenerator;
import com.wl.bs.model.entity.blog.BlogTag;
import com.wl.bs.service.blog.IBlogInfoService;
import com.wl.bs.service.blog.IBlogTagRelationService;
import com.wl.bs.service.blog.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: 标签Controller
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/11/18 12:39
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private IBlogTagService iBlogTagService;

    @Autowired
    private IBlogInfoService iBlogInfoService;

    @Autowired
    private IBlogTagRelationService iBlogTagRelationService;

    @GetMapping("/v1/tags")
    public String gotoTag() {
        return "adminLayui/tag-list";
    }

    @ResponseBody
    @GetMapping("v1/tags/list")
    public Result<BlogTag> tagsList() {
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogTag::getIsDeleted, BlogStatusConstants.ZERO);
        List<BlogTag> blogTagList = iBlogTagService.list(queryWrapper);
        if (CollectionUtils.isEmpty(blogTagList)) {
            ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, blogTagList);
    }

}

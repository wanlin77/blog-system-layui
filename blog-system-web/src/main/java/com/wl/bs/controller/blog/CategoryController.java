package com.wl.bs.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wl.bs.common.constants.BlogStatusConstants;
import com.wl.bs.common.constants.HttpStatusConstants;
import com.wl.bs.common.dto.Result;
import com.wl.bs.common.util.ResultGenerator;
import com.wl.bs.model.entity.blog.BlogCategory;
import com.wl.bs.service.blog.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: 分类controller
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/11/7 19:45
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    IBlogCategoryService iBlogCategoryService;

    @ResponseBody
    @GetMapping("/v1/category/list")
    public Result<BlogCategory> listCategorys() {
        QueryWrapper<BlogCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogCategory::getIsDeleted, BlogStatusConstants.ZERO);
        List<BlogCategory> blogCategoryList = iBlogCategoryService.list(queryWrapper);
        if (CollectionUtils.isEmpty(blogCategoryList)) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, blogCategoryList);
    }

}

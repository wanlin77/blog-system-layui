package com.wl.bs.serviceimpl.blog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.mapper.blog.BlogCategoryMapper;
import com.wl.bs.model.entity.blog.BlogCategory;
import com.wl.bs.service.blog.IBlogCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客分类 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements IBlogCategoryService {

}

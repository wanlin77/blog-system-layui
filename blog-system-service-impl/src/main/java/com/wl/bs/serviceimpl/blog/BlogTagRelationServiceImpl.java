package com.wl.bs.serviceimpl.blog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.mapper.blog.BlogTagRelationMapper;
import com.wl.bs.model.entity.blog.BlogTagRelation;
import com.wl.bs.service.blog.IBlogTagRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客跟标签的关系表 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogTagRelationServiceImpl extends ServiceImpl<BlogTagRelationMapper, BlogTagRelation> implements IBlogTagRelationService {

}

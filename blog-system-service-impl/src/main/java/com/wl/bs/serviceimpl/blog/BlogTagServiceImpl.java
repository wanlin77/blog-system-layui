package com.wl.bs.serviceimpl.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.common.constants.BlogStatusConstants;
import com.wl.bs.mapper.blog.BlogTagMapper;
import com.wl.bs.mapper.blog.BlogTagRelationMapper;
import com.wl.bs.model.entity.blog.BlogTag;
import com.wl.bs.model.entity.blog.BlogTagCount;
import com.wl.bs.model.entity.blog.BlogTagRelation;
import com.wl.bs.service.blog.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements IBlogTagService {
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogTag::getIsDeleted, BlogStatusConstants.ZERO);
        List<BlogTag> blogTagList = blogTagMapper.selectList(queryWrapper);
        List<BlogTagCount> blogTagCountList = blogTagList.stream().map(blogTag -> new BlogTagCount()
                .setTagId(blogTag.getTagId())
                .setTagName(blogTag.getTagName())
                .setTagCount(
                        blogTagRelationMapper.selectCount(new QueryWrapper<BlogTagRelation>()
                                .lambda().eq(BlogTagRelation::getTagId, blogTag.getTagId()))
                )).collect(Collectors.toList());
        return blogTagCountList;
    }
}

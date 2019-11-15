package com.wl.bs.serviceimpl.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.mapper.blog.BlogInfoMapper;
import com.wl.bs.model.entity.blog.BlogInfo;
import com.wl.bs.model.vo.SimpleBlogListVO;
import com.wl.bs.service.blog.IBlogInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 博客信息表 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements IBlogInfoService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<SimpleBlogListVO> listSimpleBlogListVOsForLatestOf(int number) {
        List<SimpleBlogListVO> simpleBlogListVOList = new ArrayList<>();
        Page<BlogInfo> page = new Page<>(1, number);
        blogInfoMapper.selectPage(page, null);
        for (BlogInfo blogInfo : page.getRecords()) {
            SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
            BeanUtils.copyProperties(blogInfo, simpleBlogListVO);
            simpleBlogListVOList.add(simpleBlogListVO);
        }
        return simpleBlogListVOList;
    }

    @Override
    public List<SimpleBlogListVO> listSimpleBlogListVOsForMostHitsOf(int number) {
        List<SimpleBlogListVO> simpleBlogListVOList = new ArrayList<>();
        Page<BlogInfo> page = new Page<>(1, number);
        blogInfoMapper.selectPage(page, new QueryWrapper<BlogInfo>()
                .lambda().orderByDesc(BlogInfo::getBlogViews));
        for (BlogInfo blogInfo : page.getRecords()) {
            SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
            BeanUtils.copyProperties(blogInfo, simpleBlogListVO);
            simpleBlogListVOList.add(simpleBlogListVO);
        }
        return simpleBlogListVOList;
    }
}

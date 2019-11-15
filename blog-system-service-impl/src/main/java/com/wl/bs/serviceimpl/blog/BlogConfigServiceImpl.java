package com.wl.bs.serviceimpl.blog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.mapper.blog.BlogConfigMapper;
import com.wl.bs.model.entity.blog.BlogConfig;
import com.wl.bs.service.blog.IBlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogConfigServiceImpl extends ServiceImpl<BlogConfigMapper, BlogConfig> implements IBlogConfigService {
    @Autowired
    private BlogConfigMapper blogConfigMapper;

    @Override
    public Map<String, String> getAllConfigs() {
        List<BlogConfig> list = blogConfigMapper.selectList(null);
        return list.stream().collect(Collectors.toMap(BlogConfig::getConfigField, BlogConfig::getConfigValue));
    }
}
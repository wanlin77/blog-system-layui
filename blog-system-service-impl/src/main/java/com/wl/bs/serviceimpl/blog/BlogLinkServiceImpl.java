package com.wl.bs.serviceimpl.blog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.mapper.blog.BlogLinkMapper;
import com.wl.bs.model.entity.blog.BlogLink;
import com.wl.bs.service.blog.IBlogLinkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 友情链接表 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogLinkServiceImpl extends ServiceImpl<BlogLinkMapper, BlogLink> implements IBlogLinkService {

}

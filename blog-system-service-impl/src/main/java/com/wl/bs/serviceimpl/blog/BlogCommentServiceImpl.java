package com.wl.bs.serviceimpl.blog;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.bs.mapper.blog.BlogCommentMapper;
import com.wl.bs.model.entity.blog.BlogComment;
import com.wl.bs.service.blog.IBlogCommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

}

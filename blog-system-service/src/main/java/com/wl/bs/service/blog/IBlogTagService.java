package com.wl.bs.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.bs.model.entity.blog.BlogTag;
import com.wl.bs.model.entity.blog.BlogTagCount;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
public interface IBlogTagService extends IService<BlogTag> {

    List<BlogTagCount> getBlogTagCountForIndex();

}

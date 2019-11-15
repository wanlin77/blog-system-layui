package com.wl.bs.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.bs.model.entity.blog.BlogConfig;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
public interface IBlogConfigService extends IService<BlogConfig> {

    Map<String, String> getAllConfigs();

}

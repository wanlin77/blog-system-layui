package com.wl.bs.service.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.bs.model.entity.blog.BlogInfo;
import com.wl.bs.model.vo.SimpleBlogListVO;

import java.util.List;

/**
 * <p>
 * 博客信息表 服务类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
public interface IBlogInfoService extends IService<BlogInfo> {

    /**
     * @description: 返回最新的文章列表
     * @param number 返回数量
     * @return: java.util.List<com.wl.bs.model.vo.SimpleBlogListVO>
     * @author: wanlin
     * @createtime: 2019/10/20 21:03
     */
    List<SimpleBlogListVO> listSimpleBlogListVOsForLatestOf(int number);

    /**
     * @description: 返回点击量最多的文章列表
     * @param number 返回数量
     * @return: java.util.List<com.wl.bs.model.vo.SimpleBlogListVO>
     * @author: wanlin
     * @createtime: 2019/10/20 21:06
     */
    List<SimpleBlogListVO> listSimpleBlogListVOsForMostHitsOf(int number);

}

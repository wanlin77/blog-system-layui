package com.wl.bs.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.bs.model.entity.blog.BlogConfig;
import com.wl.bs.model.entity.user.AdminUser;

/**
 * <p>
 * 后台管理员信息表 服务类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
public interface IAdminUserService extends IService<AdminUser> {

    /*
     * @description: 校验密码
     * @param userId
     * @param oldPassword
     * @return: boolean
     * @author: wanlin
     * @createtime: 2019/10/6 23:17
     */
    boolean validatePassword(Integer userId, String oldPassword);

    /**
     * @description: 修改用户信息
     * @param adminUser
     * @param blogConfig
     * @return: boolean
     * @author: wanlin
     * @createtime: 2019/10/7 3:54
     */
    boolean updateUserInfo(AdminUser adminUser, BlogConfig blogConfig);

}

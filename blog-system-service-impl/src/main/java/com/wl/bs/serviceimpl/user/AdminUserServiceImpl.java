package com.wl.bs.serviceimpl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wl.bs.mapper.blog.BlogConfigMapper;
import com.wl.bs.mapper.user.AdminUserMapper;
import com.wl.bs.model.entity.blog.BlogConfig;
import com.wl.bs.model.entity.user.AdminUser;
import com.wl.bs.service.user.IAdminUserService;
import com.wl.bs.util.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 后台管理员信息表 服务实现类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;
    @Autowired
    BlogConfigMapper blogConfigMapper;

    @Override
    public boolean validatePassword(Integer userId, String oldPassword) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>(
                new AdminUser().setAdminUserId(userId)
                        .setLoginPassword(BlogUtils.stringMD5(oldPassword))
        );
        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);
        return adminUser!=null;
    }

    @Transactional
    @Override
    public boolean updateUserInfo(AdminUser adminUser, BlogConfig blogConfig) {
        if (SqlHelper.retBool(adminUserMapper.updateById(adminUser))
                && SqlHelper.retBool(blogConfigMapper.updateById(blogConfig))) {
            return true;
        }
        return false;
    }
}

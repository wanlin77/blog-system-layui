package com.wl.bs.service;

import com.wl.bs.BlogSystemWebApplication;
import com.wl.bs.mapper.user.AdminUserMapper;
import com.wl.bs.model.entity.user.AdminUser;
import com.wl.bs.util.BlogUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @description: AdminUserServiceImpl测试类
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/9/28 18:57
 */
@SpringBootTest(classes = BlogSystemWebApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@Transactional
public class AdminUserServiceImplTest {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Test
    public void testSelect() {
        System.out.println("-------selectAll method test------");
        List<AdminUser> userList = adminUserMapper.selectList(null);
        Assert.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        AdminUser adminUser = new AdminUser();
        adminUser.setLoginUserName("admin2");
        adminUser.setLoginPassword(BlogUtils.stringMD5("admin"));
        adminUser.setNickName("张可乐");
        adminUser.setLocked(0);
        adminUserMapper.insert(adminUser);
        List<AdminUser> userList = adminUserMapper.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }

}

package com.wl.bs.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wl.bs.common.constants.BlogStatusConstants;
import com.wl.bs.common.constants.HttpStatusConstants;
import com.wl.bs.common.constants.SessionConstants;
import com.wl.bs.common.constants.SysConfigConstants;
import com.wl.bs.common.dto.Result;
import com.wl.bs.common.util.ResultGenerator;
import com.wl.bs.model.entity.blog.*;
import com.wl.bs.model.entity.user.AdminUser;
import com.wl.bs.service.blog.*;
import com.wl.bs.service.user.IAdminUserService;
import com.wl.bs.util.BlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * @description: 用户登录
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/9/28 21:43
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminUserService iAdminUserService;

    @Autowired
    private IBlogConfigService iBlogConfigService;

    @Autowired
    private IBlogCategoryService iBlogCategoryService;

    @Autowired
    private IBlogCommentService iBlogCommentService;

    @Autowired
    private IBlogInfoService iBlogInfoService;

    @Autowired
    private IBlogLinkService iBlogLinkService;

    @Autowired
    private IBlogTagRelationService iBlogTagRelationService;

    @Autowired
    private IBlogTagService iBlogTagService;

    /**
     * @description: 跳转登录页面
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/9/30 22:35
     */
    @GetMapping(value = "/v1/login")
    public String login() {
        return "adminLayui/login";
    }

    /**
     * @description: 登录验证
     * @param request
     * @return: com.wl.bs.common.dto.Result
     * @author: wanlin
     * @createtime: 2019/9/30 22:37
     */
    @PostMapping(value = "/v1/login")
    @ResponseBody
    public Result login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST);
        }
        AdminUser queryAdminUser = new AdminUser();
        queryAdminUser.setLoginUserName(username);
        queryAdminUser.setLoginPassword(BlogUtils.stringMD5(password));
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>(queryAdminUser);
        AdminUser adminUser = iAdminUserService.getOne(adminUserQueryWrapper);
        if (adminUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LOGIN_USER, adminUser.getNickName());
            session.setAttribute(SessionConstants.LOGIN_USER_ID, adminUser.getAdminUserId());
            session.setAttribute(SessionConstants.LOGIN_USER_NAME, adminUser.getLoginUserName());
            session.setAttribute(SessionConstants.AUTHOR_IMG, iBlogConfigService.getById(
                    SysConfigConstants.SYS_AUTHOR_IMG.getConfigField()));
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,"/admin/v1/index");
        } else {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.UNAUTHORIZED);
        }
    }

    /**
     * @description: 返回首页相关数据
     * @param session
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/9/29 20:43
     */
    @GetMapping("/v1/index")
    public String index(HttpSession session) {
        session.setAttribute("categoryCount", iBlogCategoryService.count(
                new QueryWrapper<BlogCategory>().lambda().eq(BlogCategory::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("blogCount", iBlogInfoService.count(
                new QueryWrapper<BlogInfo>().lambda().eq(BlogInfo::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("linkCount", iBlogLinkService.count(
                new QueryWrapper<BlogLink>().lambda().eq(BlogLink::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("tagCount", iBlogTagService.count(
                new QueryWrapper<BlogTag>().lambda().eq(BlogTag::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("commentCount", iBlogCommentService.count(
                new QueryWrapper<BlogComment>().lambda().eq(BlogComment::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("sysList",iBlogConfigService.list());
        return "adminLayui/index";
    }

    @GetMapping("v1/welcome")
    public String welcome() {
        return "adminLayui/welcome";
    }

    @GetMapping("/v1/userInfo")
    public String goToUserInfo() {
        return "adminLayui/userInfo-edit";
    }

    /**
     * @description: 校验密码是否正确
     * @param oldPwd
     * @param request
     * @return: com.wl.bs.common.dto.Result
     * @author: wanlin
     * @createtime: 2019/10/6 23:18
     */
    @GetMapping("/v1/password")
    @ResponseBody
    public Result validatePassword(String oldPwd, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(SessionConstants.LOGIN_USER_ID);
        boolean flag = iAdminUserService.validatePassword(userId, oldPwd);
        if (flag) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST);
    }

    /*
     * @description: 修改用户信息，成功后跳转登录页面
     * @param request
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/10/7 0:58
     */
    @PostMapping("/v1/userInfo")
    @ResponseBody
    public Result userInfoUpdate(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String newPwd = request.getParameter("newPwd");
        String nickName = request.getParameter("nickName");
        String sysAuthorImg = request.getParameter("sysAuthorImg");
        HttpSession session = request.getSession();
        Integer loginUserId = (Integer) session.getAttribute(SessionConstants.LOGIN_USER_ID);
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(newPwd)) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.BAD_REQUEST);
        }
        BlogConfig blogConfig = new BlogConfig()
                .setConfigField(SysConfigConstants.SYS_AUTHOR_IMG.getConfigField())
                .setConfigValue(sysAuthorImg);
        AdminUser adminUser = new AdminUser()
                .setAdminUserId(loginUserId)
                .setLoginUserName(userName)
                .setLoginPassword(BlogUtils.stringMD5(newPwd))
                .setNickName(nickName);
        if (iAdminUserService.updateUserInfo(adminUser, blogConfig)) {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, "/admin/v1/logout");
        } else {
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * @description: 注销登录
     * @param session
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/10/7 4:16
     */
    @GetMapping("/v1/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "adminLayui/login";
    }



}

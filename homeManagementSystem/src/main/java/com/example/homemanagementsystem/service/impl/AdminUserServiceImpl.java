package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.AdminUserMapper;
import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.AdminUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    // 登录
    @Override
    public AdminUser login(AdminUser adminUser) {
        return adminUserMapper.getUserByUsernameAndPassword(adminUser);
    }

    // 注册
    @Override
    public void register(AdminUser adminUser) {
        adminUserMapper.insertadminUser(adminUser);
    }

    // 通过id查询信息
    @Override
    public AdminUser getUserInfo(Integer id) {
        return adminUserMapper.getUserById(id);
    }

    // 修改用户信息
    @Override
    public void editUserInfo(AdminUser adminUser) {
        adminUserMapper.updateUser(adminUser);
    }

    // 修改密码
    @Override
    public Result editPassword(Integer id, String password) {
        AdminUser adminUser = adminUserMapper.getUserById(id);
        // 如果原密码与新密码相同
        if (password.equals(adminUser.getPassword())) {
            return Result.error("原密码与新密码相同");
        }
        adminUserMapper.updatePassword(id, password);
        return Result.success();
    }

    // 删除
    @Override
    public void deleteAdminUser(String username) {
        adminUserMapper.deleteByName(username);
    }

    // 批量删除
    @Override
    public void deleteAllAdminUser(List<Integer> ids) {
        adminUserMapper.deleteAllAdminUser(ids);
    }

    @Override
    public PageBean getAdminUser(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<AdminUser> adminUsers = adminUserMapper.getAllUser();
        Page<AdminUser> p = (Page<AdminUser>) adminUsers;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}

package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.*;
import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.AdminUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private ConsumerUserMapper consumerUserMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private KindsMapper kindsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public AdminUser login(AdminUser adminUser) {
        return adminUserMapper.getUserByUsernameAndPassword(adminUser);
    }

    @Override
    public AdminUser getThisAdminInfo(Integer id) {
        return adminUserMapper.getAdminById(id);
    }

    @Override
    public PageBean getAdminInfoByConditionQuery(Integer page, Integer pageSize, AdminUser adminUser) {
        PageHelper.startPage(page, pageSize);

        List<AdminUser> adminUsers = adminUserMapper.getAdminInfoByConditionQuery(adminUser);
        Page<AdminUser> p = (Page<AdminUser>) adminUsers;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public PageBean getAllAdminUser(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<AdminUser> adminUsers = adminUserMapper.selectAllAdminUser();
        Page<AdminUser> p = (Page<AdminUser>) adminUsers;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Goods getGoodsInfo(Integer id) {
        return goodsMapper.getById(id);
    }

    @Override
    public Result addAdminUser(AdminUser adminUser) {
        // 通过用户名查询查询
        AdminUser au = adminUserMapper.selectUserByUsername(adminUser.getUsername());

        if (au != null) {
            return Result.error("用户名已存在");
        }

        adminUserMapper.insertAdminUser(adminUser);
        return Result.success("添加成功");
    }

    @Override
    public Result editAdminUserInfo(AdminUser adminUser) {
        // 通过用户名查询查询
        AdminUser au = adminUserMapper.selectUserByUsername(adminUser.getUsername());

        if (au != null) {
            return Result.error("用户名已存在");
        }

        adminUserMapper.updateAdmin(adminUser);
        return Result.success("修改成功");
    }

    @Override
    public void uploadImage(Integer id, String url) {
        goodsMapper.updateImage(id, url);
    }

    @Override
    public void deleteAllAdminUser(List<Integer> ids) {
        adminUserMapper.deleteAllAdminUser(ids);
    }
}

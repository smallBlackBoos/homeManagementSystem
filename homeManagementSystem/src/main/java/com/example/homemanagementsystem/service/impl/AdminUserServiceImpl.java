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
    public AdminUser getAdminInfo(Integer id) {
        return adminUserMapper.getAdminInfo(id);
    }

    @Override
    public void editAdminUserInfo(AdminUser adminUser) {
        adminUserMapper.updateAdmin(adminUser);
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
    public void uploadImage(Integer id, String url) {
        goodsMapper.updateImage(id, url);
    }

    @Override
    public void deleteAllAdminUser(List<Integer> ids) {
        adminUserMapper.deleteAllAdminUser(ids);
    }
}

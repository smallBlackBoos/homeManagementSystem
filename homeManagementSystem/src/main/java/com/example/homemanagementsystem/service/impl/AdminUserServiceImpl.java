package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.AdminUserMapping;
import com.example.homemanagementsystem.mapper.ConsumerUserMapper;
import com.example.homemanagementsystem.mapper.GoodsMapper;
import com.example.homemanagementsystem.mapper.KindsMapper;
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
    private AdminUserMapping adminUserMapping;

    @Autowired
    private ConsumerUserMapper consumerUserMapper;

    @Autowired
    private KindsMapper kindsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public AdminUser login(AdminUser adminUser) {
        return adminUserMapping.getUserByUsernameAndPassword(adminUser);
    }

    @Override
    public PageBean getAllKinds(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Kinds> kindsList = kindsMapper.selectAll();
        Page<Kinds> p = (Page<Kinds>) kindsList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public PageBean getAllGoods(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Goods> goods = goodsMapper.getAllGoods();
        Page<Goods> p = (Page<Goods>) goods;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Goods getGoodsInfo(Integer id) {
        return goodsMapper.getById(id);
    }
}

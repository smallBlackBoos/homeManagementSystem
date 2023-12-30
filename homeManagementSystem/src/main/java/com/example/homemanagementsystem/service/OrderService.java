package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface OrderService {

    /**
     * 删除订单
     * @param id 订单id
     */
    void removeOrder(Integer id);

    /**
     * 分页查询所有订单信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return PageBean
     */
    PageBean getAllOrder(Integer page, Integer pageSize);
}

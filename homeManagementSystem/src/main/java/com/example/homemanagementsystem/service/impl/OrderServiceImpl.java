package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.OrderMapper;
import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageBean getAllOrders(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Order> orders = orderMapper.selectAll();
        Page<Order> p = (Page<Order>) orders;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public PageBean getOrdersInfoByConditionQuery(Integer page, Integer pageSize, Order order) {
        PageHelper.startPage(page, pageSize);

        List<Order> orders = orderMapper.getOrdersInfoByConditionQuery(order);
        Page<Order> p = (Page<Order>) orders;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void changeOrderIdAndState(Integer workerId, Integer orderId) {
        orderMapper.updateWorkerIdAndState(workerId, orderId, 2);
    }

    @Override
    public void changeState(Integer orderId) {
        orderMapper.updateStatus(orderId, 3);
    }

    @Override
    public void removeOrder(Integer id) {
        orderMapper.deleteOrderById(id);
    }
}

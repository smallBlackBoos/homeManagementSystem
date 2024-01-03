package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface OrderService {

    /**
     * 分页查询所有订单信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return PageBean
     */
    PageBean getAllOrders(Integer page, Integer pageSize);

    /**
     * 条件查询订单信息
     * @param page 页码
     * @param pageSize 每页大小
     * @param order 订单对象
     * @return PageBean
     */
    PageBean getOrdersInfoByConditionQuery(Integer page, Integer pageSize, Order order);

    /**
     * 修改订单的家政人员id和状态
     * @param workerId 家政人员id
     * @param orderId 订单id
     */
    void changeOrderIdAndState(Integer workerId, Integer orderId);

    /**
     * 修改订单状态
     * @param orderId 订单id
     */
    void changeState(Integer orderId);

    /**
     * 删除订单
     * @param id 订单id
     */
    void removeOrder(Integer id);
}

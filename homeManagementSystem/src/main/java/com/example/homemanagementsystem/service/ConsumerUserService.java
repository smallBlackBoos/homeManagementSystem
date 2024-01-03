package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.*;

import java.util.List;

public interface ConsumerUserService {
    /**
     * 登录
     * @param consumerUser 用户对象
     * @return 用户对象
     */
    ConsumerUser login(ConsumerUser consumerUser);

    /**
     * 注册
     * @param consumerUser 用户对象
     * @return Result
     */
    Result register(ConsumerUser consumerUser);

    /**
     * 查询用户信息
     * @param id 用户id
     * @return ConsumerUser
     */
    ConsumerUser getUserInfo(Integer id);

    /**
     * 分页查询所有消费者用户
     * @param page 页码
     * @param pageSize 每页大小
     * @return PageBean
     */
    PageBean getAllConsumerUser(Integer page, Integer pageSize);

    /**
     * 条件查询获取消费者用户信息
     * @param page 页码
     * @param pageSize 每页大小
     * @param consumerUser 消费者对象
     * @return Result
     */
    PageBean getConsumerInfoByConditionQuery(Integer page, Integer pageSize, ConsumerUser consumerUser);


    /**
     * 分页查询用户订单
     * @param userId 用户id
     * @param page 页数
     * @param pageSize 每页大小
     * @return PageBean
     */
    PageBean getConsumerUserOrder(Integer userId, int page, Integer pageSize);

    /**
     * 修改个人信息
     * @param consumerUser 用户对象
     * @return ConsumerUser
     */
    ConsumerUser editUserInfo(ConsumerUser consumerUser);

    /**
     * 修改密码
     * @param id 用户id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 结果
     */
    Result editPassword(Integer id, String oldPassword, String newPassword);

    /**
     * 上传图片
     *
     * @param id
     * @param url url路径
     */
    ConsumerUser uploadImage(Integer id, String url);

    /**
     * 充值
     * @param id 用户id
     * @param password 密码
     * @param topUpMoney 充值金额
     * @return Result
     */
    Result topUp(Integer id, String password, String topUpMoney);

    /**
     * 支付
     * @param id 用户id
     * @param password 密码
     * @param orderId 订单id
     * @param payment 支付金额
     * @return Result
     */
    Result pay(Integer id, String password, Integer orderId, Double payment);

    /**
     * 订购商品
     * @param id 用户id
     * @param goodsId 商品id
     */
    void buy(Integer id, Integer goodsId);

    /**
     * 获取所有空闲家政人员的id
     * @return Integer
     */
    // Integer getWorkerByStatus();

    /**
     * 给订单分配空闲的家政人员
     * @param orderId 订单Id
     */
//    void atLeisureWorker(Integer orderId);
}

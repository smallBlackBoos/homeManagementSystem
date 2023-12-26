package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 查询当前用户的订单
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> getAllUserOrder(Integer userId);

    /**
     * 查询所有订单
     * @return List<Order>
     */
    List<Order> selectAll();

    /**
     * 通过订单id删除订单
     * @param id 订单id
     */
    @Delete("delete from `order` where id = #{id}")
    void deleteOrderById(Integer id);

    /**
     * 通过id修改订单状态
     * @param orderId 订单id
     * @param status 状态（0 未完成，1 已完成）
     */
    @Update("update `order` set status = #{status} where id = #{orderId}")
    void updateStatus(Integer orderId, Integer status);

    /**
     * 插入一个订单
     * @param order 订单
     */
    @Insert("insert into `order` (user_id, goods_id, status, create_time) values (#{userId}, #{goodsId}, " +
            "#{status}, #{createTime})")
    void insert(Order order);

    /**
     * 通过订单Id修改订单的家政人员id
     * @param orderId 订单id
     * @param workerId 家政人员id
     */
    @Update("update `order` set worker_id = #{workerId} where id = #{orderId}")
    void updateWorkerId(Integer orderId, Integer workerId);
}

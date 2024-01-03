package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Order;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.OrderService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 删除订单
     * @param id 订单id
     * @return 结果
     */
    @DeleteMapping("/removeOrder")
    public Result removeOrder(Integer id) {
        orderService.removeOrder(id);
        return Result.success();
    }
}

package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.ConsumerUserMapper;
import com.example.homemanagementsystem.mapper.KindsMapper;
import com.example.homemanagementsystem.mapper.OrderMapper;
import com.example.homemanagementsystem.mapper.WorkerMapper;
import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.ConsumerUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumerUserServiceImpl implements ConsumerUserService {

    @Autowired
    private ConsumerUserMapper consumerUserMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KindsMapper kindsMapper;

    @Override
    public ConsumerUser login(ConsumerUser consumerUser) {
        return consumerUserMapper.getConsumerUserNameAndPassWord(consumerUser);
    }

    @Override
    public PageBean getAllConsumerUser(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<ConsumerUser> consumerUsers = consumerUserMapper.getAllUser();
        Page<ConsumerUser> p = (Page<ConsumerUser>) consumerUsers;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public PageBean getConsumerUserOrder(Integer userId, int page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Order> orders = orderMapper.getAllUserOrder(userId);
        Page<Order> p = (Page<Order>) orders;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public Result register(ConsumerUser consumerUser) {
        String username = consumerUser.getUsername().trim();
        String password = consumerUser.getPassword().trim();

        consumerUser.setImage("http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg");

        if (username.equals("") || password.equals("")) {
            return Result.error("用户名密码不能为空");
        }

        // 通过用户名查找用户信息
        List<ConsumerUser> users = consumerUserMapper.getUserByName(username);

        if ((users != null) && (users.size() > 0)) {
            return Result.error("用户名已存在");
        }

        consumerUserMapper.insert(consumerUser);
        return Result.success("注册成功，请登录！");
    }

    @Override
    public ConsumerUser getUserInfo(Integer id) {
        return consumerUserMapper.getUserById(id);
    }

    @Override
    public ConsumerUser editUserInfo(ConsumerUser consumerUser) {
        // 修改个人信息
        consumerUserMapper.updateUser(consumerUser);
        // 查询个人信息
        return consumerUserMapper.getUserById(consumerUser.getId());
    }

    @Override
    public Result editPassword(Integer id, String oldPassword, String newPassword) {
        ConsumerUser consumerUser = consumerUserMapper.getUserById(id);

        // 判断密码是否正确
        if (!oldPassword.equals(consumerUser.getPassword())) {
            return Result.error("原密码输入错误");
        }

        // 如果原密码与新密码相同
        if (oldPassword.equals(newPassword)) {
            return Result.error("新密码与原密码一致");
        }

        consumerUserMapper.updatePassword(id, newPassword);

        return Result.success();
    }

    @Override
    public ConsumerUser uploadImage(Integer id, String url) {
        consumerUserMapper.updateImage(id, url);
        return consumerUserMapper.getUserById(id);
    }

    @Override
    public Result topUp(Integer id, String password, String topUpMoney) {
        // 获取用户信息
        ConsumerUser consumerUser = consumerUserMapper.getUserById(id);

        // 若密码不正确
        if (!password.equals(consumerUser.getPassword())) {
            return Result.error("密码错误，请重新输入");
        }

        // String --> Double
        Double tum = Double.valueOf(topUpMoney);

        BigDecimal bg = new BigDecimal(tum);
        // 直接舍去多余的小数位
        double money = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        // 更新账户余额
        consumerUserMapper.updateMoney(id, money);
        return Result.success();
    }

    @Override
    @Transactional
    public Result pay(Integer id, String password, Integer orderId, Double payment) {

        BigDecimal bg = new BigDecimal(payment);
        // 直接舍去多余的小数位
        double money = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        // 获取用户信息
        ConsumerUser consumerUser = consumerUserMapper.getUserById(id);
        double userMoney = consumerUser.getMoney(); // 用户余额

        // 若密码不正确
        if (!password.equals(consumerUser.getPassword())) {
            return Result.error("密码错误，请重新输入");
        } else if (userMoney < money) { // (余额 < 商品价格) --> 余额不足
            return Result.error("余额不足，请充值");
        }

        // 更新消费者账户余额
        consumerUserMapper.updateMoney(id, -money);
        // 更新家政人员账户余额
        // workerMapper.updateMoney(workerId, money);
        // 更新订单状态为（1 已支付，等待服务人员上门）
        orderMapper.updateStatus(orderId, 1);

        return Result.success();
    }

    @Override
    public void buy(Integer id, Integer goodsId) {
        Order order = new Order();
        order.setUserId(id);
        order.setGoodsId(goodsId);
        order.setStatus((short) 0); // 默认订单待支付
        order.setCreateTime(LocalDateTime.now());

        // 添加订单表
         orderMapper.insert(order);
    }

    @Transactional
    @Override
    public void atLeisureWorker(Integer orderId) {
        // 获取所有空闲的家政人员的id
        List<Integer> workerIdList = workerMapper.getWorkerByStatus();

        // 从空闲的家政人员id列表中随机取得一个id值
        int index = (int) (Math.random() * workerIdList.size());
        Integer workerId = workerIdList.get(index);

        // 更新订单的家政人员id  -->  给订单分配一个家政人员
        orderMapper.updateWorkerId(orderId, workerId);

        // 修改该家政人员的状态为1，工作中
        workerMapper.updateStatus(workerId, 1);
    }

    /*
    @Override
    public Integer getWorkerByStatus() {
        // 获取所有空闲的家政人员id列表
        List<Integer> workerIdList = workerMapper.getWorkerByStatus();
        // 从列表中随机获取一个id
        Integer workerId = (int) (Math.random() * workerIdList.size());

        return workerId;
    }
     */

//
//    @Override
//    public List<Worker> browseLeisureWorker() {
//        return consumerUserMapper.getWorkerByStatus();
//    }
}

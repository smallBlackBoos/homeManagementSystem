package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.ConsumerUser;
import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.ConsumerUserService;
import com.example.homemanagementsystem.utils.JwtUtils;
import com.example.homemanagementsystem.utils.QiniuKodoUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/consumerUser")
public class ConsumerUserController {

    @Autowired
    private ConsumerUserService consumerUserService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

    /**
     * 订购服务
     * @param token 令牌
     * @param goodsId 商品id
     * @return Result
     */
    @PostMapping("/consumerBuy")
    public Result consumerBuy(@RequestHeader String token, Integer goodsId) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        // 添加订单
        consumerUserService.buy(id, goodsId);

        return Result.success();
    }

    /**
     * 获取当前用户的个人信息
     * @param token token令牌
     * @return 结果
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {

        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        ConsumerUser consumerUser = consumerUserService.getUserInfo(id);

        return Result.success(consumerUser);
    }

    /**
     * 分页展示用户的所有订单信息
     * @param page 页数
     * @param pageSize 每页展示的条数
     * @param token 令牌
     * @return Result
     */
    @GetMapping("/getConsumerUserOrder")
    public Result getConsumerUserOrder(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "2") Integer pageSize,
                                       @RequestHeader String token) {

        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        PageBean pageBean = consumerUserService.getConsumerUserOrder(id, page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 修改个人信息
     * @param token token令牌
     * @param consumerUser 用户对象
     * @return Result
     */
    @PutMapping("/editUserInfo")
    public Result editUserInfo(@RequestHeader String token, @RequestBody ConsumerUser consumerUser) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        consumerUser.setId(id);

        ConsumerUser user = consumerUserService.editUserInfo(consumerUser);

        return Result.success(user);
    }

    /**
     * 修改密码
     * @param token 令牌
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return Result
     */
    @PutMapping("/editPassword")
    public Result editPassword(@RequestHeader String token, String oldPassword, String newPassword) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        return consumerUserService.editPassword(id, oldPassword, newPassword);
    }

    /**
     * 充值
     * @param token 令牌
     * @param password 密码
     * @param topUpMoney 充值的金额
     * @return Result
     */
    @PutMapping("/topUp")
    public Result topUp(@RequestHeader String token, @RequestParam String password, @RequestParam String topUpMoney) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        // 充值
        consumerUserService.topUp(id, password, topUpMoney);
        // 获取新的用户信息
        ConsumerUser consumerUser = consumerUserService.getUserInfo(id);

        return Result.success(consumerUser);
    }

    /**
     * 支付
     * @param token 令牌
     * @param password 密码
     * @param orderId 订单id
     * @param payment 支付金额
     * @return
     */
    @PostMapping("/pay")
    public Result pay(@RequestHeader String token, String password, Integer orderId, String payment) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        if (password.equals("")) {
            return Result.error("密码不能为空");
        }

        // String --> Double
        Double money = Double.valueOf(payment);
        if (money < 0) {
            return Result.error("支付金额不能为0");
        }

        // 付钱
        Result result = consumerUserService.pay(id, password, orderId, money);
        // 重新获取用户订单信息
        PageBean pageBean = consumerUserService.getConsumerUserOrder(id, 1, 2);
        return Result.success(pageBean);
    }

    /**
     * 上传头像
     * @param token 令牌
     * @param image 图片
     * @return 结果
     * @throws IOException
     */
    @Transactional
    @PostMapping("/upload")
    public Result upload(@RequestHeader String token, MultipartFile image) throws IOException {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值
        // 获得用户信息
        ConsumerUser consumerUser = consumerUserService.getUserInfo(id);
        String oldUrl = consumerUser.getImage();

        // 上传文件
        String url = qiniuKodoUtils.upload(image);

        // 修改用户的image信息
        ConsumerUser user = consumerUserService.uploadImage(id, url);

        // 判断oldUrl是否为null或空
        if (!oldUrl.equals("http://s5ylvlikx.hd-bkt.clouddn.com/1.jpg")){
            // 删除七牛云中原本的文件
            qiniuKodoUtils.delete(oldUrl);
        }

        return Result.success(user);
    }
}

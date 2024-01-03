package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.OrderService;
import com.example.homemanagementsystem.service.WorkerService;
import com.example.homemanagementsystem.utils.JwtUtils;
import com.example.homemanagementsystem.utils.QiniuKodoUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

    /**
     * 获取当前家政人员个人信息
     * @param token 令牌
     * @return Result
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值

        Worker worker = workerService.getUserInfo(id);
        return Result.success(worker);
    }

    /**
     * 获取当前家政人员余额
     * @param token 令牌
     * @return Result
     */
    @GetMapping("/getUserMoney")
    public Result getUserMoney(@RequestHeader String token) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值

        // 获取家政人员余额
        Double money = workerService.getUserMoney(id);

        return Result.success(money);
    }

    /**
     * 分页查询待接单的订单
     * @param page 页码
     * @param pageSize 每页大小
     * @return Result
     */
    @GetMapping("/getWaitOrders")
    public Result getWaitOrders(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        Order order = new Order();
        order.setStatus((short) 1);

        PageBean pageBean = orderService.getOrdersInfoByConditionQuery(page, pageSize, order);

        return Result.success(pageBean);
    }

    /**
     * 分页查询当前家政人员的订单
     * @param token 令牌
     * @param page 页码
     * @param pageSize 每页大小
     * @return Result
     */
    @GetMapping("/getWorkerOrders")
    public Result getWorkerOrders(@RequestHeader String token,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值

        Order order = new Order();
        order.setWorkerId(id);

        PageBean pageBean = orderService.getOrdersInfoByConditionQuery(page, pageSize, order);

        return Result.success(pageBean);
    }

    /**
     * 修改当前家政人员个人信息
     * @param token 令牌
     * @return Result
     */
    @PutMapping("/editWorkerInfo")
    public Result editWorkerInfo(@RequestHeader String token, @RequestBody Worker worker) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值

        // 修改用户信息
        Result result = workerService.editWorkerInfo(worker);

        // 修改失败
        if (result.getCode() == 0) {
            return result;
        } else {
            // 获取修改后的用户信息
            Worker user = workerService.getUserInfo(id);
            return Result.success(user);
        }
    }

    /**
     * 提现
     * @param token 令牌
     * @param money 提现金额
     * @return Result
     */
    @PutMapping("/withdrawCash")
    public Result withdrawCash(@RequestHeader String token, String password, String money) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到用户id值

        return workerService.withdrawCash(id, password, money);
    }

    /**
     * 接单
     * @return Result
     */
    @PutMapping("/takeOrder")
    public Result takeOrder(@RequestHeader String token, Integer orderId) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到家政人员id值

        // 通过id查询订单
        orderService.changeOrderIdAndState(id, orderId);

        return Result.success();
    }

    /**
     * 完成订单
     * @param token 令牌
     * @return Result
     */
    @PutMapping("/accomplishOrder")
    public Result accomplishOrder(@RequestHeader String token, Integer orderId, String price) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到家政人员id值

        // 修改订单状态为3（已完成）
        orderService.changeState(orderId);

        // 修改家政人员余额
        workerService.addMoney(id, price);

        return Result.success();
    }

    /**
     * 修改家政人员密码
     * @param token 令牌
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return Result
     */
    @PutMapping("/editWorkerPassword")
    public Result editWorkerPassword(@RequestHeader String token, String oldPassword, String newPassword) {
        // 解析 token。
        Claims claims = JwtUtils.parseJWT(token);  // jwt 包含当前包含的员工信息
        Integer id = (Integer) claims.get("id");    // 得到家政人员id值

        // 修改家政人员密码
        return workerService.editWorkerPassword(id, oldPassword, newPassword);
    }

    /**
     * 上传头像
     * @param image 图片
     * @return 结果
     */
    @Transactional
    @PostMapping("/upload")
    public Result upload(MultipartFile image, HttpServletRequest request) throws IOException {

        // 上传文件
        String url = qiniuKodoUtils.upload(image);

        // 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        Integer id = null;

        // 解析 token。
        try {
            Map<String, Object> claims = new HashMap<>();
            claims = JwtUtils.parseJWT(jwt);  // jwt 包含当前包含的员工信息

            id = (Integer) claims.get("id");    // 得到用户id值
        } catch (Exception e) {
            e.printStackTrace();

            return Result.error("您还未登录");
        }

        // 获得用户信息
        Worker worker = workerService.getUserInfo(id);
        String oldUrl = worker.getImage();

        // 修改用户的image信息
        workerService.uploadImage(id, url);

        // 判断 oldUrl 是否为 null或空
        if (!(oldUrl == null) && !oldUrl.equals("")){
            // 删除七牛云中原本的文件
            qiniuKodoUtils.delete(oldUrl);
        }

        return Result.success(url);
    }
}

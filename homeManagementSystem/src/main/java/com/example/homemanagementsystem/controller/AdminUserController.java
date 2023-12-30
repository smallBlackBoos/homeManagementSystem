package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.*;
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
@RequestMapping("/adminUser")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private KindsService kindsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

    /**
     * 获取当前管理员的个人信息
     * @param token 令牌
     * @return Result
     */
    @RequestMapping("/getAdminInfo")
    public Result getAdminInfo(@RequestHeader String token) {
        // 解析token，获取管理员id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");

        AdminUser adminUser = adminUserService.getAdminInfo(id);
        return Result.success(adminUser);
    }

    /**
     * 分页查询所有管理员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return Result
     */
    @GetMapping("/getAllAdminUser")
    public Result getAllAdminUser(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = adminUserService.getAllAdminUser(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 分页查询所有商品种类信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return Result
     */
    @GetMapping("/getAllKinds")
    public Result getAllKinds(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = kindsService.getAllKinds(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 分页查询所有商品信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return Result
     */
    @GetMapping("/getAllGoods")
    public Result getAllGoods(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = goodsService.getAllGoods(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 分页查询所有家政人员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return Result
     */
    @GetMapping("/getAllWorker")
    public Result getAllWorker(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = workerService.getAllWorker(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 分页查询所有订单信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return Result
     */
    @GetMapping("/getAllOrder")
    public Result getAllOrder(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = orderService.getAllOrder(page, pageSize);

        return Result.success(pageBean);
    }

    /**
     * 添加商品
     * @param goods 商品对象
     * @return Result
     */
    @PostMapping("/addGoods")
    public Result addGoods(@RequestBody Goods goods) {
        goodsService.addGoods(goods);
        return Result.success();
    }

    /**
     * 添加商品种类
     * @param kinds 商品种类对象
     * @return Result
     */
    @PostMapping("/addKinds")
    public Result addKinds(@RequestBody Kinds kinds) {
        kindsService.addKinds(kinds);
        return Result.success();
    }

    /**
     * 修改管理员个人信息
     * @param token 令牌
     * @param adminUser 管理员对象
     * @return Result
     */
    @PutMapping("/editAdminUserInfo")
    public Result editAdminUserInfo(@RequestHeader String token, @RequestBody AdminUser adminUser) {
        // 解析token，获取用户id
        Claims claims = JwtUtils.parseJWT(token);
        Integer id = (Integer) claims.get("id");
        adminUser.setId(id);

        adminUserService.editAdminUserInfo(adminUser);
        return Result.success();
    }

    /**
     * 修改商品信息
     * @param goods 商品对象
     * @return Result
     */
    @PutMapping("/editGoodsInfo")
    public Result editGoodsInfo(@RequestBody Goods goods) {
        goodsService.editGoodsInfo(goods);
        return Result.success();
    }

    /**
     * 修改商品种类
     * @param kinds 商品种类对象
     * @return Result
     */
    @PutMapping("/editKindsInfo")
    public Result editKindsInfo(@RequestBody Kinds kinds) {
        kindsService.editKindsInfo(kinds);
        return Result.success();
    }

    /**
     * 上传商品图片
     * @param image 图片
     * @return 结果
     */
    @Transactional
    @PostMapping("/upload")
    public Result upload(Integer id, MultipartFile image ,HttpServletRequest request) throws IOException {

        // 上传文件
        String url = qiniuKodoUtils.upload(image);

        // 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        // 解析 token。
        try {
            Map<String, Object> claims = new HashMap<>();
            claims = JwtUtils.parseJWT(jwt);  // jwt 包含当前包含的员工信息
        } catch (Exception e) {
            e.printStackTrace();

            return Result.error("您还未登录");
        }

        // 获得商品信息
        Goods goods = adminUserService.getGoodsInfo(id);
        String oldUrl = goods.getImage();

        // 修改商品的image信息
        adminUserService.uploadImage(id, url);

        // 判断oldUrl是否为null或空
        if (!(oldUrl == null) && !oldUrl.equals("")){
            // 删除七牛云中原本的文件
            qiniuKodoUtils.delete(oldUrl);
        }

        return Result.success();
    }

    /**
     * 通过用户名去删
     * @return Result
     */
    /*
    @DeleteMapping("/removeAdminUser")
    public Result removeWorker(String username) {
        adminUserService.deleteAdminUser(username);
        return Result.success();
    }
     */

    /**
     * 删除家政人员
     * @param ids 家政人员id
     * @return Result
     */
    @DeleteMapping("/removeWorker/{ids}")
    public Result removeWorker(@PathVariable List<Integer> ids) {
        workerService.removeWorker(ids);

        return Result.success();
    }

    /**
     * 删除商品
     * @param ids 商品id
     * @return Result
     */
    @DeleteMapping("/removeGoods/{ids}")
    public Result removeGoods(@PathVariable List<Integer> ids) {
        goodsService.removeGoods(ids);

        return Result.success();
    }

    /**
     * 删除商品种类
     * @param ids 商品种类id
     * @return Result
     */
    @DeleteMapping("/removeKinds/{ids}")
    public Result removeKinds(@PathVariable List<Integer> ids) {
        kindsService.removeKinds(ids);

        return Result.success();
    }

    /**
     * 删除管理员
     * @param ids 管理员id数组
     * @return Result
     */
    @DeleteMapping("/deleteAllAdminUser/{ids}")
    public Result deleteAllAdminUser(@PathVariable List<Integer> ids) {
        adminUserService.deleteAllAdminUser(ids);
        return Result.success();
    }

    /**
     * 删除订单
     * @param id 订单id
     * @return Result
     */
    /*
    @DeleteMapping("/deleteOrder")
    public Result deleteOrder(Integer id) {
        orderService.deleteOrder(id);
        return Result.success();
    }
     */
}

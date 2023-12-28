package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 获取当前管理员的个人信息
     * @param id 管理员id
     * @return 结果
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(Integer id) {
        AdminUser adminUser = adminUserService.getUserInfo(id);
        return Result.success(adminUser);
    }

    /**
     * 修改个人信息
     * @param adminUser 管理员对象
     * @return
     */
    @PutMapping("/editUserInfo")
    public Result editUserInfo(@RequestBody AdminUser adminUser) {
        adminUserService.editUserInfo(adminUser);
        return Result.success();
    }

    /**
     * 修改密码
     * @return
     */
    @PutMapping("/editPassword/{id},{password}")
    public Result editPassword(Integer id, String password) {
        return adminUserService.editPassword(id, password);
    }

    /**
     * 通过用户名去删
     * @return
     */
    @DeleteMapping("deleteadminUser/{username}")
    public Result removeWorker(String username) {
        adminUserService.deleteAdminUser(username);
        return Result.success();
    }

    /**
     * 批量删除管理员用户
     * @param ids 管理员用户id
     * @return Result
     */
    @DeleteMapping("deleteAllAdminUser/{ids}")
    public Result deleteAllAdminUser(@PathVariable List<Integer> ids) {
        adminUserService.deleteAllAdminUser(ids);
        return Result.success();
    }

    /**
     * 分页查询所有管理员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return
     */
    @GetMapping("/adminUser/getAdminUser")
    public Result getAdminUser(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean pageBean = adminUserService.getAdminUser(page, pageSize);

        return Result.success(pageBean);
    }
}
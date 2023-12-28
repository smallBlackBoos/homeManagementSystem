package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;

import java.util.List;

public interface AdminUserService {
    /**
     * 管理员登录
     * @param adminUser 管理员对象
     * @return 管理员对象
     */
    AdminUser login(AdminUser adminUser);

    /**
     * 注册
     * @param adminUser 管理员对象
     */
    void register(AdminUser adminUser);


    /**
     * 查询管理员信息
     * @param id 管理员id
     * @return
     */
    AdminUser getUserInfo(Integer id);

    /**
     * 修改个人信息
     * @param adminUser 管理员对象
     * @return
     */
    void editUserInfo(AdminUser adminUser);

    /**
     * 修改密码
     * @param id 管理员id
     * @param password 密码
     * @return 结果
     */
    Result editPassword(Integer id, String password);

    /**
     * 删除管理员
     * @param username 用户名
     * @return 结果
     */
    void deleteAdminUser(String username);

    /**
     * 批量删除管理员
     * @param ids 管理员id
     */
    void deleteAllAdminUser(List<Integer> ids);

    /**
     * 分页查询所有家政人员信息
     * @param page 页码
     * @param pageSize 每页展示的条数
     * @return
     */
    PageBean getAdminUser(Integer page, Integer pageSize);
}

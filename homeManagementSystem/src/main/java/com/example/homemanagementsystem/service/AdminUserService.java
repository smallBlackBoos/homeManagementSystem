package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;

import java.io.IOException;
import java.util.List;

public interface AdminUserService {
    /**
     * 管理员登录
     * @param adminUser 管理员对象
     * @return 管理员对象
     */
    AdminUser login(AdminUser adminUser);

    /**
     * 分页查询所有管理员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return Result
     */
    PageBean getAllAdminUser(Integer page, Integer pageSize);

    /**
     * 获取当前管理员的个人信息
     * @param id 管理员id
     * @return Result
     */
    AdminUser getAdminInfo(Integer id);

    /**
     * 获取商品信息
     * @param id 商品id
     * @return 商品对象
     */
    Goods getGoodsInfo(Integer id);

    /**
     * 修改个人信息
     * @param adminUser 管理员对象
     * @return Result
     */
    void editAdminUserInfo(AdminUser adminUser);

    /**
     * 上传图片
     * @param id 商品id
     * @param url 图片url
     */
    void uploadImage(Integer id, String url);

    /**
     * 批量删除管理员用户
     * @param ids 管理员用户id
     */
    void deleteAllAdminUser(List<Integer> ids);
}

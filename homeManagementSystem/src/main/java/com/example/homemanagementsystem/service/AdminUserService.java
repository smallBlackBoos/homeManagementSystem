package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;

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
     * 获取当前管理员个人信息
     * @param id 管理员id
     * @return AdminUser
     */
    AdminUser getThisAdminInfo(Integer id);

    /**
     * 分页查询所有管理员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return Result
     */
    PageBean getAllAdminUser(Integer page, Integer pageSize);

    /**
     * 条件查询获取管理员信息
     * @param adminUser 管理员对象
     * @return PageBean
     */
    PageBean getAdminInfoByConditionQuery(Integer page, Integer pageSize, AdminUser adminUser);

    /**
     * 获取商品信息
     * @param id 商品id
     * @return 商品对象
     */
    Goods getGoodsInfo(Integer id);

    /**
     * 添加管理员
     * @param adminUser 管理员对象
     * @return Result
     */
    Result addAdminUser(AdminUser adminUser);

    /**
     * 修改个人信息
     * @param adminUser 管理员对象
     * @return Result
     */
    Result editAdminUserInfo(AdminUser adminUser);

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

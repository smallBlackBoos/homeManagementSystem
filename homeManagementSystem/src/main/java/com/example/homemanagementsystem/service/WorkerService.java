package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;

import java.util.List;

public interface WorkerService {

    /**
     * 登录
     * @param worker 家政人员
     * @return Worker
     */
    Worker login(Worker worker);

    /**
     * 获取家政人员信息
     * @param id 用户id
     * @return 家政人员对象
     */
    Worker getUserInfo(Integer id);

    /**
     * 获取当前家政人员余额
     * @param id 家政人员id
     * @return Double
     */
    Double getUserMoney(Integer id);

    /**
     * 条件查询获取家政人员信息
     * @param page 页码
     * @param pageSize 每页大小
     * @param worker 家政人员对象
     * @return PageBean
     */
    PageBean getWorkerInfoByConditionQuery(Integer page, Integer pageSize, Worker worker);

    /**
     * 分页查询所有家政人员信息
     * @param page 页数
     * @param pageSize 每页展示的数据条数
     * @return PageBean
     */
    PageBean getAllWorker(Integer page, Integer pageSize);

    /**
     * 家政人员注册
     * @param worker 家政人员
     * @return Result
     */
    Result register(Worker worker);

    /**
     * 添加家政人员
     * @param worker 家政人员对象
     * @return Result
     */
    Result addWorkerUser(Worker worker);

    /**
     * 上传头像
     * @param id 用户id
     * @param url 图片url
     */
    void uploadImage(Integer id, String url);

    /**
     * 取现
     * @param id 家政人员id
     * @param password 密码
     * @param money 取现金额
     * @return Result
     */
    Result withdrawCash(Integer id, String password, String money);

    /**
     * 修改家政人员的工作状态
     * @param worker 家政人员对象
     */
    void editWorkerStatus(Worker worker);

    /**
     * 修改当前家政人员个人信息
     * @param worker 家政人员对象
     * @return Result
     */
    Result editWorkerInfo(Worker worker);

    /**
     * 增加家政人员的余额
     * @param id 家政人员id
     * @param price 金额
     */
    void addMoney(Integer id, String price);

    /**
     * 修改家政人员密码
     * @param id 家政人员id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return Result
     */
    Result editWorkerPassword(Integer id, String oldPassword, String newPassword);

    /**
     * 删除家政人员
     * @param ids 家政人员id
     */
    void removeWorker(List<Integer> ids);
}

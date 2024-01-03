package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.WorkerMapper;
import com.example.homemanagementsystem.pojo.*;
import com.example.homemanagementsystem.service.WorkerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public Worker login(Worker worker) {
        return workerMapper.getWorkerByUsernameAndPassword(worker);
    }

    @Override
    public PageBean getAllWorker(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Worker> workers = workerMapper.selectAll();
        Page<Worker> p = (Page<Worker>) workers;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public Worker getUserInfo(Integer id) {
        return workerMapper.getWorkerById(id);
    }

    @Override
    public Double getUserMoney(Integer id) {
        return workerMapper.getWorkerMoneyById(id);
    }

    @Override
    public PageBean getWorkerInfoByConditionQuery(Integer page, Integer pageSize, Worker worker) {
        PageHelper.startPage(page, pageSize);

        List<Worker> workers = workerMapper.getConsumerInfoByConditionQuery(worker);
        Page<Worker> p = (Page<Worker>) workers;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void uploadImage(Integer id, String url) {
        workerMapper.updateImage(id, url);
    }

    @Override
    public Result withdrawCash(Integer id, String password, String money) {
        // 获取家政人员信息
        Worker worker = workerMapper.getWorkerById(id);

        // 密码错误
        if (!password.equals(worker.getPassword())) {
            return Result.error("密码错误");
        }

        BigDecimal bg = new BigDecimal(money);
        // 直接舍去多余的小数位
        Double m = bg.setScale(2, RoundingMode.DOWN).doubleValue();

        // 更新家政人员余额
        workerMapper.updateMoney(id, -m);
        return Result.success();
    }

    @Override
    public void editWorkerStatus(Worker worker) {

        int id = worker.getId();
        int status = worker.getStatus();

        workerMapper.updateStatus(id, status);
    }

    @Override
    public Result editWorkerInfo(Worker worker) {
        String username = worker.getUsername();

        // 通过用户名查找用户
        List<Worker> workers = workerMapper.getUserByName(username);

        if (workers != null && workers.size() > 0) {
            workerMapper.updateWorkerInfo(worker);
            return Result.success("修改成功");
        }


        return Result.error("用户名已存在");
    }

    @Override
    public void addMoney(Integer id, String price) {
        BigDecimal bg = new BigDecimal(price);
        // 直接舍去多余的小数位
        double money = bg.setScale(2, RoundingMode.DOWN).doubleValue();

        // 修改余额
        workerMapper.updateMoney(id, money);
    }

    @Override
    public Result editWorkerPassword(Integer id, String oldPassword, String newPassword) {
        // 通过id查询家政人员
        Worker worker = workerMapper.getWorkerById(id);

        // 密码错误
        if (!oldPassword.equals(worker.getPassword())) {
            return Result.error("密码输入错误");
        } else {
            workerMapper.updatePassword(id, newPassword);
            return Result.success();
        }
    }

    @Override
    public Result register(Worker worker) {
        String username = worker.getUsername().trim();
        String password = worker.getPassword().trim();

        worker.setStatus((short) 0);    // 工作状态默认为0，未启用

        worker.setImage("http://s5ylvlikx.hd-bkt.clouddn.com/avatar.jpg");

        if (username.equals("") || password.equals("")) {
            return Result.error("用户名密码不能为空");
        }

        // 通过用户名查找用户信息
        List<Worker> workers = workerMapper.getUserByName(username);

        if ((workers != null) && (workers.size() > 0)) {
            return Result.error("用户名已存在");
        }

        workerMapper.insertUser(worker);
        return Result.success("注册成功，请登录！");
    }

    @Override
    public Result addWorkerUser(Worker worker) {
        String username = worker.getUsername().trim();
        String name = worker.getName().trim();
        String password = worker.getPassword().trim();

        if (username.equals("") || password.equals("")) {
            return Result.error("用户名密码不能为空");
        }

        // 通过用户名查找用户信息
        List<Worker> workers = workerMapper.getUserByName(username);

        if ((workers != null) && (workers.size() > 0)) {
            return Result.error("用户名已存在");
        }

        workerMapper.insertUser(worker);
        return Result.success("添加成功");
    }

    @Override
    public void removeWorker(List<Integer> ids) {
        workerMapper.deleteById(ids);
    }
}

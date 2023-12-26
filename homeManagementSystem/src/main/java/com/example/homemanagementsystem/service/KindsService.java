package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Kinds;

import java.util.List;

public interface KindsService {
    /**
     * 查询所有种类
     * @return 种类列表
     */
    List<Kinds> listAll();
}

package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface GoodsService {
    /**
     * 按分类展示服务
     * @param id 分类id
     * @return 服务列表
     */
    List<Goods> listByKind(Integer id);
}

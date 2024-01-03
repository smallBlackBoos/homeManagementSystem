package com.example.homemanagementsystem.service;

import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.PageBean;

import java.util.List;

public interface KindsService {

    /**
     * 分页查询所有商品种类信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return PageBean
     */
    PageBean getAllKindsPage(Integer page, Integer pageSize);

    /**
     * 条件查询商品种类信息
     * @param page 页码
     * @param pageSize 每页大小
     * @param kinds 商品种类对象
     * @return PageBean
     */
    PageBean getKindsInfoByConditionQuery(Integer page, Integer pageSize, Kinds kinds);

    /**
     * 查询所有商品种类信息
     * @return List<Kinds>
     */
    List<Kinds> getAllKinds();

    /**
     * 添加商品种类
     * @param kinds 商品种类对象
     */
    void addKinds(Kinds kinds);

    /**
     * 修改商品种类
     * @param kinds 商品种类对象
     */
    void editKindsInfo(Kinds kinds);

    /**
     * 删除商品种类
     * @param ids 商品种类id
     */
    void removeKinds(List<Integer> ids);
}

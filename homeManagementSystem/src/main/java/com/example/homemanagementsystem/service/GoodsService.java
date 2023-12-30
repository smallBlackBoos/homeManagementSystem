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

    /**
     * 分页查询所有商品信息
     * @param page 页数
     * @param pageSize 分页大小
     * @return Result
     */
    PageBean getAllGoods(Integer page, Integer pageSize);

    /**
     * 获取商品信息
     * @param id 商品id
     * @return 商品对象
     */
    Goods getGoodsInfo(Integer id);

    /**
     * 添加商品
     * @param goods 商品对象
     */
    void addGoods(Goods goods);

    /**
     * 修改商品信息
     * @param goods 商品对象
     */
    void editGoodsInfo(Goods goods);

    /**
     * 删除商品
     * @param ids 商品id
     */
    void removeGoods(List<Integer> ids);
}

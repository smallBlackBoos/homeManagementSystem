package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.GoodsMapper;
import com.example.homemanagementsystem.mapper.KindsMapper;
import com.example.homemanagementsystem.pojo.AdminUser;
import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.service.GoodsService;
import com.example.homemanagementsystem.service.KindsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class KindsServiceImpl implements KindsService {

    @Autowired
    private KindsMapper kindsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageBean getAllKindsPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Kinds> kindsList = kindsMapper.selectAll();
        Page<Kinds> p = (Page<Kinds>) kindsList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public PageBean getKindsInfoByConditionQuery(Integer page, Integer pageSize, Kinds kinds) {
        PageHelper.startPage(page, pageSize);

        List<Kinds> kindsList = kindsMapper.getKindsInfoByConditionQuery(kinds);
        Page<Kinds> p = (Page<Kinds>) kindsList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public List<Kinds> getAllKinds() {
        List<Kinds> kindsList = kindsMapper.selectAll();
        return  kindsList;
    }

    @Override
    public void addKinds(Kinds kinds) {
        kindsMapper.insertKinds(kinds);
    }

    @Override
    public void editKindsInfo(Kinds kinds) {
        kindsMapper.updateKinds(kinds);
    }

    @Override
    @Transactional
    public void removeKinds(List<Integer> ids) {

        // ids 商品种类id
        for(Integer id : ids) {
            // 通过商品种类id查找商品信息
            List<Goods> goodsList = goodsMapper.getByKindsId(id);

            if (goodsList != null && goodsList.size() > 0) {
                // 将商品id添加到goodsIds列表中
                List<Integer> goodsIds = new ArrayList<>();
                for (Goods goods : goodsList) {
                    goodsIds.add(goods.getId());
                }

                // 删除商品信息
                goodsMapper.deleteGoods(goodsIds);
            }
        }

        // 删除商品种类
        kindsMapper.deleteKinds(ids);
    }
}

package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 按分类展示服务
     * @param id 分类id
     * @return 结果
     */
    @GetMapping("/{id}")
    public Result listByKind(@PathVariable Integer id) {
        List<Goods> goods =  goodsService.listByKind(id);

        return Result.success(goods);
    }
}

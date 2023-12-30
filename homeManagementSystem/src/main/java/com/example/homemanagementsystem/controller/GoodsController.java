package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.pojo.Worker;
import com.example.homemanagementsystem.service.GoodsService;
import com.example.homemanagementsystem.utils.JwtUtils;
import com.example.homemanagementsystem.utils.QiniuKodoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private QiniuKodoUtils qiniuKodoUtils;

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

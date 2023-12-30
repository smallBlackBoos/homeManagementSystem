package com.example.homemanagementsystem.controller;

import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.pojo.PageBean;
import com.example.homemanagementsystem.pojo.Result;
import com.example.homemanagementsystem.service.KindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kinds")
public class KindsController {

    @Autowired
    private KindsService kindsService;

    /**
     * 查询所有种类
     * @return 结果
     */
    @GetMapping("/listAll")
    public Result listAll() {
        List<Kinds> kinds = kindsService.listAll();
        return Result.success(kinds);
    }
}

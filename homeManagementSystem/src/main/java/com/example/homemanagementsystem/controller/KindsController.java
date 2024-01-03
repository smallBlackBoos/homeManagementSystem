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
}

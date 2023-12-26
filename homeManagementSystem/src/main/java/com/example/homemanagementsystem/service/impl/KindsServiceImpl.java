package com.example.homemanagementsystem.service.impl;

import com.example.homemanagementsystem.mapper.KindsMapper;
import com.example.homemanagementsystem.pojo.Kinds;
import com.example.homemanagementsystem.service.KindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindsServiceImpl implements KindsService {

    @Autowired
    private KindsMapper kindsMapper;

    @Override
    public List<Kinds> listAll() {
        return kindsMapper.selectAll();
    }
}

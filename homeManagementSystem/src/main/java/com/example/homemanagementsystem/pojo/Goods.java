package com.example.homemanagementsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 服务商品
 */
public class Goods {
    private Integer id; // 服务商品ID
    private Integer kindsId;    // 服务种类id（外键）
    private String kindsName;  // 服务种类名
    private String goodsName;    // 商品名
    private double price;   // 服务价格
    private String image;   // 图片
}

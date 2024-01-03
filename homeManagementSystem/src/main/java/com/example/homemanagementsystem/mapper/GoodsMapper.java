package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Goods;
import com.example.homemanagementsystem.pojo.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsMapper {

    /**
     * 按分类展示服务
     * @param id 分类id
     * @return 服务列表
     */
    @Select("select id, kinds_id, goods_name, price from goods where kinds_id = #{id}")
    List<Goods> getByKindsId(Integer id);

    /**
     * 通过id获取商品信息
     * @param id 商品id
     * @return 商品对象
     */
    @Select("select id, kinds_id, goods_name, price, image from goods where id = #{id}")
    Goods getById(Integer id);

    /**
     * 条件查询所有商品信息
     * @param goods 商品对象
     * @return Result
     */
    List<Goods> getGoodsInfoByConditionQuery(Goods goods);

    /**
     * 查询所有商品信息
     * @return List<Goods>
     */
    List<Goods> getAllGoods();

    /**
     * 修改商品信息
     * @param goods 商品对象
     */
    void updateGoods(Goods goods);

    /**
     * 修改图片路径
     * @param id 商品id
     * @param url 图片url
     */
    @Update("update goods set image = #{url} where id = #{id}")
    void updateImage(Integer id, String url);

    /**
     * 添加商品
     * @param goods 商品对象
     */
    void insertGoods(Goods goods);

    /**
     * 删除商品
     * @param ids 商品id列表
     */
    void deleteGoods(List<Integer> ids);
}

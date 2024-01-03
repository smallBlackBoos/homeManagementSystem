package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Kinds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface KindsMapper {
    /**
     * 查询所有种类
     * @return 种类列表
     */
    @Select("select id, name from kinds")
    List<Kinds> selectAll();

    /**
     * 条件查询商品种类信息
     * @param kinds 商品种类对象
     * @return List<Kinds>
     */
    List<Kinds> getKindsInfoByConditionQuery(Kinds kinds);

    /**
     * 添加商品种类
     * @param kinds 商品种类对象
     */
    @Insert("insert into kinds(name) values (#{name})")
    void insertKinds(Kinds kinds);

    /**
     * 修改商品种类
     * @param kinds 商品种类对象
     */
    void updateKinds(Kinds kinds);

    /**
     * 通过id删除商品种类
     * @param ids 商品种类id列表
     */
    void deleteKinds(List<Integer> ids);
}

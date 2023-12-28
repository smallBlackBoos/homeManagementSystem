package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.Kinds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
     * 通过id删除商品种类
     * @param ids 商品种类id列表
     */
    void deleteKinds(List<Integer> ids);

    /**
     * 修改种类信息
     * @param kinds 种类对象
     */
    void updatekinds(Kinds kinds);

    /**
     * 添加
     * @param kinds 要添加的商品对象
     */
    @Insert("insert into kinds(id, name) values (#{id}, #{name})")
    void insertkinds(Kinds kinds);

    /**
     * 通过id删除种类
     * @param ids 商品id种类列表
     */
    void deleteAllKinds(List<Integer> ids);
}

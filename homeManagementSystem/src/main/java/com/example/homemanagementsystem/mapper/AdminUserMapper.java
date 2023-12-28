package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.AdminUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminUserMapper {

    /**
     * 通过用户名和密码
     * @param adminUser 管理员对象
     * @return 管理员对象
     */
    @Select("select id, username, password from admin_user where username = #{username} and password = #{password}")
    AdminUser getUserByUsernameAndPassword(AdminUser adminUser);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select id, username, password from admin_user")
    List<AdminUser> getAllUser();

    /**
     * 通过id查询管理员用户信息
     * @param id 管理员id
     * @return 管理员对象
     */
    @Select("select id, username, password from admin_user where id = #{id}")
    AdminUser getUserById(Integer id);

    /**
     * 添加
     * @param adminUser 要添加的管理员对象
     */
    @Insert("insert into admin_user(username, password) values (#{username}, #{password})")
    void insertadminUser(AdminUser adminUser);

    /**
     * 修改管理员用户信息
     * @param adminUser 管理员对象
     */
    void updateUser(AdminUser adminUser);

    /**
     * 修改密码
     * @param password 新密码
     */
    @Update("update admin_user set password = #{password} where id = #{id}")
    void updatePassword(Integer id, String password);

    @Delete("delete from admin_user where username = #{username}")
    void deleteByName(String username);



    /**
     * 通过id删除管理员
     * @param ids 管理员id列表
     */
    void deleteAllAdminUser(List<Integer> ids);
}

package com.example.homemanagementsystem.mapper;

import com.example.homemanagementsystem.pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminUserMapper {

    /**
     * 查询所有管理员信息
     * @return List<AdminUser>
     */
    @Select("select id, username from admin_user")
    List<AdminUser> selectAllAdminUser();

    /**
     * 通过用户名和密码查询管理员对象
     * @param adminUser 管理员对象
     * @return 管理员对象
     */
    @Select("select id, username, password from admin_user where username = #{username} and password = #{password}")
    AdminUser getUserByUsernameAndPassword(AdminUser adminUser);

    /**
     * 获取当前管理员的个人信息
     * @param id 管理员id
     * @return Result
     */
    @Select("select id, username from admin_user where id = #{id}")
    AdminUser getAdminInfo(Integer id);

    /**
     * 修改个人信息
     * @param adminUser 管理员对象
     */
    void updateAdmin(AdminUser adminUser);

    /**
     * 批量删除管理员用户
     * @param ids 管理员id数组
     */
    void deleteAllAdminUser(List<Integer> ids);
}

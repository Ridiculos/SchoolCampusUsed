package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /*查询功能*/
    //查询所有
    @Select("select * from users")
    List<User> findAll();
    //根据ID查询单个
    @Select("select * from users where user_id = #{userId}")
    User findOneById(String userId);
    //根据用户名和密码查询单个
    @Select("select * from users where username = #{username} and password = #{password} and status = 1")
    User findOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    //根据昵称查询单个或多个
    @Select("select * from users where name = #{name}")
    List<User> findAllByName(String name);
    //根据邮箱查询单个
    @Select("select * from users where email = #{email}")
    User findOneByEmail(String email);
    //查询所有管理员账户
    @Select("select * from users where admin = 1")
    List<User> findAllAdmin();
    //根据昵称模糊查询
    @Select("select * from users where name like #{name}")
    List<User> findAllByNameAlike(String name);
    //根据用户名查询
    @Select("select * from users where username = #{username}")
    User findOneByUsername(String username);
    //查询总数
    @Select("select count(*) from users")
    int findCount();

    /*删除功能*/
    //根据ID删除用户
    @Delete("delete from users where user_id = #{userId}")
    int deleteUserById(String userId);
    //根据邮箱删除用户
    @Delete("delete from users where email = #{email}")
    int deleteUserByEmail(String email);

    /*修改功能*/
    //根据ID修改用户昵称、密码、性别
    @Update("update users set name = #{name},password = #{password},sex = #{sex} where user_id = #{userId}")
    int changeUserById(@Param("userId") String userId, @Param("name") String name, @Param("password") String password, @Param("sex") int sex);
    //根据ID修改用户密码
    @Update("update users set password = #{password} where user_id = #{userId}")
    int changeUserPasswordById(@Param("userId") String userId, @Param("password") String password);
    //根据ID改变管理员权限
    @Update("update users set admin = #{admin} where user_id = #{userId}")
    int changeUserAdmin(@Param("userId") String userId, @Param("admin") int admin);
    //根据ID改变账户状态
    @Update("update users set status = #{status} where user_id = #{userId}")
    int changeUserStatus(@Param("userId") String userId, @Param("status") int status);

    /*添加功能*/
    //创建新用户
    @Insert("insert into users values(#{userId},#{name},#{username},#{password},#{sex},#{email},#{status},#{admin})")
    int createUser(User user);
}

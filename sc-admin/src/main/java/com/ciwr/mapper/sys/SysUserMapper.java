package com.ciwr.mapper.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciwr.modle.sys.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends com.baomidou.mybatisplus.mapper.BaseMapper<SysUser> {
    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Select({
        "select",
        "id, user_name, password, phone, email, status, create_user, create_time, is_del, ",
        "location, last_time",
        "from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.BIT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.BIT),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_time", property="lastTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysUser selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, user_name, password, phone, email, status, create_user, create_time, is_del, ",
        "location, last_time",
        "from sys_user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.BIT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.BIT),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_time", property="lastTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysUser> selectAll(Page page, Map<String,Object> params);

    @Update({
        "update sys_user",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=BIT},",
          "create_user = #{createUser,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "is_del = #{isDel,jdbcType=BIT},",
          "location = #{location,jdbcType=VARCHAR},",
          "last_time = #{lastTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUser record);



    @Select({
            "select",
            "id, user_name, password,salt, phone, email, status, create_user, create_time, is_del,location, last_time ",
            "from sys_user",
            "where user_name = #{userName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.BIT),
            @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.BIT),
            @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_time", property="lastTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysUser findUserByName(String userName);

    @Select({
            "SELECT sm.authority FROM sys_user_role AS ur "+
            "LEFT JOIN sys_role_menu AS rm ON rm.role_id=ur.role_id "+
            "LEFT JOIN sys_menu AS sm ON rm.menu_id=sm.id "+
            "WHERE ur.user_id=#{userID,jdbcType=VARCHAR}"
            })
    List<String> findUserAuthority(String userID);

    @Select({
            "SELECT COUNT(1) FROM sys_user_role"
    })
    Long count(Map<String, Object> params);
}
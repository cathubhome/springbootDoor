package com.ciwr.mapper.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciwr.modle.sys.SysMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    @Delete({
        "delete from sys_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Select({
        "select",
        "id, name, icon, url, authority, remark, sort, parent_id, create_user, create_time, ",
        "is_del",
        "from sys_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="authority", property="authority", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.SMALLINT),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.BIT)
    })
    SysMenu selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, name, icon, url, authority, remark, sort, parent_id, create_user, create_time, ",
        "is_del",
        "from sys_menu"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="authority", property="authority", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.SMALLINT),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.BIT)
    })
    List<SysMenu> selectAll();

    @Update({
        "update sys_menu",
        "set name = #{name,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "authority = #{authority,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=SMALLINT},",
          "parent_id = #{parentId,jdbcType=BIGINT},",
          "create_user = #{createUser,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "is_del = #{isDel,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysMenu record);


    @Select({
            "select",
            "id, name, icon, url, authority, remark, sort, parent_id, create_user, create_time, ",
            "is_del",
            "from sys_menu",
            "where is_del=0 AND parent_id IS NULL ORDER BY `sort` DESC"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="authority", property="authority", jdbcType=JdbcType.VARCHAR),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="sort", property="sort", jdbcType=JdbcType.SMALLINT),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
            @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.BIT)
    })
    List<SysMenu> findRooMenu();
}
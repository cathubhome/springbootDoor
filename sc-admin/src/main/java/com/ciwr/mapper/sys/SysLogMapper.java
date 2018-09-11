package com.ciwr.mapper.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciwr.modle.sys.SysLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SysLogMapper extends BaseMapper<SysLog> {
    @Delete({
        "delete from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_log (operation, clazz, ",
        "method, remark, ",
        "params, timer, modify_user, ",
        "modify_time, ip_address)",
        "values (#{operation,jdbcType=VARCHAR}, #{clazz,jdbcType=VARCHAR}, ",
        "#{method,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{params,jdbcType=VARCHAR}, #{timer,jdbcType=BIGINT}, #{modifyUser,jdbcType=BIGINT}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{ipAddress,jdbcType=VARCHAR})"
    })
    @Select({
        "select",
        "id, operation, clazz, method, remark, params, timer, modify_user, modify_time, ",
        "ip_address",
        "from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
        @Result(column="clazz", property="clazz", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="timer", property="timer", jdbcType=JdbcType.BIGINT),
        @Result(column="modify_user", property="modifyUser", jdbcType=JdbcType.BIGINT),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ip_address", property="ipAddress", jdbcType=JdbcType.VARCHAR)
    })
    SysLog selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, operation, clazz, method, remark, params, timer, modify_user, modify_time, ",
        "ip_address",
        "from sys_log"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
        @Result(column="clazz", property="clazz", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="timer", property="timer", jdbcType=JdbcType.BIGINT),
        @Result(column="modify_user", property="modifyUser", jdbcType=JdbcType.BIGINT),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ip_address", property="ipAddress", jdbcType=JdbcType.VARCHAR)
    })
    List<SysLog> selectAll();

    @Update({
        "update sys_log",
        "set operation = #{operation,jdbcType=VARCHAR},",
          "clazz = #{clazz,jdbcType=VARCHAR},",
          "method = #{method,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "params = #{params,jdbcType=VARCHAR},",
          "timer = #{timer,jdbcType=BIGINT},",
          "modify_user = #{modifyUser,jdbcType=BIGINT},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "ip_address = #{ipAddress,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysLog record);

    @Update({"TRUNCATE sys_log"})
    void truncateTable();
}
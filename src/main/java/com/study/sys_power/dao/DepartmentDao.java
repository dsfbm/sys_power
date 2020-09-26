package com.study.sys_power.dao;

import com.study.sys_power.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DepartmentDao {
//    select * from sys_user p inner join sys_user_permission rp on p.id = rp.userId where rp.id= 2 order by p.id limit #{startPosition} , #{limit}
//    select * from sys_user p inner join sys_user_permission rp on p.id = rp.userId where rp.id= #{branch} order by p.id limit #{startPosition} , #{limit}
    @Select("select * from sys_user WHERE depatment=#{branch} limit #{startPosition} , #{limit}")
    List<SysUser> getAllUsersByPage(@Param("startPosition")Integer startPosition, @Param("limit") Integer limit,@Param("branch") String branch );
    @Select("select count(*) from sys_user t")
    Long countAllUsers();
}

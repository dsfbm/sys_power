package com.study.sys_power.dao;


import com.study.sys_power.model.SysUser;
import com.study.sys_power.model.SysUserMap;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserDao {

	@Select("select * from sys_user t where t.username = #{username}")
	SysUser getUser(String username);

	@Select("select * from sys_user t order by t.id limit #{startPosition} , #{limit}")
	List<SysUser> getAllUsersByPage(@Param("startPosition")Integer startPosition, @Param("limit") Integer limit);

	@Select("select count(*) from sys_user t")
	Long countAllUsers();

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(username, password, nickname, depatment, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{depatment},#{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
	int save(SysUser user);

	@Select("select * from sys_user t where t.telephone = #{telephone}")
	SysUser getUserByPhone(String telephone);

	@Select("select * from sys_user t where t.id = #{id}")
	SysUser getUserById(Long id);

	int updateUser(SysUser sysUser);

	@Delete("delete from sys_user where id = #{userId}")
	int deleteUser(int userId);

	@Select("select count(*) from sys_user t where t.username like '%${username}%'")
	Long getUserByFuzzyUsername(@Param("username") String username);

	@Select("select * from sys_user t where t.username like '%${username}%' limit #{startPosition} , #{limit}")
	List<SysUser> getUserByFuzzyUsernameByPage(@Param("username") String username, @Param("startPosition") Integer startPosition,  @Param("limit") Integer limit);
	@Select("select id,username,sale from sys_user")
	List<SysUserMap> findAll();
}

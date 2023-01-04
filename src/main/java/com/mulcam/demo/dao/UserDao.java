package com.mulcam.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.demo.entity.User;

@Mapper
public interface UserDao {

	@Select("select * from user where isDel=0")
	List<User> getList();

	@Select("select * from user where uid=#{uid}")
	User get(String uid);
	
	@Insert("insert into user values(#{uid}, #{pwd}, #{uname}, #{email}, default, default)")
	void insert(User u);
	
	@Update("UPDATE user SET pwd = #{pwd} , uname = #{uname}, email = #{email} WHERE uid = #{uid};")
	void update(User u);
	
//	@Delete("DELETE FROM user where uid = #{uid}")
//	void delete(String uid);
	
	@Update("UPDATE user SET isDel = 1 WHERE uid = #{uid};")
	void delete(String uid);
	
}

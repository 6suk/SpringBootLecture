package com.mulcam.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mulcam.demo.entity.User;

@Mapper
public interface UserDao {

	@Select("select * from user where isDel=0")
	List<User> getList();

	@Select("select * from user where uid=#{uid}")
	User get(String uid);
	
	@Insert("insert into user values(#{uid}, #{pwd}, #{uname}, #{email}, default, default)")
	void insert(User u);
	
}

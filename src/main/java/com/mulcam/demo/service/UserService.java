package com.mulcam.demo.service;

import java.util.List;

import com.mulcam.demo.entity.User;

public interface UserService {
	public static final int CORRENT_LOGIN = 0;
	public static final int WRONG_PWD = 1;
	public static final int NULL_UID = 2;

	List<User> getList();

	User get(String uid);
	
	void register(User u);
	
	void update(User u);
	
	void delete(String uid);

	int login(String uid, String pwd);
	
}

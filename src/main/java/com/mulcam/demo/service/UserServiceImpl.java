package com.mulcam.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.demo.dao.UserDao;
import com.mulcam.demo.entity.User;
import com.mulcam.demo.session.UserSession;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserSession ss;

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getList() {
		List<User> list = userDao.getList();
		return list;
	}

	@Override
	public User get(String uid) {
		User user = userDao.get(uid);
		return user;
	}

	@Override
	public void register(User u) {
		String Bpwd = BCrypt.hashpw(u.getPwd(), BCrypt.gensalt());
		u.setPwd(Bpwd);
		userDao.insert(u);
	}

	@Override
	public void update(User u) {
		if (u.getPwd() != null || !u.getPwd().isEmpty()) {
			String Bpwd = BCrypt.hashpw(u.getPwd(), BCrypt.gensalt());
			u.setPwd(Bpwd);
		} else {
			User user = userDao.get(u.getUid());
			u.setPwd(user.getPwd());
		}
		userDao.update(u);
	}

	@Override
	public void delete(String uid) {
		userDao.delete(uid);
	}

	@Override
	public int login(String uid, String pwd) {
		User u = userDao.get(uid);
		if (u.getUid() != null) {
			if (BCrypt.checkpw(pwd, u.getPwd())) {
				return CORRENT_LOGIN;
			} else { // 패스워드 불일치
				return WRONG_PWD;
			}
		}
		System.out.println("아이디 불일치");
		return NULL_UID;

	}

}

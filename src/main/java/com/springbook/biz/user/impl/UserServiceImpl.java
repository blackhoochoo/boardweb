package com.springbook.biz.user.impl;

import com.springbook.biz.user.User;
import com.springbook.biz.user.UserService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(User user) {
		return userDao.getUser(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
}

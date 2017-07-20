package com.springbook.biz.user;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.User;
import com.springbook.biz.user.UserService;

public class UserServiceClient {
	public static void main(String[] args) {
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService)container.getBean("userService");

		User user = new User();
		user.setId("test");
		user.setPassword("test123");
		user = userService.getUser(user);
		
		if(user != null) {
			System.out.println(user.getName() + " �� ȯ���մϴ�.");
		} else {
			System.out.println("�α��� ����");
		}
		container.close();
	}
}

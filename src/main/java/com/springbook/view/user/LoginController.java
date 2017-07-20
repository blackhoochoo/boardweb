package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.User;
import com.springbook.biz.user.impl.UserDao;

@Controller
public class LoginController {
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("my") User user) {
		return "login.jsp";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(User user, UserDao userDao, HttpSession session) {
		if(user.getId() == null || ("").equals(user.getId())) {
			throw new IllegalArgumentException("���̵�� �ݵ�� �Է��ؾ� �մϴ�.");
		}
		User signedUser = userDao.getUser(user);
		if(signedUser != null) {
			session.setAttribute("userName", signedUser.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}

}

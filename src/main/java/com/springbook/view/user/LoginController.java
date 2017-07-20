package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.User;
import com.springbook.biz.user.impl.UserDao;

@Controller
public class LoginController {
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("usr")User user, ModelAndView mav){
		System.out.println("로그인 화면으로 이동");
		user.setId("test");
		user.setPassword("test123");
//		mav.addObject("user", userVo);
//		mav.setViewName("login.jsp");
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(User vo, UserDao userDao, HttpSession session) throws Exception {
		if(vo.getId() == null || vo.getId().isEmpty()) {
			throw new IllegalArgumentException("아이디는 반드시 입력해라.");
		}
		User user = userDao.getUser(vo);
		// redirect: 지시자를 붙임으로써 ViewResolver 설정을 무시하고 리다이렉트 함
		if(user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}

}

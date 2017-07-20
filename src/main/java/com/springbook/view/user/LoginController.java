package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.User;
import com.springbook.biz.user.impl.UserDao;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리");
		
		// 1. 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
		User vo = new User();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDao userDao = new UserDao();
		User user = userDao.getUser(vo);
		
		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		// redirect: 지시자를 붙임으로써 ViewResolver 설정을 무시하고 리다이렉트 함
		if(user != null) {
			mav.setViewName("redirect:getBoardList.do");
		} else {
			mav.setViewName("redirect:login.jsp");
		}
		return null;
	}

}

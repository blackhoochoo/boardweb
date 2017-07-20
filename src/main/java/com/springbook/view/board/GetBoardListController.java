package com.springbook.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVo;
import com.springbook.biz.board.impl.BoardDao;

public class GetBoardListController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("글 목록 검색 처리");
		
		// 2. DB 연동 처리
		BoardVo vo = new BoardVo();
		BoardDao boardDao = new BoardDao();
		List<BoardVo> boardList = boardDao.getBoardList(vo);
		
		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList); // Model 정보 저장
		// ViewResolver 적용시에는 확장자를 제거해야 함
		mav.setViewName("getBoardList"); // View 정보 저장
		return mav;
	}
	

}

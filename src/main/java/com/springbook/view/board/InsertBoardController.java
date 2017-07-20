package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVo;
import com.springbook.biz.board.impl.BoardDao;

@Controller
public class InsertBoardController {
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		BoardVo board = new BoardVo();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		BoardDao boardDao = new BoardDao();
		boardDao.insertBoard(board);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		return mav;
	}
}

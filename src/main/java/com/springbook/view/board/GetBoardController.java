package com.springbook.view.board;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVo;
import com.springbook.biz.board.impl.BoardDao;

public class GetBoardController  {
	public ModelAndView getBoard(BoardVo board, BoardDao boardDao, ModelAndView mav) {
/*		
		String seq = request.getParameter("seq");
		BoardDao boardDao = new BoardDao();
		Board board = new Board();
		board.setSeq(Integer.parseInt(seq));
		board = boardDao.getBoard(board);
		
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("getBoard");*/
		
		mav.addObject("board", boardDao.getBoard(board));
		mav.setViewName("getBoard.jsp"); 
		return mav;
	}

}

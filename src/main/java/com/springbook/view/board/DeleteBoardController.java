package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVo;
import com.springbook.biz.board.impl.BoardDao;

public class DeleteBoardController {
	public String deleteBoard(BoardVo board, BoardDao boardDao) {
		boardDao.deleteBoard(board);
		return "getBoardList.do";
	}

}

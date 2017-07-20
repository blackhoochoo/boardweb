package com.springbook.biz.board.impl;

import java.util.List;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVo;
import com.springbook.biz.common.LogAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDAO;
//	@Autowired
//	private BoardDAOSpring boardDAO;

	public BoardServiceImpl() {
		super();
	}

	@Override
	public void insertBoard(BoardVo vo) {
//		if(vo.getSeq() == 0) {
//			throw new IllegalArgumentException("0�� ���� ����� �� �����ϴ�.");
//		}
		boardDAO.insertBoard(vo);
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVo vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVo vo) {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVo getBoard(BoardVo vo) {
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVo> getBoardList(BoardVo vo) {
		return boardDAO.getBoardList(vo);
	}

}


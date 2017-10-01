package com.springbook.biz.board.impl;

import java.util.List;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVo;
import com.springbook.biz.common.LogAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	@Qualifier("boardDaoMybatisTemplate")
	private BoardDao boardDao;
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
		boardDao.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVo vo) {
		boardDao.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVo vo) {
		boardDao.deleteBoard(vo);
	}

	@Override
	public BoardVo getBoard(BoardVo vo) {
		return boardDao.getBoard(vo);
	}

	@Override
	public List<BoardVo> getBoardList(BoardVo vo) {
		return boardDao.getBoardList(vo);
	}

}


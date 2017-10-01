package com.springbook.biz.board.impl;

import java.util.List;

import com.springbook.biz.board.BoardVo;

public interface BoardDao {

	void insertBoard(BoardVo vo);

	void updateBoard(BoardVo vo);

	void deleteBoard(BoardVo vo);

	BoardVo getBoard(BoardVo vo);

	List<BoardVo> getBoardList(BoardVo vo);

}
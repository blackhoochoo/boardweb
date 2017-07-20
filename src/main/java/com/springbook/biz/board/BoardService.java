package com.springbook.biz.board;

import java.util.List;

public interface BoardService {

	void insertBoard(BoardVo vo);

	void updateBoard(BoardVo vo);

	void deleteBoard(BoardVo vo);

	BoardVo getBoard(BoardVo vo);

	List<BoardVo> getBoardList(BoardVo vo);

}
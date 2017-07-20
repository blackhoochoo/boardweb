package com.springbook.biz.board;

import java.util.List;

public interface BoardService {

	// CRUD 기능의 메소드 구현
	// 글 등록
	void insertBoard(BoardVo vo);

	// 글 수정
	void updateBoard(BoardVo vo);

	// 글 삭제
	void deleteBoard(BoardVo vo);

	// 글 상세 조회
	BoardVo getBoard(BoardVo vo);

	// 글 목록 조회
	List<BoardVo> getBoardList(BoardVo vo);

}
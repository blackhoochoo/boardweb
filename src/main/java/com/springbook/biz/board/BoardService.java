package com.springbook.biz.board;

import java.util.List;

public interface BoardService {

	// CRUD ����� �޼ҵ� ����
	// �� ���
	void insertBoard(BoardVo vo);

	// �� ����
	void updateBoard(BoardVo vo);

	// �� ����
	void deleteBoard(BoardVo vo);

	// �� �� ��ȸ
	BoardVo getBoard(BoardVo vo);

	// �� ��� ��ȸ
	List<BoardVo> getBoardList(BoardVo vo);

}
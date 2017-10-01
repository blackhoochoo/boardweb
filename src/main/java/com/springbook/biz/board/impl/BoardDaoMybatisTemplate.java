package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVo;

@Repository
public class BoardDaoMybatisTemplate implements BoardDao {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#insertBoard(com.springbook.biz.board.BoardVo)
	 */
	public void insertBoard(BoardVo vo) {
		sessionTemplate.insert("BoardDao.insertBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#updateBoard(com.springbook.biz.board.BoardVo)
	 */
	public void updateBoard(BoardVo vo) {
		sessionTemplate.update("BoardDao.updateBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#deleteBoard(com.springbook.biz.board.BoardVo)
	 */
	public void deleteBoard(BoardVo vo) {
		sessionTemplate.delete("BoardDao.deleteBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#getBoard(com.springbook.biz.board.BoardVo)
	 */
	public BoardVo getBoard(BoardVo vo) {
		return (BoardVo)sessionTemplate.selectOne("BoardDao.getBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#getBoardList(com.springbook.biz.board.BoardVo)
	 */
	public List<BoardVo> getBoardList(BoardVo vo) {
		return sessionTemplate.selectList("BoardDao.getBoardList", vo);
	}
}

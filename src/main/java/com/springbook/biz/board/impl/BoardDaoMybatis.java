package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVo;

@Repository
public class BoardDaoMybatis extends SqlSessionDaoSupport implements BoardDao {
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		System.out.println("BoardDaoMyBatis.setSqlSessionFactory 호출.");
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#insertBoard(com.springbook.biz.board.BoardVo)
	 */
	public void insertBoard(BoardVo vo) {
		getSqlSession().insert("BoardDao.insertBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#updateBoard(com.springbook.biz.board.BoardVo)
	 */
	public void updateBoard(BoardVo vo) {
		getSqlSession().update("BoardDao.updateBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#deleteBoard(com.springbook.biz.board.BoardVo)
	 */
	public void deleteBoard(BoardVo vo) {
		getSqlSession().delete("BoardDao.deleteBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#getBoard(com.springbook.biz.board.BoardVo)
	 */
	public BoardVo getBoard(BoardVo vo) {
		return (BoardVo)getSqlSession().selectOne("BoardDao.getBoard", vo);
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#getBoardList(com.springbook.biz.board.BoardVo)
	 */
	public List<BoardVo> getBoardList(BoardVo vo) {
		return getSqlSession().selectList("BoardDao.getBoardList", vo);
	}
}

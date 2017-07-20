package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVo;

// DAO(Data Access Object)
@Repository("boardDao")
public class BoardDAOSpring extends JdbcDaoSupport{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String BOARD_INSERT = "insert into board(seq, title, writer, " + 
	" content) values((select * from (select coalesce(max(seq), 0)+1 from board) seq),?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?,"
			+ "content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	
	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	public void insertBoard(BoardVo board) {
		jdbcTemplate.update(BOARD_INSERT, 
				board.getSeq(), board.getTitle(), board.getWriter(), board.getContent());
	}
	
	public void updateBoard(BoardVo board) {
		jdbcTemplate.update(BOARD_UPDATE,
				board.getTitle(), board.getContent(), board.getSeq());
	}
	
	public void deleteBoard(BoardVo board) {
		jdbcTemplate.update(BOARD_DELETE,
				board.getSeq());
	}
	
	public BoardVo getBoard(BoardVo board) {
		Object[] args = {board.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	public List<BoardVo> getBoardList(BoardVo board) {
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
}

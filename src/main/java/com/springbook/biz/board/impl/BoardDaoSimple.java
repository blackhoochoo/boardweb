package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVo;
import com.springbook.biz.common.JDBCUtil;

// DAO(Dada Access Object)
//@Component("boardDao") <-- @Repository ��ſ� ���� ����
@Repository
public class BoardDaoSimple implements BoardDao{
	// JDBC ���� ����
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// SQL ��ɾ��
//	private final String BOARD_INSERT = "insert into board(seq, title, writer, " + 
//			" content) values((select * from (select coalesce(max(seq), 0)+1 from board) seq),?,?,?)";
	private final String BOARD_INSERT = "insert into board(seq, title, writer, " + 
			" content) values(?,?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?,"
			+ " content=? where seq=?";
	private final String BOARD_DELETE = "delete from board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	private final String BOARD_LIST_SEARCH_BY_TITLE = 
			"select * from board where title like CONCAT('%', ?, '%') order by seq desc";
	private final String BOARD_LIST_SEARCH_BY_CONTENT =
			"select * from board where content like CONCAT('%', ?, '%') order by seq desc";
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#insertBoard(com.springbook.biz.board.BoardVo)
	 */
	public void insertBoard(BoardVo vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setInt(1,  vo.getSeq());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getWriter());
			stmt.setString(4, vo.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#updateBoard(com.springbook.biz.board.BoardVo)
	 */
	public void updateBoard(BoardVo vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// �� ����
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#deleteBoard(com.springbook.biz.board.BoardVo)
	 */
	public void deleteBoard(BoardVo vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// �� �� ��ȸ
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#getBoard(com.springbook.biz.board.BoardVo)
	 */
	public BoardVo getBoard(BoardVo vo) {
		BoardVo board = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if(rs.next()) {
				board = new BoardVo();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	}
	
	/* (non-Javadoc)
	 * @see com.springbook.biz.board.impl.BoardDao#getBoardList(com.springbook.biz.board.BoardVo)
	 */
	public List<BoardVo> getBoardList(BoardVo vo) {
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		String searchCondition = vo.getSearchCondition();
		String sql = null;
		
		try {
			conn = JDBCUtil.getConnection();
			if(vo.getSearchKeyword().isEmpty()) {
				sql = BOARD_LIST;
				stmt = conn.prepareStatement(sql);
			} else {
				if("TITLE".equals(searchCondition)) {
					sql = BOARD_LIST_SEARCH_BY_TITLE;
				} else if("CONTENT".equals(searchCondition)) {
					sql = BOARD_LIST_SEARCH_BY_CONTENT;
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "'%" + vo.getSearchKeyword() + "%'");
			}
			
			System.out.println("getBoardList SQL: " + stmt.toString());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVo board = new BoardVo();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	}
	
	public static void main(String[] args) {
		BoardDaoSimple dao = new BoardDaoSimple();
		
		BoardVo vo = new BoardVo();
		vo.setTitle("a");
		vo.setWriter("b");
		vo.setContent("content");
		dao.insertBoard(vo);
	}
}

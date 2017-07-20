package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.user.User;
import com.springbook.biz.common.JDBCUtil;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	// JDBC ���� ����
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET = "select * from userS where id=? and password=?";
	
	public User getUser(User user) {
		User foundUser = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				foundUser = new User();
				foundUser.setId(rs.getString("ID"));
				foundUser.setPassword(rs.getString("PASSWORD"));
				foundUser.setName(rs.getString("NAME"));
				foundUser.setRole(rs.getString("ROLE"));
				System.out.println("===> User ã��. " +foundUser.toString());

			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return foundUser;
	}
	
}


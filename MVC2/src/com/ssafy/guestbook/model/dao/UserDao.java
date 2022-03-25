package com.ssafy.guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.backend.util.DBUtil;
import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.UserDto;

public class UserDao {
	private DBUtil dbUtil = DBUtil.getInstance();
	
	public UserDto login(String id, String pwd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "select * from user where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pass").equals(pwd)) {
					UserDto user = new UserDto(id, pwd, rs.getString("name"));
					return user;
				}
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return null;
	}
}

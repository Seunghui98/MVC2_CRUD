package com.ssafy.guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.backend.util.DBUtil;
import com.ssafy.guestbook.model.GuestBookDto;

public class GuestBookDao {
	private DBUtil dbUtil = DBUtil.getInstance();
	
	public void registBook(GuestBookDto guestBookDto) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "insert into book (`isbn`, `title`, `author`, `price`, `desc`) \n";
			sql += "values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, guestBookDto.getIsbn());
			pstmt.setString(2, guestBookDto.getTitle());
			pstmt.setString(3, guestBookDto.getAuthor());
			pstmt.setInt(4, guestBookDto.getPrice());
			pstmt.setString(5, guestBookDto.getDesc());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	public List<GuestBookDto> listBook() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookDto> list = new ArrayList<GuestBookDto>();
		try {
			conn = dbUtil.getConnection();
			String sql = "select * from book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookDto guestBookDto = new GuestBookDto();
				guestBookDto.setIsbn(rs.getString("isbn"));
				guestBookDto.setTitle(rs.getString("title"));
				guestBookDto.setAuthor(rs.getString("author"));
				guestBookDto.setPrice(rs.getInt("price"));
				guestBookDto.setDesc(rs.getString("desc"));
				
				list.add(guestBookDto);
			}
		
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public GuestBookDto getBook(String isbn) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "select * from book where isbn = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				GuestBookDto book = new GuestBookDto();
				book.setIsbn(isbn);
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setDesc(rs.getString("desc"));
				return book;
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return null;
	}
	
	public void updateBook(GuestBookDto guestBookDto) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "update book set `title`=?, `author`=?, `price`=?, `desc`=? \n";
			sql += "where `isbn`=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, guestBookDto.getTitle());
			pstmt.setString(2, guestBookDto.getAuthor());
			pstmt.setInt(3, guestBookDto.getPrice());
			pstmt.setString(4, guestBookDto.getDesc());
			pstmt.setString(5, guestBookDto.getIsbn());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
	public void deleteBook(String isbn) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			String sql = "delete from book where isbn = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
	
}

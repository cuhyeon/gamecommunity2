package org.masterjung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.masterjung.dto.BoardDto;
import org.masterjung.dto.UserDto;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.util.GetIp;
import org.masterjung.util.SqlDate;
import org.masterjung.util.StringSkip;

public class AdminDao {
	DataSource ds;
	Connection conn;
	Context context;
	StringSkip stringSkip;
	SqlDate sqldate;
	GetIp ip;
	PreparedStatement pstmt;
	ResultSet rs;
	public AdminDao() {
		conn = null;	
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			stringSkip = new StringSkip();
			sqldate = new SqlDate();
			ip = new GetIp();
		} catch (NamingException e) {
			System.out.println("memodao.java -> 기본생성자 오류 : " + e.getMessage());
		}
	}
	public int getUserCount() throws SQLException {
		String sql = "select count(id) as count from user where enabled=1";
		conn = ds.getConnection();
		pstmt=conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int result =0;
		if(rs.next()) {
			result= rs.getInt("count");
		}
		
		if(rs!=null) try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
		return result;
	}
	public List<UserDto> getUserList(int startRow, int endRow){
		String sql = "select * from user where enabled=1 order by id desc limit " + startRow + ","+ endRow;
		List<UserDto> userList = new ArrayList<UserDto>();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserDto dto = new UserDto();
				dto.setEmail(rs.getString("email"));
				
				dto.setId(rs.getInt("id"));
				dto.setPassword(rs.getString("password"));
				dto.setNick_name(rs.getString("nick_name"));
				dto.setPhone_number(rs.getInt("phone_number"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_auth(rs.getInt("user_auth"));
				dto.setUser_image_path(rs.getString("user_image_path"));
				dto.setDate_created(rs.getDate("date_created"));
				dto.setLast_updated(rs.getDate("last_updated"));
				dto.setEnabled(rs.getInt("enabled"));
				userList.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Message : "+e.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e1) {
				System.out.println("getBoardListById 오류 : " + e1.getMessage());
			}
		}
		return userList;
	}
	public int getWithdrawUserCount() throws SQLException {
		String sql = "select count(id) as count from user where enabled=0";
		 conn = ds.getConnection();
		 pstmt=conn.prepareStatement(sql);
		 rs = pstmt.executeQuery();
		int result =0;
		if(rs.next()) {
			result= rs.getInt("count");
		}
		
		if(rs!=null) try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
		return result;
	}
	public List<UserDto> getWithdrawUserList(int startRow, int endRow){
		String sql = "select * from user where enabled=0 limit "+startRow+","+endRow;
		List<UserDto> userList = new ArrayList<UserDto>();

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserDto dto = new UserDto();
				dto.setEmail(rs.getString("email"));
				
				dto.setId(rs.getInt("id"));
				dto.setPassword(rs.getString("password"));
				dto.setNick_name(rs.getString("nick_name"));
				dto.setPhone_number(rs.getInt("phone_number"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_auth(rs.getInt("user_auth"));
				dto.setUser_image_path(rs.getString("user_image_path"));
				dto.setDate_created(rs.getDate("date_created"));
				dto.setLast_updated(rs.getDate("last_updated"));
				dto.setEnabled(rs.getInt("enabled"));
				userList.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Message : "+e.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e1) {
				System.out.println("getBoardListById 오류 : " + e1.getMessage());
			}
		}
		return userList;
	}
	public UserDto getUserDetailById(int id) {
		String sql = "select * from user where id=? and enabled = 1";
		
		UserDto dto = null;
		
		try {
			dto = new UserDto();
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setEmail(rs.getString("email"));
				dto.setId(rs.getInt("id"));
				dto.setPassword(rs.getString("password"));
				dto.setNick_name(rs.getString("nick_name"));
				dto.setPhone_number(rs.getInt("phone_number"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setUser_auth(rs.getInt("user_auth"));
				dto.setUser_image_path(rs.getString("user_image_path"));
				dto.setDate_created(rs.getDate("date_created"));
				dto.setLast_updated(rs.getDate("last_updated"));
				dto.setEnabled(rs.getInt("enabled"));
			}
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e) {
				
			}
		}
		return dto;
	}
	public int updateUser(UserDto dto) throws SQLException {
		String sql = "update user set user_auth=? where id=?";
		int result=0;
		 conn = ds.getConnection();
		 pstmt = conn.prepareStatement(sql);
	
		pstmt.setInt(1, dto.getUser_auth());
		pstmt.setInt(2, dto.getId());

		result = pstmt.executeUpdate();

		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}

		return result;
	}
	public int deleteUser(int id) throws SQLException {
		String sql = "update user set enabled = 0 where id = ? ";
		int result=0;
		 conn = ds.getConnection();
		 pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		result=pstmt.executeUpdate();
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		return result;
	}
	
	
	public int getDeleteBoardListCount() throws SQLException {
		String sql = "select count(id) as count from board where enabled=0";
		 conn = ds.getConnection();
		 pstmt=conn.prepareStatement(sql);
		 rs = pstmt.executeQuery();
		int result =0;
		if(rs.next()) {
			result= rs.getInt("count");
		}
		
		if(rs!=null) try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
		return result;
	}
	
	public List<BoardDto> getDeleteBoardList(int startRow, int endRow){
		String sql = "select * from board where enabled=0 order by id desc limit "+startRow+"," +endRow;
		List<BoardDto> boardList = new ArrayList<BoardDto>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDto dto = new BoardDto();

				 
				dto.setId(rs.getInt("id"));
				dto.setNick_name(rs.getString("nick_name"));
				dto.setTitle(rs.getString("title"));
				dto.setBoard_list_id(rs.getInt("board_list_id"));
				boardList.add(dto);
			}
			
			System.out.println(boardList);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Message : "+e.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e1) {
				System.out.println("getBoardListById 오류 : " + e1.getMessage());
			}
		}
		return boardList;
	}
	public int restoreBoard(int id) throws SQLException {
		String sql = "update board set enabled = 1 where id = ? ";
		int result=0;
		 conn = ds.getConnection();
		 pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		result=pstmt.executeUpdate();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		return result;
	}
	public BoardDetailDto getDeleteBoardById(int id) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select id, board_list_id, nick_name, anonymity, create_ip, refer, depth, step, date_created, last_updated, view_count, vote_count, title, content, file_path, ");
		sql.append("(select count(id) from reply where reply_id=board.id and enabled=1) count, ");
		sql.append("(select count(*) from content_vote where c_vote_id=board.id) recommend ");
		sql.append("from board ");
		sql.append("where enabled=0 and id=?");
		
		BoardDetailDto dto = null;
		try {
			dto = new BoardDetailDto();
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setNick_name(rs.getString("nick_name"));
				dto.setAnonymity(rs.getInt("anonymity"));
				dto.setCreate_ip(rs.getString("create_ip"));
				dto.setRefer(rs.getInt("refer"));
				dto.setDepth(rs.getInt("depth"));
				dto.setStep(rs.getInt("step"));
				dto.setDate_created(rs.getDate("date_created"));
				try {
					dto.setLast_updated(rs.getDate("last_updated"));
				}catch(Exception e1) {
					dto.setLast_updated(rs.getDate("date_created"));
				}
				dto.setView_count(rs.getInt("view_count"));
				dto.setVote_count(rs.getInt("vote_count"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile_path(rs.getString("file_path"));
				dto.setCount(rs.getString("count"));
				dto.setRecommend(rs.getInt("recommend"));
			}
		}catch(Exception e) {
			System.out.println("getBoardDetailById() 오류 발생1" + e.getMessage());
		
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("getBoardDetailById() 오류 발생2" + e.getMessage());
			}
		}
		return dto;
	}
}

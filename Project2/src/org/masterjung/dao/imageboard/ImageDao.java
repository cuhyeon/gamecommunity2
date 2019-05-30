package org.masterjung.dao.imageboard;

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
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.util.SqlDate;
import org.masterjung.util.StringSkip;

public class ImageDao {	
	
	static DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	StringSkip stringSkip;
	SqlDate sqldate;
	
	public ImageDao() {		
		
		InitialContext ctx;	
		
		try {
			ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("/jdbc/mysql");
			stringSkip = new StringSkip();
			sqldate = new SqlDate();			
		} catch (NamingException e) {
			System.out.println("lookup Fail : " + e.getMessage());
		}
	}

	// 이미지 보드 리스트 보여주는 메소드
	public List<BoardDto> getImageList() throws SQLException {
					
	    String sql = "select * from board where enabled=1 and board_list_id=5 order by id desc";
		List<BoardDto> boardList = new ArrayList<>();
		
		try	{
			
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while(rs.next()) {
			
			BoardDto dto = new BoardDto();
			dto.setId(rs.getInt("id"));
			dto.setNick_name(rs.getString("nick_name"));
			dto.setAnonymity(rs.getInt("anonymity"));
			dto.setCreate_ip(rs.getString("create_ip"));
			dto.setRefer(rs.getInt("refer"));
			dto.setDepth(rs.getInt("depth"));
			dto.setStep(rs.getInt("step"));
			dto.setDate_created(sqldate.timestampToDate(rs.getTimestamp("date_created")));
			try {
				dto.setLast_updated(sqldate.timestampToDate(rs.getTimestamp("last_updated")));
			}catch(Exception e1) {
				dto.setLast_updated(sqldate.timestampToDate(rs.getTimestamp("date_created")));
			}
			dto.setView_count(rs.getInt("view_count"));
			dto.setVote_count(rs.getInt("vote_count"));
			dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 10));
			dto.setContent(rs.getString("content"));
			dto.setFile_path(rs.getString("file_path"));
			boardList.add(dto);
		}
	}catch(Exception e) {
		System.out.println("getBoardListById() 오류발생1 : " + e.getMessage());
	}finally {
		try {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
		}catch(Exception e1) {
			System.out.println("getBoardListById 오류발생2 : " + e1.getMessage());
		}
	}
	return boardList;
	}

	public int InsertBoard(BoardDto boardDto) throws SQLException {
		
		int resultrow = 0;
		String sql = "insert into board(title,content,nick_name,board_list_id,file_path) values(?,?,?,?,?)";
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, boardDto.getTitle());
		pstmt.setString(2, boardDto.getContent());
		pstmt.setString(3, boardDto.getNick_name());
		pstmt.setInt(4, boardDto.getBoard_list_id());
		pstmt.setString(5, boardDto.getFile_path());
		resultrow = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return resultrow;
	}
	
	//글번호로 파일패스 찾기
	public String getfilepath(int id) {
		PreparedStatement pstmt = null;
		String filepath = "";
		ResultSet rs = null;
		
		try {
			String sql = "select filepath from board where id=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				filepath=rs.getString(1);
			}else {
				filepath="NotFind";
			}
		}catch(Exception e) {
			System.out.println("getfilepath 오류 : " +e.getMessage());

		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e1) {
				System.out.println("getfilepath 오류");
			}
		}
		return filepath;
	}
}


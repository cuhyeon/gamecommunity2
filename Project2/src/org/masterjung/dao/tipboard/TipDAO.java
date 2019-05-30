package org.masterjung.dao.tipboard;

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
import org.masterjung.dto.join.BoardReplyDto;

public class TipDAO {
	DataSource ds = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public TipDAO() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
	}

	 public int getTotalCount() throws SQLException{
		    int total = 0;
		     
		    try {
		      conn = ds.getConnection();
		       
		      String sql = "select count(*) from board where enabled=1 and board_list_id=3";
		      pstmt = conn.prepareStatement(sql);
		       
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        total = rs.getInt(1);
		      }
		    } catch (Exception e){
		    	System.out.println(e.getMessage());
		    } finally {
		    	if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
				if(conn!=null) try{conn.close();}catch(SQLException ex){}
		    }
		    return total;
		  }

	public List<BoardReplyDto> getNewsListAndReplyCount(int board_list_id, int startRow, int endRow) throws SQLException {
		String sql = "select date_created , content , vote_count , last_updated , anonymity , refer , depth , step , id,board_list_id , title , view_count , nick_name , file_path , (select count(id) from reply where reply_id=b.id and enabled=1)count from board b where board_list_id=? and enabled=1 order by id desc limit "+startRow+","+endRow;
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board_list_id);
		ResultSet rs = pstmt.executeQuery();
		List<BoardReplyDto> boardList = new ArrayList<>();
		
		while (rs.next()) {
			BoardReplyDto dto = new BoardReplyDto();
			dto.setDate_created(rs.getDate("date_created"));
			dto.setContent(rs.getString("content"));
			dto.setVote_count(rs.getInt("vote_count"));
			try {
				try {
					dto.setLast_updated(rs.getDate("last_updated"));
				}catch(Exception e) {
	                dto.setLast_updated(rs.getDate("date_created"));
				}
             dto.setAnonymity(rs.getInt("anonymity"));
 			dto.setRefer(rs.getInt("refer"));
 			dto.setDepth(rs.getInt("depth"));
 			dto.setStep(rs.getInt("step"));
 			dto.setId(rs.getInt("id"));
 			dto.setBoard_list_id(rs.getInt("board_list_id"));
 			dto.setTitle(rs.getString("title"));
 			dto.setView_count(rs.getInt("view_count"));
 			dto.setNick_name(rs.getString("nick_name"));
 			dto.setFile_path(rs.getString("file_path"));
 			dto.setReply_count(rs.getInt("count"));
 			boardList.add(dto);
         }catch(Exception e1) {
         } finally {
 			if(rs!=null) {rs.close();}
 			if(pstmt!=null) {pstmt.close();}
 			if(conn!=null) {conn.close();}
	
         }
			
		}
		return boardList;
	}

	public List<BoardDto> getNewsList() throws SQLException {
		String sql = "select date_created, id, board_list_id, title, view_count, nick_name, file_path from board  where board_list_id = 3 order by id desc";
		// POOL 연결 객체 얻어오기
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		List<BoardDto> boardList = new ArrayList<>();
		while (rs.next()) {
			BoardDto news = new BoardDto();
			news.setDate_created(rs.getDate("date_created"));
			news.setId(rs.getInt("id"));
			news.setBoard_list_id(rs.getInt("board_list_id"));
			news.setTitle(rs.getString("title"));
			news.setView_count(rs.getInt("view_count"));
			news.setNick_name(rs.getString("nick_name"));
			news.setFile_path(rs.getString("file_path"));
			boardList.add(news);
		}

		if(rs!=null) try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
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
		
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
		return resultrow;
	}

	public int UpdateBoard(BoardDetailDto boardDto) throws SQLException {
		int resultrow = 0;
		boolean notUpload = isEmpty(boardDto.getFile_path());
		String sql = notUpload ? 
							"update board set title=?,content=?,nick_name=?,board_list_id=? where id=?" :
							"update board set title=?,content=?,nick_name=?,board_list_id=?,file_path=? where id=?";
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, boardDto.getTitle());
		pstmt.setString(2, boardDto.getContent());
		pstmt.setString(3, boardDto.getNick_name());
		pstmt.setInt(4, boardDto.getBoard_list_id());
		if (notUpload) {
			pstmt.setInt(5, boardDto.getId());
		} else {
			pstmt.setString(5, boardDto.getFile_path());
			pstmt.setInt(6, boardDto.getId());
		}
		resultrow = pstmt.executeUpdate();

		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
		return resultrow;
	}

	private boolean isEmpty(String param) {
		return param == null || param.equals("");
	}
	public int ReturnReplyId() throws SQLException {
		String sql = "select max(id) as max from reply";
		Connection conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int result =0;
		if(rs.next()) {
			result= rs.getInt("max");
		}
		
		
		if(rs!=null) try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
		return result;
	}
}
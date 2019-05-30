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
import org.masterjung.dto.ReplyDto;
import org.masterjung.dto.join.BoardDetailDto;
import org.masterjung.dto.join.BoardDto2;
import org.masterjung.dto.join.BoardReplyDto;
import org.masterjung.dto.join.BoardReplyDtoIndex;
import org.masterjung.dto.join.ReplyJoinReplyVoteDto;
import org.masterjung.dto.join.UserCountDto;
import org.masterjung.util.ConvertCategoryName;
import org.masterjung.util.GetIp;
import org.masterjung.util.SqlDate;
import org.masterjung.util.StringSkip;

import com.google.gson.Gson;


public class BoardDao {
	DataSource ds;
	Connection conn;
	Context context;
	StringSkip stringSkip;
	SqlDate sqldate;
	GetIp ip;
	ConvertCategoryName convertcategoryname;
	
	public BoardDao() {
		conn = null;	
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			stringSkip = new StringSkip();
			sqldate = new SqlDate();
			ip = new GetIp();
			convertcategoryname = new ConvertCategoryName();
		} catch (NamingException e) {
			System.out.println("memodao.java -> 기본생성자 오류 : " + e.getMessage());
		}
	}
	
	//create
	public int addBoardContent(BoardDto boarddto)  {
		int resultrow=0;
		PreparedStatement pstmt = null;
		try {
		    String sql="insert into board(board_list_id, nick_name, create_ip, refer, depth, step, title, content, file_path) values(?,?,?,?,?,?,?,?,?)";
			conn = ds.getConnection();
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, boarddto.getBoard_list_id());
		    pstmt.setString(2, boarddto.getNick_name());
		    pstmt.setString(3, boarddto.getCreate_ip());
		    pstmt.setInt(4, boarddto.getRefer());
		    pstmt.setInt(5, boarddto.getDepth());
		    pstmt.setInt(6, boarddto.getStep());
		    pstmt.setString(7, boarddto.getTitle());
		    pstmt.setString(8, boarddto.getContent());
		    pstmt.setString(9, boarddto.getFile_path());
		    resultrow = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("addBoardContent() 오류발생 :" + e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} 
		}
		return resultrow;
	}
	
	//update - Modify
	public int updateBoardContent(BoardDto boarddto)  {
		int resultrow=0;
		PreparedStatement pstmt = null;
		try {
		    String sql="Update board set title=?, content=?, file_path=? where id=?";
			conn = ds.getConnection();
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, boarddto.getTitle());
		    pstmt.setString(2, boarddto.getContent());
		    pstmt.setString(3, boarddto.getFile_path());
		    pstmt.setInt(4, boarddto.getId());
		    resultrow = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("updateBoardContent() 오류발생1 :" + e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				System.out.println("updateBoardContent2 : "+e.getMessage());
			} 
		}
		return resultrow;
	}
	
	//이메일로 닉네임 찾기
	public String findNickNameByEmail(String email) {
		PreparedStatement pstmt = null;
		String nick_name = "";
		ResultSet rs = null;
		
		try {
			String sql = "select nick_name from user where email=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				nick_name=rs.getString(1);
			}else {
				nick_name="NotFind";
			}
		}catch(Exception e) {
			System.out.println("findNickNameByEmail 오류 : " +e.getMessage());

		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e1) {
				System.out.println("findNickNameByEmail 오류" + e1.getMessage());
			}
		}
		return nick_name;
	}
	
	
	//Read list
	public List<BoardDto> getBoardListById(int boardListId) {
		String sql = "select * from board where enabled=1 and board_list_id=?";
		List<BoardDto> boardList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try	{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardListId);
			rs = pstmt.executeQuery();
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
				dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 30));
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
				System.out.println("getBoardListById 오류 : " + e1.getMessage());
			}
		}
		return boardList;
	}
	//답글 갯수 세기(글번호로)
	public int replyCount(int board_id) {
		int reply_count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select nick_name from user where email=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply_count = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("replyCount에러 : " +e.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}

		}
		return reply_count;
	}
	
	//한개글만 조회 -글번호로
		public BoardDetailDto getBoardDetailById(int boardid) {
			StringBuffer sql = new StringBuffer();
			
			sql.append("select id, board_list_id, nick_name, anonymity, create_ip, refer, depth, step, date_created, last_updated, view_count, vote_count, title, content, file_path, ");
			sql.append("(select count(id) from reply where reply_id=board.id and enabled=1) count, ");
			sql.append("(select count(*) from content_vote where c_vote_id=board.id) recommend ");
			sql.append("from board ");
			sql.append("where enabled=1 and id=?");
			BoardDetailDto dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				dto = new BoardDetailDto();
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, boardid);
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
		
		//select 게시글 수정용
		public BoardDto getInfoEditById(int boardid) { 
			
			StringBuffer sql = new StringBuffer();
			sql.append("select id, title, content, file_path ");
			sql.append("from board ");
			sql.append("where enabled=1 and id=?");
			BoardDto dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				dto = new BoardDto();
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, boardid);
				rs = pstmt.executeQuery();
				if(rs.next()){
					dto.setId(rs.getInt("id"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setFile_path(rs.getString("file_path"));
				}
			}catch(Exception e) {
				System.out.println("getInfoEditById() 오류 발생1 : " + e.getMessage());
			
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
					if(conn != null) {conn.close();}
				}catch(Exception e) {
					System.out.println("getInfoEditById() 오류 발생2" + e.getMessage());
				}
			}
			return dto;
		}
	
	//한개글의 댓글과 댓글 추천수 조회 -글번호로
	public List<ReplyJoinReplyVoteDto> getReplyListById(int boardid) {
		StringBuffer sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyJoinReplyVoteDto> ReplyDtoList = new ArrayList<>();
		try {
			sql = new StringBuffer();
			sql.append("select r.id, r.reply_id, r.date_created, r.last_updated, r.reply_content, r.r_nick_name, r.vote_count, r.refer, r.depth, r.step, (select count(reply_id) from reply where reply_id=22 and enabled=1), ");
			sql.append("ifnull(rv.id,0) rv_id, ifnull(rv.vote_id, 0) rv_vote_id, ifnull(rv.reply_voter, 'null') rv_reply_voter, ifnull(rv.date_created, 0) rv_date_created, ");
			sql.append("ifnull(u.user_image_path,'null') u_user_image_path ");
			sql.append("from reply as r left join reply_vote as rv ");
			sql.append("on r.id = rv.vote_id ");
			sql.append("left join user as u	");
			sql.append("on r.r_nick_name = u.nick_name ");
			sql.append("where r.reply_id=? and r.enabled=1");
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardid);
			rs = pstmt.executeQuery();
		
			while(rs.next()){
				ReplyJoinReplyVoteDto dto = new ReplyJoinReplyVoteDto();	
				if(rs.getInt(1)!=0) {		
					dto.setId(rs.getInt(1));
					dto.setReply_id(rs.getInt(2));
					dto.setDate_created(sqldate.timestampToDate(rs.getTimestamp(3)));
					dto.setLast_updated(sqldate.timestampToDate(rs.getTimestamp(4)));
					dto.setReply_content(rs.getString(5));
					dto.setR_nick_name(rs.getString(6));
					dto.setVote_count(rs.getInt(7));
					dto.setRefer(rs.getInt(8));
					dto.setDepth(rs.getInt(9));
					dto.setStep(rs.getInt(10));
					dto.setReply_count(rs.getInt(11));
					dto.setU_user_image_path(rs.getString(16));
				}			
				if(rs.getInt(12)!=0) {
					dto.setRv_id(rs.getInt(12));
					dto.setRv_vote_id(rs.getInt(13));
					dto.setRv_reply_voter(rs.getString(14));
					dto.setRv_date_created(sqldate.timestampToDate(rs.getTimestamp(15)));
				}
				ReplyDtoList.add(dto);
			}
		}catch(Exception e) {
			System.out.println("getReplyListById() 오류 발생1" + e.getMessage());
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("getReplyListById() 오류 발생2" + e.getMessage());
			}
		}
		return ReplyDtoList;
	}
	
	public List<BoardReplyDto> getNewsListAndReplyCount(int board_list_id){
		List<BoardReplyDto> boardList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select date_created, content, vote_count, last_updated, anonymity, refer, depth, step, id,board_list_id, title, view_count, nick_name, file_path, "); 
		sql.append("(select count(id) from reply where reply_id=b.id and enabled=1) count, ");
		sql.append("(select user_image_path from user where nick_name=b.nick_name) user_image_path ");
		sql.append("from board b where board_list_id=? and enabled=1 order by id desc");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, board_list_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardReplyDto dto = new BoardReplyDto();
				dto.setDate_created(rs.getDate("date_created"));
				dto.setContent(rs.getString("content"));
				dto.setVote_count(rs.getInt("vote_count"));
				try {
	                dto.setLast_updated(rs.getDate("last_updated"));
	            }catch(Exception e1) {
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
				dto.setUser_image_path(rs.getString("user_image_path"));
				boardList.add(dto);
			}
		}catch(Exception e) {
			System.out.println("getNewsListAndReplyCount() 오류 발생1: " + e.getMessage());
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("getNewsListAndReplyCount() 오류 발생2" + e.getMessage());
			}
		}
		return boardList;
	}
	
	
	//insert 댓글
	public int createReply(ReplyDto replydto) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into reply(reply_id, reply_content, r_nick_name, refer, depth, step)");

		sql.append("values(?,?,?,?,?,?)");
		PreparedStatement pstmt = null;
		int resultrow = 0;
		
		try {
			conn = ds.getConnection();
		    pstmt = conn.prepareStatement(sql.toString());
		    pstmt.setInt(1, replydto.getReply_id());
		    pstmt.setString(2, replydto.getReply_content());
		    pstmt.setString(3, replydto.getR_nick_name());
		    pstmt.setInt(4, replydto.getRefer());
		    pstmt.setInt(5, replydto.getDepth());
		    pstmt.setInt(6, replydto.getStep());

		    resultrow = pstmt.executeUpdate();
		
		}catch(Exception e) {
			System.out.println("createReply() 오류 발생1 : " + e.getMessage());
			resultrow = -1;
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("createReply() 오류 발생2" + e.getMessage());
			}
		}
		return resultrow;
	}
	// 게시글 삭제
	public int deleteFakeBoardContent(int boardid) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "update board set enabled=0 where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardid);
			resultrow = pstmt.executeUpdate();
		}catch(Exception e) {
			resultrow = -1;
			System.out.println("deleteFakeBoardContent() 오류1 - "+e.getMessage());
		}finally {
			try {
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				System.out.println("deleteFakeBoardContent() 오류2 - "+e.getMessage());
			} 
		}
		return resultrow;
	}
	
	// 댓글 삭제
	public int deleteFakeBoardreply(int reply_id) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "update reply set enabled=0 where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply_id);
			resultrow = pstmt.executeUpdate();
		}catch(Exception e) {
			resultrow = -1;
			System.out.println("deleteFakeBoardreply() 오류1 - "+e.getMessage());
		}finally {
			try {
	
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				System.out.println("deleteFakeBoardreply() 오류2 - "+e.getMessage());
			} 
		}
		return resultrow;
	}
	
	//select 게시글 갯수 찾기
	public int getCountContent() {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) ");
		sql.append("from board ");
		sql.append("where enabled=1");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getCountContent() 오류 발생1 : " + e.getMessage());
		
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("getCountContent() 오류 발생2" + e.getMessage());
			}
		}
		return count;
	}
	//select 게시글 갯수 찾기
	public int getCountContent(int boardid) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) ");
		sql.append("from board ");
		sql.append("where board_list_id=? and enabled=1");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getCountContent() 오류 발생1 : " + e.getMessage());
		
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("getCountContent() 오류 발생2" + e.getMessage());
			}
		}
		return count;
	}
	
	//게시판 컨텐츠 select / 페이징 변수 추가
		public List<BoardReplyDto> getContentList(int board_list_id, int printStart, int printSize){
			List<BoardReplyDto> boardList = new ArrayList<>();
			StringBuffer sql = new StringBuffer();
			sql.append("select date_created, content, vote_count, last_updated, anonymity, refer, depth, step, id,board_list_id, title, view_count, nick_name, file_path, "); 
			sql.append("(select count(id) from reply where reply_id=b.id and enabled=1) count, ");
			sql.append("(select user_image_path from user where nick_name=b.nick_name) user_image_path, ");
			sql.append("(select count(*) from content_vote where c_vote_id=b.id) recommand ");
			sql.append("from board b where board_list_id=? and enabled=1 order by id desc ");
			sql.append("limit ?,?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, board_list_id);
				pstmt.setInt(2, printStart);
				pstmt.setInt(3, printSize);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardReplyDto dto = new BoardReplyDto();
					dto.setDate_created(rs.getDate("date_created"));
					dto.setContent(rs.getString("content"));
					dto.setVote_count(rs.getInt("vote_count"));
					try {
		                dto.setLast_updated(rs.getDate("last_updated"));
		            }catch(Exception e1) {
		                dto.setLast_updated(rs.getDate("date_created"));
		            }			
					dto.setAnonymity(rs.getInt("anonymity"));
					dto.setRefer(rs.getInt("refer"));
					dto.setDepth(rs.getInt("depth"));
					dto.setStep(rs.getInt("step"));
					dto.setId(rs.getInt("id"));
					dto.setBoard_list_id(rs.getInt("board_list_id"));
					dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 35));
					dto.setView_count(rs.getInt("view_count"));
					dto.setNick_name(rs.getString("nick_name"));
					dto.setFile_path(rs.getString("file_path"));
					dto.setReply_count(rs.getInt("count"));
					dto.setUser_image_path(rs.getString("user_image_path"));
					dto.setRecommand(rs.getInt("recommand"));
					boardList.add(dto);
				}
			}catch(Exception e) {
				System.out.println("getNewsListAndReplyCount() 오류 발생1: " + e.getMessage());
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
					if(conn != null) {conn.close();}
				}catch(Exception e) {
					System.out.println("getNewsListAndReplyCount() 오류 발생2" + e.getMessage());
				}
			}
			return boardList;
		}
	
	//게시판 컨텐츠 select / 페이징 변수 추가
	public List<BoardReplyDtoIndex> getContentList(int printStart, int printSize){
		List<BoardReplyDtoIndex> boardList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select date_created, content, vote_count, last_updated, anonymity, refer, depth, step, id,board_list_id, title, view_count, nick_name, file_path, "); 
		sql.append("(select count(id) from reply where reply_id=b.id and enabled=1) count, ");
		sql.append("(select user_image_path from user where nick_name=b.nick_name) user_image_path, ");
		sql.append("(select count(*) from content_vote where c_vote_id=b.id) recommand ");
		sql.append("from board b where enabled=1 order by id desc ");
		sql.append("limit ?,?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, printStart);
			pstmt.setInt(2, printSize);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BoardReplyDtoIndex dto = new BoardReplyDtoIndex();
				dto.setDate_created(rs.getDate("date_created"));
				dto.setContent(rs.getString("content"));
				dto.setVote_count(rs.getInt("vote_count"));
				try {
	                dto.setLast_updated(rs.getDate("last_updated"));
	            }catch(Exception e1) {
	                dto.setLast_updated(rs.getDate("date_created"));
	            }			
				dto.setAnonymity(rs.getInt("anonymity"));
				dto.setRefer(rs.getInt("refer"));
				dto.setDepth(rs.getInt("depth"));
				dto.setStep(rs.getInt("step"));
				dto.setId(rs.getInt("id"));
				dto.setBoard_list_id(convertcategoryname.convertCategoryName(rs.getInt("board_list_id")));
				dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 20));
				dto.setView_count(rs.getInt("view_count"));
				dto.setNick_name(rs.getString("nick_name"));
				dto.setFile_path(rs.getString("file_path"));
				dto.setReply_count(rs.getInt("count"));
				dto.setUser_image_path(rs.getString("user_image_path"));
				dto.setRecommand(rs.getInt("recommand"));
				boardList.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("getNewsListAndReplyCount() 오류 발생1: " + e.getMessage());
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("getNewsListAndReplyCount() 오류 발생2" + e.getMessage());
			}
		}
		return boardList;
	}
	
	//게시판 컨텐츠 select / 페이징 변수 추가
		public List<BoardReplyDtoIndex> getContentListOrderByReply(int board_list_id, int printStart, int printEnd){
			List<BoardReplyDtoIndex> boardList = new ArrayList<>();
			StringBuffer sql = new StringBuffer();
			sql.append("select date_created, content, vote_count, last_updated, anonymity, refer, depth, step, id,board_list_id, title, view_count, nick_name, file_path, "); 
			sql.append("(select count(id) from reply where reply_id=b.id and enabled=1) count, ");
			sql.append("(select user_image_path from user where nick_name=b.nick_name) user_image_path, ");
			sql.append("(select count(*) from content_vote where c_vote_id=b.id) recommand ");
			sql.append("from board b where board_list_id=? and enabled=1 order by count desc ");
			sql.append("limit ?,?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, board_list_id);
				pstmt.setInt(2, printStart);
				pstmt.setInt(3, printEnd);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					BoardReplyDtoIndex dto = new BoardReplyDtoIndex();
					dto.setDate_created(rs.getDate("date_created"));
					dto.setContent(rs.getString("content"));
					dto.setVote_count(rs.getInt("vote_count"));
					try {
		                dto.setLast_updated(rs.getDate("last_updated"));
		            }catch(Exception e1) {
		                dto.setLast_updated(rs.getDate("date_created"));
		            }			
					dto.setAnonymity(rs.getInt("anonymity"));
					dto.setRefer(rs.getInt("refer"));
					dto.setDepth(rs.getInt("depth"));
					dto.setStep(rs.getInt("step"));
					dto.setId(rs.getInt("id"));
					dto.setBoard_list_id(convertcategoryname.convertCategoryName(rs.getInt("board_list_id")));
					dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 15));
					dto.setView_count(rs.getInt("view_count"));
					dto.setNick_name(rs.getString("nick_name"));
					dto.setFile_path(rs.getString("file_path"));
					dto.setReply_count(rs.getInt("count"));
					dto.setUser_image_path(rs.getString("user_image_path"));
					dto.setRecommand(rs.getInt("recommand"));
					boardList.add(dto);
				}
			}catch(Exception e) {
				System.out.println("getContentListOrderByReply() 오류 발생1: " + e.getMessage());
			}finally {
				try {
					if(rs != null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
					if(conn != null) {conn.close();}
				}catch(Exception e) {
					System.out.println("getContentListOrderByReply() 오류 발생2" + e.getMessage());
				}
			}
			return boardList;
		}
	
	// 방문자 ip create
	public int createVisitor() {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into visit(visit_ip) values(?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ip.getIp());
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			resultrow = -1;
			System.out.println("createVisitor() 오류1 - "+e.getMessage());
			
		}finally {
			try {
				
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				System.out.println("createVisitor() 오류2 - "+e.getMessage());
			} 
		}
		return resultrow;
	}
	
	// view_count UPDATE
	public int updateViewCount(int boardid, int view_count) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "update board set view_count=? where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, view_count);
			pstmt.setInt(2, boardid);
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			resultrow = -1;
			System.out.println("updateViewCount() 오류1 - "+e.getMessage());
			
		}finally {
			try {

				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				System.out.println("updateViewCount() 오류2 - "+e.getMessage());
			} 
		}
		return resultrow;
	}
	
	//답글 갯수 세기(글번호로)
	public int view_Count(int board_id) {
		String sql = "select view_count from board where id=?";
		int view_Count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				view_Count = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("view_Count에러 : " +e.getMessage());
		}finally{
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("view_Count() 오류 발생2" + e.getMessage());
			}
		}
		return view_Count;
	}
	
	//추천 갯수 가져오기
	public int count_ContentVote(int boardid) {
		String sql = "select count(*) count from content_vote where c_vote_id=?";
		int vote_Count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vote_Count = rs.getInt("count");
			}
		}catch(Exception e) {
			System.out.println("count_ContentVote에러 : " +e.getMessage());
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("count_ContentVote() 오류 발생2" + e.getMessage());
			}
		}
		return vote_Count;
	}
	
//	중복 recommend 확인
	public Boolean isCheckVoter(String content_voter, int c_vote_id) {
		String sql = "select * from content_vote where content_voter=? and c_vote_id=?";
		Boolean ischeckvoter = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content_voter);
			pstmt.setInt(2, c_vote_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ischeckvoter = true;
			}else {
				ischeckvoter = false;
			}
			
		}catch(Exception e) {
			System.out.println("isCheckVoter에러 : " +e.getMessage());
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(Exception e) {
				System.out.println("isCheckVoter() 오류 발생2" + e.getMessage());
			}
		}
		return ischeckvoter;
	}
	
	// vote_count INSERT
	public int insertVoteCount(int bodrdid, String content_voter) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into content_vote(c_vote_id, content_voter) values(?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bodrdid);
			pstmt.setString(2, content_voter);
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			resultrow = -1;
			System.out.println("insertVoteCount() 오류1 - "+e.getMessage());
			
		}finally {
			try {

				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			} catch (SQLException e) {
				System.out.println("insertVoteCount() 오류2 - "+e.getMessage());
			} 
		}
		return resultrow;
	}
	
	//Read list
	public List<BoardDto2> getBoardListAll(int limit) {
		String sql = "select * from board where enabled=1 order by date_created desc limit ?";
		List<BoardDto2> boardList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try	{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto2 dto = new BoardDto2();
				dto.setId(rs.getInt("id"));
				dto.setBoard_list_id(convertcategoryname.convertCategoryName(rs.getInt("board_list_id")));
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
				dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 13));
				dto.setContent(rs.getString("content"));
				dto.setFile_path(rs.getString("file_path"));
				boardList.add(dto);
			}
		}catch(Exception e) {
			System.out.println("getBoardListAll() 오류발생1 : " + e.getMessage());
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e1) {
				System.out.println("getBoardListAll 오류 : " + e1.getMessage());
			}
		}
		return boardList;
	}
	
	//Read list
		public List<BoardDto2> getBoardListAllByView(int limit) {
			String sql = "select * from board where enabled=1 order by view_count desc limit ?";
			List<BoardDto2> boardList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try	{
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, limit);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDto2 dto = new BoardDto2();
					dto.setId(rs.getInt("id"));
					dto.setBoard_list_id(convertcategoryname.convertCategoryName(rs.getInt("board_list_id")));
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
					dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 11));
					dto.setContent(rs.getString("content"));
					dto.setFile_path(rs.getString("file_path"));
					boardList.add(dto);
				}
			}catch(Exception e) {
				System.out.println("getBoardListAll() 오류발생1 : " + e.getMessage());
			}finally {
				try {
					if(rs!=null) {rs.close();}
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				}catch(Exception e1) {
					System.out.println("getBoardListAll 오류 : " + e1.getMessage());
				}
			}
			return boardList;
		}
		
		//json
		public String getCountRegister() {
			String sql = "SELECT DATE_FORMAT(date_created, '%m/%d') AS date, count(*) AS cnt FROM user GROUP BY DATE_FORMAT(date_created, '%Y-%m-%d') ORDER BY date ASC;";
			List<UserCountDto> list = new ArrayList<>();		
			Gson gson = new Gson();
			String json="";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				try
					{
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						UserCountDto dto = new UserCountDto();
					dto.setDate(rs.getString("date"));
					dto.setCnt(rs.getInt("cnt"));
					list.add(dto);
					}
					json = gson.toJson(list);
					
			}catch(Exception e) {
				System.out.println("getBoardListAll() 오류발생1 : " + e.getMessage());
			}finally {
			try {
					if(rs!=null) {rs.close();}
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}

			
			return json;
		}
		
		//Read list
		public List<BoardDto2> getBoardMain(int boardlistid, int limit) {
			String sql = "select * from board where enabled=1 and board_list_id=? order by date_created desc limit ?";
			List<BoardDto2> boardList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try	{
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardlistid);
				pstmt.setInt(2, limit);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDto2 dto = new BoardDto2();
					dto.setId(rs.getInt("id"));
					dto.setBoard_list_id(convertcategoryname.convertCategoryName(rs.getInt("board_list_id")));
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
					dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 18));
					dto.setContent(rs.getString("content"));
					dto.setFile_path(rs.getString("file_path"));
					boardList.add(dto);
				}
			}catch(Exception e) {
				System.out.println("getBoardListAll() 오류발생1 : " + e.getMessage());
			}finally {
				try {
					if(rs!=null) {rs.close();}
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				}catch(Exception e1) {
					System.out.println("getBoardListAll 오류 : " + e1.getMessage());
				}
			}
			return boardList;
		}
		
		//Read list
		public List<BoardDto2> getBoardReplyDesc(int boardlistid, int limit) {
			String sql = "select * from board where enabled=1 and board_list_id=? order by date_created desc limit ?";
			List<BoardDto2> boardList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try	{
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardlistid);
				pstmt.setInt(2, limit);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDto2 dto = new BoardDto2();
					dto.setId(rs.getInt("id"));
					dto.setBoard_list_id(convertcategoryname.convertCategoryName(rs.getInt("board_list_id")));
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
					dto.setTitle(stringSkip.stringSkip(rs.getString("title"), 15));
					dto.setContent(rs.getString("content"));
					dto.setFile_path(rs.getString("file_path"));
					boardList.add(dto);
				}
			}catch(Exception e) {
				System.out.println("getBoardListAll() 오류발생1 : " + e.getMessage());
			}finally {
				try {
					if(rs!=null) {rs.close();}
					if(pstmt!=null) {pstmt.close();}
					if(conn!=null) {conn.close();}
				}catch(Exception e1) {
					System.out.println("getBoardListAll 오류 : " + e1.getMessage());
				}
			}
			return boardList;
		}
		
} //클래스 끝 

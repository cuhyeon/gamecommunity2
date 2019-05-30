package org.masterjung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.masterjung.dto.UserDto;
import org.masterjung.util.GetImagePath;

public class MainDao {
	DataSource ds = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MainDao() {
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	} // 생성자 끝

	// 회원 등록
	public boolean insertMember(UserDto userdto) {
		int resultrow = 0;
		String sql = "insert into user(email, password, nick_name, phone_number, user_name, user_image_path) values(?,?,?,?,?,?)";

		boolean joinOk = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userdto.getEmail());
			pstmt.setString(2, userdto.getPassword());
			pstmt.setString(3, userdto.getNick_name());
			pstmt.setInt(4, userdto.getPhone_number());
			pstmt.setString(5, userdto.getUser_name());
			pstmt.setString(6, userdto.getUser_image_path());

			resultrow = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return joinOk;
	} // 회원등록 끝

	// 회원 확인
	public int isMember(UserDto userdto) {
		int result = -1;
		String sql = "SELECT password FROM user WHERE email = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userdto.getEmail());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userdto.getPassword())) {
					result = 1; // 로그인 성공
				} else {
					result = 0; // 비밀번호 불일치
				}
			} else {
				result = -1; // ID가 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 2; // DB 오류
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return result;

	} // 회원 확인 끝

	// 회원 정보 출력
	public UserDto userDetail(String email) {
		String sql = "SELECT * FROM user WHERE email = ?";
		UserDto ud = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			rs.next();

			GetImagePath gp = new GetImagePath();

			ud = new UserDto();
			String path = gp.imagePath(rs.getString("user_image_path"));
			if (path.contains("resource/img/default-img")) {
				ud.setUser_image_path(path);
			} else {
				ud.setUser_image_path(rs.getString("user_image_path"));
			}

			ud.setUser_name(rs.getString("user_name"));
			ud.setEmail(rs.getString("email"));
			ud.setPassword(rs.getString("password"));
			ud.setNick_name(rs.getString("nick_name"));
			ud.setPhone_number(rs.getInt("phone_number"));
			ud.setUser_address(rs.getString("user_address"));
			ud.setUser_auth(rs.getInt("user_auth"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return ud;

	}// 회원 정보 출력 끝

	// 회원 정보 수정
	public int editUser(UserDto userDto, int path) {
        int rowcount = 0;
        int imagecount = 0;
        String sql = "update user set nick_name=?, phone_number=?, user_name=?,user_address=?,user_auth=? ,user_image_path=?, password=? where email=?";
        String sql2 = "update user set nick_name=?, phone_number=?, user_name=?,user_address=?,user_auth=? , password=? where email=?";
        String sql3 = "select email from user where user_image_path=?";
        String address = userDto.getUser_address();
        
        try {

            conn = ds.getConnection();

            if (path == 2) {
                
                pstmt = conn.prepareStatement(sql3);
                pstmt.setString(1, userDto.getUser_image_path());
                rs = pstmt.executeQuery();
                
                if(rs.next()) {
                    
                    pstmt = conn.prepareStatement(sql2);

                    pstmt.setString(1, userDto.getNick_name());
                    pstmt.setInt(2, userDto.getPhone_number());
                    pstmt.setString(3, userDto.getUser_name());
                    
                    if(address != "") {
                        pstmt.setString(4, userDto.getUser_address());
                    } else {
                        pstmt.setString(4, "");
                    }
                    
                    pstmt.setInt(5, userDto.getUser_auth());
                    pstmt.setString(6, userDto.getPassword());
                    pstmt.setString(7, userDto.getEmail());
                    rowcount = pstmt.executeUpdate();
                    imagecount = 1;
                } else {

                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, userDto.getNick_name());
                    pstmt.setInt(2, userDto.getPhone_number());
                    pstmt.setString(3, userDto.getUser_name());
                    if(address != "") {
                        pstmt.setString(4, userDto.getUser_address());
                    } else {
                        pstmt.setString(4, "");
                    }
                    pstmt.setInt(5, userDto.getUser_auth());
                    pstmt.setString(6, userDto.getUser_image_path());
                    pstmt.setString(7, userDto.getPassword());
                    pstmt.setString(8, userDto.getEmail());
                    rowcount = pstmt.executeUpdate();
                    imagecount = 2;
                }
                
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
                
            } else if (path ==1 ){
                pstmt = conn.prepareStatement(sql2);

                pstmt.setString(1, userDto.getNick_name());
                pstmt.setInt(2, userDto.getPhone_number());
                pstmt.setString(3, userDto.getUser_name());
                if(address != "") {
                    pstmt.setString(4, userDto.getUser_address());
                } else {
                    pstmt.setString(4, "");
                }
                pstmt.setInt(5, userDto.getUser_auth());
                pstmt.setString(6, userDto.getPassword());
                pstmt.setString(7, userDto.getEmail());
                rowcount = pstmt.executeUpdate();
                imagecount = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
        }

        return imagecount;

    }// 회원 정보 수정 끝

	// nickname 확인
	public String isCheckByNickName(String nick) {
		String isusernickname = null;

		try {

			String sql = "select id from user where nick_name=?";
			// POOL 연결 객체 얻어오기
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nick);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				isusernickname = "false";
			} else {
				isusernickname = "true";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return isusernickname;
	} // nickname 확인 끝

	// email 확인
	public String isCheckByEmail(String email) {
		String isuseremail = null;

		try {

			String sql = "select id from user where email=?";
			// POOL 연결 객체 얻어오기
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				isuseremail = "false";
			} else {
				isuseremail = "true";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return isuseremail;
	} // email 확인 끝

	// user count
	public int getTotalCount() throws SQLException {
		int total = 0;

		try {
			conn = ds.getConnection();

			String sql = "select count(*) from user";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return total;
	} // end user count

	// 비밀번호 찾기
	public int findPw(int phone, String email) {
		int rowcount = 0;
		String sql = "select * from user where email=? and Phone_number=? ";

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rowcount = 1;
			} else {
				rowcount = 0; // 비밀번호 불일치
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return rowcount;

	}// 비밀번호 찾기 끝

	// 비밀번호 수정
	public int editPw(String pwd, String email) {
		int rowcount = 0;
		String sql = "update user set  password=? where email=?";

		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pwd);
			pstmt.setString(2, email);

			rowcount = pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

		return rowcount;

	}// 비밀번호 수정 끝

} // 클래스 끝

package com.kh.semi.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;

import com.kh.semi.board.model.dto.BoardDTO;
import com.kh.semi.board.model.dto.CommentDTO;
import com.kh.semi.board.model.dto.FileDTO;
import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.member.model.dto.Member;

public class BoardDao {

	private JDBCTemplate template = JDBCTemplate.getInstance();


	public int add_file(FileDTO file, Connection conn) {
		// TODO Auto-generated method stub
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into MP_FILE(FILE_NO, BNO, BBS_TYPE, ORG_FILE_NAME, STORED_FILE_NAME, FILE_SIZE)"
						+ " values(SEQ_MP_FILE_NO.nextval,?,?,?,?,?)";
		

		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, file.getBNO());
			pstm.setString(2, file.getBBS_TYPE());
			pstm.setString(3, file.getORG_FILE_NAME());
			pstm.setString(4, file.getSTORED_FILE_NAME());
			pstm.setInt(5, file.getFILE_SIZE());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;	
	}


	public int add_board(BoardDTO board, Connection conn) {
		// TODO Auto-generated method stub
		int BNO = getBNO(conn);
		PreparedStatement pstm = null;
		
		String query = "insert into BBS(BNO, BBS_TYPE, TITLE, CONTENT, USER_CODE)"
						+ " values(?,?,?,?,?)";
		

		try {
			pstm = conn.prepareStatement(query);
			
			
			pstm.setInt(1, BNO);
			pstm.setString(2, board.getBBS_TYPE());
			pstm.setString(3, board.getTITLE());
			pstm.setString(4, board.getCONTENT());
			pstm.setString(5, board.getUSER_CODE());
			pstm.executeUpdate();
			
			return BNO;
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}
	
	public int getBNO(Connection conn) {
		// TODO Auto-generated method stub
		int BNO = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select SEQ_BNO.nextval AS BNO FROM DUAL";
		
		try {
			pstm = conn.prepareStatement(query);

			rset = pstm.executeQuery();
			
			if(rset.next()) {
				BNO = rset.getInt("BNO");
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return BNO;
	}


	public List<BoardDTO> getBoardList(String BBS_TYPE, int START_ROW, int END_ROW, Connection conn) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM "
				+ "(SELECT rownum RN, T.* FROM  "
				+ "(SELECT BNO,BBS_TYPE,TITLE,CONTENT,TO_CHAR(REGDATE,'yyyy/MM/dd HH24:mi:ss') AS REGDATE, B.USER_CODE, M.ID,"
				+ "M.NAME || '(' || RPAD(SUBSTR(M.id,0,3),LENGTH(M.id), '*') || ')'  AS NAME  "
				+ "FROM BBS B "
				+ "LEFT JOIN MEMBER M ON B.USER_CODE = M.USER_CODE WHERE B.BBS_TYPE = ? ORDER BY REGDATE DESC ) T "
				+ ") WHERE rn BETWEEN ? AND ?";
		List<BoardDTO> list = null;
		try {
			pstm = conn.prepareStatement(query);

			pstm.setString(1, BBS_TYPE);
			pstm.setInt(2, START_ROW);
			pstm.setInt(3, END_ROW);
			
			rs = pstm.executeQuery();
			if (rs.next()) { // 데이터베이스에 데이터가 있으면 실행
				list = new ArrayList<>(); // list 객체 생성
				do {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					BoardDTO board = new BoardDTO();
					board.setRN(rs.getInt("RN"));
					board.setBNO(rs.getInt("BNO"));
					board.setBBS_TYPE(rs.getString("BBS_TYPE"));
					board.setTITLE(rs.getString("TITLE"));
					board.setCONTENT(rs.getString("CONTENT"));
					board.setREGDATE(rs.getString("REGDATE"));
					board.setUSER_CODE(rs.getString("USER_CODE"));
					board.setID(rs.getString("ID"));
					board.setNAME(rs.getString("NAME"));

					list.add(board); 
				} while (rs.next());
			}


			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rs, pstm);
		}
		return list; // list 반환

	}


	public int getBoardCount(String BBS_TYPE, Connection conn) {
		// TODO Auto-generated method stub
		int COUNT = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select COUNT(*) BBS FROM BBS WHERE BBS_TYPE = ?";
		
		try {
			
			pstm = conn.prepareStatement(query);

			pstm.setString(1, BBS_TYPE);

			rset = pstm.executeQuery();
			
			if(rset.next()) {
				COUNT = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return COUNT;
	}


	public BoardDTO getBoard(String BBS_TYPE, int BNO, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM "
				+ "(SELECT rownum RN, T.* FROM  "
				+ "(SELECT BNO,BBS_TYPE,TITLE,CONTENT,TO_CHAR(REGDATE,'yyyy/MM/dd HH24:mi:ss') AS REGDATE, B.USER_CODE, M.ID,"
				+ "M.NAME || '(' || RPAD(SUBSTR(M.id,0,3),LENGTH(M.id), '*') || ')'  AS NAME  "
				+ "FROM BBS B "
				+ "LEFT JOIN MEMBER M ON B.USER_CODE = M.USER_CODE WHERE B.BBS_TYPE = ? ORDER BY REGDATE DESC ) T "
				+ ") WHERE BNO = ?";
		BoardDTO vo = null;
		try {
			pstm = conn.prepareStatement(query);

			pstm.setString(1, BBS_TYPE);
			pstm.setInt(2, BNO);
			
			rs = pstm.executeQuery();
			if (rs.next()) { // 데이터베이스에 데이터가 있으면 실행
				vo = new BoardDTO(); //  객체 생성
				
				vo.setRN(rs.getInt("RN"));
				vo.setBNO(rs.getInt("BNO"));
				vo.setBBS_TYPE(rs.getString("BBS_TYPE"));
				vo.setTITLE(rs.getString("TITLE"));
				vo.setCONTENT(rs.getString("CONTENT"));
				vo.setREGDATE(rs.getString("REGDATE"));
				vo.setUSER_CODE(rs.getString("USER_CODE"));
				vo.setID(rs.getString("ID"));
				vo.setNAME(rs.getString("NAME"));
			}


			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rs, pstm);
		}
		return vo; // list 반환
	}


	public FileDTO getFile(int FILE_NO, Connection conn) { 
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MP_FILE WHERE FILE_NO = ?";
		FileDTO vo = null;
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, FILE_NO);
	
			rs = pstm.executeQuery();
			if (rs.next()) { // 데이터베이스에 데이터가 있으면 실행
				vo = new FileDTO(); //  객체 생성
				
				vo.setFILE_NO(rs.getInt("FILE_NO"));
				vo.setBNO(rs.getInt("BNO"));
				vo.setBBS_TYPE(rs.getString("BBS_TYPE"));
				vo.setORG_FILE_NAME(rs.getString("ORG_FILE_NAME"));
				vo.setSTORED_FILE_NAME(rs.getString("STORED_FILE_NAME"));
				vo.setFILE_SIZE(rs.getInt("FILE_SIZE"));
				vo.setREGDATE(rs.getString("REGDATE"));
	
			}					
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rs, pstm);
		}
		return vo; // list 반환
	}


	public List<FileDTO> getFileList(int BNO, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MP_FILE WHERE BNO = ?";

		List<FileDTO> list = null;
		try {
			pstm = conn.prepareStatement(query);

			pstm.setInt(1, BNO);
			
			rs = pstm.executeQuery();
			
			
			
			if (rs.next()) { // 데이터베이스에 데이터가 있으면 실행 
				list = new ArrayList<>(); // list 객체 생성
				do {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					FileDTO vo = new FileDTO();
					
					vo.setFILE_NO(rs.getInt("FILE_NO"));
					vo.setBNO(rs.getInt("BNO"));
					vo.setBBS_TYPE(rs.getString("BBS_TYPE"));
					vo.setORG_FILE_NAME(rs.getString("ORG_FILE_NAME"));
					vo.setSTORED_FILE_NAME(rs.getString("STORED_FILE_NAME"));
					vo.setFILE_SIZE(rs.getInt("FILE_SIZE"));
					vo.setREGDATE(rs.getString("REGDATE"));

					list.add(vo); 
				} while (rs.next());
			}

			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rs, pstm);
		}
		return list; // list 반환
	}



	

	public int delete_board(int BNO, Connection conn) {
		// TODO Auto-generated method stub

		PreparedStatement pstm = null;
		
		String query = "DELETE FROM BBS WHERE BNO = ?";
		

		try {
			pstm = conn.prepareStatement(query);
			
			
			pstm.setInt(1, BNO);

			int rs = pstm.executeUpdate();
			
			return rs;
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}


	public int update_board(int BNO, String TITLE, String CONTENT, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		
		String query = "UPDATE BBS SET TITLE = ?, CONTENT = ? WHERE BNO = ?";
		

		try {
			pstm = conn.prepareStatement(query);
			
			
			
			pstm.setString(1, TITLE);
			pstm.setString(2, CONTENT);
			pstm.setInt(3, BNO);
			
			int rs = pstm.executeUpdate();
			
			return rs;
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}


	public List<CommentDTO> getCommentList(int BNO, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT BNO"
				+ "       ,LEVEL COMMENTLEVEL"
				+ "       ,COMMENT_ID "
				+ "       ,PARENT_COMMENT_ID "
				+ "       ,CONTENT"
				+ "       ,USER_CODE"
				+ "       ,DELETE_AT"
				+ "       ,TO_CHAR(REGDATE,'yyyy/MM/dd HH24:mi:ss') AS  REGDATE "
				+ "       ,NAME || '(' || RPAD(SUBSTR(id,0,3),LENGTH(id), '*') || ')'  AS NAME "
				+ "FROM (SELECT A.*,M.id,M.NAME "
				+ "      FROM BBS_COMMENT A "
				+ "      LEFT JOIN MEMBER M ON A.USER_CODE = M.USER_CODE "
				+ "      WHERE BNO = ?"
				+ "      ) "
				+ "START WITH  PARENT_COMMENT_ID =0"
				+ "CONNECT BY PRIOR COMMENT_ID = PARENT_COMMENT_ID "
				+ "ORDER SIBLINGS BY COMMENT_ID ";

		List<CommentDTO> list = null;
		try {
			pstm = conn.prepareStatement(query);

			pstm.setInt(1, BNO);
			
			rs = pstm.executeQuery();
			
			
			
			if (rs.next()) { // 데이터베이스에 데이터가 있으면 실행 
				list = new ArrayList<>(); // list 객체 생성
				do {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					CommentDTO vo = new CommentDTO();
					
					vo.setBNO(rs.getInt("BNO"));
					
					vo.setCOMMENTLEVEL(rs.getInt("COMMENTLEVEL"));
					vo.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
					vo.setPARENT_COMMENT_ID(rs.getInt("PARENT_COMMENT_ID"));
					vo.setCONTENT(rs.getString("CONTENT"));
					vo.setUSER_CODE(rs.getString("USER_CODE"));
					vo.setNAME(rs.getString("NAME"));
					vo.setDELETE_AT(rs.getString("DELETE_AT"));
					vo.setREGDATE(rs.getString("REGDATE"));

					list.add(vo); 
				} while (rs.next());
			}

			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rs, pstm);
		}
		return list; // list 반환
	}


	public int add_board_comment(int bNO, int pARENT_COMMENT_ID, String uSER_CODE,String CONTENT, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		
		String query = "INSERT INTO BBS_COMMENT(BNO, COMMENT_ID, PARENT_COMMENT_ID, CONTENT, USER_CODE, DELETE_AT) "
				+ "VALUES(?,SEQ_COMMENT_ID.nextval,?,?,?,'N')";
		

		try {
			pstm = conn.prepareStatement(query);
			
			
			
			pstm.setInt(1, bNO);
			pstm.setInt(2, pARENT_COMMENT_ID);
			pstm.setString(3, CONTENT);
			pstm.setString(4, uSER_CODE);

			
			int rs = pstm.executeUpdate();
			
			return rs;
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}


	public int delete_board_comment(int COMMENT_ID, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		
		String query = "UPDATE BBS_COMMENT SET DELETE_AT = 'Y' "
				+ "WHERE COMMENT_ID = ?"; 
		

		try {
			pstm = conn.prepareStatement(query);
			
			
			
			pstm.setInt(1, COMMENT_ID);

			System.out.println(COMMENT_ID);
			int rs = pstm.executeUpdate();
			
			return rs;
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}	
	}

}

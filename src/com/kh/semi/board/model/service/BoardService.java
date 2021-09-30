package com.kh.semi.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.board.model.dao.BoardDao;
import com.kh.semi.board.model.dto.BoardDTO;
import com.kh.semi.board.model.dto.CommentDTO;
import com.kh.semi.board.model.dto.FileDTO;
import com.kh.semi.common.db.JDBCTemplate;


//Service
//어플리케이션의 비지니스 로직을 작성
//사용자의 요청을 컨트롤러로 부터 위임받아 해당 요청을 처리하기 위해 필요한 핵심적인 작업을 진행
//작업을 수행하기 위해 데이터베이스에 저장된 데이터가 필요하면 Dao에게 요청
//비지니스로직을 Service가 담당하기 때문에 Transaction관리를 Service가 담당.
//commit, rollback을 Service가 담당

//Connection객체 생성, close 처리
//commit, rollback
//SQLException 에 대한 예외처리(rollback)
public class BoardService {
	
	private BoardDao boardDao = new BoardDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	


	public int add_file(FileDTO file) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.add_file(file, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public int add_board(BoardDTO board) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.add_board(board, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public List<BoardDTO> getBoardList(String BBS_TYPE, int START_ROW, int END_ROW) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		List<BoardDTO> res = new ArrayList<BoardDTO>();
		
		try { 
			res = boardDao.getBoardList(BBS_TYPE,START_ROW,END_ROW, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public int getBoardCount(String BBS_TYPE) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.getBoardCount(BBS_TYPE, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public BoardDTO getBoard(String BBS_TYPE, int BNO) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		BoardDTO res = new BoardDTO();
		
		try { 
			res = boardDao.getBoard(BBS_TYPE,BNO, conn);
	

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public FileDTO getFile(int FILE_NO) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		FileDTO res = new FileDTO();
		
		try { 
			res = boardDao.getFile(FILE_NO, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public List<FileDTO> getFileList(int BNO) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		List<FileDTO> res = new ArrayList<FileDTO>();
		try { 
			res = boardDao.getFileList(BNO, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public int delete_board(int BNO) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.delete_board(BNO, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public int update_board(int BNO, String TITLE, String CONTENT) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.update_board(BNO,TITLE,CONTENT, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public List<CommentDTO> getCommentList(int BNO) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		List<CommentDTO> res = new ArrayList<CommentDTO>();
		try { 
			res = boardDao.getCommentList(BNO, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public int add_board_comment(int BNO, int PARENT_COMMENT_ID, String USER_CODE,String CONTENT) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.add_board_comment(BNO,PARENT_COMMENT_ID,USER_CODE,CONTENT, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}



	public int delete_board_comment(int COMMENT_ID) {
		// TODO Auto-generated method stub
		Connection conn = template.getConnection();
		int res =0;
		
		try {
			res = boardDao.delete_board_comment(COMMENT_ID, conn);

			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	




	
	
	
	
}
package com.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.dto.BoardDTO;
import com.kh.semi.board.model.dto.CommentDTO;
import com.kh.semi.board.model.dto.FileDTO;
import com.kh.semi.board.model.service.BoardService;
import com.kh.semi.common.exception.PageNotFoundException;

@WebServlet("/board/*")
public class BoardController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private BoardService boardService = new BoardService();
	
	int pageSize = 10; // 한 페이지에 출력할 레코드 수
	int pageGroupSize = 3; //한 페이지에 보여줄 블럭 번호
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "board": // 게시판 리스트. 페이징 처리
			board(request,response);
			break;
		case "write_board": // 게시판 등록,수정 페이지
			writeBoard(request,response);
			break;
		case "view_board": //게시판 확인 페이지
			viewBoard(request,response);
			break;
		case "add_board_comment": //댓글 추가
			addBoardComment(request,response);
			break;
		case "delete_board_comment": // 게시판 삭제
			deleteBoardComment(request,response);
			break;
		case "delete_board": // 게시판 삭제
			deleteBoard(request,response);
			break;
		case "update_board": // 게시판 수정
			updateBoard(request,response);
			break;
		default: throw new PageNotFoundException();
		}
	}
	
	// 게시판 리스트. 페이징 처리
	private void board(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String BBS_TYPE = request.getParameter("BBS_TYPE");

		String STR_CURRENT_ROW = request.getParameter("CURRENT_ROW");
		
		int START_ROW = 0;
		int END_ROW = 0;
		int CURRENT_ROW = 0;
		
		if(STR_CURRENT_ROW == null) {
			CURRENT_ROW = 1;
		}else{
			CURRENT_ROW = Integer.parseInt(STR_CURRENT_ROW);
		}
		
		START_ROW = (CURRENT_ROW - 1) * pageSize + 1;
		END_ROW = CURRENT_ROW * pageSize;

		
		request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
		
		List<BoardDTO> list = boardService.getBoardList(BBS_TYPE,START_ROW,END_ROW);
		int total = boardService.getBoardCount(BBS_TYPE);
		
		int pageGroupCount = total/(pageSize*pageGroupSize)+( total % (pageSize*pageGroupSize) == 0 ? 0 : 1);
        //페이지 그룹 번호 
        int numPageGroup = (int) Math.ceil((double)CURRENT_ROW/pageGroupSize);
        
		request.setAttribute("list", list);
		request.setAttribute("total", total);
		
		request.setAttribute("pageGroupCount",pageGroupCount);
		request.setAttribute("numPageGroup",numPageGroup);
		request.setAttribute("pageGroupSize",pageGroupSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("CURRENT_ROW", CURRENT_ROW);
		request.setAttribute("START_ROW", START_ROW);
		request.setAttribute("END_ROW", END_ROW);
		
		request.getRequestDispatcher("/board/board").forward(request, response);		
	}
	
	// 게시판 등록,수정 페이지
	private void writeBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String type = request.getParameter("type");
		
		if(type != null) { // 수정일경우에는 데이터 가져오기.
			
			BoardDTO vo = boardService.getBoard(request.getParameter("BBS_TYPE"),Integer.parseInt(request.getParameter("BNO")));
			request.setAttribute("vo", vo);
		}else {
			type = "insert";
		}
		
		request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
		request.setAttribute("type", type);
		
		request.getRequestDispatcher("/board/write_board").forward(request, response);
	}
	
	//게시판 확인 페이지
	private void viewBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		BoardDTO vo = boardService.getBoard(request.getParameter("BBS_TYPE"),Integer.parseInt(request.getParameter("BNO")));
		
		List<FileDTO> fileList = boardService.getFileList(Integer.parseInt(request.getParameter("BNO")));
		
		List<CommentDTO> commentList = boardService.getCommentList(Integer.parseInt(request.getParameter("BNO")));
		request.setAttribute("vo", vo);
		request.setAttribute("fileList", fileList);
		request.setAttribute("commentList", commentList);
		request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
		
		request.getRequestDispatcher("/board/view_board").forward(request, response);
	}
	
	//댓글 추가
	private void addBoardComment(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		int BNO = Integer.parseInt(request.getParameter("BNO"));
		int PARENT_COMMENT_ID = Integer.parseInt(request.getParameter("PARENT_COMMENT_ID"));
		String USER_CODE = request.getParameter("USER_CODE");
		String COMMENT_CONTENT = request.getParameter("CONTENT");

		
		boardService.add_board_comment(BNO,PARENT_COMMENT_ID,USER_CODE,COMMENT_CONTENT); 

		request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
		request.setAttribute("BNO", request.getParameter("BNO"));
		
		response.sendRedirect("/board/view_board?BBS_TYPE="+request.getParameter("BBS_TYPE")+"&BNO="+request.getParameter("BNO"));
		
		//request.getRequestDispatcher("/board/view_board").forward(request, response);
	}
	
	// 게시판 삭제
	private void deleteBoardComment(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		boardService.delete_board_comment(Integer.parseInt(request.getParameter("COMMENT_ID")));
		
		
		response.sendRedirect("/board/view_board?BBS_TYPE="+request.getParameter("BBS_TYPE")+"&BNO="+request.getParameter("BNO"));
		//request.getRequestDispatcher("/board/board").forward(request, response);
	}
	
	// 게시판 삭제
	private void deleteBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		boardService.delete_board(Integer.parseInt(request.getParameter("BNO")));
		
		request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
		response.sendRedirect("/board/board?BBS_TYPE="+request.getParameter("BBS_TYPE"));
		//request.getRequestDispatcher("/board/board").forward(request, response);
	}
	
	// 게시판 수정
	private void updateBoard(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		
		boardService.update_board(Integer.parseInt(request.getParameter("BNO")),TITLE,CONTENT);
		
		request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
		
		response.sendRedirect("/board/board?BBS_TYPE="+request.getParameter("BBS_TYPE"));
		//request.getRequestDispatcher("/board/board").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	
	
}
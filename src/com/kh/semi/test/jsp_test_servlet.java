package com.kh.semi.test;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.board.model.dto.BoardDTO;
import com.kh.semi.board.model.dto.CommentDTO;
import com.kh.semi.board.model.dto.FileDTO;
import com.kh.semi.board.model.service.BoardService;

@WebServlet("/test/*")
public class jsp_test_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jsp_test_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private BoardService boardService = new BoardService();
	
	int pageSize = 10; // 한 페이지에 출력할 레코드 수
	int pageGroupSize = 3; //한 페이지에 보여줄 블럭 번호

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "join":
			request.getRequestDispatcher("/member/page-joinForm").forward(request, response);
			break;
		case "checkId":
			request.getRequestDispatcher("/member/checkId").forward(request, response);
			break;
		case "checkpassword":
			request.getRequestDispatcher("/member/checkpassword").forward(request, response);
			break;
		case "findId":
			request.getRequestDispatcher("/member/findId").forward(request, response);
			break;
		case "findPassword":
			request.getRequestDispatcher("/member/findPassword").forward(request, response);
			break;
		case "memberinfo":
			request.getRequestDispatcher("/member/page-memberInfo").forward(request, response);
			break;
		case "schedule":
			request.getRequestDispatcher("/schedule/schedule_main").forward(request, response);
			break;
		case "searchHos":
			request.getRequestDispatcher("/hospital/searchHospital").forward(request, response);
			break;
		case "searchPharm":
			request.getRequestDispatcher("/pharmacy/pharmacy").forward(request, response);
			break;
		case "searchMedi":
			request.getRequestDispatcher("/medicine/medicine").forward(request, response);
			break;
		case "board": // 게시판 리스트. 페이징 처리
			
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
			break;
		case "write_board": // 게시판 등록,수정 페이지
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
			break;
		case "view_board": //게시판 확인 페이지
			BoardDTO vo = boardService.getBoard(request.getParameter("BBS_TYPE"),Integer.parseInt(request.getParameter("BNO")));
			
			List<FileDTO> fileList = boardService.getFileList(Integer.parseInt(request.getParameter("BNO")));
			
			List<CommentDTO> commentList = boardService.getCommentList(Integer.parseInt(request.getParameter("BNO")));
			request.setAttribute("vo", vo);
			request.setAttribute("fileList", fileList);
			request.setAttribute("commentList", commentList);
			request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
			
			request.getRequestDispatcher("/board/view_board").forward(request, response);
			break;
			
		case "add_board_comment": //댓글 추가
			
			int BNO = Integer.parseInt(request.getParameter("BNO"));
			int PARENT_COMMENT_ID = Integer.parseInt(request.getParameter("PARENT_COMMENT_ID"));
			String USER_CODE = request.getParameter("USER_CODE");
			String COMMENT_CONTENT = request.getParameter("CONTENT");

			
			boardService.add_board_comment(BNO,PARENT_COMMENT_ID,USER_CODE,COMMENT_CONTENT); 

			request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
			request.setAttribute("BNO", request.getParameter("BNO"));
			
			response.sendRedirect("/test/view_board?BBS_TYPE="+request.getParameter("BBS_TYPE")+"&BNO="+request.getParameter("BNO"));
			
			//request.getRequestDispatcher("/board/view_board").forward(request, response);
			break;
		case "delete_board_comment": // 게시판 삭제
			
			boardService.delete_board_comment(Integer.parseInt(request.getParameter("COMMENT_ID")));
			
		
			response.sendRedirect("/test/view_board?BBS_TYPE="+request.getParameter("BBS_TYPE")+"&BNO="+request.getParameter("BNO"));
			//request.getRequestDispatcher("/board/board").forward(request, response);
			break;
			
		case "delete_board": // 게시판 삭제
			
			boardService.delete_board(Integer.parseInt(request.getParameter("BNO")));
			
			request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
			response.sendRedirect("/test/board?BBS_TYPE="+request.getParameter("BBS_TYPE"));
			//request.getRequestDispatcher("/board/board").forward(request, response);
			break;
			
		case "update_board": // 게시판 수정
			
			String TITLE = request.getParameter("TITLE");
			String CONTENT = request.getParameter("CONTENT");
			
			boardService.update_board(Integer.parseInt(request.getParameter("BNO")),TITLE,CONTENT);
			
			request.setAttribute("BBS_TYPE", request.getParameter("BBS_TYPE"));
			
			response.sendRedirect("/test/board?BBS_TYPE="+request.getParameter("BBS_TYPE"));
			//request.getRequestDispatcher("/board/board").forward(request, response);
			break;
		case "loginPage":
			request.getRequestDispatcher("/member/loginPage").forward(request, response);
			break;
		case "joinPage":
			request.getRequestDispatcher("/member/joinPage").forward(request, response);
			break;
			
		default: response.setStatus(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

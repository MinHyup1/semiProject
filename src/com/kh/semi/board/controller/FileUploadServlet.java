package com.kh.semi.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.semi.board.model.dto.BoardDTO;
import com.kh.semi.board.model.dto.FileDTO;
import com.kh.semi.board.model.service.BoardService;

@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private BoardService boardService = new BoardService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {

			// 디스크상의 프로젝트 실제 경로얻기
			String contextRootPath = this.getServletContext().getRealPath("/");
			// 1. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
			DiskFileItemFactory diskFactory = new DiskFileItemFactory(); // 디스크 파일 아이템 공장
			diskFactory.setSizeThreshold(4096); // 업로드시 사용할 임시 메모리
			diskFactory.setRepository(new File(contextRootPath + "/WEB-INF/temp")); // 임시저장폴더

			// 2. 업로드 요청을 처리하는 ServletFileUpload생성
			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			upload.setSizeMax(3 * 1024 * 1024); // 3MB : 전체 최대 업로드 파일 크기
			@SuppressWarnings("unchecked")
			// 3. 업로드 요청파싱해서 FileItem 목록구함​​
			List<FileItem> items = upload.parseRequest(request);

			String BBS_TYPE = "";
	
			
			String TITLE = "";
			String CONTENT = "";
			String USER_CODE = "";
			
			Iterator iter = items.iterator(); // 반복자(Iterator)로 받기
			
			Iterator iter2 = items.iterator(); //반복자(Iterator)로 받기​            
            while(iter.hasNext()) { //반목문으로 처리​    
                FileItem item = (FileItem) iter.next(); //아이템 얻기
                 //4. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리
                if(item.isFormField()){ //파일이 아닌경우
                	if("BBS_TYPE".equals(item.getFieldName())) {
                		BBS_TYPE =  item.getString("UTF-8");
                	}else if("USER_CODE".equals(item.getFieldName())) {
                		USER_CODE = item.getString("UTF-8");
                	}else if("CONTENT".equals(item.getFieldName())) {
                		CONTENT = item.getString("UTF-8");
                	}else if("TITLE".equals(item.getFieldName())) {
                		TITLE = item.getString("UTF-8");
                	}
                } 
            }
            
            BoardDTO board = new BoardDTO();
            board.setBBS_TYPE(BBS_TYPE);
            board.setTITLE(TITLE);
            board.setCONTENT(CONTENT);
            board.setUSER_CODE(USER_CODE);
            
            int BNO = boardService.add_board(board);
            
			while (iter2.hasNext()) { // 반목문으로 처리​
				FileItem item = (FileItem) iter2.next(); // 아이템 얻기
				// 4. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리
				
				 if(!item.isFormField()){ //파일인 경우
					 processUploadFile(BNO,BBS_TYPE,out, item, contextRootPath);
				 }
				
				
				
			}
			response.sendRedirect("/test/board?BBS_TYPE="+BBS_TYPE);
		} catch (Exception e) {
			out.println("<PRE>");
			e.printStackTrace(out);
			out.println("</PRE>");
		}

		out.println("</BODY></HTML>");
	}

	// 업로드한 정보가 파일인경우 처리
	private void processUploadFile(int BNO,String BBS_TYPE,PrintWriter out, FileItem item, String contextRootPath) throws Exception {
		String name = item.getFieldName(); // 파일의 필드 이름 얻기
		String fileName = item.getName(); // 파일명 얻기
		String contentType = item.getContentType();// 컨텐츠 타입 얻기
		long fileSize = item.getSize(); // 파일의 크기 얻기
		
		if(!fileName.isEmpty()) {
			// 업로드 파일명을 현재시간으로 변경후 저장
			String fileExt = fileName.substring(fileName.lastIndexOf("."));
			String uploadedFileName = System.currentTimeMillis() + fileExt;
			System.out.println(fileExt);
			System.out.println(uploadedFileName);

			// 저장할 절대 경로로 파일 객체 생성
			File uploadedFile = new File(contextRootPath + "/WEB-INF/upload/" + uploadedFileName);
			item.write(uploadedFile); // 파일 저장

			// ========== 뷰단에 출력 =========//
			out.println("<P>");
			out.println("파라미터 이름:" + name + "<BR>");
			out.println("파일 이름:" + fileName + "<BR>");
			out.println("콘텐츠 타입:" + contentType + "<BR>");
			out.println("파일 사이즈:" + fileSize + "<BR>");
			// 확장자가 이미지인겨우 이미지 출력
			if (".jpg.jpeg.bmp.png.gif".contains(fileExt.toLowerCase())) {
				out.println("<IMG SRC='upload/" + uploadedFileName + "' width='300'><BR>");
			}
			out.println("</P>");
			out.println("<HR>");
			out.println("실제저장경로 : " + uploadedFile.getPath() + "<BR>");
			out.println("<HR>");
			
			FileDTO file = new FileDTO();
			file.setBBS_TYPE(BBS_TYPE);
			file.setORG_FILE_NAME(item.getName());
			file.setSTORED_FILE_NAME(uploadedFileName);
			file.setFILE_SIZE(Long.valueOf(fileSize).intValue());
			file.setBNO(BNO);
			boardService.add_file(file);
		}
		
		
	}



}

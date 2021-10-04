package com.kh.semi.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.kh.semi.board.model.dto.FileDTO;
import com.kh.semi.board.model.service.BoardService;

@WebServlet("/FileDownServlet")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileDTO vo = boardService.getFile(Integer.parseInt(request.getParameter("FILE_NO")));

		String orgname = vo.getORG_FILE_NAME();
        String newname = vo.getSTORED_FILE_NAME();

        // MIME Type 을 application/octet-stream 타입으로 변경

        // 무조건 팝업(다운로드창)이 뜨게 된다.
        response.setContentType("application/octet-stream");

        // 브라우저는 ISO-8859-1을 인식하기 때문에

        // UTF-8 -> ISO-8859-1로 디코딩, 인코딩 한다
        orgname = new String(orgname.getBytes("UTF-8"), "iso-8859-1");

        // 파일명 지정
        response.setHeader("Content-Disposition", "attachment; filename=\""+orgname+"\"");


        OutputStream os = response.getOutputStream();
        // String path = servletContext.getRealPath("/resources");
        // c:/upload 폴더를 생성한다.
        // server에 clean을 하면 resources 경로의 것이 다 지워지기 때문에
        // 다른 경로로 잡는다(실제 서버에서는 위의 방식으로)

        String path = this.getServletContext().getRealPath("/") + "/WEB-INF/upload/" ;
        

        FileInputStream fis = new FileInputStream(path + File.separator + newname);

        int n = 0;

        byte[] b = new byte[512];

        while((n = fis.read(b)) != -1 ) {
            os.write(b, 0, n);
        }
        fis.close();
        os.close();


	}

	

}

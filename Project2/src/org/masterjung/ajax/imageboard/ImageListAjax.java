package org.masterjung.ajax.imageboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.masterjung.dao.BoardDao;
import org.masterjung.dto.join.BoardReplyDto;
import org.masterjung.util.IbPagination;


@WebServlet("/imagelist.ibajax")
public class ImageListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageListAjax() {super();}


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
		int totalPage=0;
		int startPageBlock=0;
		int endPageBlock=0;
		BoardDao dao = new BoardDao();
		int totalListCount = dao.getCountContent(4);
		List<BoardReplyDto> resultList = null;
		String curStr = request.getParameter("page");
		int cur = Integer.parseInt(curStr);
		IbPagination.pageSize =9;
		IbPagination.curPage = cur;

		totalPage = IbPagination.totalPage(totalListCount, IbPagination.pageSize);
		startPageBlock = IbPagination.startPageBlock(IbPagination.curPage, IbPagination.pageSize);
		endPageBlock = IbPagination.endPageBlock(startPageBlock, IbPagination.pageSize, totalPage);
		
		
		int printStart = IbPagination.printStart(IbPagination.curPage, IbPagination.pageSize);
		int printEnd = IbPagination.printEnd(IbPagination.curPage, IbPagination.pageSize);
		if(printEnd>totalListCount) {
			printEnd=totalListCount;
		}
		resultList = dao.getContentList(4, printStart, IbPagination.pageSize);
		request.setAttribute("curPage", IbPagination.curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPageBlock", startPageBlock);
		request.setAttribute("endPageBlock", endPageBlock);
		
		request.setAttribute("resultList", resultList);

    	
    	PrintWriter out = response.getWriter();
    	StringBuffer buffer = new StringBuffer();
    	
    	for(int i=0; i<resultList.size();i++) {
        	buffer.append("<figure><a href='imagedetail.ib?id="+resultList.get(i).getId()+"'>");
        	buffer.append("<img src='"+resultList.get(i).getFile_path()+"' id='image' alt=' '/></a>");
        	buffer.append("<figcaption><div style='padding-bottom: 5px;'><font size='3' color='balck'>");
        	buffer.append("<a href='imagedetail.ib?id="+resultList.get(i).getId()+"'><b>"+resultList.get(i).getTitle()+"</b></a><br></font>");
        	buffer.append("<font size='1' color='lightgray'>");
        	buffer.append("<i class='fas fa-user-edit'></i>&nbsp;"+resultList.get(i).getNick_name()+"&nbsp;&nbsp;&nbsp;");
        	buffer.append("<i class='far fa-clock'></i>&nbsp;"+resultList.get(i).getDate_created()+"&nbsp;&nbsp;&nbsp;");
        	buffer.append("<i class='far fa-eye'></i>&nbsp;"+resultList.get(i).getView_count()+"&nbsp;&nbsp;&nbsp;");
        	buffer.append("</font><br></div>");
        	buffer.append("<div class='txt_line'><font size='2' color='gray'>"+resultList.get(i).getContent()+"</font>");
        	buffer.append("</div></figcaption></figure>");
    	}
    	if(resultList.size()<9) {
    		buffer.append("<script>$('#temp').remove(); alert('마지막 페이지입니다.');$('#image_pagination').remove(); </script>");
    	}
    	
    	out.print(buffer.toString());
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

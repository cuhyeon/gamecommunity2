package org.masterjung.service.videoboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.masterjung.action.Action;
import org.masterjung.action.Actionforward;
import org.masterjung.dao.BoardDao;
import org.masterjung.dao.newsboard.NewsDAO;
import org.masterjung.dto.BoardDto;
import org.masterjung.dto.join.BoardDetailDto;

public class VideoBoardEditOkAction implements Action {

	@Override
	public Actionforward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		BoardDao boardDao = new BoardDao();
		BoardDetailDto boardDto = new BoardDetailDto();
		boardDto = boardDao.getBoardDetailById(Integer.parseInt(request.getParameter("id")));

		Actionforward forward = null;
		String realFileName = "";
		String fileName2 = "";
		String fileName = "";
		String fid = request.getParameter("id"); 
		int id = Integer.parseInt(fid);

		try {
			forward = new Actionforward();
			NewsDAO dao = new NewsDAO();

			boardDto.setBoard_list_id(5);
			boardDto.setId(id);
			boardDto.setNick_name(request.getParameter("nick_name"));
			boardDto.setTitle(request.getParameter("title"));
			boardDto.setContent(request.getParameter("editor1"));

			if (realFileName != null && !realFileName.equals("")) {
				boardDto.setFile_path(fileName2);
			}
			int result = dao.UpdateBoard(boardDto);

			if (result > 0) {
			} else {
			}
			forward.setPath("videoread.vb?id="+id);

		} catch (Exception e) {
			forward.setRedirect(false);
			System.out.println(e.getMessage());
		}


		return forward;

	}

}

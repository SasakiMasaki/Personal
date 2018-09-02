package myec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SearchIndexBeans;
import dao.ItemDao;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SearchIndexBeans indexs = (SearchIndexBeans)Controllor.getSessionAttribute(session, "keyword");

		if(indexs == null) {
			indexs = new SearchIndexBeans();
			request.getRequestDispatcher(Controllor.SEARCH_RESULT_PAGE).forward(request, response);
			return;
		}

		indexs.setResultNum(ItemDao.getNumberOfResult(indexs.getKeyword()));

		try {
			request.setAttribute("itemList", ItemDao.getItemListResultByKeyword(indexs));
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("indexs", indexs);
		request.getRequestDispatcher(Controllor.SEARCH_RESULT_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SearchIndexBeans indexs = new SearchIndexBeans();
		indexs.setKeyword((String)request.getParameter("keyword"));
		if(request.getParameter("index") != null) {
			indexs.setIndex(Integer.parseInt(request.getParameter("index")));
		}
		session.setAttribute("indexs", indexs);
		String redirect = "SearchResult";

		if(request.getParameter("detail") != null) {
			session.setAttribute("item_id", request.getParameter("detail"));
			redirect = "ItemDetail";
		}

		response.sendRedirect(redirect);
	}
}

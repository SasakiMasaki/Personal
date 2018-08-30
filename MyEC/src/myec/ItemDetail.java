package myec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDao;

/**
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int itemId = Integer.parseInt((String)Controllor.getSessionAttribute(session, "item_id"));
		request.setAttribute("redirectMsg", Controllor.getSessionAttribute(session, "redirectMsg"));

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "Controll");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}else if((Integer)session.getAttribute("id") != 1){
			response.sendRedirect("Top");
			return;
		}

		if(itemId == 0) {
			response.sendRedirect("Controll");
			return;
		}

		try {
			request.setAttribute("item", ItemDao.getItemById((int)itemId));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(Controllor.ITEM_DETAIL_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int itemId = Integer.parseInt(request.getParameter("item_id"));

		switch(request.getParameter("action")) {
		case("update"):
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemUpdate");
			return;
		case("delete"):
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemDelete");
			return;
		case("cart"):
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemDetail");
			return;
		case("return"):
			response.sendRedirect("Controll");
		return;
		}
	}
}

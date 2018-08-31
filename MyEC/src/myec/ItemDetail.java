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
		String temp = session.getAttribute("item_id") != null ? (String) Controllor.getSessionAttribute(session, "item_id") : "0" ;
		int itemId = Integer.parseInt(temp);
		request.setAttribute("redirectMsg", Controllor.getSessionAttribute(session, "redirectMsg"));

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "Top");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}else if(itemId == 0){
			if((Integer)session.getAttribute("id") == 1) {
				response.sendRedirect("Controll");
				return;
			}
			response.sendRedirect("Top");
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
		String temp = request.getParameter("item_id") != null ? (String) request.getParameter("item_id") : "0" ;
		int itemId = Integer.parseInt(temp);

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
			String redirectMsg = "カートに商品を入れました";
			session.setAttribute("redirectMsg", redirectMsg);
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemDetail");
			return;
		case("return"):
			response.sendRedirect("Controll");
		return;
		}
	}
}

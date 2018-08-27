package myec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDao;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Controll")
public class Controll extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("itemList", ItemDao.getAllItemList());
			request.getRequestDispatcher(Controllor.CONTROLL_PAGE).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = 0;
		String redirect = null;

		if(request.getParameter("detail") != null) {
			id = Integer.parseInt(request.getParameter("detail"));
			redirect = "ItemDetail";
		}

		if(request.getParameter("update") != null) {
			id = Integer.parseInt(request.getParameter("update"));
			redirect = "ItemUpdate";
		}

		if(request.getParameter("delete") != null) {
			id = Integer.parseInt(request.getParameter("delete"));
			redirect = "ItemDelete";
		}

		if(id != 0) {
			try {
				session.setAttribute("item",ItemDao.getItemById(id));
				response.sendRedirect(redirect);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		switch(request.getParameter("action")) {
		case("search"):
			try {
				request.setAttribute("itemList", ItemDao.getItemListByKeyword(request.getParameter("keyword")));
				request.getRequestDispatcher(Controllor.CONTROLL_PAGE).forward(request, response);
				return;
			}catch(SQLException e){
				e.printStackTrace();
			}
		case("regist"):
			response.sendRedirect("ItemRegist");
			return;
		}

	}

}

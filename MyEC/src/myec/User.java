package myec;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BuyDao;
import dao.UserDao;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Controllor.getSessionAttribute(session, "indexs");
		request.setAttribute("redirectMsg", session.getAttribute("redirectMsg")!=null ? (String)Controllor.getSessionAttribute(session, "redirectMsg") : "");

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "User");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}

		try {
			int id = (Integer)session.getAttribute("id");
			request.setAttribute("user", UserDao.findUserById(id));
			request.setAttribute("buyList", BuyDao.getBuyDataListByUserId(id));
		}catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(Controllor.USER_PAGE);
		dispatcher.forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(request.getParameter("action").equals("update")) {
			response.sendRedirect("UpdateUser");
			return;
		}

		session.setAttribute("buyId", request.getParameter("buyId"));
		response.sendRedirect("BuyHistory");
		return;
	}

}

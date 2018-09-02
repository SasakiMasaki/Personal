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

import beans.UserDataBeans;
import dao.UserDao;

/**
 * Servlet implementation class UpdateUserConfirm
 */
@WebServlet("/UpdateUserConfirm")
public class UpdateUserConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDataBeans udb = (UserDataBeans)Controllor.getSessionAttribute(session, "udb");

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "UpdateUser");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}

		request.setAttribute("udb", udb);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Controllor.UPDATE_USER_CONFIRM_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			UserDataBeans udb = new UserDataBeans();
			udb.setId((int) session.getAttribute("id"));
			udb.setName(request.getParameter("name"));
			udb.setAddress(request.getParameter("address"));
			udb.setEmail(request.getParameter("email"));
			udb.setPassword(request.getParameter("password"));

			switch(request.getParameter("confirm")) {
			case("cancel"):
				session.setAttribute("udb", udb);
				response.sendRedirect("UpdateUser");
				break;
			case("update"):
				String redirectMsg = "登録情報を更新しました";
				UserDao.updateUser(udb);
				session.setAttribute("redirectMsg", redirectMsg);
				response.sendRedirect("User");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

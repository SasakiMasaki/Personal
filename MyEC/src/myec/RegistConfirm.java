package myec;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;

/**
 * Servlet implementation class RegistConfirm
 */
@WebServlet("/RegistConfirm")
public class RegistConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDataBeans udb = (UserDataBeans)Controllor.getSessionAttribute(session, "udb");
		if(udb == null) {
			response.sendRedirect("Regist");
			return;
		}
		request.setAttribute("udb", udb);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Controllor.REGIST_CONFIRM_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String confirm = request.getParameter("confirm");
		UserDataBeans udb = new UserDataBeans();
		udb.setName(request.getParameter("name"));
		udb.setAddress(request.getParameter("address"));
		udb.setEmail(request.getParameter("email"));
		udb.setPassword(request.getParameter("password"));
		session.setAttribute("udb", udb);

		switch(confirm) {
		case("cancel"):
			response.sendRedirect("Regist");
			break;
		case("regist"):
			response.sendRedirect("RegistResult");
			break;
		}
	}

}

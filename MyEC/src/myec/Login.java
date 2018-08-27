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

import dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("email", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher(Controllor.LOGIN_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int id = 0;
		String password = (String)request.getParameter("password");
		String email = (String)request.getParameter("email");

		try {
			id = UserDao.findIdByEmail(email, password);
		}catch(SQLException e) {
			e.getStackTrace();
		}
		if(id == 0){
			String errMsg = "メールアドレスかパスワードが正しくありません";
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("email", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Controllor.LOGIN_PAGE);
			dispatcher.forward(request, response);
		}else {
			session.setAttribute("id", id);
			String backUrl = (String)request.getParameter("backUrl");
			if(!backUrl.equals("null")){
				response.sendRedirect(backUrl);
			}else {
				response.sendRedirect("Top");
			}
		}
	}

}

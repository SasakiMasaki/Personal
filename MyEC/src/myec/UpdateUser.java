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
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String redirectMsg = (String)Controllor.getSessionAttribute(session, "redirectMsg");
		UserDataBeans udb = (UserDataBeans)Controllor.getSessionAttribute(session, "udb");

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "UpdateUser");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}

		if(redirectMsg != null) {
			request.setAttribute("redirectMsg", redirectMsg);
		}

		if(udb != null) {
			request.setAttribute("udb", udb);
		}else {
			try {
				request.setAttribute("udb", UserDao.findUserById((int)session.getAttribute("id")));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher(Controllor.UPDATE_USER_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			UserDataBeans udb = UserDao.findUserById((int) session.getAttribute("id"));
			udb.setName(request.getParameter("name"));
			udb.setAddress(request.getParameter("address"));
			String email = request.getParameter("email");
			String password = request.getParameter("password").length() != 0 ? request.getParameter("password") : "";
			String confirmPassword = request.getParameter("confirm_password")!=null?request.getParameter("confirm_password"):"";
			String errMsg = "";

			if(!password.equals(confirmPassword)){
				errMsg = "入力されたパスワードと確認用パスワードが異なります。<br>";
			}

			if(UserDao.isOverlapEmail(email)&&!email.equals(udb.getEmail())) {
				errMsg += "入力されたメールアドレスは既に使用されています。";
			}

			udb.setPassword(password);
			udb.setEmail(email);

			if(errMsg.length()!=0) {
				request.setAttribute("udb", udb);
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher(Controllor.UPDATE_USER_PAGE).forward(request, response);
			}else {
				session.setAttribute("udb", udb);
				response.sendRedirect("UpdateUserConfirm");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}

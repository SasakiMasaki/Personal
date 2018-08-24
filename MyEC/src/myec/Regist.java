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
 * Servlet implementation class Regist
 */
@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Regist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(Controllor.getSessionAttribute(session, "return") != null) {
			request.setAttribute("udb", Controllor.getSessionAttribute(session, "udb"));
		}else{
			request.setAttribute("udb", new UserDataBeans());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(Controllor.REGIST_PAGE);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm_password");
			String errMsg = "";
			UserDataBeans udb = new UserDataBeans();

			udb.setName(name);
			udb.setAddress(address);
			udb.setEmail(email);
			udb.setPassword(password);

			if(!password.equals(confirmPassword)){
				errMsg = "入力されたパスワードと確認用パスワードが異なります。<br>";
			}

			if(UserDao.isOverlapEmail(email)) {
				errMsg += "入力されたメールアドレスは既に使用されています。";
			}

			if(errMsg.length()!=0) {
				request.setAttribute("udb", udb);
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher(Controllor.REGIST_PAGE).forward(request, response);
			}else {
				session.setAttribute("udb", udb);
				response.sendRedirect("RegistConfirm");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}

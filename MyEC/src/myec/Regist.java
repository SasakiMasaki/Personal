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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String errMsg = "";
		UserDataBeans udb = new UserDataBeans();

		udb.setName(name);
		udb.setAddress(address);
		udb.setEmail(email);

		if(!request.getParameter("pass").equals(request.getParameter("rePass"))) {
			errMsg = "入力されたパスワードと確認用パスワードが異なります。<br>";
		}

		if(UserDao.isOverlapEmail(email)) {
			errMsg += "入力されたメールアドレスは既に使用されています。";
		}

		if(errMsg.length()!=0) {
			session.setAttribute("udb", udb);
			session.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp").forward(request, response);
		}else {
			request.setAttribute("udb", udb);
			request.getRequestDispatcher("/WEB-INF/jsp/registconfirm.jsp").forward(request, response);
		}
	}

}

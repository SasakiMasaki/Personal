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

import dao.DeliveryMethodDao;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer dmId = (Integer)Controllor.getSessionAttribute(session, "dmId");

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "Cart");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}

		if(session.getAttribute("cart") == null) {
			response.sendRedirect("Cart");
			return;
		}

		try {
			request.setAttribute("dm", DeliveryMethodDao.getDeliveryMethodById(dmId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(Controllor.BUY_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		switch(request.getParameter("action")) {
		case("return"):
			response.sendRedirect("Cart");
			return;
		case("buy"):
			session.setAttribute("dmId", Integer.parseInt(request.getParameter("dmId")));
			response.sendRedirect("BuyResult");
			return;
		}
	}

}

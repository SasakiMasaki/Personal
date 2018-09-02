package myec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDao;
import dao.BuyDetailDao;

/**
 * Servlet implementation class BuyHistory
 */
@WebServlet("/BuyHistory")
public class BuyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer buyId = (Integer) Controllor.getSessionAttribute(session, "buyId");

		if(buyId == null || session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "User");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}

		BuyDataBeans bdb = new BuyDataBeans();
		List<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		try {
			itemList = BuyDetailDao.getItemDataBeansListByBuyId(buyId);
			bdb = BuyDao.getBuyDataByBuyId(buyId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("itemList", itemList);
		request.setAttribute("bdb", bdb);
		request.getRequestDispatcher(Controllor.BUY_HISTORY_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("User");
	}

}

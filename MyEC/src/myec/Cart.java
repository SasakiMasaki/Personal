package myec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.DeliveryMethodDao;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("dmList", DeliveryMethodDao.getAllDeliveryMethod());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(Controllor.CART_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<ItemDataBeans> cart = (ArrayList)session.getAttribute("cart");

		if(cart == null) {
			request.getRequestDispatcher(Controllor.CART_PAGE).forward(request, response);
			return;
		}

		if(request.getParameter("cancell") != null) {
			ItemDataBeans item = cart.get(Integer.parseInt(request.getParameter("cancell")));
			request.setAttribute("msg", "「" + item.getName() + "」をカートから取り除きました");
			cart.remove(item);
			session.setAttribute("cart", cart);
			request.getRequestDispatcher(Controllor.CART_PAGE).forward(request, response);
			return;
		}

		for(ItemDataBeans item : cart) {
			int listNum = cart.indexOf(item);
			item.setCount(Integer.parseInt(request.getParameter(Integer.toString(listNum))));
		}

		Iterator<ItemDataBeans> itr = cart.iterator();
		while(itr.hasNext()) {
			if(itr.next().getCount() == 0) {
				itr.remove();
			}
		}
		session.setAttribute("dmId", Integer.parseInt(request.getParameter("dmId")));
		session.setAttribute("cart", cart);
		response.sendRedirect("Buy");
	}

}

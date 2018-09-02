package myec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.DeliveryMethodDataBeans;
import beans.ItemDataBeans;
import dao.BuyDao;
import dao.BuyDetailDao;
import dao.DeliveryMethodDao;

/**
 * Servlet implementation class BuyResult
 */
@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer dmId = (Integer)Controllor.getSessionAttribute(session, "dmId");
		List<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) Controllor.getSessionAttribute(session, "cart");

		if(cart == null || dmId == null) {
			response.sendRedirect("Cart");
		}

		try {
			DeliveryMethodDataBeans dm = DeliveryMethodDao.getDeliveryMethodById(dmId);
			int totalPrice = dm.getPrice();
			for(ItemDataBeans item : cart) {
				totalPrice += item.getPrice() * item.getCount();
			}
			BuyDataBeans bdb = new BuyDataBeans();
			bdb.setUserId((Integer)session.getAttribute("id"));
			bdb.setDeliveryMethod(dm.getId());
			bdb.setTotalPrice(totalPrice);
			int buyId = BuyDao.insertBuyData(bdb);
			for(ItemDataBeans item : cart) {
				BuyDetailDataBeans bddb =new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setItemId(item.getId());
				bddb.setItemCount(item.getCount());
				BuyDetailDao.insertBuyDetail(bddb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher(Controllor.BUY_RESULT_PAGE).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

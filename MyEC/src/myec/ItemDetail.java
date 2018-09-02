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

import beans.ItemDataBeans;
import dao.ItemDao;

/**
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("redirectMsg", Controllor.getSessionAttribute(session, "redirectMsg"));
		int itemId = 0;

		if(session.getAttribute("item_id") != null) {
			itemId = Integer.parseInt((String) Controllor.getSessionAttribute(session, "item_id"));
		}

		if(itemId == 0){
			response.sendRedirect("Top");
			return;
		}

		try {
			request.setAttribute("item", ItemDao.getItemById(itemId));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(Controllor.ITEM_DETAIL_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("item_id") == null) {
			response.sendRedirect("Top");
		}
		String itemId = request.getParameter("item_id");

		switch(request.getParameter("action")) {
		case("update"):
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemUpdate");
			return;
		case("delete"):
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemDelete");
			return;
		case("cart"):
			List<ItemDataBeans> cart = (ArrayList<ItemDataBeans>)Controllor.getSessionAttribute(session, "cart");
			int listNum = -1;
			if(cart == null) {
				cart = new ArrayList<ItemDataBeans>();
			}
			for(ItemDataBeans item : cart){
				if(item.getId() == Integer.parseInt(itemId)){
					listNum = cart.indexOf(item);
					break;
				}
			}
			if(listNum != -1) {
				int count = cart.get(listNum).getCount() + Integer.parseInt(request.getParameter("count"));
				if(count > 99) {
					count = 99;
				}
				cart.get(listNum).setCount(count);
			}else {
				try {
					ItemDataBeans item = ItemDao.getItemById(Integer.parseInt(itemId));
					item.setCount(Integer.parseInt(request.getParameter("count")));
					cart.add(item);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			session.setAttribute("cart", cart);
			session.setAttribute("redirectMsg", "カートに商品を入れました");
			session.setAttribute("item_id", itemId);
			response.sendRedirect("ItemDetail");
			return;
		case("return"):
			if(session.getAttribute("id").equals("1")) {
				response.sendRedirect("Controll");
				return;
			}

			return;
		}
	}
}

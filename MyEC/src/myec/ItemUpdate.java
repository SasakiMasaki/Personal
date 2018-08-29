package myec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.ItemDataBeans;
import dao.FileDao;
import dao.ItemDao;

/**
 * Servlet implementation class ItemUpdate
 */
@WebServlet("/ItemUpdate")
@MultipartConfig
public class ItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemUpdate() {
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
		Integer i = (Integer) Controllor.getSessionAttribute(session, "item_id");
		if(i != null) {
			itemId = i;
		}

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "ItemRegist");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}else if((Integer)session.getAttribute("id") != 1){
			response.sendRedirect("Top");
			return;
		}

		try {
			request.setAttribute("item", ItemDao.getItemById(itemId));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(Controllor.ITEM_UPDATE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			ItemDataBeans item = new ItemDataBeans();
			item.setName(request.getParameter("name"));
			item.setDetail(request.getParameter("detail"));
			item.setPrice(Integer.parseInt(request.getParameter("price")));
			item.setId(Integer.parseInt(request.getParameter("item_id")));
			item.setFileName("sample.png");
			Part part = request.getPart("file");

			if (getFileName(part).indexOf(".") != -1) {
				item.setFileName(("image_" + item.getId() + getFileName(part).substring(getFileName(part).indexOf("."))));
			}


			part.write(FileDao.getLocation("img") + item.getFileName());
			ItemDao.updateItem(item);
			session.setAttribute("item_id", item.getId());
			session.setAttribute("redirectMsg", "商品情報を更新しました");
			response.sendRedirect("ItemUpdate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getFileName(Part part) {
		for(String cd : part.getHeader("Content-Disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}

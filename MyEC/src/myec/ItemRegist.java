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
 * Servlet implementation class ItemRegist
 */
@WebServlet("/ItemRegist")
@MultipartConfig(location="C:\\Users\\likeit_student\\Documents\\Personal\\MyEC\\WebContent\\img")
public class ItemRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemRegist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("id") == null) {
			request.setAttribute("backUrl", "ItemRegist");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}else if((Integer)session.getAttribute("id") != 1){
			response.sendRedirect("Top");
			return;
		}

		request.getRequestDispatcher(Controllor.ITEM_REGIST_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			String filename = "sample.png";
			int itemId = ItemDao.getLastId() + 1;
			Part part = request.getPart("file");

			if (getFileName(part).indexOf(".") != -1) {
				filename = "image_" + itemId + getFileName(part).substring(getFileName(part).indexOf("."));
			}

			ItemDataBeans item = new ItemDataBeans();
			item.setName(request.getParameter("name"));
			item.setDetail(request.getParameter("detail"));
			item.setPrice(new Integer(request.getParameter("price")));
			item.setFileName(filename);
			ItemDao.addItem(item);
			part.write(FileDao.getLocation("img") + filename);
			session.setAttribute("item_id", itemId);
			session.setAttribute("redirectMsg", "商品情報を登録しました");
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

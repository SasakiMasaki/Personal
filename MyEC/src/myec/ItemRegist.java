package myec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ItemDao;

/**
 * Servlet implementation class ItemRegist
 */
@WebServlet("/ItemRegist")
@MultipartConfig()
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
//		ItemDataBeans item = new ItemDataBeans();
//		item.setName(request.getParameter("name"));
//		item.setDetail(request.getParameter("detail"));
//		item.setPrice(new Integer(request.getParameter("price")));
//		item.setFileName("sample.png");
//		int id = ItemDao.getLastId() + 1;
//
//		String distination = "MyEC/img";
//		Part part = request.getPart("file");
//		String fileName = "image_" + id;

		try {
			ServletContext context = this.getServletContext();
			Part part = request.getPart("file");
			if (getFileName(part).indexOf(".") != -1) {
				int id = ItemDao.getLastId() + 1;
				String filename = "img/image_" + id + getFileName(part).substring(getFileName(part).indexOf("."));
				part.write(context.getRealPath(filename));
				request.setAttribute("test", filename);
			}
			request.getRequestDispatcher(Controllor.ITEM_REGIST_PAGE).forward(request, response);
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

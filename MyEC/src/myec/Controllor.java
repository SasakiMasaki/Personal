package myec;

import javax.servlet.http.HttpSession;

public class Controllor {

	static final String TOP_PAGE = "/WEB-INF/jsp/top.jsp";
	static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
	static final String REGIST_PAGE = "/WEB-INF/jsp/regist.jsp";
	static final String REGIST_CONFIRM_PAGE = "/WEB-INF/jsp/registconfirm.jsp";
	static final String REGIST_RESULT_PAGE = "/WEB-INF/jsp/registresult.jsp";

	public static Object getSessionAttribute(HttpSession session, String str) {
		Object attribute = session.getAttribute(str);
		session.removeAttribute(str);
		return attribute;
	}
}

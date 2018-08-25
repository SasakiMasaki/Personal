package myec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

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

	public static String hashStr(String str) throws NoSuchAlgorithmException{;
		try {
			Charset charset = StandardCharsets.UTF_8;
			String algorithm = "MD5";
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(str.getBytes(charset));
			str = DatatypeConverter.printHexBinary(bytes);
		}catch(NoSuchAlgorithmException e) {
			e.getStackTrace();
		}
		return str;
	}
}

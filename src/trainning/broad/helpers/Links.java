package trainning.broad.helpers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Links {

	public final static String ROOT_PATH = "/";
	public final static String LOGIN_PATH = "/login";
	public final static String LOGIN_JSP = "/WEB-INF/authentication/login.jsp";
	public final static String REGISTER_JSP = "/WEB-INF/authentication/register.jsp";
	public final static String URI_LOGIN = "/broad/login";
	public final static String URI_LOGOUT = "/broad/logout";
	public final static String URI_REGISTER = "/broad/register";

	public static void fowardTo(HttpServletRequest req, HttpServletResponse resp, String target) {

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(target);

		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void redirectTo(HttpServletRequest req, HttpServletResponse resp, String target) {

		try {
			resp.sendRedirect(req.getContextPath() + target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

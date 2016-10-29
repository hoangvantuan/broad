package trainning.broad.helpers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Link {

	public final static String ROOT_PATH = "/";
	public final static String LOGIN_PATH = "/login";
	public final static String LOGIN_JSP = "/WEB-INF/authentication/login.jsp";
	public final static String URI_LOGIN = "/broad/login";
	public final static String URI_LOGOUT = "/broad/logout";
	public final static String URI_REGISTER = "/broad/register";

	public static void redirectHomePage(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath() + ROOT_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fowardHomePage(HttpServletRequest req, HttpServletResponse resp) {

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(ROOT_PATH);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void fowardLoginPage(HttpServletRequest req, HttpServletResponse resp) {

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(LOGIN_JSP);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void redirectLoginPage(HttpServletRequest req, HttpServletResponse resp) {

		try {
			resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

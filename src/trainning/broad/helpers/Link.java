package trainning.broad.helpers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Link {

	public final static String LOGIN = "/WEB-INF/authentication/login.jsp";
	public static void redirectHomepage(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.sendRedirect(req.getContextPath() + "/");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void fowardHomepage(HttpServletRequest req, HttpServletResponse resp) {

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}

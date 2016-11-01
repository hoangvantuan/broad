package trainning.broad.helpers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Links {

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

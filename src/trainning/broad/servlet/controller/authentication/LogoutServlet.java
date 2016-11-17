package trainning.broad.servlet.controller.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainning.broad.helpers.Constants;
import trainning.broad.helpers.GoTo;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {

	public LogoutServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		session.removeAttribute(Constants.ATTR_USER);
		session.invalidate();
		GoTo.redirectTo(req, resp, Constants.HOME_PATH);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}
}


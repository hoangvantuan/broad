package trainning.broad.servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.business.AuthenticationBusiness;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Link;

@WebServlet(urlPatterns = { "/login", "/logout", "/register" })
public class AuthenticationServlet extends HttpServlet {

	private AuthenticationBusiness authenticationBusiness;

	public AuthenticationServlet() {

		this.authenticationBusiness = new AuthenticationBusiness();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestName = req.getRequestURI();
		switch (requestName) {
		case Link.URI_LOGIN:
			if (Helpers.isEmpty(Helpers.getUserFromSession(req)))
				Link.fowardLoginPage(req, resp);
			else
				Link.redirectHomePage(req, resp);
			break;
		case Link.URI_LOGOUT:
			this.logout(req, resp);
			break;
		case Link.URI_REGISTER:
			// TODO
			break;
		default:
			Link.redirectHomePage(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestName = req.getRequestURI();
		switch (requestName) {
		case Link.URI_LOGIN:
			this.login(req, resp);
			break;
		case Link.URI_REGISTER:
			this.register(req, resp);
			break;
		default:
			Link.redirectHomePage(req, resp);
			break;
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) {

		User user = new User();
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		if (!Helpers.isEmpty(user.getEmail()) && !Helpers.isEmpty(user.getPassword())) {

			try {
				user = authenticationBusiness.checkLogin(user);
				if (Helpers.isEmpty(user)) {
					req.setAttribute("error", "アカウントとかパスワードは問題があります。");
					Link.fowardLoginPage(req, resp);
				} else {
					Helpers.storeUserToSession(req, user);
					req.setAttribute("message", "ログイン成功しました");
					Link.fowardHomePage(req, resp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			Link.redirectLoginPage(req, resp);
		}

	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) {

		Helpers.removeSession(req);
		Link.redirectHomePage(req, resp);
	}

	public void register(HttpServletRequest req, HttpServletResponse resp) {

	}

}

package trainning.broad.servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import trainning.broad.bean.User;
import trainning.broad.business.AuthenticationBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/login", "/logout", "/register" })
public class AuthenticationServlet extends HttpServlet {

	private AuthenticationBusiness authenticationBusiness;

	public AuthenticationServlet() {

		this.authenticationBusiness = new AuthenticationBusiness();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestName = req.getRequestURI();
		if (Helpers.isOnline(req)) {
			switch (requestName) {
			case Constants.URI_LOGOUT:
				this.logout(req, resp);
				break;
			default:
				Links.redirectTo(req, resp, Constants.ROOT_PATH);
				break;
			}
		} else {
			switch (requestName) {
			case Constants.URI_LOGIN:
				Links.fowardTo(req, resp, Constants.LOGIN_JSP);
				break;
			case Constants.URI_REGISTER:
				Links.fowardTo(req, resp, Constants.REGISTER_JSP);
				break;
			default:
				Links.redirectTo(req, resp, Constants.ROOT_PATH);
				break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestName = req.getRequestURI();
		User user = new User();
		user.setEmail(req.getParameter(Constants.ATTR_EMAIL));
		user.setPassword(req.getParameter(Constants.ATTR_PASSWORD));

		switch (requestName) {
		case Constants.URI_LOGIN:
			this.login(req, resp, user);
			break;
		case Constants.URI_REGISTER:
			this.register(req, resp, user);
			break;
		default:
			Links.redirectTo(req, resp, Constants.ROOT_PATH);
			break;
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp, User user) {

		if (!Helpers.isEmpty(user.getEmail()) && !Helpers.isEmpty(user.getPassword())) {

			try {
				user = authenticationBusiness.checkLogin(user);
				if (Helpers.isEmpty(user)) {
					req.setAttribute(Constants.ERROR, Constants.LOGIN_ERROR);
					Links.fowardTo(req, resp, Constants.LOGIN_JSP);
				} else {
					Helpers.storeUserToSession(req, user);
					req.setAttribute(Constants.MESSAGE, Constants.LOGIN_SUCCESS);
					Links.fowardTo(req, resp, Constants.ROOT_PATH);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.ROOT_PATH);
			}
		} else {
			Links.redirectTo(req, resp, Constants.LOGIN_JSP);
		}

	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) {

		Helpers.removeSession(req);
		Links.redirectTo(req, resp, Constants.ROOT_PATH);
	}

	public void register(HttpServletRequest req, HttpServletResponse resp, User user) {

		if (Helpers.isEmpty(user.getEmail())) {

			Links.fowardTo(req, resp, Constants.REGISTER_JSP);
		} else {
			try {

				if (authenticationBusiness.isAvalibleUser(user)) {
					req.setAttribute(Constants.ERROR, Constants.ACCOUNT_AVALIBLE_ERROR);
					req.setAttribute(Constants.ATTR_USER, user);
					Links.fowardTo(req, resp, Constants.REGISTER_JSP);
				} else {
					authenticationBusiness.register(user);
					req.setAttribute(Constants.MESSAGE, Constants.REGISTER_SUCCESS);
					Links.fowardTo(req, resp, Constants.REGISTER_JSP);
				}
			} catch (SQLException | EmailException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.ROOT_PATH);
			}
		}

	}
}

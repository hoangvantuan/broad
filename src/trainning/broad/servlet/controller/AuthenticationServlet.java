package trainning.broad.servlet.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

@WebServlet(urlPatterns = { "/login", "/logout", "/register", "/active" })
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
				Links.redirectTo(req, resp, Constants.HOME_PATH);
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
			case Constants.URI_ACTIVE:
				this.confirmPassword(req, resp);
				break;
			default:
				Links.redirectTo(req, resp, Constants.HOME_PATH);
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
		case Constants.URI_ACTIVE:
			this.active(req, resp, user);
			break;
		default:
			Links.redirectTo(req, resp, Constants.HOME_PATH);
			break;
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp, User user) {

		if (!Helpers.isEmpty(user.getEmail()) && !Helpers.isEmpty(user.getPassword())) {

			req.setAttribute(Constants.ATTR_USER, user);
			try {
				user = authenticationBusiness.checkLogin(user);
				if (Helpers.isEmpty(user)) {
					req.setAttribute(Constants.ERROR, Constants.LOGIN_ERROR);
					Links.fowardTo(req, resp, Constants.LOGIN_JSP);
				} else {
					Helpers.storeUserToSession(req, user);
					Links.redirectTo(req, resp, Constants.HOME_PATH);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			}
		} else {
			Links.redirectTo(req, resp, Constants.LOGIN_JSP);
		}

	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) {

		Helpers.removeSession(req);
		Links.redirectTo(req, resp, Constants.HOME_PATH);
	}

	public void register(HttpServletRequest req, HttpServletResponse resp, User user) {

		if (Helpers.isEmpty(user.getEmail())) {

			Links.fowardTo(req, resp, Constants.REGISTER_JSP);
		} else {
			try {

				if (authenticationBusiness.isAvalibleUser(user)) {
					req.setAttribute(Constants.ERROR, Constants.ACCOUNT_AVALIBLE_ERROR);
					req.setAttribute(Constants.ATTR_USER, user);
				} else {
					authenticationBusiness.register(user);
					req.setAttribute(Constants.MESSAGE, Constants.REGISTER_SUCCESS);
				}
				Links.fowardTo(req, resp, Constants.REGISTER_JSP);
			} catch (SQLException | EmailException | NoSuchAlgorithmException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.REGISTER_PATH);
			}
		}

	}

	public void confirmPassword(HttpServletRequest req, HttpServletResponse resp) {

		String email = req.getParameter(Constants.ATTR_EMAIL);
		String code = req.getParameter(Constants.CODE);
		if (!Helpers.isEmpty(email) && !Helpers.isEmpty(code)) {

			try {
				if (authenticationBusiness.isActive(email, code)) {
					req.setAttribute(Constants.ATTR_EMAIL, email);
					Links.fowardTo(req, resp, Constants.CONFIRM_PASSWORD_JSP);
				} else {
					Links.redirectTo(req, resp, Constants.HOME_PATH);
				}
			} catch (NoSuchAlgorithmException | SQLException e) {
				e.printStackTrace();
			}
		} else
			Links.redirectTo(req, resp, Constants.HOME_PATH);
	}

	public void active(HttpServletRequest req, HttpServletResponse resp, User user) {

		if (!Helpers.isEmpty(user.getEmail()) && !Helpers.isEmpty(user.getPassword())) {
			try {
				authenticationBusiness.active(user.getEmail(), user.getPassword());
				Helpers.storeUserToSession(req, user);
				req.setAttribute(Constants.MESSAGE, Constants.ACTIVE_SUCCESS);
				Links.fowardTo(req, resp, Constants.HOME_PATH);
			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			}
		} else
			Links.redirectTo(req, resp, Constants.HOME_PATH);
	}
}

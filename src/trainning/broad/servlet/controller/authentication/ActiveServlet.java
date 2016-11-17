package trainning.broad.servlet.controller.authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.business.AuthenticationBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.GoTo;

@WebServlet(urlPatterns = { "/active" })
public class ActiveServlet extends HttpServlet {

	AuthenticationBusiness authenticationBusiness;

	public ActiveServlet() {

		try {
			authenticationBusiness = new AuthenticationBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(Constants.ATTR_EMAIL).trim();
		String code = req.getParameter(Constants.CODE).trim();

		if (Helpers.isEmpty(email) || Helpers.isEmpty(code)) {
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			try {
				if (authenticationBusiness.checkActiveLink(email, code)) {
					req.setAttribute(Constants.ATTR_EMAIL, email);
					GoTo.fowardTo(req, resp, Constants.CONFIRM_PASSWORD_JSP);
				} else {
					GoTo.redirectTo(req, resp, Constants.HOME_PATH);
				}
			} catch (SQLException | NoSuchAlgorithmException e) {
				e.printStackTrace();
				GoTo.fowardTo(req, resp, Constants.HOME_PATH);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(Constants.ATTR_EMAIL).trim();
		String password = req.getParameter(Constants.ATTR_PASSWORD).trim();
		User user;

		if (!Helpers.isEmpty(email) && !Helpers.isEmpty(password)) {
			try {
				authenticationBusiness.active(email, password);
				user = authenticationBusiness.getUser(email);
				Helpers.storeUserToSession(req, user);
				req.setAttribute(Constants.MESSAGE, Constants.ACTIVE_SUCCESS);
				GoTo.fowardTo(req, resp, Constants.HOME_PATH);
			} catch (SQLException e) {
				e.printStackTrace();
				GoTo.redirectTo(req, resp, Constants.HOME_PATH);
			}
		} else
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
	}
}


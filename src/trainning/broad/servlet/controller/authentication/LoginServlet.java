package trainning.broad.servlet.controller.authentication;

import java.io.IOException;
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
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	private AuthenticationBusiness authenticationBusiness;

	public LoginServlet() {

		try {
			this.authenticationBusiness = new AuthenticationBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Helpers.isOnline(req)) {
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			Links.fowardTo(req, resp, Constants.LOGIN_JSP);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(Constants.ATTR_EMAIL);
		String password = req.getParameter(Constants.ATTR_PASSWORD);
		User user = Helpers.createUser(email, password);

		try {
			boolean validate;
			validate = authenticationBusiness.checkLogin(user);

			if (validate) {
				Helpers.storeUserToSession(req, user);
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			} else {
				req.setAttribute(Constants.ERROR, Constants.ERROR_EMAIL_OR_PASSWORD);
				Links.fowardTo(req, resp, Constants.LOGIN_JSP);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Links.redirectTo(req, resp, Constants.LOGIN_PATH);
		}
	}
}

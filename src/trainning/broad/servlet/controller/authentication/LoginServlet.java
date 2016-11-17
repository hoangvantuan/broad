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
import trainning.broad.helpers.GoTo;

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
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			GoTo.fowardTo(req, resp, Constants.LOGIN_JSP);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(Constants.ATTR_EMAIL).trim();
		String password = req.getParameter(Constants.ATTR_PASSWORD).trim();
		User user;

		try {
			boolean validate;
			validate = authenticationBusiness.userValidator(email, password);

			if (validate) {
				user = authenticationBusiness.getUser(email);
				Helpers.storeUserToSession(req, user);
				req.setAttribute(Constants.MESSAGE, Constants.LOGIN_SUCCESS);
				GoTo.fowardTo(req, resp, Constants.HOME_PATH);
			} else {
				req.setAttribute(Constants.ERROR, Constants.ERROR_EMAIL_OR_PASSWORD);
				GoTo.fowardTo(req, resp, Constants.LOGIN_JSP);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			GoTo.redirectTo(req, resp, Constants.LOGIN_PATH);
		}
	}
}


package trainning.broad.servlet.controller.authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import trainning.broad.business.AuthenticationBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {

	private AuthenticationBusiness authenticationBusiness;

	public RegisterServlet() {

		try {
			authenticationBusiness = new AuthenticationBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Helpers.isOnline(req)) {
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			Links.fowardTo(req, resp, Constants.REGISTER_JSP);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(Constants.ATTR_EMAIL).trim();

		try {
			if (!authenticationBusiness.hasAvalibleEmail(email)) {
				req.setAttribute(Constants.ERROR, Constants.ACCOUNT_HAS_AVALIBLE);
				req.setAttribute(Constants.ATTR_EMAIL, email);
				Links.fowardTo(req, resp, Constants.REGISTER_JSP);
			} else {
				authenticationBusiness.register(email);
				req.setAttribute(Constants.MESSAGE, Constants.REGISTER_SUCCESS);
				Links.fowardTo(req, resp, Constants.REGISTER_JSP);
			}
		} catch (NoSuchAlgorithmException | SQLException | EmailException e) {
			e.printStackTrace();
			req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
			Links.fowardTo(req, resp, Constants.REGISTER_JSP);
		}
	}
}

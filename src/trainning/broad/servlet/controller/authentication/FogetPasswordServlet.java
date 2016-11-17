package trainning.broad.servlet.controller.authentication;

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
import trainning.broad.helpers.GoTo;
import trainning.broad.helpers.Helpers;

@WebServlet(urlPatterns = { "/fogetpassword" })
public class FogetPasswordServlet extends HttpServlet {

	private AuthenticationBusiness authenticationBusiness;

	public FogetPasswordServlet() {

		try {
			this.authenticationBusiness = new AuthenticationBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GoTo.fowardTo(req, resp, Constants.FOGOT_PASSWORD_JSP);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(Constants.ATTR_EMAIL).trim();
		User user;
		try {
			user = authenticationBusiness.getUser(email);
			if (Helpers.isEmpty(user)) {
				req.setAttribute(Constants.ERROR, Constants.NOT_REGISTER);
			} else {
				authenticationBusiness.sendPassword(user, email);
				req.setAttribute(Constants.MESSAGE, Constants.SEND_PASSWORD);
			}
		} catch (EmailException | SQLException e) {
			e.printStackTrace();
			req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
		}
		GoTo.fowardTo(req, resp, Constants.FOGOT_PASSWORD_JSP);
	}
}

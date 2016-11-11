package trainning.broad.servlet.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.business.UserBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/user/edit" })
public class EditUserServlet extends HttpServlet {

	UserBusiness userBusiness;

	public EditUserServlet() {

		try {
			userBusiness = new UserBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = Helpers.getUserFromSession(req);

		try {
			user = userBusiness.getUser(user.getEmail());
			req.setAttribute(Constants.ATTR_USER, user);
		} catch (SQLException e) {
			e.printStackTrace();
			Links.fowardTo(req, resp, Constants.HOME_PATH);
		}
		Links.fowardTo(req, resp, Constants.USER_EDIT_JSP);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter(Constants.ATTR_USER_NAME);
		String password = req.getParameter(Constants.ATTR_PASSWORD);
		String confirmPassword = req.getParameter("confirmpassword");
		User user = Helpers.getUserFromSession(req);

		try {
			user = userBusiness.getUser(user.getEmail());

			if (!password.equals(confirmPassword)) {
				req.setAttribute(Constants.ERROR, Constants.CONFIRM_PASSWORD_WRONG);
				req.setAttribute(Constants.ATTR_USER, user);
				Links.fowardTo(req, resp, Constants.USER_EDIT_JSP);
			} else {
				user.setUserName(userName);
				user.setPassword(password);
				userBusiness.updateUser(user);
				req.setAttribute(Constants.MESSAGE, Constants.EDIT_SUCCESS);
				Links.fowardTo(req, resp, Constants.USER_PROFILE_PATH);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Links.fowardTo(req, resp, Constants.HOME_PATH);
		}
	}
}

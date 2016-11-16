package trainning.broad.servlet.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.bean.UserPostComment;
import trainning.broad.business.UserBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/user/profile" })
public class ProfileUserServlet extends HttpServlet {

	UserBusiness userBusiness;

	public ProfileUserServlet() {

		try {
			userBusiness = new UserBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = req.getParameter(Constants.ATTR_USER_ID);
		User user;
		UserPostComment userPostComment;

		try {
			if (Helpers.isEmpty(userId) || !Helpers.isNumber(userId)) {
				user = Helpers.getUserFromSession(req);
			} else {
				int id = Integer.parseInt(userId);
				user = userBusiness.getUser(id);
			}

			if (Helpers.isEmpty(user)) {
				req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
				Links.fowardTo(req, resp, Constants.HOME_PATH);
			} else {
				userPostComment = userBusiness.getUserPostComment(user.getUserId());
				req.setAttribute(Constants.ATTR_USER, userPostComment);
				Links.fowardTo(req, resp, Constants.USER_PROFILE_JSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}
}

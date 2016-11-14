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

@WebServlet(urlPatterns = { "/user/profile" })
public class AllUserServlet extends HttpServlet {

	UserBusiness userBusiness;

	public AllUserServlet() {

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
		int numOfPost = 0;
		int numOfComment = 0;

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
				numOfPost = userBusiness.getNumPost(user.getUserId());
				numOfComment = userBusiness.getNumComment(user.getUserId());
				req.setAttribute(Constants.ATTR_USER, user);
				req.setAttribute("num_of_post", numOfPost);
				req.setAttribute("num_of_comment", numOfComment);
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

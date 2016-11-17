package trainning.broad.servlet.controller.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.UserPostComment;
import trainning.broad.business.UserBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.GoTo;

@WebServlet(urlPatterns = { "/user/users" })
public class ListUserServlet extends HttpServlet {

	UserBusiness userBusiness;

	public ListUserServlet() {

		try {
			userBusiness = new UserBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<UserPostComment> userPostComments = new ArrayList<UserPostComment>();
		try {
			userPostComments = userBusiness.getUserPostComments();
			req.setAttribute(Constants.USER_POST_COMMENTS, userPostComments);
			GoTo.fowardTo(req, resp, Constants.USER_LIST_JSP);
		} catch (SQLException e) {
			e.printStackTrace();
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

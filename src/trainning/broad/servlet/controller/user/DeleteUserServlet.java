package trainning.broad.servlet.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.business.UserBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/user/delete" })
public class DeleteUserServlet extends HttpServlet {

	UserBusiness userBusiness;

	public DeleteUserServlet() {

		try {
			userBusiness = new UserBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = req.getParameter(Constants.ATTR_USER_ID);

		if (!Helpers.isEmpty(userId)) {
			try {
				int id = Integer.parseInt(userId);
				userBusiness.deleteUser(id);
			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
		Links.redirectTo(req, resp, Constants.HOME_PATH);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
}

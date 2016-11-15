package trainning.broad.servlet.controller.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/post/delete" })
public class DeletePostServlet extends HttpServlet {

	PostBusiness postBusiness;
	String userId;

	public DeletePostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postId = req.getParameter(Constants.ATTR_POST_ID);
		userId = req.getParameter(Constants.ATTR_USER_ID);

		if (!Helpers.isEmpty(postId) && Helpers.isNumber(postId)) {
			int id = Integer.parseInt(postId);
			try {
				postBusiness.deletePost(id);
				req.setAttribute(Constants.MESSAGE, Constants.DELETE_SUCCESS);
			} catch (SQLException e) {
				e.printStackTrace();
				req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
			}
		}

		if (Helpers.isEmpty(userId))
			Links.fowardTo(req, resp, Constants.HOME_PATH);
		else {
			req.setAttribute(Constants.ATTR_USER_ID, userId);
			Links.fowardTo(req, resp, Constants.LIST_POST_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}
}

package trainning.broad.servlet.controller.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.PostUserTag;
import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.GoTo;
import trainning.broad.helpers.Helpers;

@WebServlet(urlPatterns = { "/post/edit" })
public class EditPostServlet extends HttpServlet {

	PostBusiness postBusiness;

	public EditPostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postId = req.getParameter(Constants.ATTR_POST_ID);
		if (!Helpers.isEmpty(postId) && Helpers.isNumber(postId)) {
			int id = Integer.parseInt(postId);
			try {
				PostUserTag postUserTag = postBusiness.getPost(id);
				if (Helpers.isEmpty(postUserTag)) {
					req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
					GoTo.fowardTo(req, resp, Constants.HOME_PATH);
				} else {
					req.setAttribute(Constants.POST_USER_TAG, postUserTag);
					GoTo.fowardTo(req, resp, Constants.POST_EDIT_JSP);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				GoTo.redirectTo(req, resp, Constants.HOME_PATH);
			}
		} else {
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postName = req.getParameter(Constants.POST_NAME);
		String content = req.getParameter(Constants.ATTR_CONNTENT);
		String postId = req.getParameter(Constants.ATTR_POST_ID);
		String[] tags = req.getParameterValues(Constants.TAG);
		if (!Helpers.isEmpty(postId) && Helpers.isNumber(postId)) {
			int id = Integer.parseInt(postId);
			try {
				postBusiness.editPost(id, postName, content, tags);
				req.setAttribute(Constants.MESSAGE, Constants.EDIT_SUCCESS);
				req.setAttribute(Constants.ATTR_POST_ID, id);
				GoTo.fowardTo(req, resp, Constants.POST_DETAIL);
			} catch (SQLException e) {
				e.printStackTrace();
				req.setAttribute(Constants.ERROR, Constants.EDIT_ERROR);
				GoTo.fowardTo(req, resp, Constants.POST_EDIT_JSP);
			}
		} else {
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}
}

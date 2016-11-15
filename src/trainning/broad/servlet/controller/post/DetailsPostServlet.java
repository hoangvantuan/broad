package trainning.broad.servlet.controller.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.PostUserTag;
import trainning.broad.bean.UserComment;
import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/post/details" })
public class DetailsPostServlet extends HttpServlet {

	PostBusiness postBusiness;

	public DetailsPostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postId = req.getParameter(Constants.ATTR_POST_ID);

		if (Helpers.isEmpty(postId) || !Helpers.isNumber(postId)) {
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			int id = Integer.parseInt(postId);
			try {
				PostUserTag postUserTag = postBusiness.getPost(id);
				List<UserComment> postComments = postBusiness.getPostComment(id);
				int numOfComment = postComments.size();

				if (Helpers.isEmpty(postUserTag)) {
					Links.redirectTo(req, resp, Constants.HOME_PATH);
				} else {
					req.setAttribute(Constants.POST_USER_TAG, postUserTag);
					req.setAttribute(Constants.USER_COMMENT, postComments);
					req.setAttribute("num_of_comments", numOfComment);
					Links.fowardTo(req, resp, Constants.POST_DETAIL_JSP);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

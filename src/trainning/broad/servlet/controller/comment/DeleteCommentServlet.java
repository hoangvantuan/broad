package trainning.broad.servlet.controller.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.business.CommentBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/comment/delete" })
public class DeleteCommentServlet extends HttpServlet {

	CommentBusiness commentBusiness;

	public DeleteCommentServlet() {

		try {
			commentBusiness = new CommentBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String commentId = req.getParameter(Constants.ATTR_COMMENT_ID);
		String postId = req.getParameter(Constants.ATTR_POST_ID);

		if (Helpers.isEmpty(commentId) || Helpers.isEmpty(postId)) {
			req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
			Links.fowardTo(req, resp, Constants.HOME_PATH);
		} else {
			try {
				int commentIdTemp = Integer.parseInt(commentId);
				int postIdTemp = Integer.parseInt(postId);
				boolean check = commentBusiness.checkCommentOfPost(commentIdTemp, postIdTemp);

				if (!check) {
					req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
					Links.fowardTo(req, resp, Constants.HOME_PATH);
				} else {
					commentBusiness.deleteComment(commentIdTemp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}

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
import trainning.broad.helpers.GoTo;
import trainning.broad.helpers.Helpers;

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
		if (Helpers.isEmpty(commentId) && !Helpers.isNumber(commentId)) {
			req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
			GoTo.fowardTo(req, resp, Constants.HOME_PATH);
		} else {
			try {
				int id = Integer.parseInt(commentId);
				commentBusiness.deleteComment(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
}

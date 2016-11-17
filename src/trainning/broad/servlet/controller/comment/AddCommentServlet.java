package trainning.broad.servlet.controller.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import trainning.broad.bean.User;
import trainning.broad.bean.UserComment;
import trainning.broad.business.CommentBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.GoTo;

@WebServlet(urlPatterns = { "/comment/add" })
public class AddCommentServlet extends HttpServlet {

	private CommentBusiness commentBusiness;

	public AddCommentServlet() {

		try {
			commentBusiness = new CommentBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		GoTo.redirectTo(req, resp, Constants.HOME_PATH);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String postId = req.getParameter(Constants.ATTR_POST_ID);
		String content = req.getParameter(Constants.ATTR_CONNTENT);
		User user = Helpers.getUserFromSession(req);
		UserComment userComment;
		Gson json = new Gson();
		if (!Helpers.isEmpty(postId) && !Helpers.isEmpty(content)) {
			int id = Integer.parseInt(postId);

			try {
				userComment = commentBusiness.addComment(user.getUserId(), id, content);
				userComment.setUser(user);
				resp.getWriter().print(json.toJson(userComment));

			} catch (SQLException e) {
				e.printStackTrace();
				GoTo.redirectTo(req, resp, Constants.HOME_PATH);
			}

		}
	}
}

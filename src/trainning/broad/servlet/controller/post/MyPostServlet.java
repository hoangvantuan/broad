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
import trainning.broad.bean.User;
import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/post/myposts" })
public class MyPostServlet extends HttpServlet {

	PostBusiness postBusiness;

	public MyPostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = Helpers.getUserFromSession(req);

		try {
			List<PostUserTag> myPosts = postBusiness.getMyPosts(user);
			req.setAttribute("post", myPosts);
		} catch (SQLException e) {
			e.printStackTrace();
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		}
		Links.fowardTo(req, resp, Constants.HOMEPAGE_JSP);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

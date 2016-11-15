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
import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/user/posts" })
public class ListPostServlet extends HttpServlet {

	PostBusiness postBusiness;

	public ListPostServlet() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id;
		String userId = "";
		List<PostUserTag> postUserTags;

		if (!Helpers.isEmpty(req.getAttribute(Constants.ATTR_USER_ID)))
			userId = (String) req.getAttribute(Constants.ATTR_USER_ID);

		if (Helpers.isEmpty(userId))
			userId = req.getParameter(Constants.ATTR_USER_ID);

		if (Helpers.isEmpty(userId) || !Helpers.isNumber(userId)) {
			id = Helpers.getUserFromSession(req).getUserId();
		} else {
			id = Integer.parseInt(userId);
		}

		try {
			postUserTags = postBusiness.getPosts(id);

			if (Helpers.isEmpty(postUserTags)) {
				req.setAttribute(Constants.ERROR, Constants.NO_DATA);
				Links.fowardTo(req, resp, Constants.HOMEPAGE_JSP);
			} else {
				req.setAttribute(Constants.ATTR_USER_ID, id);
				req.setAttribute(Constants.POST_USER_TAGS, postUserTags);
				Links.fowardTo(req, resp, Constants.HOMEPAGE_JSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Links.fowardTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

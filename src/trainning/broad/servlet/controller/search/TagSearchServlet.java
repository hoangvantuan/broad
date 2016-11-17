package trainning.broad.servlet.controller.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.PostUserTag;
import trainning.broad.business.SearchBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.GoTo;
import trainning.broad.helpers.Helpers;

@WebServlet(urlPatterns = { "/tag/search" })
public class TagSearchServlet extends HttpServlet {

	private SearchBusiness searchBusiness;

	public TagSearchServlet() {

		try {
			searchBusiness = new SearchBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String tagId = req.getParameter(Constants.ATTR_TAG_ID);
		List<PostUserTag> userPostTags;
		if (Helpers.isEmpty(tagId) || !Helpers.isNumber(tagId)) {
			req.setAttribute(Constants.ERROR, Constants.ERROR_UNKONW);
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			int id = Integer.parseInt(tagId);
			try {
				userPostTags = searchBusiness.searchByTagId(id);
				if (Helpers.isEmpty(userPostTags)) {
					req.setAttribute(Constants.ERROR, Constants.NO_DATA);
				} else {
					req.setAttribute(Constants.MESSAGE, userPostTags.size() + " 検索結果の " + (":"));
					req.setAttribute(Constants.POST_USER_TAGS, userPostTags);
				}
				GoTo.fowardTo(req, resp, Constants.HOMEPAGE_JSP);
			} catch (SQLException e) {
				e.printStackTrace();
				GoTo.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

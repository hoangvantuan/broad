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

@WebServlet(urlPatterns = { "/post/search" })
public class HomepageSearchServlet extends HttpServlet {

	private SearchBusiness searchBusiness;

	public HomepageSearchServlet() {

		try {
			searchBusiness = new SearchBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<PostUserTag> postUserTags;
		String keyWord = req.getParameter(Constants.KEY_WORD);
		try {
			postUserTags = searchBusiness.searchByAttributeOfPost(keyWord);
			if (Helpers.isEmpty(postUserTags)) {
				req.setAttribute(Constants.ERROR, Constants.NO_DATA);
			} else {
				req.setAttribute(Constants.MESSAGE, postUserTags.size() + " 検索結果の " + keyWord + (":"));
				req.setAttribute(Constants.POST_USER_TAGS, postUserTags);
			}
			GoTo.fowardTo(req, resp, Constants.HOMEPAGE_JSP);
		} catch (SQLException e) {
			e.printStackTrace();
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

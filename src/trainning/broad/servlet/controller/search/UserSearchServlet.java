package trainning.broad.servlet.controller.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.UserPostComment;
import trainning.broad.business.SearchBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/user/search" })
public class UserSearchServlet extends HttpServlet {

	private SearchBusiness searchBusiness;

	public UserSearchServlet() {

		try {
			searchBusiness = new SearchBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keyWord = req.getParameter(Constants.KEY_WORD);
		List<UserPostComment> userPostComments;
		try {
			userPostComments = searchBusiness.searchUser(keyWord);

			if (Helpers.isEmpty(userPostComments)) {
				req.setAttribute(Constants.ERROR, Constants.NO_DATA);
			} else {
				req.setAttribute(Constants.MESSAGE, "検索結果の" + keyWord + (":"));
				req.setAttribute(Constants.USER_POST_COMMENTS, userPostComments);
			}
			Links.fowardTo(req, resp, Constants.USER_LIST_JSP);
		} catch (SQLException e) {
			e.printStackTrace();
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

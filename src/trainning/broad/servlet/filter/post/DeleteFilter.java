package trainning.broad.servlet.filter.post;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.business.PostBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.GoTo;

@WebFilter(urlPatterns = { "/post/delete" })
public class DeleteFilter implements Filter {

	PostBusiness postBusiness;

	public DeleteFilter() {

		try {
			postBusiness = new PostBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		User user = Helpers.getUserFromSession(req);
		String postId = req.getParameter(Constants.ATTR_POST_ID);

		if (Helpers.isEmpty(postId) || !Helpers.isNumber(postId)) {
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			try {
				boolean check;
				int id = Integer.parseInt(postId);
				check = postBusiness.isMyPost(user.getUserId(), id);

				if (check || user.getIsRole()) {
					arg2.doFilter(arg0, arg1);
				} else {
					GoTo.redirectTo(req, resp, Constants.HOME_PATH);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				GoTo.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
	}
}

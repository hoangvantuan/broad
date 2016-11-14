package trainning.broad.servlet.filter.comment;

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
import trainning.broad.business.CommentBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebFilter(urlPatterns = { "/comment/delete" })
public class DeleteFilter implements Filter {

	CommentBusiness commentBusiness;

	public DeleteFilter() {

		try {
			commentBusiness = new CommentBusiness();
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
		String commentId = req.getParameter(Constants.ATTR_COMMENT_ID);

		if (Helpers.isEmpty(commentId)) {
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			try {
				boolean check;
				int id = Integer.parseInt(commentId);
				check = commentBusiness.isMyComment(user.getEmail(), id);

				if (check || user.getIsRole()) {
					arg2.doFilter(arg0, arg1);
				} else {

					Links.redirectTo(req, resp, Constants.HOME_PATH);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
	}
}

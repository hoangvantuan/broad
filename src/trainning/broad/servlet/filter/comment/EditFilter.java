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

@WebFilter(urlPatterns = { "/comment/edit" })
public class EditFilter implements Filter {

	CommentBusiness commentBusiness;

	public EditFilter() {

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

		if (Helpers.isEmpty(commentId) || !Helpers.isNumber(commentId)) {
			Links.redirectTo(req, resp, Constants.HOME_PATH);
		} else {
			int id = Integer.parseInt(commentId);
			try {
				boolean check;
				check = commentBusiness.isMyComment(user.getUserId(), id);

				if (check) {
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

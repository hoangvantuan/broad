package trainning.broad.servlet.filter.user;

import java.io.IOException;

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
import trainning.broad.business.UserBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.GoTo;
import trainning.broad.helpers.Helpers;

@WebFilter(urlPatterns = { "/user/delete" })
public class DeleteFilter implements Filter {

	UserBusiness userBusiness;

	public DeleteFilter() {

		try {
			userBusiness = new UserBusiness();
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
		if (user.getIsRole()) {
			arg2.doFilter(arg0, arg1);
		} else {
			GoTo.redirectTo(req, resp, Constants.HOME_PATH);
		}

	}
}

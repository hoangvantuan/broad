package trainning.broad.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import trainning.broad.bean.User;

public class Helpers {

	// store session of user when login
	public static void storeUserToSession(HttpServletRequest request, User user) {

		HttpSession session = request.getSession();
		session.setAttribute(Constants.ATTR_USER, user);

	}

	// get user is login
	public static User getUserFromSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		return (User) session.getAttribute(Constants.ATTR_USER);

	}

	public static void removeSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (!Helpers.isEmpty(session.getAttribute(Constants.ATTR_USER)))
			session.removeAttribute(Constants.ATTR_USER);
		session.invalidate();
	}

	public static boolean isOnline(HttpServletRequest request) {

		return !isEmpty(getUserFromSession(request));
	}

	public static boolean isEmpty(String object) {

		return "".equals(object) || object == null;
	}

	public static boolean isEmpty(Object object) {

		return object == null;
	}
}

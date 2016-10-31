package trainning.broad.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import trainning.broad.bean.User;

public class Helpers {

	// store session of user when login
	public static void storeUserToSession(HttpServletRequest request, User user) {

		HttpSession session = request.getSession();
		session.setAttribute(User.USER, user);

	}

	// get user is login
	public static User getUserFromSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		return (User) session.getAttribute(User.USER);

	}

	public static void removeSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (!Helpers.isEmpty(session.getAttribute(User.USER)))
			session.removeAttribute(User.USER);
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

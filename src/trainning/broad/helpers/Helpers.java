package trainning.broad.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import trainning.broad.bean.User;

public class Helpers {

	public static final String CURRENT_USER_SESSION = "CURRENT_USER";
	public static final String CONNECTION_STORED = "CONNECTION_STORED";

	// store session of user when login
	public static void storeUserToSession(HttpServletRequest request, User user) {

		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER_SESSION, user);

	}

	// get user is login
	public static User getUserFromSession(HttpServletRequest request) {

		return (User) request.getSession().getAttribute(CURRENT_USER_SESSION);

	}

	public static boolean isEmpty(String object) {

		return "".equals(object);
	}

	public static boolean isEmpty(Object object) {

		return object == null;
	}
}

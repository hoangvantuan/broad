package trainning.broad.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

	public static String getCodeActive(String email) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(email.concat(Constants.ENCODE).getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}

	public static Timestamp getCurrenTimeStamp() {

		Date date = new java.util.Date();
		return new Timestamp(date.getTime());
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

	public static boolean isEmpty(List<Object> list) {

		return list == null || list.size() == 0;
	}

	public static String cutString(String str) {

		if (str.length() >= 100) {
			return str.substring(0, 100);
		} else
			return str;

	}

	public static boolean isNumber(String str) {

		return str.matches("\\d+") ? true : false;
	}
}

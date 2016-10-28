package trainning.broad.servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.business.AuthenticationBusiness;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Link;

@WebServlet(urlPatterns = { "/login" })
public class AuthenticationServlet extends HttpServlet {

	private AuthenticationBusiness authenticationBusiness;

	public AuthenticationServlet() {

		this.authenticationBusiness = new AuthenticationBusiness();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.fowardLoginPage(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		if (!Helpers.isEmpty(email) && !Helpers.isEmpty(password)) {

			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			try {
				user = authenticationBusiness.checkLogin(user);
				if (Helpers.isEmpty(user)) {
					req.setAttribute("error", "アカウントとかパスワードは問題があります。");
					fowardLoginPage(req, resp);
				} else {
					Helpers.storeUserToSession(req, user);
					req.setAttribute("message", "ログイン成功しました");
					Link.redirectHomepage(req, resp);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			this.fowardLoginPage(req, resp);
		}

	}

	public void fowardLoginPage(HttpServletRequest req, HttpServletResponse resp) {

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(Link.LOGIN);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}

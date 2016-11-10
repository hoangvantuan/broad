package trainning.broad.servlet.controller.homepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.PostUserTag;
import trainning.broad.business.HomepageBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "" })
public class HomepageServlet extends HttpServlet {

	HomepageBusiness homepageBusiness;

	public HomepageServlet() {

		try {
			this.homepageBusiness = new HomepageBusiness();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Helpers.isOnline(req)) {
			try {
				List<PostUserTag> infoPostHomepages = homepageBusiness.getDataForHomepage();
				req.setAttribute("datas", infoPostHomepages);
			} catch (SQLException e) {
				e.printStackTrace();
				Links.redirectTo(req, resp, Constants.HOME_PATH);
			}
		}
		Links.fowardTo(req, resp, Constants.HOMEPAGE_JSP);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}

}

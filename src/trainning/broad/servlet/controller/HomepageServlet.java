package trainning.broad.servlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.InfoPostHomepage;
import trainning.broad.business.HomepageBusiness;
import trainning.broad.helpers.Constants;
import trainning.broad.helpers.Helpers;
import trainning.broad.helpers.Links;

@WebServlet(urlPatterns = { "/home" })
public class HomepageServlet extends HttpServlet {

	HomepageBusiness homepageBusiness;

	public HomepageServlet() {

		this.homepageBusiness = new HomepageBusiness();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Helpers.isOnline(req)) {
			List<InfoPostHomepage> infoPostHomepages = homepageBusiness.getDataForHomepage();
			req.setAttribute("datas", infoPostHomepages);
		}
		Links.fowardTo(req, resp, Constants.HOMEPAGE_JSP);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}

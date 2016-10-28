package trainning.broad.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainning.broad.bean.User;
import trainning.broad.database.DAOManager;
import trainning.broad.database.Table;
import trainning.broad.database.connection.PostgresSQLConnection;

@WebServlet(urlPatterns = { "/test" })
public class test extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ

		DAOManager d = new DAOManager(new PostgresSQLConnection());
		d.getDAO(Table.USER).delete(new User());
		d.close();
	}
}

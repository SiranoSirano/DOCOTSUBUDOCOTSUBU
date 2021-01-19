package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.GetEmployeeListLogic;
import model.PostEmployeeLogic;
import model.User;


/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//アプリケーションスコープからリクエストスコープに変更、スコープに保存して取得する方法からDBに置き換え
		//リストを取得してDAOからDBに接続
		GetEmployeeListLogic getEmployeeListLogic = new GetEmployeeListLogic();
		List<Employee> employeeList = getEmployeeListLogic.execute();
		request.setAttribute("employeeList", employeeList);

		//ログインしているか確かめるためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			//ログインしていないときはリダイレクト
			response.sendRedirect("/社員情報管理システムDB接続/index.jsp");
		}else {
			//			ログイン済み
			//ログインしているときはフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}


	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//			リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		//			入力値チェック
		if((text != null) && (text.length() != 0)) {
			//			セッションスコープに保存された社員情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//			入力内容をリストに追加
			Employee employee = new Employee(loginUser.getName(),text);
			PostEmployeeLogic postEmployeeLogic = new PostEmployeeLogic();
			postEmployeeLogic.execute(employee);
		}else {
			//			エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMSG", "登録内容が入力されていません");
		}
		//		入力内容リストを取得して、リクエストスコープに保存
		GetEmployeeListLogic getEmployeeListLogic = new GetEmployeeListLogic();
		List<Employee> employeeList =getEmployeeListLogic.execute();
		request.setAttribute("employeeList",employeeList);

		//			メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request,response);

	}

}

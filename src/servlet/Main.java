package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;


/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//入力した社員情報のリストを取得してアプリケーションスコープに保存
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		//		List<Mutter>の未キャストが原因で警告文→ジェネリックスを使って解消

		//		リストを取得できなかった時は、新規作成してアプリケーションスコープに保存
		if(mutterList == null) {
			mutterList = new ArrayList<>();
			application.setAttribute("mutterList", mutterList);
		}

		//ログインしているか確かめるためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			//ログインしていないときはリダイレクト
			response.sendRedirect("/DOCOTSUBUDOCOTSUBU/index.jsp");
		}else {
			//ログインしているときはフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//			リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		//			入力値チェック
		if((text != null) && (text.length() != 0)) {
			//				アプリケーションスコープに保存された社員情報リストを取得
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			//			セッションスコープに保存された社員情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//			入力内容をリストに追加
			Mutter mutter = new Mutter(loginUser.getName(),text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter,mutterList);

			//			アプリケーションスコープにリストを保存
			application.setAttribute("mutterList", mutterList);

		}else {
			//			エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMSG", "登録内容が入力されていません");
		}

		//			メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request,response);

	}

}

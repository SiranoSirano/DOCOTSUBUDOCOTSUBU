package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//入力内容からリクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");

		//Userインスタンス(ユーザー情報)の生成
		User user  = new User(mail,pass);

		//ログインのチェック処理
		//LoginLogicインスタンスの生成
		LoginLogic loginLogic = new LoginLogic();
		//ログイン可ならセッションスコープにログインしたuserの情報をuserロジックを使って実行
		boolean isLogin = loginLogic.execute(user);

		//ログイン成功時の動き
		if(isLogin)
		{
			//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		//ログイン結果画面にフォワード
		//→セッションスコープからユーザー情報を取得しログイン成功・失敗の2画面のどちらかを出力
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);

	}

}

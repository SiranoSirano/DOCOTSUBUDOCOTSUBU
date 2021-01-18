package model;

public class LoginLogic {
	public boolean execute(User user) {
		//ログイン時のパスワードが1234なら通す
		if(user.getPass().equals("1234")) { //ここをDBと連携させて,
			//			mailとpasswordの情報を個人個人の情報と一致させる
			return true;
		}
		//ログイン時のパスワードがそれ以外なら通さない
		return false;
	}
}

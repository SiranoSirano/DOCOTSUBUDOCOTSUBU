package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO {
	//	DB接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:~/社員情報管理システムDB接続";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//SELECTしてArrayListに入力内容を格納する
	public List<Employee> findAll(){
		List<Employee> employeeList = new ArrayList<>();

		//		DBとの接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//		SELECT文の準備
			String sql = "SELECT ID, NAME ,TEXT FROM MUTTER ORDER BY ID DSC ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//			SELECTの実行
			ResultSet rs = pStmt.executeQuery();

			//			SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text =rs.getString("TEXT");
				Employee employee = new Employee(id,userName,text);
				employeeList.add(employee);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}return employeeList;
	}
	//	INSERTしてSQLを実行、そして入力内容を出力
	public boolean create(Employee employee) {
		//			DBとの接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//				INSERT文の準備
			//				?,?がインサート文で挿入される
			String sql = "INSERT INTO Employee(NAME,TEXT) VALUES (?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//				INSERT文中の？に使用する値を設定しSQLを完成
			pStmt.setString(1,employee.getUserName());
			pStmt.setString(2,employee.getText());

			//				INSERT文を実行(resultには追加された行数が代入される)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}






package model;
//登録内容の取得に関する処理を行うDAOを利用したモデル

import java.util.List;

import dao.EmployeeDAO;

public class GetEmployeeListLogic {
	public List<Employee> execute(){
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> employeeList = dao.findAll();
		return employeeList;
	}
}

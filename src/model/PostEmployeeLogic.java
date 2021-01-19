package model;

import dao.EmployeeDAO;

public class PostEmployeeLogic {

	public void execute(Employee employee) {

		EmployeeDAO dao = new EmployeeDAO();
		dao.create(employee);
	}
}

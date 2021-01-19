package model;
//登録情報を持つJava Beans

import java.io.Serializable;

public class Employee implements Serializable{
	private int id; //ID番号
	private String userName; //ユーザー名
	private String text; //登録内容

	public Employee() {}
	public Employee(String userName,String text) {
		this.userName = userName;
		this.text = text;
	}
	public Employee(int id, String userName,String text) {
		this.id = id;
		this.userName = userName;
		this.text = text;
	}
	public int getId() {return id;}
	public String getUserName() {return userName;}
	public String getText() {return text;}

}

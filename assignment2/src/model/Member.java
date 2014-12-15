package model;

import java.util.List;

public class Member{
	private int id;
	private String firstName, lastName;
	private String email;
	private List<Group> groups;
	private List<Expense> expenses;

	public Member(int id, String firstName, String lastName, String email){
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Group> getGroups(){
		return groups;
	}
	
	public List<Expense> getExpenses(){
		return expenses;
	}

	public String toString(){
		return getFirstName() + " " + getLastName();
	}
}

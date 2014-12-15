package model;

import java.util.List;

public class Expense {
	private int id, senderId;
	private double amount;
	private String type, description;
	private String date;
	private List<Member> membersPaidFor;
	
	public Expense(int id, int senderId, double amount, String date, String description){
		setId(id);
		setSenderId(senderId);
		setAmount(amount);
		setDate(date);
		setDescription(description);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Member> getMembersPaidFor() {
		return membersPaidFor;
	}
	public void setMembersPaidFor(List<Member> membersPaidFor) {
		this.membersPaidFor = membersPaidFor;
	}
	
}

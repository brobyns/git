package model;

import java.util.Date;
import java.util.List;

public class Expense {
	private double amount;
	private String type, description;
	private Date date;
	private List<Member> membersPaidFor;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Member> getMembersPaidFor() {
		return membersPaidFor;
	}
	public void setMembersPaidFor(List<Member> membersPaidFor) {
		this.membersPaidFor = membersPaidFor;
	}
	
}

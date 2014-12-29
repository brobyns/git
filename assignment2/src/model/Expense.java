package model;

import java.util.HashSet;
import java.util.Set;

public class Expense {
	private static int counter = 0;
	private int id;
	private int senderId, groupId;
	private double amount;
	private String type, description;
	private String date;
	private Set<Integer> membersPaidFor = new HashSet<Integer>();

	public Expense(int senderId, double amount, String date, String description, int groupId){
		counter++;
		id = counter;
		setId(id);
		setSenderId(senderId);
		setAmount(amount);
		setDate(date);
		setDescription(description);
		setGroupId(groupId);
	}
	
	public Expense(int id, int senderId, double amount, String date, String description){
		setId(id);
		setSenderId(senderId);
		setAmount(amount);
		setDate(date);
		setDescription(description);
	}

	public Expense(int id, int senderId, double amount, String date, String description, Set<Integer> membersPaidFor, int groupId){
		this(id, senderId, amount, date, description);
		setGroupId(groupId);
		setMembersPaidFor(membersPaidFor);
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
	public Set<Integer> getMembersPaidFor() {
		return membersPaidFor;
	}
	public void setMembersPaidFor(Set<Integer> membersPaidFor) {
		this.membersPaidFor = membersPaidFor;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}

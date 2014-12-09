package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private Admin admin;
	private String name;
	private List<Member> members;
	
	public Group(Admin admin, String name){
		members = new ArrayList<Member>();
		setAdmin(admin);
		setName(name);
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public void deleteMember(long memberId){
		for(Member m : members){
			if(m.getId() == memberId){
				members.remove(m);
			}
		}
	}
	
	
}

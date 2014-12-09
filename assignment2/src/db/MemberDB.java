package db;

import java.util.ArrayList;
import java.util.List;

import model.Member;

public class MemberDB {
	private Member CurrMember;
	private List<Member> members;
	private volatile static MemberDB instance;
	
	private MemberDB(){
		members = new ArrayList<Member>();
		members.add(new Member("Bram", "Robyns", "bramrobyns@hotmail.com"));
		members.add(new Member("Allen", "Scott", "allenScott@hotmail.com"));
	}
	
	public static MemberDB getInstance(){
		if(instance == null){
			synchronized(MemberDB.class){
				if(instance == null){
					instance = new MemberDB();
				}
			}
		}
		return instance;
	}
	
	public boolean addMember(Member member){
		if(member == null){
			return false;
		}else{
			members.add(member);
			return true;
		}
	}
	
	public boolean deleteMember(Member member){
		if(member == null){
			return false;
		}else{
			members.remove(member);
			return true;
		}
	}
	
	public Member getCurrMember() {
		return CurrMember;
	}
	
	public void setCurrMember(Member currMember) {
		CurrMember = currMember;
	}
	
	public List<Member> getAllMembers() {
		return members;
	}
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}	
	
	
}

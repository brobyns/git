package model.Facade;

import java.util.List;

import model.Admin;
import model.Group;
import model.Member;
import db.MemberDB;

public class FacadeImpl implements Facade{
	private MemberDB memberDB;

	public FacadeImpl(){
		memberDB = MemberDB.getInstance();
	}
	
	@Override
	public void createGroup(String groupName, List<Member> membersInvited) {
		Member m = getCurrentMember();
		Group group = new Group(new Admin(m.getFirstName(), m.getLastName(), m.getEmail()),groupName);
	}

	@Override
	public Member getCurrentMember() {
		return memberDB.getCurrMember();
	}

	@Override
	public List<Member> getAllMembers() {
		return memberDB.getAllMembers();
	}

	@Override
	public void settlePayments() {
		// TODO Auto-generated method stub
		
	}

}

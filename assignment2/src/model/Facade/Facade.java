package model.Facade;

import java.util.List;

import model.Member;

public interface Facade {
	void createGroup(String groupName, List<Member> membersInvited);
	Member getCurrentMember();
	List<Member> getAllMembers();
	void settlePayments();
}

package model.Facade;

import java.util.List;

import model.Expense;
import model.Group;
import model.Member;

public interface Facade {
	void createGroup(String groupName, List<Member> membersInvited);
	Member getCurrentMember();
	List<Member> getAllMembers();
	void settlePayments();
	List<Group> getAllGroups();
	List<Member> getMembersInGroup(int groupid);
	Group getGroupForId(int id);
	void writeExpense(List<Member> recipients, double amount, String date,
			String description);
	List<Expense> getExpensesPaidByMember(int memberid);
	List<Expense> getExpensesPaidToMember(int memberid);
	double getAmount(int id);
}

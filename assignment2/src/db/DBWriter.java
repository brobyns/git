package db;

import java.util.List;

import model.Expense;
import model.Group;
import model.Member;

public interface DBWriter {	
	void writeMember(String firstname, String lastname, String email) throws Exception;
	List<Member> getMembers();
	void closeConnection();
	List<Group> getGroups();
	void writeGroup(String groupname, List<Member> members);
	List<Member> getMembersInGroup(int groupid);
	Group getGroupForId(int id);
	void writeExpense(List<Member> recipients, double amount, String date,
			String description);
	List<Expense> getExpensesPaidByMember(int memberid);
	List<Expense> getExpensesPaidToMember(int memberid);
	
}

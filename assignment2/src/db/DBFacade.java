package db;

import java.util.List;

import model.Expense;
import model.Group;
import model.Member;

public interface DBFacade {
	DBWriter getWriter();
	void setWriter(DBWriter writer);
	void writeMember(String firstname, String lastname, String email) throws Exception;
	void closeConnection();
	List<Member> getMembers();
	void writeGroup(String groupname, List<Member> members);
	List<Group> getGroups();
	List<Member> getMembersInGroup(int groupid);
	Group getGroupForId(int id);
	void writeExpense(List<Member> recipients, double amount, String date,
			String description);
	List<Expense> getExpensesPaidByMember(int memberid);
	List<Expense> getExpensesPaidToMember(int memberid);
}

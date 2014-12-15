package db;

import java.util.List;

import model.Expense;
import model.Group;
import model.Member;

public class DBFacadeImpl implements DBFacade {

	private DBWriter writer;
	
	public DBFacadeImpl (DBWriter writer) {
		this.writer = writer;
	}

	@Override
	public void writeMember(String firstname, String lastname, String email) throws Exception {
		writer.writeMember(firstname, lastname, email);
	}
	
	public DBWriter getWriter() {
		return writer;
	}

	@Override
	public void setWriter(DBWriter writer) {
		this.writer = writer;
	}

	@Override
	public void closeConnection() {
		writer.closeConnection();
	}

	@Override
	public List<Member> getMembers() {
		return writer.getMembers();
	}
	
	@Override
	public List<Member> getMembersInGroup(int groupid) {
		return writer.getMembersInGroup(groupid);
	}
	
	public Group getGroupForId(int id){
		return writer.getGroupForId(id);
	}

	@Override
	public void writeGroup(String groupname, List<Member> members){
		writer.writeGroup(groupname, members);
		closeConnection();
	}

	@Override
	public List<Group> getGroups() {
		return writer.getGroups();
	}
	
	public void writeExpense(List<Member> recipients, double amount, String date,
			String description){
		writer.writeExpense(recipients, amount, date, description);
	}

	@Override
	public List<Expense> getExpensesPaidByMember(int memberid) {
		return writer.getExpensesPaidByMember(memberid);
	}

	@Override
	public List<Expense> getExpensesPaidToMember(int memberid) {
		return writer.getExpensesPaidToMember(memberid);
	}
}

package model.Facade;
import java.util.List;

import model.Expense;
import model.Group;
import model.Member;
import db.DBWriter;
import db.DBWriterFactory;
import db.MemberDB;
import db.DBFacade;
import db.DBFacadeImpl;

public class FacadeImpl implements Facade{
	private MemberDB memberDB;
	private DBFacade dbFacade;

	public FacadeImpl(String dbWriterType){
		DBWriter writer = DBWriterFactory.createDBWriter(dbWriterType);
		dbFacade = new DBFacadeImpl(writer);
	}

	@Override
	public void createGroup(String groupName, List<Member> membersInvited) {
		//Member m = getCurrentMember();
		dbFacade.writeGroup(groupName, membersInvited);
	}

	@Override
	public Member getCurrentMember() {
		return memberDB.getCurrMember();
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> members = null;
		members = dbFacade.getMembers();
		return members;
	}


	public List<Member> getMembersInGroup(int groupid){
		return dbFacade.getMembersInGroup(groupid);
	}

	public Group getGroupForId(int id){
		return dbFacade.getGroupForId(id);
	}

	@Override
	public void settlePayments() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Group> getAllGroups() {
		return dbFacade.getGroups();
	}

	public void writeExpense(List<Member> recipients, double amount, String date,
			String description){
		dbFacade.writeExpense(recipients, amount, date, description);
	}

	@Override
	public List<Expense> getExpensesPaidByMember(int memberid) {
		return dbFacade.getExpensesPaidByMember(memberid);
	}

	@Override
	public List<Expense> getExpensesPaidToMember(int memberid) {
		return dbFacade.getExpensesPaidToMember(memberid);
	}

	public double getAmount(int memberid){
		double amount = 0;
		List<Expense> expensesReceived = getExpensesPaidByMember(memberid);
		List<Expense> expensesPaid = getExpensesPaidToMember(memberid);
		for(Expense e : expensesPaid){
			amount += e.getAmount();
		}
		for(Expense e : expensesReceived){
			amount -= e.getAmount();
		}
		return amount;
	}
}

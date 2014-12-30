package model.Facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import service.StoreData;
import service.XMLWriter;

import model.Expense;
import model.Group;
import model.Member;
import model.Settings;
import db.DBWriter;
import db.DBWriterFactory;
import db.GroupDB;
import db.MemberDB;
import db.DBFacade;
import db.DBFacadeImpl;

public class FacadeImpl implements Facade {
	private DBFacade dbFacade;
	private MemberDB memberDB = MemberDB.getInstance();
	private GroupDB groupDB = GroupDB.getInstance();
	private Settings settings = Settings.getInstance();
	private DBWriter writer;

	public FacadeImpl(String dbWriterType) {
		writer = DBWriterFactory.createDBWriter(dbWriterType);
		dbFacade = new DBFacadeImpl(writer);
	}

	public DBWriter getDBWriter() {
		return writer;
	}

	@Override
	public void createGroup(Group group) {
		dbFacade.writeGroup(group);
	}

	@Override
	public void updateGroup(int groupId, String groupName,
			List<Member> membersInvited) {
		dbFacade.updateGroup(groupId, groupName, membersInvited);
	}

	@Override
	public Member getCurrentMember() {
		return memberDB.getCurrMember();
	}

	@Override
	public Map<Integer, Member> getMembers() {
		return memberDB.getMembers();
	}
	
	public Member getMemberForId(int id){
		return dbFacade.getMemberForId(id);
	}

	@Override
	public Map<Integer, Member> getMembersOnline() {
		return dbFacade.getMembers();
	}

	public List<Member> getMembersInGroup(int groupId) {
		return groupDB.getMembersInGroup(groupId);
	}

	@Override
	public void settlePayments() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<Integer, Group> getGroups() {
		return groupDB.getGroups();
	}

	@Override
	public Map<Integer, Group> getGroupsOnline() {
		return dbFacade.getGroups();
	}

	public void writeExpense(Expense expense, List<Member> recipients) {
		dbFacade.writeExpense(expense, recipients);
	}

	public Map<Integer, Double> getAmountsPaid(int senderId) {
		Map<Integer, Double> amountMap = new HashMap<Integer, Double>();
		Map<Integer, Member> members = getMembers();
		Member sender = members.get(senderId);
		for(Member m : members.values())
		for(Expense e : sender.getExpenses().values()){
			Log.v("bram", "number: "+e.getMembersPaidFor().size());
			for(int id : e.getMembersPaidFor()){
				if(amountMap.containsKey(id)){
					amountMap.put(id, amountMap.get(id) + (e.getAmount()/e.getMembersPaidFor().size()));
				}else{
					amountMap.put(id, e.getAmount()/e.getMembersPaidFor().size());
				}
			}
		}
		return amountMap;
	}

	public Map<Integer, Double> getAmountsReceived(int receiverId, Group group) {
		Map<Integer, Double> amountMap = new HashMap<Integer, Double>();
		Map<Integer, Member> members = getMembers();
		for (Member sender : group.getMembers()) {
			for (Expense e : sender.getExpenses().values()){
				if(e.getMembersPaidFor().contains(receiverId)) { 
					if(amountMap.containsKey(sender.getId())){
						amountMap.put(sender.getId(), amountMap.get(sender.getId()) - (e.getAmount()/e.getMembersPaidFor().size()));
					}else{
						amountMap.put(sender.getId(), -e.getAmount()/e.getMembersPaidFor().size());
					}
				}
			}
		}
		return amountMap;
	}

	@Override
	public void clearDatabase() {
		dbFacade.clearDatabase();
	}

	public void writeMembers(List<Member> members) {
		dbFacade.writeMembers(members);
	}

	public void writeGroups(List<Group> groups) {
		dbFacade.writeGroups(groups);
	}

	public void writeExpenses(List<Expense> expenses) {
		dbFacade.writeExpenses(expenses);
	}

	@Override
	public boolean checkIfNewDataAvailable() {
		return true;
	}

	@Override
	public void createMember(Member member) {
		dbFacade.writeMember(member);
	}

	public void writeSettings(Context context, Settings settings) {
		StoreData storeData = new StoreData(context, null, null);
		storeData.saveSettings(settings);
	}
}

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Expense;
import model.Group;
import model.Member;
import model.Settings;

import android.os.StrictMode;

public class OnlineDBWriter implements DBWriter {

	private Connection connection;
	private PreparedStatement statement;
	private Properties connectionProperties;
	private Settings settings = Settings.getInstance();
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://gegevensbanken.khleuven.be:51415/probeer";

	public OnlineDBWriter(){
		if (android.os.Build.VERSION.SDK_INT >= 9) {														//Schijnt kan SDK <= 9 geen HTTP connecties maken
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		initializeProperties();
		try {
			connection = DriverManager.getConnection(DB_URL,connectionProperties);
			connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initializeProperties(){
		connectionProperties =new Properties();
		connectionProperties.setProperty("user", "r0265929");
		connectionProperties.setProperty("password", "Zy5xfx10");
		connectionProperties.setProperty("ssl", "true");
		connectionProperties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
	}

	@Override
	public void writeMember(String firstname, String lastname, String email) {
		ResultSet set = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM member WHERE firstname = '" + firstname 
					+ "' AND lastname ='" + lastname + "'");
			set = statement.executeQuery();
			if(!set.next()) { 																		//member bestaat nog niet
				statement = connection.prepareStatement("INSERT INTO member (firstname, lastname, email) VALUES(?,?,?)");
				statement.setString(1, firstname);
				statement.setString(2, lastname);
				statement.setString(3, email);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Member> getMembers() {
		List<Member> members = new ArrayList<Member>();
		try {
			statement = connection.prepareStatement("SELECT * FROM member");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				members.add(new Member(set.getInt(1), set.getString(2), set.getString(3), set.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return members;
	}
	
	public Group getGroupForId(int id){
		Group group = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM groups WHERE id= '" + id +"'");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				group = new Group(set.getInt(1), set.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}

	private Member getMemberForName(String firstname, String lastname){
		Member member = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM member WHERE firstname= '" + firstname +"' AND lastname='" + lastname + "'");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				member = new Member(set.getInt(1), set.getString(2), set.getString(3), set.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return member;
	}

	private Group getGroupForName(String name){
		Group group = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM groups WHERE groupname= '" + name +"'");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				group = new Group(set.getInt(1), set.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}

	public List<Member> getMembersInGroup(int groupid){
		List<Member> members = new ArrayList<Member>();
		try {
			statement = connection.prepareStatement("SELECT member.id, firstname, lastname, email FROM group_member " +
					"INNER JOIN groups ON group_member.groupid = '" + groupid 
					+"' INNER JOIN member ON group_member.memberid = member.id GROUP BY member.id");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				members.add(new Member(set.getInt(1), set.getString(2), set.getString(3), set.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return members;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeExpense(List<Member> recipients, double amount, String date, String description) {
		try {
			statement = connection.prepareStatement("INSERT INTO expense (senderid, amount, expensedate, description) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, settings.getCurrentMember().getId());
			statement.setDouble(2, amount);
			statement.setString(3, date);
			statement.setString(4, description);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ResultSet set;
		try {
			set = statement.getGeneratedKeys();
			if(set.next()){
				writeExpenseReceivers(set.getInt(1), recipients);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void writeExpenseReceivers(int expenseid, List<Member> recipients) {
		try {
			for(int i = 0; i < recipients.size(); i++){
				statement = connection.prepareStatement("INSERT INTO expense_receiver (expenseid, receiverid) VALUES(?,?)");
				statement.setInt(1, expenseid);
				statement.setInt(2, recipients.get(i).getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeGroup(String groupname, List<Member> members) {
		try {
			if(getGroupForName(groupname) == null) { 
				statement = connection.prepareStatement("INSERT INTO groups (groupname) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, groupname);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet set;
		try {
			set = statement.getGeneratedKeys();
			if(set.next() && !members.isEmpty()){
				writeGroupMembers(set.getInt(1), members);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void writeGroupMembers(int groupid, List<Member> members){
		try {
			for(int i = 0; i < members.size(); i++){
				statement = connection.prepareStatement("INSERT INTO group_member (groupid, memberid) VALUES(?,?)");
				statement.setInt(1, groupid);
				statement.setInt(2, members.get(i).getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Expense> getExpensesPaidToMember(int memberid){      //betaald door mij aan member
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			statement = connection.prepareStatement("SELECT expense.id, senderid, amount, expensedate, description FROM expense INNER JOIN expense_receiver ON expense.id = expense_receiver.expenseid WHERE senderid ='" + settings.getCurrentMember().getId()+ "' AND receiverid ='" + memberid  +"'");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				expenses.add(new Expense(set.getInt(1), set.getInt(2),set.getDouble(3), set.getString(4), set.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return expenses;
	}
	
	public List<Expense> getExpensesPaidByMember(int memberid){     //betaald door member aan mij
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			statement = connection.prepareStatement("SELECT expense.id, senderid, amount, expensedate, description FROM expense INNER JOIN expense_receiver ON expense.id = expense_receiver.expenseid WHERE senderid ='" + memberid+ "' AND receiverid ='" + settings.getCurrentMember().getId()  +"'");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				expenses.add(new Expense(set.getInt(1), set.getInt(2),set.getDouble(3), set.getString(4), set.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return expenses;
	}
	
	

	@Override
	public List<Group> getGroups() {
		List<Group> groups = new ArrayList<Group>();
		try {
			statement = connection.prepareStatement("SELECT * FROM groups");
			ResultSet set = statement.executeQuery();

			for(@SuppressWarnings("unused")int i = 0; set.next(); i++) {
				groups.add(new Group(set.getInt(1),set.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				statement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return groups;
	}

}

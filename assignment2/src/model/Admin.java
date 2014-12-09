package model;

import java.util.List;

public class Admin extends Member{
	
	private List<Group> adminGroups;

	public Admin(String firstName, String lastName, String email) {
		super(firstName, lastName, email);
	}

}

package com.wits.witssrcconnect.utils;

//import com.pravichi.tools.db.DatabaseHelper;
//import com.pravichi.tools.utils.CVUtils;

import android.content.ContentValues;

public class Person {
	public static final String STUDENT = "1";
	public static final String STAFF = "2";
	String name;
	String firstname;
	String lastname;
	String email;
	String id;
	String access;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAccessLevel(String access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + ", id=" + id + ", access=" + access + "]";
	}

	/*public long persist(DatabaseHelper db) {
		ContentValues cv = db.getFirstRow("select * from users where person_number=?",id);
		if (cv==null){
			return insert(db);
		} else {
			CVUtils.printCV(cv); 
			return cv.getAsLong("id"); 
		}
	}
	
	public long insert(DatabaseHelper db){
		ContentValues inserter=new ContentValues();
		inserter.put("person_number",id);
		inserter.put("firstname", firstname);
		inserter.put("lastname", lastname);
		inserter.put("usertype_id", 1);
		long id = db.insert("users", inserter);
		return id;
	}*/
	

}

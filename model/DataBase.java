package model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
	private ArrayList <Person> people; 
	
	public DataBase() {
		people = new ArrayList <Person>(); 
	}
	
	public void addPerson(Person p) {
		people.add(p);
	}
	
	public List<Person> getPeople() {
		return people; 
		
	}
}

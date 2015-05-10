package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
	private List<Person> people;
	private Connection con;

	public DataBase() {
		people = new LinkedList<Person>();
	}

	public void connect() throws Exception {

		if (con != null)
			return;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = "jdbc:mysql://localhost:3306/swingtest";
		con = DriverManager.getConnection(url, "root", "root");
		System.out.println("Connected");
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
				System.out.println("Disconnected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can not close connection");
			}
		}
	}

	public void addPerson(Person p) {
		people.add(p);
	}

	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);

	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Person[] persons = people.toArray(new Person[people.size()]);
		oos.writeObject(persons);
		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Person[] persons = (Person[]) ois.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}

	public void removePerson(int index) {
		people.remove(index);
	}

}

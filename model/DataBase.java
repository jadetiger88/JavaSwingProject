package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
	private List<Person> people;
	private Connection con;
	private int port;
	private String user;
	private String password;

	public DataBase() {
		people = new LinkedList<Person>();
	}

	public void configure(int port, String user, String password)
			throws Exception {
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public void connect() throws Exception {
		System.out.println("Enter db.connect()");

		if (con != null) {
			System.out.println("Exit db.connect: " + con);
			return;
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = "jdbc:mysql://localhost:" + port + "/swingtest";
		con = DriverManager.getConnection(url, user, password);
		System.out.println("Exit db.connect: " + con + ":" + user + ":"
				+ password + ":" + port);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	public void save() throws SQLException {
		if (con == null) {
			return;
		}
		String checkExistSql = "select count(*) as count from people where id=?";
		PreparedStatement checkExist = con.prepareStatement(checkExistSql);
		String insertSql = "insert into people (id, name, age, employment, tax_id, us_citizen, gender, occupation) value(?,?,?,?,?,?,?,?)";
		PreparedStatement insert = con.prepareStatement(insertSql);
		String updateSql = "update people set name=?, age=?, employment=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
		PreparedStatement updateOp = con.prepareStatement(updateSql);

		for (Person person : people) {

			int id = person.getId();
			String name = person.getName();
			String ageCategory = person.getAgeCategory().name();
			String employmentCategory = person.getEmpType().name();
			String tax_id = person.getTaxID();
			boolean us_citizen = person.isUsCitizen();
			String gender = person.getGender().name();
			String occupation = person.getOccupation();

			checkExist.setInt(1, id);
			ResultSet result = checkExist.executeQuery();
			result.next();
			int count = result.getInt(1);
			if (count == 0) {
				insert.setInt(1, id);
				insert.setString(2, name);
				insert.setString(3, ageCategory);
				insert.setString(4, employmentCategory);
				insert.setString(5, tax_id);
				insert.setBoolean(6, us_citizen);
				insert.setString(7, gender);
				insert.setString(8, occupation);
				insert.executeUpdate();

			} else {
				updateOp.setString(1, name);
				updateOp.setString(2, ageCategory);
				updateOp.setString(3, employmentCategory);
				updateOp.setString(4, tax_id);
				updateOp.setBoolean(5, us_citizen);
				updateOp.setString(6, gender);
				updateOp.setString(7, occupation);
				updateOp.setInt(8, id);
				updateOp.executeUpdate();

			}
		}
		insert.close();
		updateOp.close();
		checkExist.close();
	}

	public void load() throws SQLException {
		if (con == null) {
			return;
		}
		people.clear();
		String sql = "select id, name, age, employment, tax_id, us_citizen, gender, occupation from people order by name";
		Statement select = con.createStatement();
		ResultSet results = select.executeQuery(sql);
		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String employment = results.getString("employment");
			String tax_id = results.getString("tax_id");
			boolean us_citizen = results.getBoolean("us_citizen");
			String gender = results.getString("gender");
			String occupation = results.getString("occupation");
			Person person = new Person(id, name, occupation,
					AgeCategory.valueOf(age),
					EmploymentCategory.valueOf(employment), us_citizen, tax_id,
					Gender.valueOf(gender));
			people.add(person);
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

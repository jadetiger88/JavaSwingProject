package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.AgeCategory;
import model.DataBase;
import model.EmploymentCategory;
import model.Gender;
import model.Person;
import view.FormEvent;

public class Controller {
	DataBase db = new DataBase();

	public List<Person> getPeople() {
		return db.getPeople();
	}

	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCategory = ev.getAgeCategory();
		String empType = ev.getEmployeeType();
		boolean usCitizen = ev.isUsCitizen();
		String taxID = ev.getTaxID();
		String gender = ev.getGender();

		AgeCategory ageCatEnum = getAgeCatEnum(ageCategory);
		EmploymentCategory empEnum = getEmpEnum(empType);
		Gender genderEnum = getGenderEnum(gender);

		Person person = new Person(name, occupation, ageCatEnum, empEnum,
				usCitizen, taxID, genderEnum);
		db.addPerson(person);
	}

	private Gender getGenderEnum(String s) {
		Gender genderEnum;

		if (s.equals("make")) {
			genderEnum = Gender.MALE;
		} else if (s.equals("female")) {
			genderEnum = Gender.FEMALE;
		} else {
			genderEnum = Gender.OTHER;
		}
		return genderEnum;
	}

	private EmploymentCategory getEmpEnum(String s) {
		EmploymentCategory empTypeEnum;

		if (s.equals("employed")) {
			empTypeEnum = EmploymentCategory.EMPLOYED;
		} else if (s.equals("unemployed")) {
			empTypeEnum = EmploymentCategory.UNEMPLOYED;
		} else if (s.equals("employed")) {
			empTypeEnum = EmploymentCategory.SELF_EMPLOYED;
		} else {
			empTypeEnum = EmploymentCategory.OTHER;
		}

		return empTypeEnum;
	}

	private AgeCategory getAgeCatEnum(int ageIndex) {

		AgeCategory ageEnum;

		switch (ageIndex) {
		case 0:
			ageEnum = AgeCategory.CHILD;
			break;
		case 1:
			ageEnum = AgeCategory.ADULT;
			break;
		case 2:
			ageEnum = AgeCategory.SENIOR;
			break;
		default:
			ageEnum = AgeCategory.ADULT;
			break;
		}

		return ageEnum;
	}

	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}

	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void removeRow(int index) {
		db.removePerson(index);
	}

	public void save() throws SQLException {
		db.save();
	}

	public void load() throws SQLException {
		db.load();
	}

	public void configure(int port, String user, String password)
			throws Exception {
		db.disconnect();
		db.configure(port, user, password);
	}

	public void connect() throws Exception {
		db.connect();
	}

	public void disconnect() {
		db.disconnect();
	}
}

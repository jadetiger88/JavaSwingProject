import java.sql.SQLException;

import model.AgeCategory;
import model.DataBase;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class TestDataBase {

	public static void main(String[] args) {
		System.out.println("Running Database Test");
		DataBase db = new DataBase();

		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db.addPerson(new Person("Joe", "Builder", AgeCategory.ADULT,
				EmploymentCategory.EMPLOYED, true, "777", Gender.MALE));
		db.addPerson(new Person("Sue", "Eng", AgeCategory.ADULT,
				EmploymentCategory.SELF_EMPLOYED, false, "null", Gender.FEMALE));

		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		db.disconnect();
	}

}

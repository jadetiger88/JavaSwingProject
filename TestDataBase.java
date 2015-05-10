import model.DataBase;

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
		db.disconnect();
	}

}

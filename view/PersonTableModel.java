package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {

	private List<Person> db;
	private String[] colNames = { "ID", "Name", "Occupation", "Agew Category",
			"Employment Category", "US Citizen", "Tax ID" };

	public void PersonalDataModel() {
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		return (db.size());
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object val = null;

		Person person = db.get(row);
		switch (col) {
		case 0:
			val = person.getId();
			break;

		case 1:
			val = person.getName();
			break;

		case 2:
			val = person.getOccupation();
			break;

		case 3:
			val = person.getAgeCategory();
			break;

		case 4:
			val = person.getEmpType();
			break;

		case 5:
			val = person.isUsCitizen();
			break;

		case 6:
			val = person.getTaxID();
			break;

		}

		return val;
	}

}

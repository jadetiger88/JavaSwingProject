package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.EmploymentCategory;
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

	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 1:
			return true;
		case 4:
			return true; // Add this
		case 5:
			return true;
		default:
			return false;
		}
	}

	public void setValueAt(Object value, int row, int col) {

		Person person = db.get(row);
		switch (col) {
		case 1:
			person.setName((String) value);
			return;
		case 4:
			person.setEmpType((EmploymentCategory) value);
			return;
		case 5:
			person.setUsCitizen((Boolean) value);
			return;
		default:
			return;
		}
	}

	@Override
	public Class<?> getColumnClass(int col) {

		Class<?> val = null;

		switch (col) {
		case 0:
			val = Integer.class;
			break;

		case 1:
			val = String.class;
			break;

		case 2:
			val = String.class;
			break;

		case 3:
			val = String.class;
			break;

		case 4:
			val = EmploymentCategory.class;
			break;

		case 5:
			val = Boolean.class;
			break;

		case 6:
			val = String.class;
			break;

		}

		return val;
	}

}

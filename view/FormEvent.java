package view;
import java.util.EventObject;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class FormEvent extends EventObject {

	private String name;
	private String occupation;
	private int ageCategory;
	private String empType;
	private boolean usCitizen;
	private String taxID;
	private String gender;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String name, String occupation,
			int ageCategory, String empType, boolean USCitizen, String taxID,
			String gender) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empType = empType;
		this.usCitizen = USCitizen;
		this.taxID = taxID;
		this.gender = gender;
	}

	public String getTaxID() {
		return taxID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAgeCategory() {
		return ageCategory;
	}

	public String getEmployeeType() {
		// TODO Auto-generated method stub
		return empType;
	}

	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}
	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}


}

package model;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1098075348234497929L;
	private static int count = 0;
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory empType;
	private boolean usCitizen;
	private String taxID;
	private Gender gender;

	public Person(String name, String occupation, AgeCategory ageCategory,
			EmploymentCategory empType, boolean usCitizen, String taxID,
			Gender gender) {

		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empType = empType;
		this.usCitizen = usCitizen;
		this.taxID = taxID;
		this.gender = gender;
		this.id = count++;
		return;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public EmploymentCategory getEmpType() {
		return empType;
	}

	public void setEmpType(EmploymentCategory empType) {
		this.empType = empType;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}

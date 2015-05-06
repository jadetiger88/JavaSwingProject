import java.util.EventObject;

public class FormEvent extends EventObject {

	private String name;
	private String occupation;
	private int ageCategory;
	private String empType; 
	private boolean USCitizen; 
	private String TaxID; 

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, 
					 String name, 
					 String occupation, 
					 int ageCategory, 
					 String empType, 
					 boolean USCitizen,
					 String taxID) {
		super(source);
		this.name = name; 
		this.occupation = occupation;
		this.ageCategory = ageCategory; 
		this.empType = empType; 
		this.USCitizen = USCitizen; 
		this.TaxID = taxID; 
	}
	public String getTaxID() {
		return TaxID;
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

}

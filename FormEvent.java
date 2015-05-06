import java.util.EventObject;

public class FormEvent extends EventObject {

	private String name;
	private String occupation;
	private int ageCategory;
	private String empType; 

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String name, String occupation, int ageCategory, String empType) {
		super(source);
		this.name = name; 
		this.occupation = occupation;
		this.ageCategory = ageCategory; 
		this.empType = empType; 
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

package view;

class ServerInfo {
	int id;
	String name;
	boolean checked;

	ServerInfo(String name, int id) {

		this(name, id, false);
	}

	ServerInfo(String name, int id, boolean checked) {

		this.name = name;
		this.id = id;
		this.checked = checked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}

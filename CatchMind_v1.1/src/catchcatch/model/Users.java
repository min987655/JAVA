package catchcatch.model;


public class Users {
	
	private int pryNum;
	private String name;
	
	public Users(String name) {
		super();
		this.name = name;
	}

	public Users(int pryNum, String name) {
		this.pryNum = pryNum;
		this.name = name;
	}

	public int getPryNum() {
		return pryNum;
	}

	public void setPryNum(int pryNum) {
		this.pryNum = pryNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

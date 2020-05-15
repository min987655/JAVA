package catchcatch.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
public class Users {
	
	private int id;
	private String userName;
	private String password;
	
	@Builder
	public Users(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}	
	
	@Override
	public String toString() {
		return id + ":" + userName + ":" + password;
	}
}

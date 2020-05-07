package info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	private int pryNumber;
	private String idName;
	private String password;

	@Override
	public String toString() {
		return "User [pryNumber=" + pryNumber + ", idName" + idName + ", password" + password + "]";
	}
}
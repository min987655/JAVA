package DO;

import java.util.ArrayList;

import action.MainHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	private int rID;
	private String title;
	private String rPassword;
	private String userCount;
	private String masterName;
	private String subject;
	private int condtionP;
	public ArrayList<MainHandler> roomInUserList;
	
	@Override
	public String toString() {
		return "Room [rID=" + rID +", title=" + title + ", rPassword" +
				rPassword + ", userCount=" + userCount + ", masterName"
				+ masterName + ", condtionP" + condtionP + "]";
	}
}
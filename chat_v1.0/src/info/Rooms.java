package info;

import java.util.ArrayList;

import action.MainHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {

	private int rID;
	private String title;
	private String userCount;
	private String masterName;
	private int condtionP;
	public ArrayList<MainHandler> roomInUserList;
	
	@Override
	public String toString() {
		return "Room [rID=" + rID +", title=" + title + ", userCount=" + userCount + ", masterName"
				+ masterName + ", condtionP" + condtionP + "]";
	}
}
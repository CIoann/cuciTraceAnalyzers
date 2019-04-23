package DataStructures;

import java.util.ArrayList;
import java.util.List;

// SCEGAZEDATA per user! to segment the whole file.
public class puSceGazeData {

	List<SceGazeData> user = new ArrayList<SceGazeData>();

	public puSceGazeData() {
		super();
	}

	public List<SceGazeData> getUser() {
		return user;
	}

	public void setUser(List<SceGazeData> user) {
		this.user = user;
	}
	
	
}

package DataStructures;

public class Gaze {
	private String user;
	private String gaze_id; 
	private String gaze_session_id; 
	private String gaze_name; 
	private String gaze_type;
	private String x;
	private String y;
	private String left_validation;
	private String right_validation;
	private String left_pupil_diameter;
	private String right_pupil_diameter;
	private String timestamp;
	private String session_time;
	private String tracker_time; 
	private String system_time; 
	private String nano_time; 
	private String line_height;
	private String font_height;
	private String line;
	private String col;
	private String line_base_x;
	private String line_base_y;


	public Gaze(String user, String gaze_id, String gaze_session_id, String gaze_name, String gaze_type, String x, String y,
			String left_validation, String right_validation, String left_pupil_diameter, String right_pupil_diameter,
			 String timestamp, String bg, String tracker_time, String system_time, String nano_time,
			String line_height, String font_height, String line, String col, String line_base_x, String line_base_y) {
		super();
		this.user=user;
		this.gaze_id = gaze_id;
		this.gaze_session_id = gaze_session_id;
		this.gaze_name = gaze_name;
		this.gaze_type = gaze_type;
		this.x = x;
		this.y = y;
		this.left_validation = left_validation;
		this.right_validation = right_validation;
		this.left_pupil_diameter = left_pupil_diameter;
		this.right_pupil_diameter = right_pupil_diameter;
		this.timestamp = timestamp;
		this.session_time = bg;
		this.tracker_time = tracker_time;
		this.system_time = system_time;
		this.nano_time = nano_time;
		this.line_height = line_height;
		this.font_height = font_height;
		this.line = line;
		this.col = col;
		this.line_base_x = line_base_x;
		this.line_base_y = line_base_y;
	}
	public Gaze(String user, String gaze_id, String gaze_name, String gaze_type, String timestamp) {
		super();
		this.user=user;
		this.gaze_id = gaze_id;
		this.gaze_name = gaze_name;
		this.gaze_type = gaze_type;
		this.timestamp = timestamp;

	}

	public void print(){
		System.out.println(gaze_id +gaze_session_id+gaze_name + gaze_type +x +y +
				left_validation + right_validation +left_pupil_diameter + right_pupil_diameter 
				+ timestamp + session_time +tracker_time + system_time + nano_time
				+ line_height + font_height +line + col + line_base_x + line_base_y
				);
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getGaze_id() {
		return gaze_id;
	}

	public void setGaze_id(String gaze_id) {
		this.gaze_id = gaze_id;
	}

	public String getGaze_session_id() {
		return gaze_session_id;
	}

	public void setGaze_session_id(String gaze_session_id) {
		this.gaze_session_id = gaze_session_id;
	}

	public String getGaze_name() {
		return gaze_name;
	}

	public void setGaze_name(String gaze_name) {
		this.gaze_name = gaze_name;
	}

	public String getGaze_type() {
		return gaze_type;
	}

	public void setGaze_type(String gaze_type) {
		this.gaze_type = gaze_type;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getLeft_validation() {
		return left_validation;
	}

	public void setLeft_validation(String left_validation) {
		this.left_validation = left_validation;
	}

	public String getRight_validation() {
		return right_validation;
	}

	public void setRight_validation(String right_validation) {
		this.right_validation = right_validation;
	}

	public String getLeft_pupil_diameter() {
		return left_pupil_diameter;
	}

	public void setLeft_pupil_diameter(String left_pupil_diameter) {
		this.left_pupil_diameter = left_pupil_diameter;
	}

	public String getRight_pupil_diameter() {
		return right_pupil_diameter;
	}

	public void setRight_pupil_diameter(String right_pupil_diameter) {
		this.right_pupil_diameter = right_pupil_diameter;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSession_time() {
		return session_time;
	}

	public void setSession_time(String session_time) {
		this.session_time = session_time;
	}

	public String getTracker_time() {
		return tracker_time;
	}

	public void setTracker_time(String tracker_time) {
		this.tracker_time = tracker_time;
	}

	public String getSystem_time() {
		return system_time;
	}

	public void setSystem_time(String system_time) {
		this.system_time = system_time;
	}

	public String getNano_time() {
		return nano_time;
	}

	public void setNano_time(String nano_time) {
		this.nano_time = nano_time;
	}

	public String getLine_height() {
		return line_height;
	}

	public void setLine_height(String line_height) {
		this.line_height = line_height;
	}

	public String getFont_height() {
		return font_height;
	}

	public void setFont_height(String font_height) {
		this.font_height = font_height;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getLine_base_x() {
		return line_base_x;
	}

	public void setLine_base_x(String line_base_x) {
		this.line_base_x = line_base_x;
	}

	public String getLine_base_y() {
		return line_base_y;
	}

	public void setLine_base_y(String line_base_y) {
		this.line_base_y = line_base_y;
	}

}

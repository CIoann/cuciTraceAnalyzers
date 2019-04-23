package DataStructures;

public class Sce {
	private String user;
	private String sce_gaze_id;
	private String sce_name;
	private String sce_type;
	private String how;
	private String total_length;
	private String start_line;
	private String end_line;
	private String start_col;
	private String end_col;
	private String depth;
	public Sce(String user, String sce_gaze_id, String sce_name, String sce_type, String how, String total_length, String start_line,
			String end_line, String start_col, String end_col, String depth) {
		super();
		this.user = user;
		this.sce_gaze_id = sce_gaze_id;
		this.sce_name = sce_name;
		this.sce_type = sce_type;
		this.how = how;
		this.total_length = total_length;
		this.start_line = start_line;
		this.end_line = end_line;
		this.start_col = start_col;
		this.end_col = end_col;
		this.depth = depth;
	}
	
	
	
	public Sce(String user, String sce_gaze_id, String sce_name, String sce_type, String how) {
		super();
		this.user = user;
		this.sce_gaze_id = sce_gaze_id;
		this.sce_name = sce_name;
		this.sce_type = sce_type;
		this.how = how;
	}



	public void print(){
		System.out.println(sce_gaze_id + sce_name + sce_type + how + total_length + start_line + end_line
				+start_col + end_col + depth
				);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSce_gaze_id() {
		return sce_gaze_id;
	}

	public void setSce_gaze_id(String sce_gaze_id) {
		this.sce_gaze_id = sce_gaze_id;
	}

	public String getSce_name() {
		return sce_name;
	}

	public void setSce_name(String sce_name) {
		this.sce_name = sce_name;
	}

	public String getSce_type() {
		return sce_type;
	}

	public void setSce_type(String sce_type) {
		this.sce_type = sce_type;
	}

	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
	}

	public String getTotal_length() {
		return total_length;
	}

	public void setTotal_length(String total_length) {
		this.total_length = total_length;
	}

	public String getStart_line() {
		return start_line;
	}

	public void setStart_line(String start_line) {
		this.start_line = start_line;
	}

	public String getEnd_line() {
		return end_line;
	}

	public void setEnd_line(String end_line) {
		this.end_line = end_line;
	}

	public String getStart_col() {
		return start_col;
	}

	public void setStart_col(String start_col) {
		this.start_col = start_col;
	}

	public String getEnd_col() {
		return end_col;
	}

	public void setEnd_col(String end_col) {
		this.end_col = end_col;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

}

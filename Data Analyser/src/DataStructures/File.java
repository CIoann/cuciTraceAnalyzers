package DataStructures;

public class File {


	private String filename = "kokos";
	private String variant;
	private String gazeID ;
	private String tsStart;
	private String tsEnd;
	private String task;
	private String line;
	private String artifact;
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public File(String filename, String variant, String gazeID, String line, String tsStart, String tsEnd, String task, String artifact) {
		super();
		this.filename = filename;
		this.variant = variant;
		this.gazeID = gazeID;
		this.tsStart = tsStart;
		this.tsEnd = tsEnd;
		this.line=line;
		this.task = task;
		this.setArtifact(artifact);
	}
	
	public File() {
		// TODO Auto-generated constructor stub
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public String getGazeID() {
		return gazeID;
	}
	public void setGazeID(String gazeID) {
		this.gazeID = gazeID;
	}
	public String getTsStart() {
		return tsStart;
	}
	public void setTsStart(String tsStart) {
		this.tsStart = tsStart;
	}
	public String getTsEnd() {
		return tsEnd;
	}
	public void setTsEnd(String tsEnd) {
		this.tsEnd = tsEnd;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}
	
}

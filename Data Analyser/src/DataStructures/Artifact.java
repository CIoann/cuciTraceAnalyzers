package DataStructures;

public class Artifact {

	private String artifact = "kokos";
	private String variant;
String gaze ;
	private String tsStart;
	private String tsEnd;
	private String task;

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
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

	public String getGaze() {
		return gaze;
	}

	public void setGaze(String gaze) {
		this.gaze = gaze;
	}

	public Artifact(String artifact, String gaze, String variant, String tsStart, String tsEnd, String task) {
		super();
		this.gaze=gaze;
		this.artifact = artifact;
		this.variant = variant;
		this.tsStart = tsStart;
		this.tsEnd = tsEnd;
		this.task = task;
	}
}

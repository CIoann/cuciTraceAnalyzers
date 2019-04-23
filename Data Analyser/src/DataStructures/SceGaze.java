package DataStructures;

public class SceGaze {
	Gaze gaze;
	Sce sce;
	String task;
	String taskType;
	String artifact= "kokos";
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public SceGaze(Gaze gaze, Sce sce) {
		this.setGaze(gaze);
		this.setSce(sce);
		setTask(null);
		setTaskType(null);
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Sce getSce() {
		return sce;
	}

	public void setSce(Sce sce) {
		this.sce = sce;
	}

	public Gaze getGaze() {
		return gaze;
	}

	public void setGaze(Gaze gaze) {
		this.gaze = gaze;
	}

	public void print() {
		gaze.print();
		sce.print();
	}

}

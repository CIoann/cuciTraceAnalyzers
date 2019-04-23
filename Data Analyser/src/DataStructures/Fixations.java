package DataStructures;

public class Fixations {

	String method;
	String file;
	String line;
	long duration;
	public int count;
	public Fixations(String method, String file, String line, int count) {
		super();
		this.method = method;
		this.file = file;
		this.line = line;
		this.count=0;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	
}

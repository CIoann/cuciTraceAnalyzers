package DataStructures;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// Use this class when using ReadManager
public class SceGazeData {

	String variant;
	String id;
	String file;
	String method;
	String linenumber;
	String tsStart;
	String tsEnd;
	String task;
	String artifact;
	Date start;
	Date end;
	long duration;

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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTsStart() {
		return tsStart;
	}

	public void setTsStart(String tsStart) {
		this.tsStart = tsStart;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getLinenumber() {
		return linenumber;
	}

	public void setLinenumber(String linenumber) {
		this.linenumber = linenumber;
	}

	public SceGazeData(String variant, String id, String file, String method, String task, String linenumber, String tsStart,
			String tsEnd) {
		super();
		this.variant = variant;
		this.id = id;
		this.file = file;
		this.method = method;

		this.task = task;

		this.linenumber = linenumber;
		this.tsStart = tsStart;
		this.tsEnd = tsEnd;

		this.artifact = "SourceCode";
		try {
			setDuration();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public SceGazeData(String variant, String file, String tsStart, String tsEnd, String task) {
		this.variant = variant;
		this.file = file;
		this.tsStart = tsStart;
		this.tsEnd = tsEnd;
		this.task = task;
		this.artifact = "SourceCode";
		}

	public long getDuration() {
		return this.duration;
	}

	public void print() {
		// System.out.println(variant + file + tsStart + tsEnd + task+ artifact +id
		// +linenumber +method
		// );
	}

	public void Convert() throws ParseException, InterruptedException {
		int sb1 = tsStart.indexOf('T') + 1;
		int sb2 = tsStart.indexOf('+');
		String timeIs = tsStart.substring(sb1, sb2);
		int miliS = timeIs.indexOf('.') + 1;
		String dStart = timeIs.substring(0, miliS - 1);
		DateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");

		int sb3 = tsEnd.indexOf('T') + 1;
		int sb4 = tsEnd.indexOf('+');
		String timeIs2 = tsEnd.substring(sb3, sb4);
		int miliE = timeIs2.indexOf('.') + 1;
		String dEnd = timeIs2.substring(0, miliE - 1);

		DateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

		Date dateStart = sdf1.parse(dStart);
		Date dateEnd = sdf2.parse(dEnd);

		long dura = dateEnd.getTime() - dateStart.getTime();
		String msStart = timeIs.substring(miliS);
		while (msStart.length() < 3) {
			msStart = msStart + '0';
			// System.out.println(msEnd);
		}

		int miliStart = Integer.parseInt(msStart);

		String msEnd = timeIs2.substring(miliE);
		// System.out.println(msEnd);
		/*
		 * if (msEnd.length()<1) {
		 * 
		 * }
		 */
		while (msEnd.length() < 3) {
			msEnd = msEnd + '0';
			// System.out.println(msEnd);
		}

		int miliEnd = Integer.parseInt(msEnd);

		int ms = miliEnd - miliStart;
		duration = ms + dura;

	}

	public void setDuration() throws ParseException {
		try {
			Convert();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

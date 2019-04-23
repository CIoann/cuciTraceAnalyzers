package Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataStructures.Artifact;
import DataStructures.File;
import DataStructures.SceGaze;
import DataStructures.Task;

public class OrganiseManager {

	private static final String READ_RAW = "/Users/ConnieZalo/Desktop/simple/RawData/Step3Combined/raw15"
			+ ".csv";
	private static final String writeArtifactLevel = "/Users/ConnieZalo/Desktop/simple/artifactLevel7_vFinal.csv";

	private static final String writeFileLevel = "/Users/ConnieZalo/Desktop/simple/FileLevel/filelevel15_v2.csv";
	// private static final String writeFileLevel =
	// "/Users/ConnieZalo/Desktop/simple/fileLevel8_test0.csv";

	private static final String writeRawTask = "/Users/ConnieZalo/Desktop/simple/rbt8_test0.csv";

	ReadManager reader = new ReadManager();
	WriteManager writer = new WriteManager();

	List<SceGaze> rawData = new ArrayList<SceGaze>();

	List<Artifact> artSteps = new ArrayList<Artifact>();

	List<Artifact> artSource = new ArrayList<Artifact>();

	List<File> dupfileAll = new ArrayList<File>();
	List<File> fileAll = new ArrayList<File>();

	public void organizeTask() {
		rawData = reader.readRaw(READ_RAW);
		fillInTasks(setTask());
		writer.writeOrganizeTaskData(rawData, writeRawTask);

		segmentByArtifact("Task", "TASK");
		segmentByArtifact(".feature", "FEATURE");
		segmentByArtifact("Steps.java", "STEP CODE");
		segmentSourceCode();
		// segmentByTask("Task", "TASK");

		writer.writeArtifactAbstraction(artSteps, artSource, writeArtifactLevel);

	}

	public void organizeByFile() {
		rawData = reader.readRaw(READ_RAW);
		fillInTasks(setTask());
		writer.writeOrganizeTaskData(rawData, writeRawTask);

		ArrayList<String> fileNames = getFileNames();
		for (String f : fileNames) {
			segmentbyFileName(f, f);
		}

		writer.writeFileAbstraction(dupfileAll, writeFileLevel);

	}

	private ArrayList<String> getFileNames() {
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(rawData.get(0).getGaze().getGaze_name());
		for (SceGaze sg : rawData) {
			String file = sg.getGaze().getGaze_name();
			if (!(fileNames.contains(file))) {
				fileNames.add(file);
			}

		}
		return fileNames;
	}

	private void segmentbyFileName(String file, String artifactName) {
		ArrayList<SceGaze> tempSteps = new ArrayList<SceGaze>();
		HashMap<Integer, ArrayList<SceGaze>> test = new HashMap<Integer, ArrayList<SceGaze>>();
		int i = 0;
		boolean stepList = false;
		int move = 0;
		SceGaze sg = null;
		sg = rawData.get(move);
		String gazeName = null;
		gazeName = sg.getGaze().getGaze_name();
		while (move < rawData.size()) {
			stepList = false;

			while (gazeName.equals(file)) {

				// System.out.println(" Gaze Name " + gazeName + sg.getGaze().getTimestamp());
				tempSteps.add(sg);
				stepList = true;
				// rawData.get(move).setArtifact(file);

				move++;
				if (move > rawData.size() - 1) {
					break;
				}

				sg = rawData.get(move);
				gazeName = sg.getGaze().getGaze_name();

			}
			if (stepList) {
				stepList = false;
				test.put(i, tempSteps);
				tempSteps = new ArrayList<SceGaze>();
				i++;
			}
			move++;
			if (move > rawData.size() - 1) {
				stepList = false;
				break;
			}
			sg = rawData.get(move);
			gazeName = sg.getGaze().getGaze_name();

		}

		for (Map.Entry<Integer, ArrayList<SceGaze>> entry : test.entrySet()) {
			int amount = entry.getValue().size() - 1;
			SceGaze begin = entry.getValue().get(0);
			SceGaze end = entry.getValue().get(amount);
			String tName = begin.getGaze().getGaze_name();
//			if (tName.contains("Task")) {
//				tName = getQA(begin.getGaze().getGaze_name()); // named according to the task.
//				// now need to run one more time to combine questions/tasks.
//			}

			File art = new File(tName, begin.getGaze().getUser(), begin.getGaze().getGaze_id(),
					begin.getGaze().getLine(), begin.getGaze().getTimestamp(), end.getGaze().getTimestamp(),
					end.getTask(), begin.getTask());
			dupfileAll.add(art);

		}

//		System.out.println(dupfileAll.size());
//		int j = 1;
//		ArrayList<Integer> toRemove = new ArrayList<Integer>();
//
//		for (int z = 0; z < dupfileAll.size()-1; z++) {
//			if (j > dupfileAll.size()) {
//				break;
//			}
//			System.out.println(z + "  " + j + dupfileAll.size());
//			File current = new File();
//			current = dupfileAll.get(z);
//			File next = new File();
//			next = dupfileAll.get(j);
//			
//			if (current.getFilename().contains("ANSWER")) {
//				if (current.getFilename().equals(next.getFilename())) {
//
//					if (current.getTask().equals(next.getTask())) {
//
//						System.out.println("The old current time stamp" + current.getTsEnd());
//						System.out.println("The old next time stamp" + next.getTsEnd());
//						System.out.println(" " + current.getFilename() + "  " + next.getFilename());
//						current.setTsEnd(next.getTsEnd());
//						System.out.println("The new time stamp" + current.getTsEnd());
//
//						System.out.println("The new next time stamp" + next.getTsEnd());
//						toRemove.add(j);
//					}
//				}
//			}
//			j++;
//
//		}
//
//		for (Integer rm : toRemove) {
//			dupfileAll.remove(rm);
//		}
	}

	private void segmentByArtifact(String artifact, String artifactName) {
		ArrayList<SceGaze> tempSteps = new ArrayList<SceGaze>();
		HashMap<Integer, ArrayList<SceGaze>> test = new HashMap<Integer, ArrayList<SceGaze>>();
		int i = 0;
		boolean stepList = false;
		int move = 0;
		SceGaze sg = null;
		sg = rawData.get(move);
		String gazeName = null;
		gazeName = sg.getGaze().getGaze_name();
		while (move < rawData.size()) {
			stepList = false;

			while (gazeName.contains(artifact)) {

				// System.out.println(" Gaze Name " + gazeName + sg.getGaze().getTimestamp());
				tempSteps.add(sg);
				stepList = true;
				rawData.get(move).setArtifact(artifactName);

				move++;
				if (move > rawData.size() - 1) {
					break;
				}

				sg = rawData.get(move);
				gazeName = sg.getGaze().getGaze_name();

			}
			if (stepList) {
				stepList = false;
				test.put(i, tempSteps);
				tempSteps = new ArrayList<SceGaze>();
				i++;
			}
			move++;
			if (move > rawData.size() - 1) {
				stepList = false;
				break;
			}
			sg = rawData.get(move);
			gazeName = sg.getGaze().getGaze_name();

		}

		sortByArtifactTask(test, artifactName);

	}

	private void sortByArtifactTask(Map<Integer, ArrayList<SceGaze>> test, String artifactName) {
		Map<Integer, ArrayList<SceGaze>> all = new HashMap<Integer, ArrayList<SceGaze>>();
		int z = 0;
		for (Map.Entry<Integer, ArrayList<SceGaze>> entry : test.entrySet()) {

			ArrayList<SceGaze> sameTask = new ArrayList<SceGaze>();
			ArrayList<String> tNames = new ArrayList<String>();
			tNames = getTaskGroups(entry.getValue());
			// System.out.println("for key " + entry.getKey() + " The values included are "
			// + tNames.size());

			if (tNames.size() > 1) {

				for (String taskName : tNames) {
					for (SceGaze sg : entry.getValue()) {
						if (taskName.equals(sg.getTask())) {
							sameTask.add(sg);
						}
					}
					all.put(z, sameTask);
					z++;
					sameTask = new ArrayList<SceGaze>();

				}

			} else {
				all.put(z, entry.getValue());
				z++;
			}

		}

		for (Map.Entry<Integer, ArrayList<SceGaze>> entry : all.entrySet()) {
			int amount = entry.getValue().size() - 1;
			SceGaze begin = entry.getValue().get(0);
			SceGaze end = entry.getValue().get(amount);

			if (entry.getValue().size() < 3) {
				System.out.println("Entry size" + entry.getValue().size() + entry.getValue().get(0).getArtifact()
						+ entry.getValue().get(0).getGaze().getGaze_name());
			}
			// System.out.println("Begin" +begin.getGaze().getGaze_name() +" " +
			// begin.getArtifact() + begin.getTask()
			// + "end" +end.getGaze().getGaze_name() + end.getArtifact() + end.getTask());
			String artName = artifactName;

			if (begin.getArtifact().equals("TASK")) {
				artName = getQA(begin.getTask());
				System.out.println(begin.getTask() + " " + artName);

			}
			// System.out.println(begin +" " + end.getArtifact());

			Artifact art = new Artifact(artName, begin.getGaze().getGaze_name(), begin.getGaze().getUser(),
					begin.getGaze().getTimestamp(), end.getGaze().getTimestamp(), end.getTask());
			// System.out.println(art.getArtifact());
			System.out.println(".." + art.getGaze() + " " + artName);
			artSteps.add(art);
		}

	}

	private ArrayList<String> getTaskGroups(ArrayList<SceGaze> testK) {
		ArrayList<String> tNames = new ArrayList<String>();
		tNames.add(testK.get(0).getTask());
		for (SceGaze s1 : testK) {
			if (!tNames.contains(s1.getTask())) {
				tNames.add(s1.getTask());
			}
		}
		return tNames;
	}

	private void segmentSourceCode() {
		ArrayList<SceGaze> tempSteps = new ArrayList<SceGaze>();

		Map<Integer, ArrayList<SceGaze>> test2 = new HashMap<Integer, ArrayList<SceGaze>>();
		int z = 0;
		boolean stepList = false;
		int move = 0;
		SceGaze sg;
		String gazeName;
		sg = rawData.get(move);

		gazeName = sg.getGaze().getGaze_name();
		for (int i = 0; i < rawData.size(); i++) {
			String currentName = rawData.get(i).getGaze().getGaze_name();
			// System.out.println(currentName);

			if (currentName.contains("Step")) {
				// System.out.println("koko" + currentName);
				rawData.get(i).getGaze().setGaze_name("Empty");
			}
		}
		while (move < rawData.size()) {
			stepList = false;
			while (gazeName.contains(".java")) {
				// System.out.println("gaze name " + gazeName);

				tempSteps.add(sg);
				stepList = true;
				rawData.get(move).setArtifact("SOURCE CODE");
				move++;
				if (move > rawData.size() - 1) {
					break;
				} else {
					sg = rawData.get(move);
					gazeName = sg.getGaze().getGaze_name();
				}
			}

			if (stepList) {
				stepList = false;
				test2.put(z, tempSteps);
				tempSteps = new ArrayList<SceGaze>();
				z++;
				// System.out.println("never");
			}
			move++;
			if (move > rawData.size() - 1) {

				break;
			}
			sg = rawData.get(move);
			gazeName = sg.getGaze().getGaze_name();

		}

		int sum = 0;

		int size = 0;
		for (int k = 0; k < test2.size(); k++) {
			size = test2.get(k).size();
			Artifact art = new Artifact("SOURCE CODE", test2.get(k).get(0).getGaze().getGaze_name(),
					test2.get(k).get(0).getGaze().getUser(), test2.get(k).get(0).getGaze().getTimestamp(),
					test2.get(k).get(size - 1).getGaze().getTimestamp(), test2.get(k).get(0).getTask());
			artSteps.add(art);
			sum = sum + size;
		}

		System.out.println(sum + "   " + move + "    " + "   ");

	}

	private void fillInTasks(List<SceGaze> list) {

		int i = 0;
		System.out.println("The starters size is " + list.size());

		for (SceGaze sg : rawData) {
			if (i == list.size()) {
				sg.setTask("stop"); //
				sg.setTaskType("stop");
				// sg.setTask(starters.get(i).gaze.gaze_name);
				continue;
			}
			if (Integer.parseInt(sg.getGaze().getGaze_id().toString()) < Integer
					.parseInt(list.get(i).getGaze().getGaze_id().toString())) {
				sg.setTask(list.get(i - 1).getTask());

				sg.setTaskType(list.get(i - 1).getTaskType());

			} else {
				// sg.setTask(list.get(i).getTask());
				// sg.setTaskType(list.get(i).getTaskType());
				i++;

			}

		}
	}

	private String getQA(String gazeName) {

		// Task 4
		// Task 1
		if (gazeName.contains("Task 1.1.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 1.1.1.md")) {
			return "ANSWER";

		}

		if (gazeName.contains("Task 1.2.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 1.2.1.md")) {
			return "ANSWER";

		}

		// Task 2
		if (gazeName.contains("Task 2.1.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 2.1.1.md")) {
			return "ANSWER";

		}

		if (gazeName.contains("Task 2.2.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 2.2.1.md")) {
			return "ANSWER";

		}

		// Task 3
		if (gazeName.contains("Task 3.1.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 3.1.1.md")) {
			return "ANSWER";

		}

		if (gazeName.contains("Task 3.2.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 3.2.1.md")) {
			return "ANSWER";

		}
		// Task 4
		if (gazeName.contains("Task 4.1.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 4.1.1.md")) {
			return "ANSWER";

		}

		if (gazeName.contains("Task 4.2.0.md")) {
			return "QUESTION";

		}

		if (gazeName.contains("Task 4.2.1.md")) {
			return "ANSWER";
		}
		return "WARMUP";

	}

	public List<SceGaze> setTask() {
		List<SceGaze> starters = new ArrayList<SceGaze>();

		boolean tWarmUp1 = true;
		boolean tWarmUp2 = true;
		boolean tWarmUp3 = true;
		boolean tWarmUp4 = true;

		boolean t110 = true;
		boolean t111 = true;
		boolean t120 = true;
		boolean t121 = true;

		boolean t210 = true;
		boolean t211 = true;
		boolean t220 = true;
		boolean t221 = true;

		boolean t310 = true;
		boolean t311 = true;
		boolean t320 = true;
		boolean t321 = true;

		boolean t410 = true;
		boolean t411 = true;
		boolean t420 = true;
		boolean t421 = true;

		for (SceGaze sg : rawData) {
			String gazeName = sg.getGaze().getGaze_name();
			if (tWarmUp1 == true && gazeName.contains("Task 1.0.md")) {
				starters.add(sg);
				tWarmUp1 = false;
				sg.setTask("warmup");
				sg.setTaskType("Question");

			}

			if (tWarmUp2 == true && gazeName.contains("Task 1.1.md")) {
				starters.add(sg);
				tWarmUp2 = false;
				sg.setTask("warmup");

				sg.setTaskType("Answer");

			}

			if (tWarmUp3 == true && gazeName.contains("Task 2.0.md")) {
				starters.add(sg);
				tWarmUp3 = false;
				sg.setTask("warmup");

				sg.setTaskType("Question");

			}

			if (tWarmUp4 == true && gazeName.contains("Task 2.1.md")) {
				starters.add(sg);
				tWarmUp4 = false;
				sg.setTask("warmup");

				sg.setTaskType("Answer");

			}
			if (t110 == true && gazeName.contains("Task 1.1.0.md")) {
				starters.add(sg);
				t110 = false;
				sg.setTask("Task 1.1.0.md");

				sg.setTaskType("Question");

			}
			if (t111 == true && gazeName.contains("Task 1.1.1.md")) {
				starters.add(sg);
				sg.setTask("Task 1.1.1.md");

				sg.setTaskType("Answer");
				t111 = false;

			}

			if (t120 == true && gazeName.contains("Task 1.2.0.md")) {
				starters.add(sg);
				sg.setTask("Task 1.2.0.md");

				sg.setTaskType("Question");
				t120 = false;

			}

			if (t121 == true && gazeName.contains("Task 1.2.1.md")) {
				starters.add(sg);
				sg.setTask("Task 1.2.1.md");

				sg.setTaskType("Answer");
				t121 = false;

			}

			if (t210 == true && gazeName.contains("Task 2.1.0.md")) {
				starters.add(sg);
				t210 = false;
				sg.setTask("Task 2.1.0.md");

				sg.setTaskType("Question");

			}
			if (t211 == true && gazeName.contains("Task 2.1.1.md")) {
				starters.add(sg);
				sg.setTask("Task 2.1.1.md");

				sg.setTaskType("Answer");
				t211 = false;

			}

			if (t220 == true && gazeName.contains("Task 2.2.0.md")) {
				starters.add(sg);
				t220 = false;
				sg.setTask("Task 2.2.0.md");

				sg.setTaskType("Question");

			}

			if (t221 == true && gazeName.contains("Task 2.2.1.md")) {
				starters.add(sg);
				sg.setTask("Task 2.2.1.md");

				t221 = false;

				sg.setTaskType("Answer");

			}

			if (t310 == true && gazeName.contains("Task 3.1.0.md")) {
				sg.setTask("Task 3.1.0.md");

				sg.setTaskType("Question");
				starters.add(sg);
				t310 = false;

			}
			if (t311 == true && gazeName.contains("Task 3.1.1.md")) {
				sg.setTask("Task 3.1.1.md");

				sg.setTaskType("Answer");
				starters.add(sg);
				t311 = false;

			}

			if (t320 == true && gazeName.contains("Task 3.2.0.md")) {
				sg.setTask("Task 3.2.0.md");

				sg.setTaskType("Question");
				starters.add(sg);
				t320 = false;

			}

			if (t321 == true && gazeName.contains("Task 3.2.1.md")) {
				sg.setTask("Task 3.2.1.md");

				sg.setTaskType("Answer");
				starters.add(sg);
				t321 = false;

			}

			if (t410 == true && gazeName.contains("Task 4.1.0.md")) {
				sg.setTask("Task 4.1.0.md");

				sg.setTaskType("Question");
				starters.add(sg);
				t410 = false;

			}
			if (t411 == true && gazeName.contains("Task 4.1.1.md")) {
				sg.setTask("Task 4.1.1.md");

				sg.setTaskType("Answer");
				starters.add(sg);
				t411 = false;

			}

			if (t420 == true && gazeName.contains("Task 4.2.0.md")) {
				sg.setTask("Task 4.2.0.md");
				starters.add(sg);

				sg.setTaskType("Question");
				t420 = false;

			}

			if (t421 == true && gazeName.contains("Task 4.2.1.md")) {
				starters.add(sg);
				sg.setTask("Task 4.2.1.md");

				sg.setTaskType("Answer");

				t421 = false;

			}
		}
		return starters;
	}

}

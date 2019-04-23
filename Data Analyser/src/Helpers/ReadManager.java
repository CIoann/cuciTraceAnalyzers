package Helpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import DataStructures.Artifact;
import DataStructures.Gaze;
import DataStructures.Sce;
import DataStructures.SceGaze;
import DataStructures.SceGazeData;
import DataStructures.Task;

public class ReadManager {

	private static final String READ_METHODS = "/Users/ConnieZalo/Desktop/simple/sampleMethod.csv";
	private static final String READ_FILE = "/Users/ConnieZalo/Desktop/simple/sampleFiles.csv";

	private static final String READ_GAZES = "/Users/ConnieZalo/Desktop/simple/RawData/Step_2_Combined_GS/d8_gazes.csv";

	private static final String READ_SCES = "/Users/ConnieZalo/Desktop/simple/RawData/Step_2_Combined_GS/d8_sces.csv";

	List<SceGazeData> lsData;

	List<Gaze> gazeData;
	List<Sce> sceData;
	int counter = 0;
	List<SceGaze> rawData = new ArrayList<SceGaze>();

	public List<SceGazeData> readMethodData() {
		try {

			au.com.bytecode.opencsv.CSVReader csvReader = new au.com.bytecode.opencsv.CSVReader(
					new FileReader(READ_METHODS), ',');

			String[] nextRecord;
			try {

				while ((nextRecord = csvReader.readNext()) != null) {
					// System.out.println("sce_gaze_id : " + nextRecord[0]);

					SceGazeData data = new SceGazeData(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3],
							nextRecord[4], nextRecord[6], nextRecord[7], nextRecord[8]);
					// System.out.println("==========================");
					lsData.add(data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lsData;
	}

	public List<SceGazeData> readFileData() {
		try {

			au.com.bytecode.opencsv.CSVReader csvReader = new au.com.bytecode.opencsv.CSVReader(
					new FileReader(READ_FILE), ',');

			String[] nextRecord;
			try {

				while ((nextRecord = csvReader.readNext()) != null) {
					// System.out.println("sce_gaze_id : " + nextRecord[0]);

					SceGazeData data = new SceGazeData(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3],
							nextRecord[4]);
					lsData.add(data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lsData;
	}

	public void initRawData() {

		System.out.print("Start");
		readGazeData();

		System.out.print("Gaze");
		readSCEData();

		System.out.print("Sce");

		System.out.println(sceData.size() + gazeData.size());
		combine();
		WriteManager writer = new WriteManager();
		writer.writeCombiData(rawData);
		System.out.print("Combine");
	}

	private void readGazeData() {
		gazeData = new ArrayList<Gaze>();
		try {

			au.com.bytecode.opencsv.CSVReader csvReader = new au.com.bytecode.opencsv.CSVReader(
					new FileReader(READ_GAZES), ',');

			String[] nextRecord;
			try {

				while ((nextRecord = csvReader.readNext()) != null) {
					String time = nextRecord[13];
					if (nextRecord[13].length() < 26) {
						time = nextRecord[14];
					}
					Gaze g = new Gaze(nextRecord[0], nextRecord[1], nextRecord[3], nextRecord[4], time);
					gazeData.add(g);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void combine() {
		for (int i = 0; i < sceData.size(); i++) {
			for (int j = 0; j < gazeData.size(); j++) {
				// System.out.println("Gaze" + gazeData.get(j).getGaze_id());
				if (sceData.get(i).getSce_gaze_id().equals(gazeData.get(j).getGaze_id())) {
					SceGaze sg = new SceGaze(gazeData.get(j), sceData.get(i));
					rawData.add(sg);
				}
			}
		}
	}

	private void readSCEData() {
		sceData = new ArrayList<Sce>();
		au.com.bytecode.opencsv.CSVReader csvReader = null;
		try {
			csvReader = new au.com.bytecode.opencsv.CSVReader(new FileReader(READ_SCES), ',');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Reading Records One by One in a String array

		String[] nextRecord;

		try {
			while ((nextRecord = csvReader.readNext()) != null) {
				Sce sce = new Sce(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4]);
				// System.out.println("==========================");
				sceData.add(sce);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void organizeFiles() {
		// readRaw();
		// fillInTasks(setTask());
		// segmentByFile();
		// WriteManager writer = new WriteManager();
		// writer.writeArtifactAbstraction(artSteps, artSource,writeFileLevel);
		// System.out.println("Raw size " + rawData.size());
	}


	public List<SceGaze> readRaw(String filename) {

		try {

			au.com.bytecode.opencsv.CSVReader csvReader = new au.com.bytecode.opencsv.CSVReader(
					new FileReader(filename), ',');

			String[] nextRecord;
			try {

				while ((nextRecord = csvReader.readNext()) != null) {
					Gaze g = new Gaze(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[6]);
					Sce sce = new Sce(nextRecord[0], nextRecord[4], nextRecord[5], nextRecord[7], nextRecord[8]);

					SceGaze rd = new SceGaze(g, sce);
					rawData.add(rd);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rawData;

	}

	// find all the tasks and where they start

	//
	// boolean ft = true;
	// for (int f = 0; f < taskList.size(); f++) {
	// int sum =0;
	// for (int i=0; i<test.get(k).size(); i++) {
	// SceGaze s1 = test.get(k).get(i);
	//
	// if (s1.getTask().equals(taskList.get(f).getName()) && ft) {
	// System.out.println(s1.getTask());
	// taskList.get(f).setBegin(i);
	//
	//
	// }
	//
	// System.out.println("Sum " +sum + taskList.get(f).getName() );
	// }
	//
	// int start =0;
	//
	// for (int f = 0; f < taskList.size(); f++) {
	//
	// int up =taskList.get(f).getAmount()-1;
	// Artifact art = new Artifact(artifactName,
	// test.get(k).get(start).getGaze().getUser(),
	// test.get(k).get(start).getGaze().getTimestamp(),test.get(k).get(up).getGaze().getTimestamp(),
	// taskList.get(f).getName());
	// artSteps.add(art);
	// start = up+1;
	// }
	// }
	//

	// }
	private ArrayList<Task> getTaskGroups(ArrayList<SceGaze> testK) {
		ArrayList<String> tNames = new ArrayList<String>();
		tNames.add(testK.get(0).getTask());
		for (SceGaze s1 : testK) {
			if (!tNames.contains(s1.getTask())) {
				tNames.add(s1.getTask());
			}
		}
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (String s1 : tNames) {
			taskList.add(new Task(0, s1));
		}

		return taskList;
	}

}

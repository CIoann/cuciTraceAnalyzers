package Helpers;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import DataStructures.Artifact;
import DataStructures.File;
import DataStructures.Fixations;
import DataStructures.SceGaze;
import DataStructures.SceGazeData;
import au.com.bytecode.opencsv.CSVWriter;

public class WriteManager {

	private static final String write2 = "/Users/ConnieZalo/Desktop/simple/abs_method.csv";
	private static final String writeRaw = "/Users/ConnieZalo/Desktop/simple/raw3.csv";
	private static final String write_Fixation_Table = "/Users/ConnieZalo/Desktop/simple/fixationTable.csv";

	public void writeFixationTable(List<Fixations> un) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(write_Fixation_Table));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "method", "file", "line", "count" };
			csvWriter.writeNext(headerRecord);
			for (Fixations f : un) {
				csvWriter.writeNext(new String[] { f.getMethod(), f.getFile(), f.getLine(), "" + f.getCount() });

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeData(List<SceGazeData> lsData) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(write2));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "variant", "id", "file", "method", "line", "tsStart", "tsEnd", "task", "artifact",
					"duration", "task+variant" };
			csvWriter.writeNext(headerRecord);
			for (SceGazeData sg : lsData) {
				csvWriter.writeNext(new String[] { sg.getVariant(), sg.getId(), sg.getFile(), sg.getMethod(),
						sg.getLinenumber(), sg.getTsStart(), sg.getTsEnd(), sg.getTask(), sg.getArtifact(),
						sg.getDuration() + "", sg.getVariant() + sg.getTask() });

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeCombiData(List<SceGaze> combi) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(writeRaw));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "variant", "gaze_id", "gaze_name", "gaze_type", "sce_id", "sce_name", "timestamp", "sce_type",
					"sce_how" };
			csvWriter.writeNext(headerRecord);
			for (SceGaze sg : combi) {
				csvWriter.writeNext(new String[] { sg.getGaze().getUser(), sg.getGaze().getGaze_id(), sg.getGaze().getGaze_name(),
						sg.getGaze().getGaze_type(),sg.getSce().getSce_gaze_id(),sg.getSce().getSce_name(), correctTS(sg.getGaze().getTimestamp()),
						sg.getSce().getSce_type(), sg.getSce().getHow() });

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeOrganizeTaskData(List<SceGaze> combi, String filename) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(filename));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "variant", "gaze_id", "gaze_name", "gaze_type", "sce_id", "sce_name", "timestamp", "sce_type",
					"sce_how", "Task" };
			csvWriter.writeNext(headerRecord);
			for (SceGaze sg : combi) {
				csvWriter.writeNext(new String[] { sg.getGaze().getUser(), sg.getGaze().getGaze_id(), sg.getGaze().getGaze_name(),
						sg.getGaze().getGaze_type(),sg.getSce().getSce_gaze_id(),sg.getSce().getSce_name(), sg.getGaze().getTimestamp(),
						sg.getSce().getSce_type(), sg.getSce().getHow(), sg.getTask()});

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String correctTS(String timestamp) {
		String kokos =  timestamp;

		//System.out.println(timestamp);
		if (timestamp.length()<27) {
			String part1 = timestamp.substring(0, 20);
			String part2 = timestamp.substring(timestamp.indexOf('+'), timestamp.length());
			kokos = part1 + '0' + '0' + '0'+ part2;
		}
		if (timestamp.length()<28) {
			String part1 = timestamp.substring(0, 21);
			String part2 = timestamp.substring(timestamp.indexOf('+'), timestamp.length());
			kokos = part1 + '0' + '0' + part2;
		}
		if (timestamp.length()<29) {
			String part1 = timestamp.substring(0, 22);
			String part2 = timestamp.substring(timestamp.indexOf('+'), timestamp.length());
			kokos = part1 + '0' + part2;
		}
		return kokos;
	}
	public void writeArtifactAbstraction(List<Artifact> art1, List<Artifact> art2,String filename) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(filename));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "Variant", "Artifact", "gaze " , "Task", "tsStart", "tsEnd", "task+variant"};
			csvWriter.writeNext(headerRecord);
			for (Artifact elem : art1) {
				
				String start = correctTS(elem.getTsStart());
				String end = correctTS(elem.getTsEnd());
				
				String part = elem.getVariant() + elem.getTask();
				csvWriter.writeNext(new String[] { elem.getVariant(), elem.getArtifact(), elem.getGaze(), elem.getTask(), start, end ,part });

			}
			for (Artifact elem : art2) {
				String part = elem.getVariant() + elem.getTask();
				String start = correctTS(elem.getTsStart());
				String end = correctTS(elem.getTsEnd());
				csvWriter.writeNext(new String[] { elem.getVariant(), elem.getArtifact(), elem.getTask(), start, end,part });
				
			}
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeFileAbstraction(List<File> art1,String filename) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(filename));

				CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
						CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);) {
			String[] headerRecord = { "Variant", "gazeID", "Filename" , "artifact","Task", "tsStart", "tsEnd", "task+variant"};
			csvWriter.writeNext(headerRecord);
			for (File elem : art1) {
				
				String start = correctTS(elem.getTsStart());
				String end = correctTS(elem.getTsEnd());
				String part = elem.getVariant() + elem.getTask();
				csvWriter.writeNext(new String[] { elem.getVariant(), elem.getGazeID(), elem.getFilename(), elem.getArtifact(), elem.getTask(), start, end ,part });

			}
			
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

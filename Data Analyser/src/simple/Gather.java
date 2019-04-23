package simple;

import Helpers.OrganiseManager;
import Helpers.ReadManager;

public class Gather {

	//List<puSceGazeData> userLog = new ArrayList<puSceGazeData>();

//	List<SceGazeData> lsMethodData = new ArrayList<SceGazeData>();
//	List<SceGazeData> lsFileData = new ArrayList<SceGazeData>();
//	List<SceGazeData> unique = new ArrayList<SceGazeData>();
//	List<SceGazeData> sunique = new ArrayList<SceGazeData>();

	//WriteManager writer = new WriteManager();
	ReadManager reader = new ReadManager();
	OrganiseManager organizer = new OrganiseManager();
//
//	List<Fixations> un = new ArrayList<Fixations>();
//
//	List<Artifact> arts = new ArrayList<Artifact>();
//	List<Artifact> temp = new ArrayList<Artifact>();

	public void init2() {

		System.out.print("Begin");
	//Step 1 : merge sce - gazes
		//reader.initRawData();
	//Step 2 : organize by artifact
	// 	organizer.organizeTask();
		//Step 3 : organize by files

		 organizer.organizeByFile();
		 System.out.print("Done");
		 //reader.organizeFiles();
		// handleMethods();
		//handleFiles();
		System.out.println("Got data");

	}
}

//	private void handleFiles() {
//		lsFileData = reader.readFileData();
//		getArtifact(lsFileData);
//		segmentUserLog();
//		abstractionArtifact();
//
//		// unique(lsFileData);
//	}
//
//	private void segmentUserLog() {
//		List<SceGazeData> userExample = new ArrayList<SceGazeData>();
//
//		for (SceGazeData sgd : lsFileData) {
//			// sgd.getVariant()
//			// System.out.println("variant" + sgd.getVariant());
//			if (!(sgd.getVariant().equals("variant"))) {
//				userExample.add(sgd);
//
//			} else if (sgd.getVariant().equals("variant")) {
//				puSceGazeData ex = new puSceGazeData();
//				ex.setUser(userExample);
//				userLog.add(ex);
//				// userExample.clear();
//				userExample = new ArrayList<SceGazeData>();
//				// System.out.println("Seg user" + userLog.size());
//			}
//
//		}
//		// System.out.println("Segmentation of user Log" + userLog.size());
//		//
//		// for (puSceGazeData user : userLog) {
//		// System.out.print("User" + user.getUser().size());
//		// }
//	}
//
//	private void abstractionArtifact() {
//		List<String> artifact = new ArrayList<String>();
//
//		artifact.add("Task");
//		artifact.add("StepCode");
//		artifact.add("SourceCode");
//		artifact.add("Feature");
//
//		//FOR ALL USERS
//		//for (puSceGazeData user : userLog) {
//
//			List<SceGazeData> currentUser = new ArrayList<SceGazeData>();
//			currentUser = userLog.get(0).getUser();
//			boolean ftel = true;
//			boolean ltel = true;
//			for (String art : artifact) {
//				for (SceGazeData sgd : currentUser) {
//					System.out.println("Artifact" + sgd.getArtifact());
//					if (sgd.getArtifact().equals(art) && ftel) {
//						System.out.println("Begin");
//						ftel = false;
//						ltel = true;
//
//					} else if (sgd.getArtifact().equals(art) && !ftel) {
//					//	System.out.println("Not begin");
//					} else if (sgd.getArtifact().equals(art) && ltel) {
//						ftel = true;
//						ltel = false;
//						System.out.print("end");
//					}
//				}
//			}
//		//}
//		// if (sgd.getArtifact().equals(art) && ftel) {
//		//
//		//
//		// //List<SceGazeData> userList = new ArrayList<SceGazeData>();
//		// // List<Artifact> artifactListTemp = new ArrayList<Artifact>();
//		//
//		//
//		// //System.out.println(art);
//		// System.out.println(user.getUser().get(0).getVariant());
//		//
//		// for (SceGazeData sgd : userList) {
//		// if (sgd.getArtifact().equals(art) && ftel) {
//		// ftel = false;
//		// ltel = true;
//		// artifactListTemp.add(new Artifact(sgd.getArtifact(), sgd.getVariant(),
//		// sgd.getTsStart(), null,
//		// sgd.getTask()));
//		// System.out.println("A Beginning");
//		// } else if (sgd.getArtifact().equals(art) && !ftel) {
//		//
//		// } else if (sgd.getArtifact().equals(art) && ltel) {
//		// ftel = true;
//		// ltel = false;
//		// artifactListTemp.add(new Artifact(sgd.getArtifact(), sgd.getVariant(),
//		// sgd.getTsStart(),
//		// sgd.getTsEnd(), sgd.getTask()));
//		// System.out.println("An End");
//		// }
//
//		// }
//		//
//		// }
//		//
//		// }
//
//	}
//
//	private void handleMethods() {
//		lsMethodData = reader.readMethodData();
//		getArtifact(lsMethodData);
//		unique(lsMethodData);
//		writer.writeData(lsMethodData);
//		writer.writeFixationTable(un);
//	}
//
//	private void getArtifact(List<SceGazeData> lsData) {
//		for (SceGazeData dt : lsData) {
//			if (dt.getFile().contains("Steps")) {
//				dt.setArtifact("StepCode");
//			}
//			if (dt.getFile().contains("feature")) {
//				dt.setArtifact("Feature");
//			}
//			if (dt.getFile().contains("Task")) {
//				dt.setArtifact("Task");
//			}
//		}
//
//	}
//
//	private void unique(List<SceGazeData> lsData) {
//
//		Methods m = new Methods();
//		
//		for (String n : m.getMethodNames(lsMethodData)) {
//			Fixations x = new Fixations(n, "", "", 0);
//			un.add(x);
//		}
//
//		for (SceGazeData s : lsData) {
//			if (s.getDuration() >= 200) {
//				for (Fixations x : un) {
//					if (s.getMethod().equals(x.getMethod())) {
//						x.count++;
//						x.setFile(s.getFile());
//						x.setLine(s.getLinenumber());
//					}
//				}
//			}
//
//		}
//		for (Fixations x : un) {
//			System.out.println(x.getFile() + x.getMethod() + "   " + x.getCount() + "   " + x.getLine());
//		}
//	}
//
//}

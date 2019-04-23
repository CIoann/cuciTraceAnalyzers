package DataStructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Methods {

	List<String> methodNames = new ArrayList<String>();
	List<String> methodNamesDup = new ArrayList<String>();

	private void findMethodNames(List<SceGazeData> lsMethodData) {

		for (SceGazeData sgd : lsMethodData)
			methodNamesDup.add(sgd.getMethod());

		ArrayList<String> methodNames = new ArrayList<String>();

		Iterator<String> dupIter = methodNamesDup.iterator();
		while (dupIter.hasNext()) {
			String dupWord = dupIter.next();
			if (methodNames.contains(dupWord)) {
				dupIter.remove();
			} else {
				methodNames.add(dupWord);
			}
		}
		System.out.println(methodNames.size());

		for (String m : methodNames) {
			System.out.println("Method Name" + m);
		}
	}

	public Methods() {
		super();
	}

	public List<String> getMethodNames(List<SceGazeData> lsMethodData) {
		findMethodNames(lsMethodData);
		return methodNames;
	}

	public void setMethodNames(List<String> methodNames) {
		this.methodNames = methodNames;
	}

}

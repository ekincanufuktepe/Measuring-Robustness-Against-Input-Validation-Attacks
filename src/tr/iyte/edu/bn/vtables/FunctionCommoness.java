package tr.iyte.edu.bn.vtables;


import java.util.ArrayList;

import tr.iyte.edu.bn.stables.VulnerabilityStats;

public class FunctionCommoness {
	private String funcName;
	private ArrayList<VulnerabilityStats> vulCommoness;
	
//	public FunctionCommoness(String fName, ArrayList<Double> commoness) {
//		// TODO Auto-generated constructor stub
//		this.funcName = fName;
//		this.vulCommoness = commoness;
//	}

	public FunctionCommoness(String fName, ArrayList<VulnerabilityStats> vulns) {
		// TODO Auto-generated constructor stub
		this.funcName = fName;
		this.vulCommoness = vulns;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public ArrayList<VulnerabilityStats> getVulCommoness() {
		return vulCommoness;
	}

	public void setVulCommoness(ArrayList<VulnerabilityStats> vulCommoness) {
		this.vulCommoness = vulCommoness;
	}
	
	

}

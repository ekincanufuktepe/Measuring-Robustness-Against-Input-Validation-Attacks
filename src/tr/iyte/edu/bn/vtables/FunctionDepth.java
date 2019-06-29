package tr.iyte.edu.bn.vtables;

public class FunctionDepth {
	private String funcName;
	private double funcDepth;
	
	public FunctionDepth(String fName, double depth) {
		// TODO Auto-generated constructor stub
		this.funcName = fName;
		this.funcDepth = depth;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public double getFuncDepth() {
		return funcDepth;
	}

	public void setFuncDepth(double funcDepth) {
		this.funcDepth = funcDepth;
	}
	
	

}

package tr.iyte.edu.bn.vtables;

public class FunctionCalls {
	
	private String funcName;
	private double funcNumCalls;
	
	public FunctionCalls(String fName, double fCalls) {
		// TODO Auto-generated constructor stub
		this.funcName = fName;
		this.funcNumCalls = fCalls;
	}
	
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public double getFuncNumCalls() {
		return funcNumCalls;
	}
	public void setFuncNumCalls(double funcNumCalls) {
		this.funcNumCalls = funcNumCalls;
	}
	
	

}

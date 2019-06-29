package tr.iyte.tajs.iv;

public class InputInformation {
	
	private int numOfParameters;
	private int numOfLocalVariables;
	private int numOfGlobalVariables;
	private int numOfGLVariables;
	private int validatedParameters;
	private int validatedVariables;
	private int invalidatedParameters;
	private int invalidatedVariables;
	private int invalidatedVariablesFixed;
	private int validatedVariablesFixed;
	private int unknownParameter;
	private int unknownVariables;
	private int notGlobalVariable;
	
	
	public int getValidatedVariables() {
		return validatedVariables;
	}

	public void setValidatedVariables(int validatedVariables) {
		this.validatedVariables = validatedVariables;
	}

	public int getInvalidatedParameters() {
		return invalidatedParameters;
	}

	public void setInvalidatedParameters(int invalidatedParameters) {
		this.invalidatedParameters = invalidatedParameters;
	}

	public int getInvalidatedVariables() {
		return invalidatedVariables;
	}

	public void setInvalidatedVariables(int invalidatedVariables) {
		this.invalidatedVariables = invalidatedVariables;
	}

	public int getNumOfParameters() {
		return numOfParameters;
	}
	
	public void setNumOfParameters(int numOfParameters) {
		this.numOfParameters = numOfParameters;
	}
	
	public int getNumOfLocalVariables() {
		return numOfLocalVariables;
	}
	
	public void setNumOfLocalVariables(int numOfLocalVariables) {
		this.numOfLocalVariables = numOfLocalVariables;
	}
	
	public int getNumOfGlobalVariables() {
		return numOfGlobalVariables;
	}
	
	public void setNumOfGlobalVariables(int numOfGlobalVariables) {
		this.numOfGlobalVariables = numOfGlobalVariables;
	}

	public int getNumOfGLVariables() {
		return numOfGLVariables;
	}

	public void setNumOfGLVariables(int numOfGLVariables) {
		this.numOfGLVariables = numOfGLVariables;
	}

	public int getValidatedParameters() {
		return validatedParameters;
	}

	public void setValidatedParameters(int validatedParameters) {
		this.validatedParameters = validatedParameters;
	}

	public int getUnknownParameter() {
		return unknownParameter;
	}

	public void setUnknownParameter(int unknownParameter) {
		this.unknownParameter = unknownParameter;
	}

	public int getNotGlobalVariable() {
		return notGlobalVariable;
	}

	public void setNotGlobalVariable(int notGlobalVariable) {
		this.notGlobalVariable = notGlobalVariable;
	}

	public int getUnknownVariables() {
		return unknownVariables;
	}

	public void setUnknownVariables(int unknownVariables) {
		this.unknownVariables = unknownVariables;
	}

	public int getInvalidatedVariablesFixed() {
		return invalidatedVariablesFixed;
	}

	public void setInvalidatedVariablesFixed(int invalidatedVariablesFixed) {
		this.invalidatedVariablesFixed = invalidatedVariablesFixed;
	}

	public int getValidatedVariablesFixed() {
		return validatedVariablesFixed;
	}

	public void setValidatedVariablesFixed(int validatedVariablesFixed) {
		this.validatedVariablesFixed = validatedVariablesFixed;
	}

}

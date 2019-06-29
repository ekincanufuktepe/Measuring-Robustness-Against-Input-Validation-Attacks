package tr.iyte.tajs.iv;

public class ConstantIfInformation {
	
	private String register;
	private String content;
	
	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ConstantIfInformation(String reg, String cont) {
		// TODO Auto-generated constructor stub
		this.content = cont;
		this.register = reg;
	}

}

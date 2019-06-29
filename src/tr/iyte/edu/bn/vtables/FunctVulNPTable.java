package tr.iyte.edu.bn.vtables;

public class FunctVulNPTable {
	
	private String vulnerability;
	private double yes;
	private double no;

	public FunctVulNPTable(String str, double y, double n) {
		// TODO Auto-generated constructor stub
		vulnerability = str;
		yes = y;
		no = n;
	}

	public String getVulnerability() {
		return vulnerability;
	}

	public void setVulnerability(String vulnerability) {
		this.vulnerability = vulnerability;
	}

	public double getYes() {
		return yes;
	}

	public void setYes(double yes) {
		this.yes = yes;
	}

	public double getNo() {
		return no;
	}

	public void setNo(double no) {
		this.no = no;
	}
}

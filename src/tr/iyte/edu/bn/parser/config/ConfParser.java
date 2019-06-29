package tr.iyte.edu.bn.parser.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import tr.iyte.edu.bn.stables.VulnerabilityDamage;
import tr.iyte.edu.bn.stables.VulnerabilityStats;
import tr.iyte.edu.bn.vtables.FunctionCalls;
import tr.iyte.edu.bn.vtables.FunctionCommoness;
import tr.iyte.edu.bn.vtables.FunctionDepth;

public class ConfParser {
	private static ConfParser instance = null;

	private final String fileName = "files/config.conf";

	public ArrayList<VulnerabilityStats> vulns = new ArrayList<VulnerabilityStats>();
	public ArrayList<VulnerabilityDamage> vulDmg = new ArrayList<VulnerabilityDamage>();
	public ArrayList<FunctionCalls> fCalls = new ArrayList<FunctionCalls>();
	public ArrayList<FunctionDepth> fDepth = new ArrayList<FunctionDepth>();
	public ArrayList<FunctionCommoness> fComm = new ArrayList<FunctionCommoness>();
	public ArrayList<VulnerabilityStats> fvComm = new ArrayList<VulnerabilityStats>();

	private String COMMENT_TOKEN = "#";
	private String CFG_TOKEN = "=";
	private String CFG_TOKEN_2 = "_";
	private double std;
	static {
		getInstance();
	}

	public static ConfParser getInstance() {
		if (instance == null) {
			instance = new ConfParser();
		}
		return instance;
	}	

	public void parseConf()
	{

		int flag = 0;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if( (line.indexOf(CFG_TOKEN) <= 0) 
						|| (line.startsWith(COMMENT_TOKEN))
						) {
					continue;
				}



				String[] elements = line.split(CFG_TOKEN_2);
				if(elements[0].equals("vuldmg"))
				{
					String[] subElements = elements[1].split(CFG_TOKEN);
					//System.out.println("DAMAGE 1: "+subElements[0]+" 2: "+subElements[1]);
					vulDmg.add(new VulnerabilityDamage(subElements[0], Double.parseDouble(subElements[1])));
				}
				else if(elements[0].equals("fcall"))
				{
					String[] subElements = elements[1].split(CFG_TOKEN);
					//System.out.println("FCALLS 1: "+subElements[0]+" 2: "+subElements[1]);
					fCalls.add(new FunctionCalls(subElements[0], Double.parseDouble(subElements[1])));
				}
				else if(elements[0].equals("fdepth"))
				{
					String[] subElements = elements[1].split(CFG_TOKEN);
					//System.out.println("DEPTHS 1: "+subElements[0]+" 2: "+subElements[1]);
					fDepth.add(new FunctionDepth(subElements[0], Double.parseDouble(subElements[1])+1));
				}
				else if(elements[0].equals("vulcom"))
				{
					if(flag == 1)
					{
						String[] subElements = elements[1].split(CFG_TOKEN);
						System.out.println("COMMONESS 1: "+subElements[0]+" 2: "+subElements[1]);
						vulns.add(new VulnerabilityStats(subElements[0], Double.parseDouble(subElements[1])));
					}
					else
					{
						String[] subElements = elements[1].split(CFG_TOKEN);
						//System.out.println("COMMONESS 1: "+subElements[0]+" 2: "+subElements[1]);
						vulns.add(new VulnerabilityStats(subElements[0], Double.parseDouble("1")));
					}
				}
				else if(elements[0].equals("funccom"))
				{
					String[] subElements = elements[1].split(CFG_TOKEN);
					//System.out.println("DEPTHS 1: "+subElements[0]+" 2: "+subElements[1]);
					fvComm.add(new VulnerabilityStats(subElements[0], Double.parseDouble(subElements[1])));
				}
				else
				{
					String[] subElements = elements[0].split(CFG_TOKEN);
					//System.out.println("FCALLS 1: "+subElements[0]+" 2: "+subElements[1]);
					if(subElements[0].equals("USE-COMMONNES-V") && subElements[1].equals("1"))
					{
						System.out.println("NOT Including.... ");
						flag = 1;
					}
					else if(subElements[0].equals("std"))
					{
						setStd(Double.parseDouble(subElements[1]));
						System.out.println("Including.... ");
					}
				}


			}
			reader.close();

		}
		catch(IOException e){
			System.err.println("Can't open the config file: " + fileName);

		}

	}

	public double getStd() {
		return std;
	}

	public void setStd(double std) {
		this.std = std;
	}
}


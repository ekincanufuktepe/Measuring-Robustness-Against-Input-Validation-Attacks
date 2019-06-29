package tr.iyte.edu.bn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import tr.iyte.edu.bn.file.BayesNetworkCreator;
import tr.iyte.edu.bn.file.Subset;
import tr.iyte.edu.bn.parser.config.ConfParser;
import tr.iyte.edu.bn.stables.VulSysNPTable;
import tr.iyte.edu.bn.stables.VulnerabilityDamage;
import tr.iyte.edu.bn.stables.VulnerabilityStats;
import tr.iyte.edu.bn.vtables.FunctVulNPTable;
import tr.iyte.tajs.iv.FunctionParameterTree;

public class BayesianNetworkExecuter {
	
	public static HashMap<String, Double> functionNodeProbs = new HashMap<>();
	
	private static BayesianNetworkExecuter instance = null;
	
	static {
		getInstance();
	}

	public static BayesianNetworkExecuter getInstance() {
		if (instance == null) {
			instance = new BayesianNetworkExecuter();
		}
		return instance;
	} 
	
	public static void executeBN(double initialIVVals) {
		// TODO Auto-generated method stub

		Subset s = new Subset();

		ConfParser.getInstance().parseConf();

		int[] vulnerabilities = new int[ConfParser.getInstance().vulDmg.size()];
		int[] funcs = new int[1];

		funcs[0] = 0;

		ArrayList<String> fNames = new ArrayList<String>();

		fNames.add("FIPS");

		for(int i=0; i<6; i++)
		{
			vulnerabilities[i] = i+1;
		}

		System.out.println(vulnerabilities.length);
		//Vulnerability CWSS score values
		ArrayList<VulnerabilityDamage> cwss = new ArrayList<>();
		cwss = ConfParser.getInstance().vulDmg;

		//Vulnerability NVD score values
		ArrayList<VulnerabilityStats> vComm = new ArrayList<>();
		vComm = ConfParser.getInstance().fvComm;

		ArrayList<Double> vComScores = new ArrayList<Double>();

		for(int i=0; i<vComm.size(); i++)
		{
			vComScores.add(vComm.get(i).getStat()/100);
			System.out.println("VCOM: "+vComm.get(i).getStat()+" - "+vComm.get(i).getVul());
			System.out.println("CWSS: "+cwss.get(i).getDmg()+" - "+vComm.get(i).getVul());
		}

		BayesianNetworkExecuter push = new BayesianNetworkExecuter();

		//		vComScores = push.normalize(vComScores);

		//		System.out.println(vComScores);

		ArrayList<ArrayList<Integer>> subSetsOfVulns = s.findAll(vulnerabilities);


		System.out.println(s.findAll(vulnerabilities).size());

		ArrayList<Double> totPosVulScores = new ArrayList<Double>(subSetsOfVulns.size());

		for(int i=0; i<subSetsOfVulns.size(); i++)
		{
			double sum = 0;
			if(subSetsOfVulns.get(i).size() != 0)
			{
				for(int j=0; j<vulnerabilities.length; j++)
				{
					if(subSetsOfVulns.get(i).contains(vulnerabilities[j]))
					{
						sum = sum + cwss.get(j).getDmg();
					}
				}
			}
			totPosVulScores.add(sum);
		}

		ArrayList<VulSysNPTable> vsTable = new ArrayList<VulSysNPTable>();
		ArrayList<Double> a = push.normalize(totPosVulScores);
		System.out.println(totPosVulScores);
		System.out.println(a);

		for(int i = 0; i< totPosVulScores.size(); i++)
		{
			double x = Math.abs(a.get(i) - ConfParser.getInstance().getStd());
			vsTable.add(new VulSysNPTable(x, (1-x)));
		}
		System.out.println("Normalizing Complete!");

		ArrayList<Double> tmp = new ArrayList<>();

		//tmp = push.normalize(vComScores);
		ArrayList<FunctVulNPTable> fvTable = new ArrayList<>();
//		int index = 0;
		for(int i=0; i<vulnerabilities.length; i++)
		{
			double y = 1;
			fvTable.add(new FunctVulNPTable(cwss.get(i).getVul(), y, 1 - y));
			//			for(int j = index; j<index+2; j++)
			//			{
			y = Math.abs(vComScores.get(i) - ConfParser.getInstance().getStd());
			fvTable.add(new FunctVulNPTable(cwss.get(i).getVul(), y, 1 - y));
			//				System.out.println("Index: "+(i)+" SeNT F VALUES: "+tmp.get( i));
			//			}
//			index = index+2;
		}

		
		//System.out.println("Vulnerability Scores: "+vulScore);
		System.out.println("V Subsets: "+subSetsOfVulns);
		System.out.println("Pos Scores: "+totPosVulScores);
		System.out.println("Normed Scores: "+a);

		//		for(int i=0; i<vsTable.size(); i++)
		//			System.out.println("VSTable: "+vsTable.get(i).toString());

		System.out.println("NP Table Created");
		a.clear();

		System.out.println("Creating Bayesian Network File...");
		BayesNetworkCreator bnc = new BayesNetworkCreator();
		bnc.createFile(funcs, fvTable, vsTable, vComm, fNames, initialIVVals, functionNodeProbs);
		System.out.println("File Created!");

		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();
		String dir = helper.substring(0, helper.length()-2);
		//System.out.println(dir);

		String path1 = dir+"\\files\\org.openmarkov.full-0.1.3.jar";
		//System.out.println(path1);
		String path2 = dir+"\\files\\BNetwork.pgmx";
		//System.out.println(path2);
		String cmd = "java -jar " + path1 + " "+ path2;

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Double> normalize(ArrayList<Double> scores)
	{
		ArrayList<Double> normalizedVals = new ArrayList<Double>();
		Double min = Collections.min(scores);
		Double max = Collections.max(scores);

		//DecimalFormat df=new DecimalFormat("0.000");

		for(int i= 0; i<scores.size(); i++)
		{
			double finalValue = Math.round( Math.abs(((scores.get(i) - min)/(max - min)) - ConfParser.getInstance().getStd()) * 100.0 ) / 100.0;
			//String formate = df.format(Math.abs(((scores.get(i) - min)/(max - min)) - 0.001)); 
			//			
			//			try {
			//				double finalValue = (Double)df.parse(formate) ;
			normalizedVals.add(finalValue);
			//			} catch (ParseException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}


		}

		return normalizedVals;
	}


	public void calculateFuncProbs(HashMap<String, Integer> funcNodeProb, int numOfParam, HashMap<String, ArrayList<String>> vulnerabilityRules)
	{
		Object[] vulKeys = funcNodeProb.keySet().toArray();
		
		HashMap<String, Double> funcProb = new HashMap<>();
		
		
		
		for(int i=0; i<vulKeys.length; i++)
		{
			System.out.println(vulKeys[i].toString());
			double divisor = vulnerabilityRules.get(vulKeys[i].toString()).size()*numOfParam; 
			funcProb.put(vulKeys[i].toString(), funcNodeProb.get(vulKeys[i].toString()).doubleValue()/divisor);
		}
		System.out.println();
		functionNodeProbs = funcProb;
		System.out.println("PATEFIX:....."+functionNodeProbs);
	}
}

package tr.iyte.tajs.iv;

import java.util.ArrayList;
import java.util.HashMap;

public class ProbabilityCalculations {

	private HashMap<String,Double> vulnerability = new HashMap<>();
	private HashMap<String,Double> attackSurface = new HashMap<>();
	private HashMap<String,Double> exist = new HashMap<>();

	public double callgraphProbability(int totalResources,HashMap<String, Integer> callG)
	{

		double reachableResources;
		reachableResources = (double)callG.get("P+V") / (double) totalResources;

		return reachableResources;
	}

	public HashMap<String, Double> attackSurfaceProbability(double reachableResources, HashMap<String, Integer> reachInfo)
	{
		double notReachedResource = 1 - reachableResources;
		double commonResources = (reachInfo.get("P") + reachInfo.get("V")) - reachInfo.get("P+V"); 

		double p = reachInfo.get("P") - commonResources;
		double v = reachInfo.get("V") - commonResources;
		double vp = commonResources;

		System.out.println("VP: "+vp);
		System.out.println("P: "+p);
		System.out.println("V: "+v);

		vp = vp / reachInfo.get("P+V");
		v = v / reachInfo.get("P+V");
		p = p / reachInfo.get("P+V");
		v = v - 0.01;

		System.out.println("VP: "+vp);
		System.out.println("P: "+p);
		System.out.println("V: "+v);

		attackSurface.put("Only P", p);
		attackSurface.put("Only V", v);
		attackSurface.put("Only P+V", vp);
		attackSurface.put("NONE", 0.01);


		return attackSurface;
	}

	public HashMap<String, Double> existingVulnerabilityProbability(int numOfParam, int numOfGVar, int numOfVulParam, int numOfVulVar)
	{
		double total = numOfParam + numOfGVar;
		double vulnerable = numOfVulParam + numOfVulVar;

		double existingVul;

		existingVul = vulnerable/total;
		System.out.println("Exists: "+existingVul);
		exist.put("Exist", existingVul);
		exist.put("Not Exist", (1-existingVul));

		return exist;
	}

	public HashMap<String, Double> vulnerabilityProbability()
	{
		vulnerability.put("XSS", 0.32);
		vulnerability.put("SQLI", 0.25);
		vulnerability.put("IV", 0.19);
		vulnerability.put("CI", 0.13);
		vulnerability.put("PT", 0.1);
		vulnerability.put("OSCI", 0.01);

		return vulnerability;
	}

	//HashMap<String, Double> vul, HashMap<String, Double> attackSurf, HashMap<String, Double> exist
	public void attackSuccessProbability()
	{
		ArrayList<Double> success = new ArrayList<>();

		Object[] vuls = vulnerability.keySet().toArray();
		Object[] vulVal = vulnerability.values().toArray();

		Object[] exists = exist.keySet().toArray();
		Object[] existsVal = exist.values().toArray();

		Object[] attSur = attackSurface.keySet().toArray();
		Object[] attSurVal = attackSurface.values().toArray();


//		for(int j=0; j<exist.size(); j++)
//		{
//			for(int k=0; k<attackSurface.size(); k++)
//			{
//				for(int i=0; i<vulnerability.size(); i++)
//				{
//					double value = Double.parseDouble(vulVal[i].toString())*Double.parseDouble(existsVal[j].toString())*Double.parseDouble(attSurVal[k].toString());
//					success.add(value);
//					System.out.println(vuls[i].toString()+" - "+ exists[j].toString()+ " - "+ attSur[k].toString() +": "+value);
//				}
//			}
//		}
	}

}

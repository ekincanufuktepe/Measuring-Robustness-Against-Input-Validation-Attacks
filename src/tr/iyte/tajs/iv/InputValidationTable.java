package tr.iyte.tajs.iv;

import java.util.ArrayList;
import java.util.Random;

import tr.iyte.tajs.iv.FunctionParameterTree.FunctionNode;
import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;

public class InputValidationTable {
	
	public static double selectFunctions(RootFunctionNode nodeF)
	{
		Random rand = new Random();
		ArrayList<FunctionNode> filteredFunction = new ArrayList<>();
		
		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		{
			if(!nodeF.functionChildNodes.get(i).functionName.equals("NO NAME") &&
				!nodeF.functionChildNodes.get(i).functionName.equals("MAIN") &&
				nodeF.functionChildNodes.get(i).parameterChildNodes.size() > 0)
			{
				filteredFunction.add(nodeF.functionChildNodes.get(i));
			}
		}
		
		//int numOfSelectedFuncs = rand.nextInt(filteredFunction.size());
		int numOfSelectedFuncs = filteredFunction.size();
		System.out.println("ANALYZING "+numOfSelectedFuncs+" FUNCTIONS FOR BAYESIAN NETWORK");
		
		double numOfV = 0;
		double numOfIV = 0;
		
		for(int i=0; i<numOfSelectedFuncs; i++)
		{
//			int randIndex = rand.nextInt(filteredFunction.size());
			for(int j=0; j<filteredFunction.get(i).parameterChildNodes.size(); j++)
			{
				if(filteredFunction.get(i).parameterChildNodes.get(j).status.equals(ValidationStatus.NOT_VALIDATED_BEFORE_USAGE))
				{
					numOfIV++;
				}
				else if(filteredFunction.get(i).parameterChildNodes.get(j).status.equals(ValidationStatus.VALIDATED))
				{
					numOfV++;
				}
			}
		}
		
		double total = numOfV + numOfIV;
		
		System.out.println("Validated: "+(numOfV/total));
		System.out.println("Not Validated: "+(numOfIV/total));
	
		return (numOfV/total);
	}

}

package tr.iyte.tajs.iv;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionParameterTree {
	private static FunctionParameterTree instance = null;
	
	static {
		getInstance();
	}

	public static FunctionParameterTree getInstance() {
		if (instance == null) {
			instance = new FunctionParameterTree();
		}
		return instance;
	} 

	public static class RootFunctionNode
	{
		public ArrayList<FunctionNode> functionChildNodes = new ArrayList<>();
		public String root = "Root";

		public RootFunctionNode() {
			// TODO Auto-generated constructor stub
		}
	}

	public static class FunctionNode 
	{
		public ArrayList<ParameterNode> parameterChildNodes = new ArrayList<>();
		public ArrayList<FunctionNode> callingFunctions = new ArrayList<>();
		
		//the String does not overlap with other functions because each function are kept as separate 
		//function nodes. The ArrayList<Integer> keeps the blocks where the parameter is used.
		public HashMap<String, ArrayList<Integer>> paramGraph = new HashMap<String, ArrayList<Integer>>();
		
		public String functionName;
		public int funcLine, funcCol, numOfCallgraphNodes, endLine, SLOC; // Optional??

		//		String parameterName;
		//		int definedLine, usedLine, validatedLine;

		public FunctionNode(String fname, int row, int col) {
			this.functionName = fname;
			this.funcLine = row;
			this.funcCol = col;
		}
	}

	public static class ParameterNode 
	{
		//ArrayList<Integer> parameterChildNodes = new ArrayList<Integer>();
		public String parameterName;
		public int definedLine, usedLine, validatedLine;
		public ValidationStatus status;

		//with parameter info
		public ParameterNode(String name, int def, int used, int val) {
			this.parameterName = name;
			this.definedLine = def;
			this.usedLine = used;
			this.validatedLine = val;
		}

		//only parameter name
		public ParameterNode(String name) {
			this.parameterName = name;
			this.usedLine = 0;
			this.validatedLine = 0;
		}
	}

	public static RootFunctionNode run(String fname, int row, int col, ArrayList<String> pname) {

		RootFunctionNode root = new RootFunctionNode();

		FunctionNode rootnode = new FunctionNode(fname, row, col);

		insertFunctions(root, rootnode);

		System.out.println("Building tree with rootvalue" + rootnode.functionName);

		//ParameterNode pNode = new ParameterNode(name);
		System.out.println("=================================");
		for(int i=0; i<pname.size(); i++)
			insertFunctionParameters(rootnode, new ParameterNode(pname.get(i)));

		return root;
	}

	public static RootFunctionNode run(RootFunctionNode n,String fname, int row, int col, ArrayList<String> pname) {

		FunctionNode rootnode = new FunctionNode(fname, row, col);

		insertFunctions(n, rootnode);

		//System.out.println("Building tree with rootvalue" + rootnode.functionName);

		//ParameterNode pNode = new ParameterNode(name);
		System.out.println("=================================");
		for(int i=0; i<pname.size(); i++)
			insertFunctionParameters(rootnode, new ParameterNode(pname.get(i)));

		return n;
	}


	public static void insertFunctionParameters(FunctionNode node, ParameterNode pnode) {
		node.parameterChildNodes.add(pnode);	
	}

	public static void insertFunctions(RootFunctionNode node, FunctionNode fnode) {
		node.functionChildNodes.add(fnode);	
	}




	//	public void insert(Node node, String noParam) {
	//		node.functionChildNodes.add(noParam);	
	//	}

	public static void printParameterStatus(RootFunctionNode node)
	{
		for(int i=0; i<node.functionChildNodes.size(); i++)
		{
			System.out.println("Function: "+ node.functionChildNodes.get(i).functionName);
			for(int j=0; j<node.functionChildNodes.get(i).parameterChildNodes.size(); j++)
			{		
				if(node.functionChildNodes.get(i).parameterChildNodes.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
				{
					System.out.println("\tParameter: "+node.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName+" Status: NOT VALIDATED BEFORE USAGE");
				}
				else if(node.functionChildNodes.get(i).parameterChildNodes.get(j).status == ValidationStatus.VALIDATED)
				{
					System.out.println("\tParameter: "+node.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName+" Status: VALIDATED");
				}
				else if(node.functionChildNodes.get(i).parameterChildNodes.get(j).status == null)
				{
					node.functionChildNodes.get(i).parameterChildNodes.get(j).status = ValidationStatus.UNKNOWN;
					System.out.println("\tParameter: "+node.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName+" Status: UNKNOWN");
				}
			}
		}		
	}

	public static void printInOrder(FunctionNode node) {
		System.out.println("Node: "+node.functionName);
		System.out.println("Node "+node.functionName+"'s"+" Children: "+node.parameterChildNodes);
	}

	/**
	 * This function provides information about the number of functions by the
	 * number of parameters they have
	 * */
	public static void printFunctionParameterInfo(RootFunctionNode node)
	{
		HashMap<Integer, Integer> parameterInfo = new HashMap<Integer,Integer>();

		for(int i=0; i<node.functionChildNodes.size(); i++)
		{
			if(parameterInfo.containsKey(node.functionChildNodes.get(i).parameterChildNodes.size()))
			{
				int value = parameterInfo.get(node.functionChildNodes.get(i).parameterChildNodes.size());
				value++;
				parameterInfo.replace(node.functionChildNodes.get(i).parameterChildNodes.size(), value);
			}
			else
			{
				parameterInfo.put(node.functionChildNodes.get(i).parameterChildNodes.size(), 1);
			}
		}

		System.out.println("The Paremeter Information: "+parameterInfo);

	}

	/**
	 * This function provides information about the number of functions by the
	 * number of parameters they have.
	 * However! this print only the function that are only tested.
	 * */
	public static HashMap<Integer, Integer> printFunctionParameterInfo(FunctionNode node, HashMap<Integer, Integer> parameterInfo)
	{		
		if(parameterInfo.containsKey(node.parameterChildNodes.size()))
		{
			int value = parameterInfo.get(node.parameterChildNodes.size());
			value++;
			parameterInfo.replace(node.parameterChildNodes.size(), value);
		}
		else
		{
			parameterInfo.put(node.parameterChildNodes.size(), 1);
		}

		//System.out.println("The Paremeter Information: "+parameterInfo);
		return parameterInfo;
	}
}

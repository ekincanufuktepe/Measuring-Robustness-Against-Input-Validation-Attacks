package tr.iyte.tajs.iv;

import java.util.ArrayList;

public class VariableTree {
	
	private static VariableTree instance = null;
	static RootVariableNode root;
	static {
		getInstance();
	}
	
	public static VariableTree getInstance() {
		if (instance == null) {
			instance = new VariableTree();
		}
		return instance;
	}
	
	/**
	 * The root of variable nodes (parent node of variable node). This node contains the
	 * variables as their child nodes.
	 */
	static class RootVariableNode
	{
		ArrayList<VariableNode> varibleChildNodes = new ArrayList<>();
		String rootVar = "Root";

		public RootVariableNode() {
			// TODO Auto-generated constructor stub
		}
	}
	
	static class VariableNode 
	{
		/** 
		 * Child nodes of a variable node. The variable node contains information about the 
		 * variable. 
		 */
		ArrayList<VariableInFunction> callInFunc = new ArrayList<>();
		//ArrayList<VariableNode> variableChildNodes = new ArrayList<>();
		String variableName;
		String funcName;

		int variableLine, row, col; // Optional??
		VariableType vType;
		ValidationStatus status;
		int definedLine, usedLine, validatedLine;

		public VariableNode(String vName, VariableType vType, String localFunc, int r, int c) {
			this.variableName = vName;
			this.vType = vType;
			this.funcName = localFunc;
			this.row = r;
			this.col = c;
		}
		
		public VariableNode(int row, int column) {
			this.row = row;
			this.col = column;
		}
	}
	
	static class VariableInFunction
	{
		String funcName;
		ValidationStatus status;
		//these source locations hold info about which variables are used in which functions, including validation status
		int funcLine, funcCol;
		
		public VariableInFunction(String fName, int fLine, int fCol, ValidationStatus status)
		{
			this.funcLine = fLine;
			this.funcCol = fCol;
			this.funcName = fName;
			this.status = status;
		}
		
	}

	// Building Variable Tree
	public static RootVariableNode run(String vname, VariableType vType, String localFunc, int r, int c) {

		
		if(root == null)
		{
			root = new RootVariableNode();
		}	

		VariableNode varNode = new VariableNode(vname, vType, localFunc, r, c);

		insertVariables(root, varNode);

		//System.out.println("Building tree with rootvalue " + varNode.variableName);

		return root;
	}
	
	public static void insertVariables(RootVariableNode node, VariableNode vnode) {
		node.varibleChildNodes.add(vnode);
	}
	
	public static void insertVariables(VariableNode vnode, VariableInFunction vfnode) {
		vnode.callInFunc.add(vfnode);
	}

//	public static void printInOrder(RootVariableNode node) {
//		//System.out.println("Variables: "+node.varibleChildNodes.toString());
//	}
	
	public static void printVariableStatus(RootVariableNode node)
	{
		for(int i=0; i<node.varibleChildNodes.size(); i++)
		{
			if(node.varibleChildNodes.get(i).vType == VariableType.GLOBAL)
			{
				//System.out.println("GLOBAL variable '"+node.varibleChildNodes.get(i).variableName+"'");
			}
			else if(node.varibleChildNodes.get(i).vType == VariableType.LOCAL)
			{
				//System.out.println("LOCAL variable '"+node.varibleChildNodes.get(i).variableName+"' in '"+node.varibleChildNodes.get(i).funcName+"'");
			}
			else if(node.varibleChildNodes.get(i).vType == VariableType.GLOBAL_OR_LOCAL)
			{
				//System.out.println("LOCAL variable '"+node.varibleChildNodes.get(i).variableName+"' in '"+node.varibleChildNodes.get(i).funcName+"'");
			}	
		}
	}
}

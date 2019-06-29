package tr.iyte.tajs.iv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tr.iyte.edu.bn.BayesianNetworkExecuter;
import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;
import tr.iyte.tajs.iv.VariableTree.RootVariableNode;
import tr.iyte.tajs.iv.parameter.relation.ASTExecuter;
import tr.iyte.tajs.iv.parameter.relation.ASTJsonParser;
import tr.iyte.tajs.iv.parameter.relation.ParameterRelationExtractor;
import tr.iyte.tajs.testcase.code.generator.ClientTestCaseCodeGenerator;
import tr.iyte.tajs.testcase.code.generator.ServerTestCaseCodeGenerator;
import tr.iyte.tajs.testcase.code.generator.AlternateClientTestCase;
import dk.brics.tajs.analysis.Analysis;
import dk.brics.tajs.flowgraph.*;
import dk.brics.tajs.util.AnalysisException;
import dk.brics.tajs.Main;

public class MainIV {

	public double fvValue = 0;

	private static InputInformation inputs = new InputInformation();

	private Map<String,List<String>> funcDecl = new HashMap<>();
	private final static String fileName = "out/flowgraphs/final.dot";

	private static ArrayList<String> functions = new ArrayList<>();

	private ArrayList<String> parameters = new ArrayList<>();

	static RootFunctionNode nodeF = null;
	static RootVariableNode nodeV = null;

//	static ServerTestCaseCodeGenerator codeS = new ServerTestCaseCodeGenerator(); 
//	static ClientTestCaseCodeGenerator codeC = new ClientTestCaseCodeGenerator();
//	static AlternateClientTestCase codeAC = new AlternateClientTestCase();
//	static ParameterRelationExtractor pre = new ParameterRelationExtractor();

	public static void main(String[] args) {

		/**#########################################**/
		/**
		 * Experiments on Data Attack Success 
		 **/
		//String file = "clean/joose.js";	
		//String file = "clean/handlebars-v3.0.0.js";
		//String file = "clean/backbone.js";
		//String file = "clean/chaplin.js";
		//String file = "clean/midori.js";
		//String file = "clean/zepto.js";

		String file = "clean/jquery-1.9.1.js";
		
		//String file = "examples/analyzeLibs.js";
		//String file = "examples/analyzeLibsRaw.js";
		
		//String file = "clean/verify.js";
		//String file = "clean/verify.notify.js";
		//String file = "clean/gen_validatorv4.js";
		
		
		//String file = "examples/test.js";
		
		//String file = "clean/prototype.js";
		//String file = "clean/dojo.js";
		//String file = "clean/d3.js";	

		/**#########################################**/


		/**#########################################**/
		/**
		 * Tests that could be applied
		 * */
		//String file = "clean/jquery-1.9.1.js";
		//String file = "clean/midori.js";
		//String file = "clean/zepto.js";
		//String file = "clean/hammer.js";
		//String file = "clean/reveal.js";
		//String file = "clean/angular.js"; // Will probably work without any external calling style

		/**#########################################**/


		/**#########################################**/
		/**
		 * Tests for UYMS 2015
		 * */
		//String file = "clean/rivets.js";
		//String file = "clean/mustache.js";
		//String file = "clean/uikit.js";
		//String file = "clean/Chart.js";
		//String file = "clean/masonry.pkgd.js";
		/**#########################################**/

		//String file = "clean/riot.js"; 
		//String file = "clean/react.js";
//		String file = "examples/TestCase_1.js";
		//String file = "examples/test.js";
		//String file = "examples/test.js";
		//String file = "examples/a.js";


		MainIV miv = new MainIV();
		try {
			long start = System.currentTimeMillis();
			Main.initLogging();
			Analysis a = Main.init(args, null);
			if (a == null)
				System.exit(-1);

			Main.run(a);


			FlowGraph fg = new FlowGraph();
			fg.getNumberOfNodes();

			System.out.println("The Funcs:"+fg.getNumberOfNodes());
			//						miv.parser();
			nodeF = InputValidationDetector.parameterInputValDetector(fileName, miv.parser());
			SourceCodeParser scp = new SourceCodeParser();
			nodeF = scp.parseSourceCode(file, nodeF);

			nodeV = InputValidationDetector.variableInputValDetector(fileName, nodeV, nodeF);


			countValidationStatus(nodeF);

			if(inputs.getNumOfGlobalVariables() > 0)
			{
				countValidationStatus(nodeV, false);
			}
			else if(inputs.getNumOfGlobalVariables() == 0)
			{
				countValidationStatus(nodeV, true);
			}

			//			FunctionParameterTree.printParameterStatus(nodeF);
			//			VariableTree.printVariableStatus(nodeV);
			fixSingleValStatusVariable(nodeV);

			System.out.println("NUMBER OF FUNCTIONS              : "+functions.size());
			System.out.println("NUMBER OF PARAMETERS             : "+inputs.getNumOfParameters());
			System.out.println("NUMBER OF GLOBAL VARIABLES       : "+inputs.getNumOfGlobalVariables());
			System.out.println("NUMBER OF GLOBAL/LOCAL VARIABLES : "+inputs.getNumOfGLVariables());
			System.out.println("NUMBER OF LOCAL VARIABLES        : "+inputs.getNumOfLocalVariables());
			System.out.println("NUMBER OF VALIDATED PARAMETERS   : "+inputs.getValidatedParameters());
			System.out.println("NUMBER OF INVALIDATED PARAMETERS : "+inputs.getInvalidatedParameters());
			System.out.println("NUMBER OF UNKNOWN PARAMETERS     : "+inputs.getUnknownParameter());

			//			System.out.println("NUMBER OF VALIDATED VARIABLES    : "+inputs.getValidatedVariables());
			System.out.println("NUMBER OF VALIDATED VARIABLES FIX: "+inputs.getValidatedVariablesFixed());
			//			System.out.println("NUMBER OF INVALIDATED VARIABLES  : "+inputs.getInvalidatedVariables());
			System.out.println("NUMBER OF INVALIDATED VARIABLES F: "+inputs.getInvalidatedVariablesFixed());
			System.out.println("NUMBER OF UNKNOWN VARIABLES      : "+inputs.getUnknownVariables());
			System.out.println("NUMBER OF NOT GLOBAL VARIABLES   : "+inputs.getNotGlobalVariable());

//			AdjacencyMatrixGenerator amg = new AdjacencyMatrixGenerator();
//
//			nodeF = amg.adjacencyMatrix(fileName, nodeF);
//
//			HashMap<String, Integer> callG = new HashMap<>();
//
//			if(inputs.getNumOfGlobalVariables() > 0)
//			{
//				callG = AdjacencyMatrixGenerator.reachableNodes(nodeF, nodeV, false);
//			}
//			else if(inputs.getNumOfGlobalVariables() == 0)
//			{
//				callG = AdjacencyMatrixGenerator.reachableNodes(nodeF, nodeV, true);
//			}
//			ProbabilityCalculations probCalc = new ProbabilityCalculations();
//
//			double reach = probCalc.callgraphProbability(functions.size(), callG);
//			probCalc.attackSurfaceProbability(reach, callG);
//			probCalc.existingVulnerabilityProbability(inputs.getNumOfParameters(), inputs.getNumOfGLVariables(), inputs.getInvalidatedParameters(), inputs.getInvalidatedVariablesFixed());
//			probCalc.vulnerabilityProbability();
//			probCalc.attackSuccessProbability();


			//			for(int i=0; i<nodeF.functionChildNodes.size(); i++)
			//			{
			//				System.out.println("Function: "+nodeF.functionChildNodes.get(i).functionName+":"+" "+nodeF.functionChildNodes.get(i).funcLine+"\n # of Callgraph nodes: "+nodeF.functionChildNodes.get(i).numOfCallgraphNodes);
			//			}



			// Create a .jpeg file of dot extension
			/*File currentDirFile = new File(".");
			String helper = currentDirFile.getAbsolutePath();
			String dir = helper.substring(0, helper.length()-2);

			String path1 = dir+"\\out\\flowgraphs\\final.dot";
			String cmd = "dot \""+path1+"\" -Tjpeg -o \""+dir+"\\out\\inputFlowgraph.jpeg\"";

			Runtime.getRuntime().exec(cmd);

			 */

			
			String path = (System.getProperty("user.dir")+"/"+args[args.length-1]).replace("\\", "/");

			System.out.println("\n");

			System.out.println("==============INITIATING BAYESIAN NETWORK ANALYSIS==============");
			BayesianNetworkExecuter bne = new BayesianNetworkExecuter();
			InputValidationTable table = new InputValidationTable();

			bne.executeBN(table.selectFunctions(nodeF));
			long time = System.currentTimeMillis() - start;
			System.out.println("Time Elapsed: "+(double)(time / 1000)  / 60+" mins");
			System.out.println("Time Elapsed: "+(double)(time / 1000)  % 60+" seconds");
//						codeS.codeGenerator(nodeF, path);
						//codeC.clientSideCodeGenerator(nodeF, path);	
//						codeAC.clientSideCodeGenerator(nodeF, path);
//						System.out.println(System.getProperty("user.dir")+args[args.length-1].replace("\\", "/"));
//						FunctionParameterTree.printFunctionParameterInfo(nodeF);
//						nodeF = ParameterRelationExtractor.paramBlockInfo(fileName, nodeF);
//						HashMap<Integer, Set<Integer>> generalInfo = ParameterRelationExtractor.extractGeneralParamRel(fileName, nodeF);
//			ASTExecuter launchAST = new ASTExecuter();
//
//			launchAST.ASTExecutionCodeGenerator(System.getProperty("user.dir")+"\\"+args[args.length-1].replace("\\", "\\"));
//			launchAST.ASTTriggerExecution(System.getProperty("user.dir")+"\\"+"ASTJson");
//						ASTExecuter.ASTExecutionCodeGenerator(System.getProperty("user.dir")+"\\"+args[args.length-1].replace("\\", "\\"));
//						ParameterRelationExtractor.extractParamDependency(fileName, generalInfo, nodeF);
//						ASTExecuter.ASTTriggerExecution(System.getProperty("user.dir")+"\\"+"ASTJson");
			System.exit(0);

		} catch (AnalysisException e) {
			e.printStackTrace();
			System.exit(-2);
		}
		//		catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}


	/**
	 * Parses only where the Functions and Parameters are defined
	 * NEW! Global and Local variables are added
	 * */
	BufferedReader reader = null;
	public RootFunctionNode parser()
	{
		int row=0, col=0;
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			String str, f=null;
			int j=0;
			String localFunc = "GLOBAL";

			boolean flag = false;
			VariableType varType = VariableType.GLOBAL;

			while((str = reader.readLine()) != null)
			{
				/**
				 * Capture functions, its parameters and variables
				 * */
				if(str.contains("subgraph cluster"))
				{
					row =0; col=0;
					/**
					 * Locate the Global and Local variables and initialize the assigned variable type
					 * */
					if(str.contains("subgraph cluster0 {"))
					{
						System.out.println("There is a Function00");
						varType = VariableType.GLOBAL;
						localFunc = "GLOBAL";
					}
					else if(str.contains("subgraph cluster1 {"))
					{
						System.out.println("There is a Function11");
						varType = VariableType.GLOBAL_OR_LOCAL;
					}
					else if(str.contains("subgraph cluster"))
					{
						//System.out.println("There is a Function");
						varType = VariableType.LOCAL;
					}

					/**
					 * When the program captures a "subgraph cluster" this means that it is a function.
					 * The content of the subgraph cluster shows what is happening in the function
					 * */

				}
				if(str.contains("label=\"function") || str.contains("label=\"<main>"))
				{
					String tokens[] = str.split(" ");				
					if(tokens[1].equals("("))
					{
						functions.add("NO NAME");
						f = "NO NAME";
						System.out.println("F: "+f);
						j = 2;
					}
					else if(!tokens[1].equals("(") && !str.contains("label=\"<main>"))
					{
						functions.add(tokens[1]);
						f = tokens[1];
						System.out.println("F: "+f);
						j = 3;
					}
					else
					{
						functions.add("MAIN");
						f = "MAIN";
						System.out.println("F: "+f);
						j = 3;
					}

					for(int i=0; i<tokens.length; i++)
					{
						//add(tokens[i+1]);							
						//parameters
						if(tokens[i].contains(")"))
						{
							//Get function line(location) in source code
							int start = tokens[i].lastIndexOf(".js:")+3;
							int end = tokens[i].indexOf(":", start+1);
							int endEnd = 0;
							if(str.contains("\\nouter"))
							{
								endEnd = tokens[i].indexOf("\\n", end);
								//System.out.println(tokens[i].substring(end+1, endEnd));
							}
							else if(!str.contains("\\nouter"))
							{
								endEnd = tokens[i].indexOf("\";", end);
								//System.out.println(tokens[i].substring(end+1, endEnd));
							}
							row = Integer.parseInt(tokens[i].substring(start+1, end));
							col = Integer.parseInt(tokens[i].substring(end+1, endEnd));

							//get parameter of the function
							for(int k=j; k<i; k++)
							{
								parameters.add(tokens[k]);
							}

							if(flag == false)
							{
								System.out.println("There is a Function1");
								FunctionParameterTree.getInstance();
								nodeF = FunctionParameterTree.run(f, row, col, parameters);
								flag = true;
								parameters.clear();
							}
							else if(flag == true)
							{
								System.out.println("There is a Function2");
								System.out.println(parameters);
								FunctionParameterTree.getInstance();
								nodeF = FunctionParameterTree.run(nodeF, f, row, col, parameters);
								//flag = false;
								parameters.clear();
							}
						}
					}
				}

				String variableStr = "vardecl['";


				if(str.contains(variableStr))
				{
					//System.out.println("There is a Variable/s");
					String tokens[] = str.split(" ");
					for(int i=0; i<tokens.length; i++)
					{
						if(tokens[i].contains(variableStr))
						{
							int start = tokens[i].indexOf(variableStr);
							int end = tokens[i].indexOf("']", start);
							String variable = tokens[i].substring((start+variableStr.length()), end);
							VariableTree.getInstance();
							nodeV = VariableTree.run(variable, varType, localFunc, row, col);
						}
					}
				}
			}

			//System.out.println("Func infos: "+funcDecl.get("fA"));
		}
		catch(IOException e){
			System.err.println("Can't open the config file: " + fileName);

		}


		// Print the Function with parameters in tree structure

		int numOfParams = 0;

		System.out.println("========FUNCTION & PARAMETER INFO========");
		System.out.println(nodeF.functionChildNodes.size());
		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		{
			System.out.println("\nFunction: "+nodeF.functionChildNodes.get(i).functionName);
			for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
			{
				System.out.println("\tParameters: "+nodeF.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName);
			}
			numOfParams = numOfParams + nodeF.functionChildNodes.get(i).parameterChildNodes.size();
		}

		System.out.println("========VARIABLE INFO========");
		int l=0,g=0,gl=0;
		for(int i=0; i<nodeV.varibleChildNodes.size(); i++)
		{
			if(nodeV.varibleChildNodes.get(i).vType == VariableType.LOCAL)
			{
				l++;
			}
			else if(nodeV.varibleChildNodes.get(i).vType == VariableType.GLOBAL)
			{
				g++;
			}
			else if(nodeV.varibleChildNodes.get(i).vType == VariableType.GLOBAL_OR_LOCAL)
			{
				//				System.out.println("VAR: "+nodeV.varibleChildNodes.get(i).variableName+" - "+nodeV.varibleChildNodes.get(i).row);
				gl++;
			}
			else
			{
				System.err.println("Unknown variable Type!");
			}
			//System.out.println("Variable: "+nodeV.varibleChildNodes.get(i).variableName);
		}

		inputs.setNumOfGlobalVariables(g);
		inputs.setNumOfLocalVariables(l);
		inputs.setNumOfGLVariables(gl);
		inputs.setNumOfParameters(numOfParams);

		return nodeF;

	}

	public static void countValidationStatus(RootFunctionNode node)
	{
		int nv=0, v=0, u=0;
		for(int i=0; i<node.functionChildNodes.size(); i++)
		{
			System.out.println("Function: "+ node.functionChildNodes.get(i).functionName);
			for(int j=0; j<node.functionChildNodes.get(i).parameterChildNodes.size(); j++)
			{		
				if(node.functionChildNodes.get(i).parameterChildNodes.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
				{
					System.out.println("\tParameter: "+node.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName+" Status: NOT VALIDATED BEFORE USAGE");
					nv++;
				}
				else if(node.functionChildNodes.get(i).parameterChildNodes.get(j).status == ValidationStatus.VALIDATED)
				{
					System.out.println("\tParameter: "+node.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName+" Status: VALIDATED");
					v++;
				}
				else if(node.functionChildNodes.get(i).parameterChildNodes.get(j).status == ValidationStatus.UNKNOWN)
				{
					node.functionChildNodes.get(i).parameterChildNodes.get(j).status = ValidationStatus.UNKNOWN;
					System.out.println("\tParameter: "+node.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName+" Status: UNKNOWN");
					u++;
				}
			}
		}
		inputs.setInvalidatedParameters(nv);
		inputs.setValidatedParameters(v);
		inputs.setUnknownParameter(u);
	}

	public static void countValidationStatus(RootVariableNode node, boolean libSign)
	{
		int nv=0, v=0, u=0, ng=0;
		for(int i=0; i<node.varibleChildNodes.size(); i++)
		{	
			if(libSign == true)
			{
				if( node.varibleChildNodes.get(i).vType == VariableType.GLOBAL_OR_LOCAL)
				{
					System.out.println("Variable: "+node.varibleChildNodes.get(i).variableName+": "+ node.varibleChildNodes.get(i).row);
					for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
					{
						if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
						{
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" - "+node.varibleChildNodes.get(i).callInFunc.get(j).funcLine+" Status: NOT VALIDATED BEFORE USAGE");
							nv++;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.VALIDATED)
						{
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" - "+node.varibleChildNodes.get(i).callInFunc.get(j).funcLine+" Status: VALIDATED");
							v++;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.UNKNOWN)
						{
							//node.varibleChildNodes.get(i).status = ValidationStatus.UNKNOWN;
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" Status: UNKNOWN");
							u++;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_GLOBAL_VARIABLE)
						{
							//node.varibleChildNodes.get(i).status = ValidationStatus.NOT_GLOBAL_VARIABLE;
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" Status: NOT A GLOBAL VARIABLE");
							ng++;
						}
					}
					if(node.varibleChildNodes.get(i).callInFunc.size() == 0)
					{
						node.varibleChildNodes.get(i).status = ValidationStatus.UNKNOWN;
						//System.out.println("I am UNKNOWN ^.^ #2");
					}
				}
			}
			else if(libSign == false)
			{
				if( node.varibleChildNodes.get(i).vType == VariableType.GLOBAL)
				{
					System.out.println("Variable: "+node.varibleChildNodes.get(i).variableName+": "+ node.varibleChildNodes.get(i).row);
					for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
					{
						if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
						{
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" - "+node.varibleChildNodes.get(i).callInFunc.get(j).funcLine+" Status: NOT VALIDATED BEFORE USAGE");
							nv++;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.VALIDATED)
						{
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" - "+node.varibleChildNodes.get(i).callInFunc.get(j).funcLine+" Status: VALIDATED");
							v++;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.UNKNOWN)
						{
							//node.varibleChildNodes.get(i).status = ValidationStatus.UNKNOWN;
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" Status: UNKNOWN");
							u++;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_GLOBAL_VARIABLE)
						{
							//node.varibleChildNodes.get(i).status = ValidationStatus.NOT_GLOBAL_VARIABLE;
							System.out.println("\tVariable: "+node.varibleChildNodes.get(i).variableName+" In Function: "+node.varibleChildNodes.get(i).callInFunc.get(j).funcName+" Status: NOT A GLOBAL VARIABLE");
							ng++;
						}
					}
					if(node.varibleChildNodes.get(i).callInFunc.size() == 0)
					{
						node.varibleChildNodes.get(i).status = ValidationStatus.UNKNOWN;
						//System.out.println("I am UNKNOWN ^.^ #1");
					}
				}
			}

		}
		inputs.setValidatedVariables(v);
		inputs.setInvalidatedVariables(nv);
		inputs.setUnknownVariables(u);
		inputs.setNotGlobalVariable(ng);
	}

	public static void fixSingleValStatusVariable(RootVariableNode node)
	{
		int ivf=0, vvf=0, uf=0;
		if(inputs.getNumOfGlobalVariables() > 0)
		{
			for(int i=0; i<node.varibleChildNodes.size(); i++)
			{
				if(node.varibleChildNodes.get(i).vType == VariableType.GLOBAL)
				{
					if(node.varibleChildNodes.get(i).callInFunc.size() == 0 && node.varibleChildNodes.get(i).status == ValidationStatus.UNKNOWN)
					{
						uf++;
					}
					boolean stat = true;
					for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
					{
						if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.VALIDATED)
						{
							stat = stat && true;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
						{
							stat = stat && false;
						}
					}
					if(stat == false)
					{
						node.varibleChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
						ivf++;
					}
					else if(stat == true && (node.varibleChildNodes.get(i).callInFunc.size() != 0) && node.varibleChildNodes.get(i).status != ValidationStatus.UNKNOWN)
					{
						node.varibleChildNodes.get(i).status = ValidationStatus.VALIDATED;
						vvf++;
					}
				}
				inputs.setInvalidatedVariablesFixed(ivf);
				inputs.setValidatedVariablesFixed(vvf);
				inputs.setUnknownVariables(uf);
			}
		}
		else
		{
			for(int i=0; i<node.varibleChildNodes.size(); i++)
			{
				if(node.varibleChildNodes.get(i).vType == VariableType.GLOBAL_OR_LOCAL)
				{
					if(node.varibleChildNodes.get(i).callInFunc.size() == 0 && node.varibleChildNodes.get(i).status == ValidationStatus.UNKNOWN)
					{
						uf++;
					}
					boolean stat = true;
					for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
					{

						if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.VALIDATED)
						{
							stat = stat && true;
						}
						else if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
						{
							stat = stat && false;
						}
					}
					if(stat == false)
					{
						node.varibleChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
						ivf++;
					}
					else if(stat == true && (node.varibleChildNodes.get(i).callInFunc.size() != 0) && node.varibleChildNodes.get(i).status != ValidationStatus.UNKNOWN)
					{
						node.varibleChildNodes.get(i).status = ValidationStatus.VALIDATED;
						vvf++;
					}
				}
				inputs.setInvalidatedVariablesFixed(ivf);
				inputs.setValidatedVariablesFixed(vvf);
				//System.out.println("UF: "+uf);
				inputs.setUnknownVariables(uf);
			}
		}
	}

	//add function
	public void add (String func) {
		if (funcDecl.containsKey(func)) return;
		funcDecl.put(func, new ArrayList<String>());
	}

	//add parameters
	public void add (String func, String param) {
		this.add(func); this.add(param);
		funcDecl.get(func).add(param);
	}

}

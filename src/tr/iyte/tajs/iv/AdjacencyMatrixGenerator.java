package tr.iyte.tajs.iv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import Jama.*;
import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;
import tr.iyte.tajs.iv.VariableTree.RootVariableNode;

/**
 * This class generates an Adjacency matrix to show the relationships
 * between the functions. Later on this matrix will help us create a
 * call graph. Instead of parsing the source code, this class will parse
 * flow graph of each function and capture the "calls". Then match them 
 * by name (NOT BY DEFINITION LINE, BECAUSE THE TAJS DOESN'T GENERATE ITS
 * FLOW GRAPH'S CALL OPERATIONS BY THEIR DEFINITION LINE, BUT BY WHERE IT 
 * IS CALLED).
 * */
public class AdjacencyMatrixGenerator {

	BufferedReader reader = null;

	public RootFunctionNode adjacencyMatrix(String fileName, RootFunctionNode nodeF)
	{

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String str;
			int index = -1;
			boolean flag = true;
			while((str = reader.readLine()) != null)
			{
				String tokens[] = str.split(" ");
				int start=0, end=0, endEnd=0, row=0, col=0;

				if(str.contains("label=\"function") || str.contains("label=\"<main>"))
				{
					for(int i=0; i<tokens.length; i++)
					{									
						if(tokens[i].contains(")"))
						{
							//Get function line(location) in source code
							start = tokens[i].lastIndexOf(".js:")+3;
							end = tokens[i].indexOf(":", start+1);
							endEnd = 0;
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
							flag = true;
						}
					}
				}

				if(flag)
				{
					for(int i=0; i<nodeF.functionChildNodes.size(); i++)
					{
						if(nodeF.functionChildNodes.get(i).funcLine == row && nodeF.functionChildNodes.get(i).funcCol == col)
						{
							//							System.out.println("In function: "+nodeF.functionChildNodes.get(i).functionName+":"+nodeF.functionChildNodes.get(i).funcLine);
							index = i;
							flag = false;
						}
					}
				}

				for(int j=0; j<tokens.length; j++)
				{
					if(tokens[j].contains("call-"))
					{
						//System.out.println("\tThere is a Call!");
						for(int k=0; k<nodeF.functionChildNodes.size(); k++)
						{	
							if((tokens[j].endsWith("."+nodeF.functionChildNodes.get(k).functionName) || tokens[j].equals("call-"+nodeF.functionChildNodes.get(k).functionName)))
							{	
								//								if(nodeF.functionChildNodes.get(k).functionName.contains("css"))
								//								{
								//									System.out.println("F: "+nodeF.functionChildNodes.get(k).functionName);
								//								}
								if(!nodeF.functionChildNodes.get(index).callingFunctions.contains(nodeF.functionChildNodes.get(k)))
								{
									nodeF.functionChildNodes.get(index).callingFunctions.add(nodeF.functionChildNodes.get(k));
									//									System.out.println("\tFunction: "+nodeF.functionChildNodes.get(k).functionName+" : "+nodeF.functionChildNodes.get(k).funcLine+"-->"+tokens[j]);
								}
							}
						}
					}
				}


			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return nodeF;
	}

	public static HashMap<String, Integer> reachableNodes(RootFunctionNode nodeF, RootVariableNode nodeV, boolean libSign)
	{
		double[][]  adjacencyMatrix = new double[nodeF.functionChildNodes.size()][nodeF.functionChildNodes.size()];
		//BigDecimal[][]  adjacencyMatrix = new BigDecimal[nodeF.functionChildNodes.size()][nodeF.functionChildNodes.size()];

		System.out.println("Size : "+nodeF.functionChildNodes.size());
		//Arrays.fill(adjacencyMatrix, 0);

//		for(int i=0; i<adjacencyMatrix.length; i++)
//		{
//			for(int j=0; j<adjacencyMatrix.length; j++)
//			{
//				adjacencyMatrix[i][j] = new BigDecimal(0);
//			}
//		}
		
		/**
		 * Creating and initializing the Adjacency Matrix
		 * */
		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		{
			for(int j=0; j<nodeF.functionChildNodes.size(); j++)
			{
				for(int k=0; k<nodeF.functionChildNodes.get(i).callingFunctions.size(); k++)
				{
					if(nodeF.functionChildNodes.get(j) == nodeF.functionChildNodes.get(i).callingFunctions.get(k))
					{
						//						System.out.println("EQUAL");
						//System.out.println(nodeF.functionChildNodes.get(i).parameterChildNodes.size());
						
						adjacencyMatrix[i][j] = 1;
						//adjacencyMatrix[i][j] = new BigDecimal(1);
					}
				}
			}
			/**Fix here ad param and global case*/
			//			if(nodeF.functionChildNodes.get(i).parameterChildNodes.size() > 0)
			//			{
			//				System.out.println("FASDFASFASDGAASG");
			//				adjacencyMatrix[i][i] = 1;
			//			}
		}
//		for(int i=0; i<adjacencyMatrix.length; i++)
//		{
//			for(int j=0; j<adjacencyMatrix.length; j++)
//			{
//				System.out.print(adjacencyMatrix[i][j]+"  ");
//			}
//			System.out.println();
//		}
	
		/**
		 * To see which nodes are reachable we take the power of the adjacency matrix from 1-n
		 * "n" is the size of the matrix which is the number at it can be reached at most by
		 *  excluding the back traces.
		 * */

		Matrix mat = new Matrix(adjacencyMatrix);
//		BigDecimal mat[][] = new BigDecimal[adjacencyMatrix.length][];
//		for(int i = 0; i < adjacencyMatrix.length; i++)
//		    mat[i] = adjacencyMatrix[i].clone();
		Matrix matTmp = new Matrix(adjacencyMatrix);
//		BigDecimal matTmp[][] = new BigDecimal[adjacencyMatrix.length][];
//		for(int i = 0; i < adjacencyMatrix.length; i++)
//			matTmp[i] = adjacencyMatrix[i].clone();
		Matrix reachableNodes = new Matrix(adjacencyMatrix);
//		BigDecimal reachableNodes[][] = new BigDecimal[adjacencyMatrix.length][];
//		for(int i = 0; i < adjacencyMatrix.length; i++)
//			reachableNodes[i] = adjacencyMatrix[i].clone();

		//		System.out.println("Matrix #1 : ");
		//		for(int i1 = 0; i1 < adjacencyMatrix.length; i1++) {
		//			System.out.print(nodeF.functionChildNodes.get(i1).functionName+": ");
		//			for(int j = 0; j < adjacencyMatrix[i1].length; j++) {        
		//				System.out.print( "   " + adjacencyMatrix[i1][j] );
		//			}
		//			System.out.println();
		//		}

		int cnt = 0;

		for(int j=0; j<nodeF.functionChildNodes.size(); j++)
		{
			if(!nodeF.functionChildNodes.get(j).functionName.equals("NO NAME"))
			{
				cnt++;
			}
		}


		/** 
		 * Count number of calls of a function in code.
		 * The algorithm works by counting the values of 
		 * by column of the corresponding function.
		 * 
		 * However, this only works for functions with parameters.
		 * But it can be easily modified by removing the 3rd conditions
		 * in the all the "if's" below. (There are 2 ifs below)
		 **/
		FileWriter fw = null;
		BufferedWriter bw = null;

		try
		{
			fw = new FileWriter("FunctionInfo\\functionInformation.txt");
			bw = new BufferedWriter(fw);

			for(int i=0; i<nodeF.functionChildNodes.size(); i++)
			{
				//BigDecimal countNumOfFuncCalls = new BigDecimal(0);
				double countNumOfFuncCalls = 0;
				for(int j=0; j<nodeF.functionChildNodes.size(); j++)
				{
					if(!nodeF.functionChildNodes.get(j).functionName.equals("NO NAME") &&
							!nodeF.functionChildNodes.get(j).functionName.equals("MAIN") &&
							nodeF.functionChildNodes.get(j).parameterChildNodes.size() > 0)
					{
						//System.out.println("column '"+nodeF.functionChildNodes.get(i).functionName+"' "+adjacencyMatrix[j][i]);
						//System.out.println(adjacencyMatrix[j][i]);
						//countNumOfFuncCalls = adjacencyMatrix[j][i].add(countNumOfFuncCalls);
						countNumOfFuncCalls = adjacencyMatrix[j][i] + countNumOfFuncCalls;
					}
				}
				if(!nodeF.functionChildNodes.get(i).functionName.equals("NO NAME") &&
						!nodeF.functionChildNodes.get(i).functionName.equals("MAIN") &&
						nodeF.functionChildNodes.get(i).parameterChildNodes.size() > 0)
				{
					bw.write("fcall_"+nodeF.functionChildNodes.get(i).functionName+"()="+countNumOfFuncCalls+"\n\n");
				}
				//System.out.println();
			}


			MatrixOperations matOp = new MatrixOperations();
			//System.out.println("with Names: "+cnt);
			for(int i=0; i<nodeF.functionChildNodes.size(); i++)
			{
				if(i == 0)
				{
					reachableNodes = matTmp;
//					for(int k = 0; k < adjacencyMatrix.length; k++)
//						reachableNodes[k] = matTmp[k].clone();
				}
				else
				{
					
					matTmp = mat.times(matTmp);
					//matTmp = matOp.multiply(mat, matTmp);
					adjacencyMatrix = matTmp.getArray();
					//adjacencyMatrix = matTmp;
					reachableNodes = reachableNodes.plus(matTmp);
					//reachableNodes = matOp.sum(reachableNodes, matTmp);
				}
				//						System.out.println("Matrix #"+(i+2)+": ");
				//						for(int i1 = 0; i1 < adjacencyMatrix.length; i1++) {
				//							System.out.print(nodeF.functionChildNodes.get(i1).functionName+": ");
				//							for(int j = 0; j < adjacencyMatrix[i1].length; j++) {        
				//								System.out.print( "   " + adjacencyMatrix[i1][j] );
				//							}
				//							System.out.println();
				//						}
			}

			adjacencyMatrix = reachableNodes.getArray();
			//adjacencyMatrix = reachableNodes;
//			for(int k = 0; k < adjacencyMatrix.length; k++)
//				adjacencyMatrix[k] = reachableNodes[k].clone();


			/**
			 * Count the depth of Functions
			 * Here we follow the opposite algorithm of counting how many times
			 * the function is called in the source code.
			 * 
			 * We count the values that are greater than 0 row by row.
			 * However, there is a previous step before counting the values row by row
			 * First the reachability algorithm is executed (take the power of the adjacency matrix
			 * from 1 to n-1). Than we perform the row by row count algorithm.
			 * */
			//System.out.println("Matrix : ");
			bw.write("===================================="+"\n\n");
			
			for(int i = 0; i < nodeF.functionChildNodes.size(); i++) 
			{
				int funcDepth = 0;
				if(!nodeF.functionChildNodes.get(i).functionName.equals("NO NAME") &&
						!nodeF.functionChildNodes.get(i).functionName.equals("MAIN") &&
						nodeF.functionChildNodes.get(i).parameterChildNodes.size() > 0)
				{
					for(int j = 0; j < nodeF.functionChildNodes.size(); j++) 
					{   
						//System.out.print( "   " + adjacencyMatrix[i][j] );
						//if(adjacencyMatrix[i][j].compareTo(BigDecimal.ZERO) > 0)
						if(adjacencyMatrix[i][j] > 0)
						{
							funcDepth++;
						}
					}
					//System.out.print(nodeF.functionChildNodes.get(i).functionName+": "+funcDepth);
					bw.write("fdepth_"+nodeF.functionChildNodes.get(i).functionName+"()="+funcDepth+"\n\n");
				}
				//System.out.println();
			}


			bw.close();
			fw.close();
		}catch(IOException e)
		{
			System.out.println("File Not Found!");
		}
		//		System.out.println("Matrix #"+(i+2)+": ");
		//				for(int i1 = 0; i1 < adjacencyMatrix.length; i1++) {
		//					System.out.print(nodeF.functionChildNodes.get(i1).functionName+": ");
		//					for(int j = 0; j < adjacencyMatrix[i1].length; j++) {        
		//						System.out.print( "   " + adjacencyMatrix[i1][j] );
		//					}
		//					System.out.println();
		//				}

		double[] signedNodes = new double[nodeF.functionChildNodes.size()];
//		BigDecimal[] signedNodes = new BigDecimal[nodeF.functionChildNodes.size()];
//		for(int i=0; i<signedNodes.length; i++)
//			signedNodes[i] = new BigDecimal(0);
		double[] signedVNodes = new double[nodeF.functionChildNodes.size()];
//		BigDecimal[] signedVNodes = new BigDecimal[nodeF.functionChildNodes.size()];
//		for(int i=0; i<signedVNodes.length; i++)
//			signedVNodes[i] = new BigDecimal(0);

		
		int nodesReachedbyFuncs = 0, fn=0, vn=0;
		int attackSurfaceSLOC = 0;
		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		{
			/**
			 * We define the attack surfaces of a function that has;
			 * - Functions with no name, because the developer call or uses the function by name
			 * - Functions with parameters, because parameters are the inputs to enter the system 
			 *   and where the attacks can be performed.
			 *   
			 * - IMPORTANT NOTE: We do not include the situation if the parameters or variables are validated
			 *   
			 *   */
			if(true)
			{
				for(int j=0; j<nodeV.varibleChildNodes.size(); j++)
				{
					//System.out.println("........"+nodeV.varibleChildNodes.get(j).callInFunc.get(0));
					for(int k=0; k<nodeV.varibleChildNodes.get(j).callInFunc.size(); k++)
					{
						//System.out.println("---------"+nodeV.varibleChildNodes.get(j).callInFunc.get(k).funcName);
						for(int m=0; m<nodeF.functionChildNodes.size(); m++)
						{
							if((nodeV.varibleChildNodes.get(j).callInFunc.get(k).funcLine == nodeF.functionChildNodes.get(m).funcLine) &&
									(nodeV.varibleChildNodes.get(j).callInFunc.get(k).funcCol == nodeF.functionChildNodes.get(m).funcCol))
							{
								//System.out.println(" ------------------ "+m);
								if(i == m)
								{
									signedVNodes[i] = 1;
									signedNodes[i] = 1;
//									signedVNodes[i] = new BigDecimal(1);
//									signedNodes[i] = new BigDecimal(1);
								}
								signedVNodes[m] = signedVNodes[m] + adjacencyMatrix[m][i];
								signedNodes[i] = signedNodes[i] + adjacencyMatrix[m][i];
							}
							//							signedNodes[i] = signedNodes[i] + adjacencyMatrix[i][m];
						}
					}
				}
				//if(signedVNodes[i].compareTo(BigDecimal.ZERO) > 0)
				if(signedVNodes[i] > 0)
				{
					vn++;
				}		

			}


			if(!nodeF.functionChildNodes.get(i).functionName.equals("NO NAME") &&
					nodeF.functionChildNodes.get(i).parameterChildNodes.size() != 0)
			{
				//System.out.println("-----"+nodeF.functionChildNodes.get(i).functionName+"-----");

				for(int j=0; j<nodeF.functionChildNodes.size(); j++)
				{
					/**
					 * We scan the adjacency matrix by column if we capture any value greater than 0
					 * this means that the node is reachable by the attack surface we have defined.
					 */
					//	System.out.println(nodeF.functionChildNodes.get(j).functionName+": "+adjacencyMatrix[i][j]);
					if(i == j)
					{
						//signedNodes[j] = new BigDecimal(1);
						signedNodes[j] = 1;
					}
					//signedNodes[j] = signedNodes[j].add(adjacencyMatrix[i][j]);
					signedNodes[j] = signedNodes[j] + adjacencyMatrix[i][j];
				}
				//if(signedNodes[i].compareTo(BigDecimal.ZERO) > 0)
				if(signedNodes[i] > 0)
				{
					fn++;
				}
			}



			boolean flag = true;
			//if(signedNodes[i].compareTo(BigDecimal.ZERO) > 0)
			if(signedNodes[i] > 0)
			{
				if(libSign == false)
				{
					if(i>=1)
					{
						for(int j=1; j<nodeF.functionChildNodes.size(); j++)
						{
							if(!nodeF.functionChildNodes.get(j).functionName.equals("MAIN"))
							{
								//								System.out.println("FFFFFFFFFFFFF: "+nodeF.functionChildNodes.get(j).functionName);
								//if(signedNodes[j].compareTo(BigDecimal.ZERO) > 0 && nodeF.functionChildNodes.get(i).funcLine > nodeF.functionChildNodes.get(j).funcLine &&
								if(signedNodes[j] > 0 && nodeF.functionChildNodes.get(i).funcLine > nodeF.functionChildNodes.get(j).funcLine &&
										nodeF.functionChildNodes.get(i).endLine < nodeF.functionChildNodes.get(j).endLine)
										//nodeF.functionChildNodes.get(i).endLine < nodeF.functionChildNodes.get(j).endLine)
								{
									flag = false;
								}
							}
						}
						if(flag == true)
						{
							//							System.out.println("FFFFFFFFFFFFF: "+nodeF.functionChildNodes.get(i).functionName);
							nodeF.functionChildNodes.get(i).SLOC = nodeF.functionChildNodes.get(i).endLine - nodeF.functionChildNodes.get(i).funcLine + 2;
							//System.out.println(nodeF.functionChildNodes.get(i).functionName+":"+nodeF.functionChildNodes.get(i).funcLine+" - "+nodeF.functionChildNodes.get(i).SLOC);
							attackSurfaceSLOC = nodeF.functionChildNodes.get(i).SLOC + attackSurfaceSLOC;
						}	
					}
					nodesReachedbyFuncs++;
				}
				else if(libSign == true)
				{
					if(i>1)
					{
						for(int j=2; j<nodeF.functionChildNodes.size(); j++)
						{

							//							System.out.println("FFFFFFFFFFFFF: "+nodeF.functionChildNodes.get(j).functionName);
							//if(signedNodes[j].compareTo(BigDecimal.ZERO) > 0 && nodeF.functionChildNodes.get(i).funcLine > nodeF.functionChildNodes.get(j).funcLine &&
							if(signedNodes[j] > 0 && nodeF.functionChildNodes.get(i).funcLine > nodeF.functionChildNodes.get(j).funcLine &&
									nodeF.functionChildNodes.get(i).endLine < nodeF.functionChildNodes.get(j).endLine)
							{
								flag = false;
							}
						}
						if(flag == true)
						{
							//							System.out.println("FFFFFFFFFFFFF: "+nodeF.functionChildNodes.get(i).functionName);
							nodeF.functionChildNodes.get(i).SLOC = nodeF.functionChildNodes.get(i).endLine - nodeF.functionChildNodes.get(i).funcLine + 1;
							//System.out.println(nodeF.functionChildNodes.get(i).functionName+":"+nodeF.functionChildNodes.get(i).funcLine+" - "+nodeF.functionChildNodes.get(i).SLOC);
							attackSurfaceSLOC = nodeF.functionChildNodes.get(i).SLOC + attackSurfaceSLOC;
						}	
					}
					nodesReachedbyFuncs++;
				}
			}
		}

		//		for(int y=0; y<signedVNodes.length; y++)
		//			System.out.println(signedVNodes[y]);

		for(int j=0; j<signedNodes.length; j++)
		{
			//if(signedNodes[j].compareTo(BigDecimal.ZERO) > 0)
			if(signedNodes[j] > 0)
			{
				//signedNodes[j] = new BigDecimal(1);
				signedNodes[j] = 1;
			}
			//System.out.print(signedNodes[j]+"   ");
		}

		System.out.println();
		System.out.println(nodesReachedbyFuncs+" of "+nodeF.functionChildNodes.size()+" Nodes are reachable in GENERAL");
		System.out.println(fn+" of "+nodeF.functionChildNodes.size()+" Nodes are reachable by functions");
		System.out.println(vn+" of "+nodeF.functionChildNodes.size()+" Nodes are reachable by variables");
		System.out.println("Attack Surface SLOC: "+attackSurfaceSLOC);
		//		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		//		{
		//			System.out.println("SLOC: "+(nodeF.functionChildNodes.get(i).SLOC-nodeF.functionChildNodes.get(i).funcLine)+" - "+nodeF.functionChildNodes.get(i).functionName);
		//		}

		HashMap<String,Integer> calls = new HashMap<>();
		calls.put("P+V", nodesReachedbyFuncs);
		calls.put("P", fn);
		calls.put("V", vn);
		return calls;
		//		System.out.println("Matrix : ");
		//		for(int i1 = 0; i1 < adjacencyMatrix.length; i1++) {
		//			System.out.print(nodeF.functionChildNodes.get(i1).functionName+": ");
		//			for(int j = 0; j < adjacencyMatrix[i1].length; j++) {        
		//				System.out.print( "   " + adjacencyMatrix[i1][j] );
		//			}
		//			System.out.println();
		//		}
	}
}

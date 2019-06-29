package tr.iyte.tajs.iv.parameter.relation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import tr.iyte.tajs.iv.VariableType;
import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;

public class ParameterRelationExtractor {

	static BufferedReader reader = null;


	/**
	 * Capture the parameters .dot file node IDs by their usages
	 * */
	public static RootFunctionNode paramBlockInfo(String file, RootFunctionNode nodeF)
	{

		int counter = -1;
		String line;
		int limit = nodeF.functionChildNodes.size();
		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		{
			for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
			{
				ArrayList<Integer> arrays = new ArrayList<Integer>();
				nodeF.functionChildNodes.get(i).paramGraph.put(nodeF.functionChildNodes.get(i).parameterChildNodes.get(j).parameterName, arrays);
			}
			//			System.out.println("Param G: "+nodeF.functionChildNodes.get(i).paramGraph);
		}

		try
		{
			reader = new BufferedReader(new FileReader(file)); 

			while((line = reader.readLine()) != null)
			{
				String tokens[] = line.split(" ");

				if(line.contains("subgraph cluster"))
				{
					//System.out.println("checking");
					counter++;
				}

				//System.out.println(counter);
				if(counter > -1 && counter < limit)
				{
					//System.out.println(line);
					for(int j=0; j<nodeF.functionChildNodes.get(counter).parameterChildNodes.size(); j++)
					{
						if(line.contains("[shape=record") &&
								line.contains("read-variable['"+nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName+"'"))
						{
							ArrayList<Integer> paramEdges = nodeF.functionChildNodes.get(counter).paramGraph.get(nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName);
							if(!paramEdges.contains(Integer.parseInt(tokens[0].substring(2, tokens[0].length()))))
							{
								paramEdges.add(Integer.parseInt(tokens[0].substring(2, tokens[0].length())));
								nodeF.functionChildNodes.get(counter).paramGraph.replace(nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName, paramEdges);
							}
						}
					}

				}
			}
			reader.close();
		}catch(IOException e){

		}
		for(int i=0; i<nodeF.functionChildNodes.size(); i++)
		{
			System.out.println("Function: "+nodeF.functionChildNodes.get(i).functionName+" - Param G: "+nodeF.functionChildNodes.get(i).paramGraph);
		}

		return nodeF;
	}



	/**
	 * This function extracts the all parameter information by their block IDs
	 * in the .dot file. However, this extracts the all relationships, so it 
	 * does not include information about dependency between parameters.
	 * From the extracted info. in here, will be sent to another function that
	 * analyzes and filters the relationships by their dependency.
	 * @return */
	public static HashMap<Integer, Set<Integer>> extractGeneralParamRel(String file, RootFunctionNode nodeF)
	{
		HashMap<Integer, Set<Integer>> paramRelations = new HashMap<>();
		try
		{
			int counter = -1;
			String line;
			int limit = nodeF.functionChildNodes.size();
			reader = new BufferedReader(new FileReader(file));


			HashMap<Integer, ArrayList<Integer>> paramInfo = new HashMap<>();
			HashMap<Integer, Set<Integer>> generalInfo = new HashMap<>();

			while((line = reader.readLine()) != null)
			{
				String tokens[] = line.split(" ");

				if(line.contains("subgraph cluster"))
				{
					counter++;
				}

				if(counter > -1 && counter < limit)
				{
					Set<Integer> generalSet = new HashSet<>();
					if(tokens[0].contains("BB") && 
							!tokens[0].contains("BB_") && 
							tokens[1].contains("->")&&
							!line.contains("color=gray"))
					{
						Integer key = Integer.parseInt(tokens[0].substring(2, tokens[0].length()));
						//System.out.println(key);
						if(!generalInfo.containsKey(key))
						{
							generalInfo.put(key, generalSet);
						}

						Integer value = Integer.parseInt(tokens[2].substring(2, tokens[2].length()));
						//System.out.println(key+" ---> "+value);
						generalSet = generalInfo.get(key);
						generalSet.add(value);
						generalInfo.put(key, generalSet);

					}

					for(int j=0; j<nodeF.functionChildNodes.get(counter).parameterChildNodes.size(); j++)
					{	
						ArrayList<Integer> paramIDs = nodeF.functionChildNodes.get(counter).paramGraph.get(nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName);
						//ArrayList<Integer> tmpArray = new ArrayList<>();
						//	System.out.println(nodeF.functionChildNodes.get(counter).paramGraph.get(nodeF.functionChildNodes.get(counter).));

						for(int i=0; i<paramIDs.size(); i++)
						{
							Set<Integer> set = new HashSet<>();
							if(!paramRelations.containsKey(paramIDs.get(i)))
							{
								paramRelations.put(paramIDs.get(i), set);
							}

							paramInfo.put(paramIDs.get(i), new ArrayList<Integer>());


							//Get all the relationships for parameters.
							//It only gets the next relationship doesn't go deeper
							//It doesn't get the exceptional relationships.
							if(tokens[0].contains("BB"+paramIDs.get(i)) && 
									!tokens[0].contains("BB_") && 
									tokens[1].contains("->")&&
									!line.contains("color=gray"))
							{
								Set<Integer> paramSet = paramRelations.get(paramIDs.get(i));
//								System.out.println(paramIDs.get(i)+" -> "+tokens[2].substring(2, tokens[2].length()));
								paramSet.add(Integer.parseInt(tokens[2].substring(2, tokens[2].length())));
								//paramIDs.add(Integer.parseInt(tokens[2].substring(2, tokens[2].length())));
								paramRelations.put(paramIDs.get(i), paramSet);
							}
						}
					}


				}
			}
			System.out.println("The Parameter Next Relations: "+paramRelations);
			System.out.println("General Relation Info: "+generalInfo);
		}catch(IOException e){

		}

		return paramRelations;
	}


	/**
	 * This function ge*/
	public static void extractParamDependency (String file, HashMap<Integer, Set<Integer>> generalInfo, RootFunctionNode nodeF)
	{

		try
		{
			int counter = -1;
			String line;
			int limit = nodeF.functionChildNodes.size();
			reader = new BufferedReader(new FileReader(file));

			while((line = reader.readLine()) != null)
			{
				String tokens[] = line.split(" ");

				if(line.contains("subgraph cluster"))
				{
					counter++;
				}

				if(counter > -1 && counter < limit)
				{
					for(int j=0; j<nodeF.functionChildNodes.get(counter).parameterChildNodes.size(); j++)
					{	
						ArrayList<Integer> paramIDs = nodeF.functionChildNodes.get(counter).paramGraph.get(nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName);
						
						for(int i=0; i<paramIDs.size(); i++)
						{
							if(tokens[0].contains("BB"+paramIDs.get(i)) &&
								line.contains("read-variable['"+nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName+"'") &&
								line.contains("if["))
							{
								
								String s = "read-variable['"+nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName+"',";
								int u_reg1 = line.indexOf(s);
								u_reg1 = line.indexOf(",", u_reg1)+1;
								int u_reg2 = line.indexOf(",", u_reg1);
								
								//parameter register
								String ifConstraints = line.substring(u_reg1, u_reg2);

								int rightInIf = line.lastIndexOf("]");
								int leftInIf = line.lastIndexOf("[", rightInIf)+1;
								
								
								int ifRegL = line.indexOf(": if[");
								int ifRegR = line.indexOf("]", ifRegL);
								ifRegL = line.lastIndexOf("[", ifRegR)+1;
								//if register
								String ifRegister = line.substring(ifRegL, ifRegR);
								
								boolean compared = false;
								String ifr = "";
								
								for(int k=0; k<tokens.length; k++)
								{
									if(tokens[k].contains("if["))
									{
										ifr = tokens[k-1].substring(tokens[k-1].lastIndexOf(",")+1, tokens[k-1].lastIndexOf("]"));
										//System.out.println(ifr);
										
										if(tokens[k-1].contains("["+ifConstraints+",") ||
											tokens[k-1].contains(","+ifConstraints+",") ||
											tokens[k-1].contains(","+ifConstraints+"]"))
										{
											compared = true;
										}
									}
								}
								
								String nextConstraint = line.substring(leftInIf, rightInIf);
//								System.out.println("Next: "+nextConstraint);
//								System.out.println("IfConst: "+ifConstraints);
//								System.out.println("IfReg: "+ifRegister);

								//System.out.println("Regs ASSIGNED: "+leftInIf+" "+rightInIf+" "+ifRegL+" "+ifRegR);
								if(!ifRegister.equals("") && !ifConstraints.equals(""))
								{
									//System.out.println("Be quite I am in...");
									//System.out.println(node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'s register: "+str.substring(u_reg1, u_reg2));
									if(ifr.contains(ifRegister) && compared)
									{
										System.out.println(ifRegister + " -- " +ifConstraints);
										System.out.println("Questioning the Parameter: "+ nodeF.functionChildNodes.get(counter).parameterChildNodes.get(j).parameterName);
									}
								}
							}
						}

					}
				}
			}
		}catch(IOException e)
		{

		}

	}

}

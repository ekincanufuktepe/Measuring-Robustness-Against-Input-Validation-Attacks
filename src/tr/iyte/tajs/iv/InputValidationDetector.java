package tr.iyte.tajs.iv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import tr.iyte.edu.bn.BayesianNetworkExecuter;
import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;
import tr.iyte.tajs.iv.VariableTree.RootVariableNode;
import tr.iyte.tajs.iv.VariableTree.VariableInFunction;

public class InputValidationDetector {

	/**
	 * @param args
	 */
	static BufferedReader reader = null;

	/**
	 * This function detects the invalidated Global variables
	 * */
	public static RootVariableNode variableInputValDetector(String fileName, RootVariableNode node, RootFunctionNode nodeF)
	{	
		String str;
		int limit = nodeF.functionChildNodes.size(), counter=-1;
		int rightInIf=0 , leftInIf=0, ifRegL=0, ifRegR=0, u_reg1=0, u_reg2=0;
		String nextConstraint = "", ifRegister="", ifConstraints="";
		int validationCheck = -1;


		try {

			reader = new BufferedReader(new FileReader(fileName));

			int start = 0;
			int end = 0;
			int endEnd = 0, row=0, col=0;

			String f = "";

			while((str = reader.readLine()) != null)
			{
				String tokens[] = str.split(" ");

				if(str.contains("subgraph cluster"))
				{
					counter++;
					validationCheck = -1;
					for(int i=0; i<node.varibleChildNodes.size(); i++)
					{
						if(node.varibleChildNodes.get(i).vType == VariableType.GLOBAL)
						{
							node.varibleChildNodes.get(i).validatedLine = 0;
							node.varibleChildNodes.get(i).usedLine = 0;
						}
					}
				}

				if(str.contains("label=\"function") || str.contains("label=\"<main>"))
				{
					for(int i=0; i<tokens.length; i++)
					{				
						if(tokens[1].equals("("))
						{
							f = "NO NAME";
						}
						else if(!tokens[1].equals("(") && !str.contains("label=\"<main>"))
						{
							f = tokens[1];
						}
						else
						{
							f = "MAIN";
						}
						//add(tokens[i+1]);							
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

							for(int j=0; j<nodeF.functionChildNodes.size(); j++)
							{
								if(nodeF.functionChildNodes.get(j).funcLine == row && nodeF.functionChildNodes.get(j).funcCol == col)
								{
									f = nodeF.functionChildNodes.get(j).functionName;
								}
							}
						}
						//VariableInFunction vif = new VariableInFunction(f, row, col, null);
						//						vif.funcName = f;
						//						vif.funcLine = row;
						//						vif.funcCol = col;
					}
				}



				if(counter >= -1 && counter < limit)
				{
					int valIndex=0;

					/***************************************************************/
					/************ THIS PART STARTS ANALYZING VARIABLES *************/
					/***************************************************************/

					for(int i=0; i<node.varibleChildNodes.size(); i++)
					{
						//System.out.println(node.varibleChildNodes.get(i).variableName+" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 ");
						if(node.varibleChildNodes.get(i).vType == VariableType.GLOBAL || node.varibleChildNodes.get(i).vType == VariableType.GLOBAL_OR_LOCAL)
						{
							//							System.out.println("Analyzing Variable: "+node.varibleChildNodes.get(i).variableName);
							if(str.contains("if[") && 
									str.contains("read-variable['"+node.varibleChildNodes.get(i).variableName+"'"))
							{
								//node.varibleChildNodes.get(i).callInFunc.add(vif);
								valIndex = str.indexOf(": if[");
								int tmp = str.lastIndexOf("|", valIndex);

								/**
								 * These variables are defined to see the registers
								 * of that are used in the if-block
								 * */
								rightInIf = str.lastIndexOf("]", valIndex);
								leftInIf = str.lastIndexOf("[", rightInIf)+1;

								/**
								 * Get the if's register so if there are multiple constraints defined
								 * in the if-block it will be able to detect the other constraints that
								 * are used in the same if-block
								 * */
								ifRegL = str.indexOf(": if[");
								ifRegR = str.indexOf("]", ifRegL);
								ifRegL = str.lastIndexOf("[", ifRegR)+1;
								//System.out.println("if register: "+str.substring(ifRegL, ifRegR));

								String s = "read-variable['"+node.varibleChildNodes.get(i).variableName+"',";

								//check if the parameter is used validated in the if-condition
								int reg1 = str.lastIndexOf(s);
								int reg2 = str.indexOf(",", reg1+s.length());
								reg1 = str.lastIndexOf(",", reg2-1)+1;

								//System.out.println(str.substring(leftInIf, rightInIf)+" -- "+str.substring(reg1, reg2));

								u_reg1 = str.indexOf(s);
								u_reg1 = str.indexOf(",", u_reg1)+1;
								u_reg2 = str.indexOf(",", u_reg1);
								//System.out.println();

								//System.out.println("Regs ASSIGNED: "+leftInIf+" "+rightInIf+" "+ifRegL+" "+ifRegR);

								ifRegister = str.substring(ifRegL, ifRegR);
								//System.out.println(str.substring(leftInIf, rightInIf)+" --- "+str.substring(u_reg1, u_reg2));
								if(str.substring(leftInIf, rightInIf).contains(str.substring(reg1, reg2)))
								{
									//								System.out.println("Validation number --> "+str.substring(tmp+1, valIndex));
									validationCheck = Integer.parseInt(str.substring(tmp+1, valIndex));

									if(node.varibleChildNodes.get(i).validatedLine == 0)
									{
										node.varibleChildNodes.get(i).validatedLine = validationCheck;

										//									System.out.println("Function \""+
										//											node.functionChildNodes.get(counter).functionName+"'s\" \""+
										//											node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
										//											"\" Parameter is validated");

										//									node.varibleChildNodes.get(i).status = ValidationStatus.VALIDATED;
										//System.out.println("VALIDATED #1");

										boolean flag = true;
										int index = 0;
										for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
										{
											if(node.varibleChildNodes.get(i).callInFunc.get(j).funcLine == row && node.varibleChildNodes.get(i).callInFunc.get(j).funcCol == col)
											{
												flag = false;
												if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
												{
													index = j;
												}
											}

										}
										if(flag)
										{
											node.varibleChildNodes.get(i).callInFunc.add(new VariableInFunction(f, row, col, ValidationStatus.VALIDATED));
										}
										else if(index > 0)
										{
											node.varibleChildNodes.get(i).callInFunc.get(index).status = ValidationStatus.VALIDATED;
										}
										//									String newLine = "";
										//									newLine = newLine + tokens[0] + " " + tokens[1]+ " color=green " + str.substring((tokens[0].length()+tokens[1].length()+2));
										//									fileContent.append(newLine);
										//									fileContent.append("\n");
										//									a=1;


									}
									if(!str.substring(leftInIf, rightInIf).contains(str.substring(u_reg1, u_reg2)))
									{

										//								System.out.println("IN THE OPTIONAL PART");
										valIndex = str.indexOf(": read-variable['"+node.varibleChildNodes.get(i).variableName+"'");
										int tmp1 = str.lastIndexOf("|", valIndex);
										if(tmp1 == -1)
											tmp1 = str.lastIndexOf("{", valIndex);

										int tmpu = Integer.parseInt(str.substring(tmp1+1, valIndex));

										if(node.varibleChildNodes.get(i).usedLine == 0)
										{

											node.varibleChildNodes.get(i).usedLine = tmpu;
											//									System.out.println("the used thing: "+tmpu);
										}
										if(node.varibleChildNodes.get(i).validatedLine > node.varibleChildNodes.get(i).usedLine)
										{
											//									System.out.println("Function \""+
											//											node.functionChildNodes.get(counter).functionName+"'s\" \""+
											//											node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
											//											"\" Parameter is NOT validated!!");

											//										node.varibleChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
											//System.out.println("NOT VALIDATED #1");
											node.varibleChildNodes.get(i).callInFunc.add(new VariableInFunction(f, row, col, ValidationStatus.NOT_VALIDATED_BEFORE_USAGE));

											//										String newLine = "";
											//										newLine = newLine + tokens[0] + " " + tokens[1]+ " color=red " + str.substring((tokens[0].length()+tokens[1].length()+2));
											//										fileContent.append(newLine);
											//										fileContent.append("\n");
											//										a=1;
										}
										//								if(validationCheck == -1)
										//								{
										//									node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
										//								}
									}
								}
								else
								{
									//System.out.println("INFO32: "+node.varibleChildNodes.get(i).variableName+ " - "+node.varibleChildNodes.get(i).usedLine +" - "+node.varibleChildNodes.get(i).validatedLine+" - "+row+" - "+col);
									boolean flag = true;
									for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
									{
										if(node.varibleChildNodes.get(i).callInFunc.get(j).funcLine == row && node.varibleChildNodes.get(i).callInFunc.get(j).funcCol == col)
										{
											flag = false;
										}
									}
									if(flag)
									{
										node.varibleChildNodes.get(i).callInFunc.add(new VariableInFunction(f, row, col, ValidationStatus.NOT_VALIDATED_BEFORE_USAGE));
									}
								}
							}
							else if(!str.contains("if[") && str.contains("read-variable['"+node.varibleChildNodes.get(i).variableName+"'"))
							{
								String s = "read-variable['"+node.varibleChildNodes.get(i).variableName+"',";
								u_reg1 = str.indexOf(s);
								u_reg1 = str.indexOf(",", u_reg1)+1;
								u_reg2 = str.indexOf(",", u_reg1);

								ifConstraints = str.substring(u_reg1, u_reg2);

								rightInIf = str.lastIndexOf("]");
								leftInIf = str.lastIndexOf("[", rightInIf)+1;

								nextConstraint = str.substring(leftInIf, rightInIf);

								//System.out.println("Regs ASSIGNED: "+leftInIf+" "+rightInIf+" "+ifRegL+" "+ifRegR);
								if(!ifRegister.equals("") && !ifConstraints.equals(""))
								{
									//								System.out.println(node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'s register: "+str.substring(u_reg1, u_reg2));
									if(nextConstraint.contains(ifRegister) && nextConstraint.contains(ifConstraints))
									{

										//System.out.println("This parameter is also validated: "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
										ifRegister="";
										ifConstraints="";

										if(node.varibleChildNodes.get(i).validatedLine == 0)
										{
											node.varibleChildNodes.get(i).validatedLine = validationCheck;

											//										System.out.println("Function \""+
											//												node.functionChildNodes.get(counter).functionName+"'s\" \""+
											//												node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
											//												"\" Parameter is validated");

											//										node.varibleChildNodes.get(i).status = ValidationStatus.VALIDATED;
											//System.out.println("VALIDATED #2");
											boolean flag = true;
											int index = 0;
											for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
											{
												if(node.varibleChildNodes.get(i).callInFunc.get(j).funcLine == row && node.varibleChildNodes.get(i).callInFunc.get(j).funcCol == col)
												{
													flag = false;
													if(node.varibleChildNodes.get(i).callInFunc.get(j).status == ValidationStatus.NOT_VALIDATED_BEFORE_USAGE)
													{
														index = j;
													}
												}

											}
											if(flag)
											{
												node.varibleChildNodes.get(i).callInFunc.add(new VariableInFunction(f, row, col, ValidationStatus.VALIDATED));
											}
											else if(index > 0)
											{
												node.varibleChildNodes.get(i).callInFunc.get(index).status = ValidationStatus.VALIDATED;
											}

											//										String newLine = "";
											//										newLine = newLine + tokens[0] + " " + tokens[1]+ " color=green " + str.substring((tokens[0].length()+tokens[1].length()+2));
											//										fileContent.append(newLine);
											//										fileContent.append("\n");
											//										a=1;
										}
									}
								}
							}

							/**
							 * Find the parameters that are used before validation
							 */
							if(!str.contains("if[") && str.contains("read-variable['"+node.varibleChildNodes.get(i).variableName+"'"))
							{
								valIndex = str.indexOf(": read-variable['"+node.varibleChildNodes.get(i).variableName+"'");
								int tmp1 = str.lastIndexOf("|", valIndex);
								if(tmp1 == -1)
									tmp1 = str.lastIndexOf("{", valIndex);

								//							System.out.println("Problem? --> "+tmp1);
								//							System.out.println("Used number --> "+str.substring(tmp1+1, valIndex)+ " "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);

								int tmpu = Integer.parseInt(str.substring(tmp1+1, valIndex));

								if(node.varibleChildNodes.get(i).usedLine == 0)
								{
									node.varibleChildNodes.get(i).usedLine = tmpu;
								}


								if(node.varibleChildNodes.get(i).validatedLine > node.varibleChildNodes.get(i).usedLine)
								{
									//System.out.println("NOT VALIDATED #2");
									boolean flag = true;
									for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
									{
										if(node.varibleChildNodes.get(i).callInFunc.get(j).funcLine == row && node.varibleChildNodes.get(i).callInFunc.get(j).funcCol == col)
										{
											flag = false;
										}
									}
									if(flag)
									{
										node.varibleChildNodes.get(i).callInFunc.add(new VariableInFunction(f, row, col, ValidationStatus.NOT_VALIDATED_BEFORE_USAGE));
									}
								}
								else if(node.varibleChildNodes.get(i).validatedLine == 0 )
								{
									//System.out.println("NOT VALIDATED #3");
									boolean flag = true;
									for(int j=0; j<node.varibleChildNodes.get(i).callInFunc.size(); j++)
									{
										if(node.varibleChildNodes.get(i).callInFunc.get(j).funcLine == row && node.varibleChildNodes.get(i).callInFunc.get(j).funcCol == col)
										{
											flag = false;
										}
									}
									if(flag)
									{
										node.varibleChildNodes.get(i).callInFunc.add(new VariableInFunction(f, row, col, ValidationStatus.NOT_VALIDATED_BEFORE_USAGE));
									}
								}
							}
						}
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		node = assignValidationStatus(node);

		return node;
	}


	/**
	 * Function that detects the invalidated parameters of functions
	 * */
	public static RootFunctionNode parameterInputValDetector(String fileName, RootFunctionNode node) {

		HashMap<String,ArrayList<String>> vulnerabilityRules = new HashMap<>();

		HashMap<String,Integer> funcNodeProb = new HashMap<>();

		VulnerabilityRules vRules = new VulnerabilityRules();
		vulnerabilityRules = vRules.loadVulnerabilityRules();
		funcNodeProb = vRules.initializeFuncNodeScores();
		// to lazy to calculculate how many rules there are
		//TODO: do it ASAP
		//int numOfRules = 14;
		int numOfParam = 0;

		for(int i=0; i<node.functionChildNodes.size(); i++)
		{
			numOfParam = numOfParam + node.functionChildNodes.get(i).parameterChildNodes.size();
		}

		boolean[][] parameterXSSRuleMatrix = new boolean[1][numOfParam];
		boolean[][] parameterSQLIRuleMatrix = new boolean[3][numOfParam];
		boolean[][] parameterBORuleMatrix = new boolean[1][numOfParam];
		boolean[][] parameterPTRuleMatrix = new boolean[6][numOfParam];
		boolean[][] parameterOSCIRuleMatrix = new boolean[2][numOfParam];
		boolean[][] parameterUFSRuleMatrix = new boolean[1][numOfParam];

		// TODO Auto-generated method stub
		try
		{
			StringBuilder fileContent = new StringBuilder();

			reader = new BufferedReader(new FileReader(fileName));
			String str;
			int limit = node.functionChildNodes.size(), counter=-1;
			int a = 0;
			int validationCheck = -1;
			int rightInIf=0 , leftInIf=0, ifRegL=0, ifRegR=0, u_reg1=0, u_reg2=0;
			String nextConstraint = "", ifRegister="", ifConstraints="";

			ArrayList<String> callgraphNodes = new ArrayList<>();
			ArrayList<String> IfRegisters = new ArrayList<String>();

			HashMap<String, ArrayList<ConstantIfInformation>> paramterProp = new HashMap<>();

			ArrayList<String> ifRegsForRules = new ArrayList<>();

			HashMap<String, String> constants = new HashMap<>();
			// Read the flowgraph line
			while((str = reader.readLine()) != null)
			{	
				//tokenize the string in the line while parsing the dot file
				String tokens[] = str.split(" ");

				/**
				 * Acknowledge when it is in the function
				 * This also aids us to target the local variables
				 * For Global variables we may have to follow a different strategy
				 */

				/**
				 * Controller for start analyzing on a new function
				 * Also new Local variables, 
				 * */

				if(str.contains("subgraph cluster"))
				{

					if(counter != -1)
					{
						node.functionChildNodes.get(counter).numOfCallgraphNodes = callgraphNodes.size();
					}
					IfRegisters = new ArrayList<>();
					counter++;
					validationCheck = -1;
					callgraphNodes.clear();

					paramterProp = new HashMap<>();

					ifRegsForRules = new ArrayList<>();

					constants = new HashMap<>();
					//callgraphNodeCount = 0;
				}

				for(int i = 0; i<tokens.length; i++)
				{
					if(tokens[i].contains("call["))
					{
						if(!callgraphNodes.contains(tokens[i]))
						{
							callgraphNodes.add(tokens[i]);
						}
					}
				}

				if(counter >= 0 && counter < limit)
				{
					int valIndex=0;

					/***************************************************************/
					/******** THIS PART STARTS ANALYZING FUNCTION PARAMETES ********/
					/***************************************************************/
					String callReg = "";



					for(int i=0; i<node.functionChildNodes.get(counter).parameterChildNodes.size(); i++)
					{
						a = 0;
						/**
						 * Find the validated parameters
						 * */

						/**
						 * Check in the property for INDEXOF
						 * */
						if(str.contains("call-"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+".indexOf"))
						{
							System.out.println("INDEXOF FOUND");
							int reg1 = str.indexOf("call-"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+".indexOf");
							int reg2 = str.indexOf("]", reg1);

							String call = str.substring(reg1,reg2+1);

							call = call.replace(" ", "");

							String callIfReg = call.substring(call.lastIndexOf(",")+1, call.length()-1);

							ifRegsForRules.add(callIfReg);

							String property = call.substring(call.indexOf("[")+1, call.indexOf(",", call.indexOf("[")));
							System.out.println("PROOOOOOPPPPYYY: ----->> "+ property);

							ConstantIfInformation cii = new ConstantIfInformation(callIfReg, "");

							if(paramterProp.containsKey(property))
								paramterProp.get(property).add(cii);

							//							System.out.println(call+"   FLOOOOOOOOOOOP SONGGG: "+call.substring(call.lastIndexOf(",")+1, call.length()-1));
							//							Object paramProp[] = paramterProp.keySet().toArray();
							//
							//							for(int j=0; j<paramProp.length; j++)
							//							{
							//								for(int k=0; k<paramterProp.get(paramProp[j].toString()).size(); k++)
							//								{
							//									if(!call.contains(","+paramterProp.get(paramProp[j].toString()).get(k).getRegister()+","))
							//									{
							//										paramterProp.get(paramProp[j].toString()).remove(k);
							//									}
							//								}	
							//							}


							//paramterProp.get(node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
						}


						/**
						 * NO if NO read-variable
						 * */
						if(!str.contains("if[") && !str.contains("read-variable['") && !str.contains("read-property["))
						{
							Object paramProp[] = paramterProp.keySet().toArray();
							Object vulnRules[] = vulnerabilityRules.keySet().toArray();
							if(i==0)
							{
								for(int j=0; j<paramProp.length; j++)
								{
									for(int k=0; k<paramterProp.get(paramProp[j].toString()).size(); k++)
									{
										//										System.out.println("3==D "+paramProp[j].toString());
										//										System.out.println("3====D"+paramterProp.get(paramProp[j].toString()).get(k).getRegister());

										int ifr = str.lastIndexOf("]}");
										int ifl = str.lastIndexOf("[");

										int comma = str.lastIndexOf(",v",ifr)+1;
										String ifRegis = "";
										if(comma < ifr && comma > 0 && ifr > 0)
										{
											//System.out.println("COMAAAAAAAAAAAAAAAAAA: "+str.substring(comma, ifr));
											ifRegis = str.substring(comma, ifr);


											String checkPropAndIf = "";
											if(ifr > ifl)
											{
												checkPropAndIf = str.substring(ifl, ifr);
											}

											//											System.out.println("asdffffffffffffffffffasdfasgasgagasdgas");
											if(IfRegisters.contains(str.substring(comma, ifr)))
											{
												System.out.println("COMAAAAAAAAAAAAAAAAAA: "+str.substring(comma, ifr));
											}
											if(checkPropAndIf.contains(","+paramterProp.get(paramProp[j].toString()).get(k).getRegister()+","+ifRegis) ||
													(checkPropAndIf.contains(","+ifRegis) && checkPropAndIf.contains(","+paramterProp.get(paramProp[j].toString()).get(k).getRegister()+",")) ||
													(checkPropAndIf.contains(","+ifRegis) && checkPropAndIf.contains(paramterProp.get(paramProp[j].toString()).get(k).getRegister()+",")))
											{
												System.out.println(paramterProp.get(paramProp[j].toString()).get(k).getRegister()+"?????????????????????????????"+ifRegis);

												if(funcNodeProb.containsKey(paramterProp.get(paramProp[j].toString()).get(k).getRegister()))
												{
													System.out.println(paramterProp.get(paramProp[j].toString()).get(k).getRegister()+"?????????????????????????????"+ifRegis);
//													funcNodeProb.replace(paramProp[j].toString(), funcNodeProb.get(paramProp[j].toString())+1);
												}
												else
												{
													for(int m=0; m<paramterProp.get(paramProp[j].toString()).size(); m++)
													{	
														for(int n=0; n<vulnRules.length; n++)
														{	String missedPT = "";
//															System.out.println("......."+vulnerabilityRules.get(vulnRules[n].toString()));
//															System.out.println("======="+paramterProp.get(paramProp[j].toString()).get(m).getContent().toString());
															if(vulnerabilityRules.get(vulnRules[n].toString()).contains(
																	"substring(\""+paramterProp.get(paramProp[j].toString()).get(m).getContent()+"\")"))
															{
																
																funcNodeProb.replace(vulnRules[n].toString(), funcNodeProb.get(vulnRules[n].toString())+1); 
//																System.out.println(vulnRules[n].toString()+"*****"+paramProp[j].toString()+"==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
																
																System.out.println("==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
																paramterProp.get(paramProp[j].toString()).get(m).setContent("");
															}
														
															else if(paramterProp.get(paramProp[j].toString()).get(m).getContent().equals(".."))
																missedPT = paramterProp.get(paramProp[j].toString()).get(m).getContent().toString()+"\\";
															
															if(vulnerabilityRules.get(vulnRules[n].toString()).contains(
																	"substring(\""+missedPT+"\")"))
															{
																funcNodeProb.replace(vulnRules[n].toString(), funcNodeProb.get(vulnRules[n].toString())+1);
																System.out.println("==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
																paramterProp.get(paramProp[j].toString()).get(m).setContent("");
															}
														}
													}
												}

											}
										}

									}
								}

							}
						}


						/**
						 * Check in the property for SUBSTRING
						 * */
						if(str.contains("call-"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+".substring"))
						{
							System.out.println("SUBSTRING FOUND");
							int reg1 = str.indexOf("call-"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+".substring");
							int reg2 = str.indexOf("]", reg1);

							String call = str.substring(reg1,reg2+1);

							call = call.replace(" ", "");

							String callIfReg = call.substring(call.lastIndexOf(",")+1, call.length()-1);

							ifRegsForRules.add(callIfReg);

							String property = call.substring(call.indexOf("[")+1, call.indexOf(",", call.indexOf("[")));
							System.out.println("PROOOOOOPPPPYYY: ----->> "+ property);

							ConstantIfInformation cii = new ConstantIfInformation(callIfReg, "");

							if(paramterProp.containsKey(property))
								paramterProp.get(property).add(cii);
						}


						/**
						 * Check if the validations are for the property function; SUBSTRING, INDEXOF and LENGTH(is defined as a 
						 * property but doesn't have "call-parameter-lenght" node definition in flowgraph requires a different check)
						 * */
						if(str.contains("if[") && !str.contains("read-variable['"))
						{

							//////////////////////////////////////////
							int reg1 = str.indexOf("if[");
							int reg2 = str.indexOf("]", reg1);

							String callIf = str.substring(reg1, reg2);
							reg1 = callIf.indexOf("[") + 1;
							//reg2 = callIf.indexOf("]");

							callIf = callIf.substring(reg1, callIf.length());

							Object paramProp[] = paramterProp.keySet().toArray();
							Object vulnRules[] = vulnerabilityRules.keySet().toArray();
							if(i==0)
							{
								for(int j=0; j<paramProp.length; j++)
								{
									for(int k=0; k<paramterProp.get(paramProp[j].toString()).size(); k++)
									{
										//										System.out.println("3==D "+paramProp[j].toString());
										//										System.out.println("3====D"+paramterProp.get(paramProp[j].toString()).get(k).getRegister());

										if(paramterProp.get(paramProp[j].toString()).get(k).getRegister().equals(callIf))
										{

											for(int m=0; m<paramterProp.get(paramProp[j].toString()).size(); m++)
											{	
												for(int n=0; n<vulnRules.length; n++)
												{	
													//System.out.println(vulnerabilityRules.get(vulnRules[n].toString()));
													if(vulnerabilityRules.get(vulnRules[n].toString()).contains(
															"substring(\""+paramterProp.get(paramProp[j].toString()).get(m).getContent()+"\")"))
													{
														funcNodeProb.replace(vulnRules[n].toString(), funcNodeProb.get(vulnRules[n].toString())+1); 
//														//System.out.println(vulnRules[n].toString()+"*****"+paramProp[j].toString()+"==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
//
														System.out.println("==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
														paramterProp.get(paramProp[j].toString()).get(m).setContent("");
													}
												}
											}

										}
										else
										{
											int validIndex = str.indexOf(": if[");
											int if1 = str.indexOf("[", validIndex)+1;
											int if2 = str.indexOf("]", validIndex);

											String ifRegis = str.substring(if1, if2);

											System.out.println(paramProp[j].toString()+"-------IF REGGGYYY:::"+str.substring(if1, if2));

											int ifr = str.lastIndexOf("]", validIndex);
											int ifl = str.lastIndexOf("[", ifr)+1;

											String checkPropAndIf = "";
											if(ifr > ifl)
												checkPropAndIf = str.substring(ifl, ifr);
											//											System.out.println("asdffffffffffffffffffasdfasgasgagasdgas");

											if(checkPropAndIf.contains(","+paramterProp.get(paramProp[j].toString()).get(k).getRegister()+","+ifRegis) ||
													(checkPropAndIf.contains(","+ifRegis) && checkPropAndIf.contains(","+paramterProp.get(paramProp[j].toString()).get(k).getRegister()+",")) ||
													(checkPropAndIf.contains(","+ifRegis) && checkPropAndIf.contains(paramterProp.get(paramProp[j].toString()).get(k).getRegister()+",")))
											{
												System.out.println(paramterProp.get(paramProp[j].toString()).get(k).getRegister()+"?????????????????????????????"+ifRegis);

												if(funcNodeProb.containsKey(paramterProp.get(paramProp[j].toString()).get(k).getRegister()))
												{
													System.out.println(paramterProp.get(paramProp[j].toString()).get(k).getRegister()+"?????????????????????????????"+ifRegis);
//													funcNodeProb.replace(paramProp[j].toString(), funcNodeProb.get(paramProp[j].toString())+1);
												}
												else
												{
													for(int m=0; m<paramterProp.get(paramProp[j].toString()).size(); m++)
													{	
														for(int n=0; n<vulnRules.length; n++)
														{	//System.out.println(vulnerabilityRules.get(vulnRules[n].toString()));
															if(vulnerabilityRules.get(vulnRules[n].toString()).contains(
																	"substring(\""+paramterProp.get(paramProp[j].toString()).get(m).getContent()+"\")"))
															{
																funcNodeProb.replace(vulnRules[n].toString(), funcNodeProb.get(vulnRules[n].toString())+1); 
//																//System.out.println(vulnRules[n].toString()+"*****"+paramProp[j].toString()+"==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
//
																System.out.println("==========================I FOUND SOMETHING======================="+paramterProp.get(paramProp[j].toString()).get(m).getContent());
																paramterProp.get(paramProp[j].toString()).get(m).setContent("");
															}
														}
													}
												}

											}

										}
									}
								}

							}


							//System.out.println("I AM IIIIIINNN!!!!!: ----> "+callIf);
						}


						/**
						 * Capture properties and its register infos
						 * and with its constant controls
						 * */
						if(str.contains("read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'") &&
								str.contains("read-property["))
						{
							String s1 = "read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"',";

							int regv1 = str.indexOf("read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"',");
							int regv2 = str.indexOf(",", regv1+s1.length());

							String parameterReg = str.substring(regv1+s1.length(), regv2);
							//System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP"+);
							String sp = "read-property['";

							int regp1 = str.indexOf("read-property[")-1;
							int regp2 = str.indexOf(",", regp1+sp.length());
							//reg1 = str.lastIndexOf(",", reg2-1)+1;

							String matchProperty = str.substring( regp1+sp.length(), regp2);
							System.out.println(parameterReg+" IS THIS RIGHT: "+matchProperty);

							if(str.contains("read-property["+matchProperty+",'length'"))
							{
								String st = "read-property["+matchProperty+",'length',";
								int r1 = str.indexOf(st);
								int r2 = str.indexOf("]", r1+st.length());
								r1 = r1+st.length();

								String assignedProp = str.substring(r1, r2);
								System.out.println("LEN SUBSTR::::"+str.substring(r1, r2));

								paramterProp.put(matchProperty, new ArrayList<ConstantIfInformation>());
								System.out.println("LENGTH FOUND");
								if(parameterReg.equals(matchProperty))
								{
									if(str.contains("if["))
									{
										int validIndex = str.indexOf(": if[");
										int if1 = str.indexOf("[", validIndex)+1;
										int if2 = str.indexOf("]", validIndex);

										String ifRegis = str.substring(if1, if2);

										System.out.println(matchProperty+"IF REGGGYYY:::"+str.substring(if1, if2));

										int ifr = str.lastIndexOf("]", validIndex);
										int ifl = str.lastIndexOf("[", ifr)+1;

										String checkPropAndIf = str.substring(ifl, ifr);
										//										System.out.println("asdffffffffffffffffffasdfasgasgagasdgas");
										if(checkPropAndIf.contains(","+assignedProp+","+ifRegis) ||
												(checkPropAndIf.contains(","+ifRegis) && checkPropAndIf.contains(","+assignedProp+",")) ||
												(checkPropAndIf.contains(","+ifRegis) && checkPropAndIf.contains(assignedProp+",")))
										{
											System.out.println("enter Len score here");
											funcNodeProb.replace("BO", funcNodeProb.get("BO")+1);
										}

										//										System.out.println("IF REGGGYYY:::"+str.substring(ifl, ifr));
									}
								}

							}
							else if(str.contains("read-property["+matchProperty+",'indexOf'"))
							{
								paramterProp.put(matchProperty, new ArrayList<ConstantIfInformation>());
							}
							else if(str.contains("read-property["+matchProperty+",'substring'"))
							{
								paramterProp.put(matchProperty, new ArrayList<ConstantIfInformation>());
							}

							if(str.contains("constant["))
							{
								String sc = "constant[";
								int regc1 = str.indexOf(sc);
								int regc2 = str.indexOf(",", regc1);
								//reg1 = str.lastIndexOf("\"", reg2-1)+1;
								int regc3 = str.indexOf("]", regc2);
								String cons = str.substring(regc1+sc.length(), regc2);
							
								cons = cons.replace("\\", "");
								cons = cons.replace("\"", "");
								String consReg = str.substring(regc2+1, regc3);
								constants.put(consReg, cons);

								ConstantIfInformation cii = new ConstantIfInformation(consReg, cons);


								if(paramterProp.containsKey(matchProperty))
									paramterProp.get(matchProperty).add(cii);



								//								System.out.println(cons+" CONSTANT: "+str.substring(reg1+s.length(), reg2));
								//								System.out.println("CONSTANT: "+str.substring(reg2+1, reg3));
								//constants
							}

						}

						if(str.contains("if[") && str.contains("read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'"))
						{
							valIndex = str.indexOf(": if[");
							int tmp = str.lastIndexOf("|", valIndex);

							/**
							 * These variables are defined to see the registers
							 * of that are used in the if-block
							 * */
							rightInIf = str.lastIndexOf("]", valIndex);
							leftInIf = str.lastIndexOf("[", rightInIf)+1;

							/**
							 * Get the if's register so if there are multiple constraints defined
							 * in the if-block it will be able to detect the other constraints that
							 * are used in the same if-block
							 * */
							ifRegL = str.indexOf(": if[");
							ifRegR = str.indexOf("]", ifRegL);
							ifRegL = str.lastIndexOf("[", ifRegR)+1;
							//System.out.println("if register: "+str.substring(ifRegL, ifRegR));

							String s = "read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"',";

							//check if the parameter is used validated in the if-condition
							int reg1 = str.lastIndexOf(s);
							int reg2 = str.indexOf(",", reg1+s.length());
							reg1 = str.lastIndexOf(",", reg2-1)+1;

							//System.out.println(str.substring(leftInIf, rightInIf)+" -- "+str.substring(reg1, reg2));

							u_reg1 = str.indexOf(s);
							u_reg1 = str.indexOf(",", u_reg1)+1;
							u_reg2 = str.indexOf(",", u_reg1);
							//System.out.println();

							//System.out.println("Regs ASSIGNED: "+leftInIf+" "+rightInIf+" "+ifRegL+" "+ifRegR);

							ifRegister = str.substring(ifRegL, ifRegR);

							IfRegisters.add(ifRegister);
							//System.out.println(node.functionChildNodes.get(counter).functionName+"Possible param1: "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
							//System.out.println("WAS "+str.substring(u_reg1, u_reg2));
							//System.out.println(str.substring(leftInIf, rightInIf)+" --- "+str.substring(u_reg1, u_reg2));

							if(str.substring(leftInIf, rightInIf).contains(str.substring(reg1, reg2)))
							{
								//								System.out.println("Validation number --> "+str.substring(tmp+1, valIndex));
								validationCheck = Integer.parseInt(str.substring(tmp+1, valIndex));
								System.out.println(node.functionChildNodes.get(counter).functionName+"Possible param1: "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
								if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine == 0)
								{
									node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine = validationCheck;

									//									System.out.println("Function \""+
									//											node.functionChildNodes.get(counter).functionName+"'s\" \""+
									//											node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
									//											"\" Parameter is validated");
									node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.VALIDATED;
									System.out.println(node.functionChildNodes.get(counter).functionName+"   what HAPPEDNDDASDF  "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
									String newLine = "";
									newLine = newLine + tokens[0] + " " + tokens[1]+ " color=green " + str.substring((tokens[0].length()+tokens[1].length()+2));
									fileContent.append(newLine);
									fileContent.append("\n");
									a=1;
								}
							}

							// This is optional for #1

							if(!str.substring(leftInIf, rightInIf).contains(str.substring(u_reg1, u_reg2)))
							{

								//								System.out.println("IN THE OPTIONAL PART");
								valIndex = str.indexOf(": read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'");
								int tmp1 = str.lastIndexOf("|", valIndex);
								if(tmp1 == -1)
									tmp1 = str.lastIndexOf("{", valIndex);

								int tmpu = Integer.parseInt(str.substring(tmp1+1, valIndex));

								if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).usedLine == 0)
								{
									node.functionChildNodes.get(counter).parameterChildNodes.get(i).usedLine = tmpu;
									//									System.out.println("the used thing: "+tmpu);
								}

								if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine > node.functionChildNodes.get(counter).parameterChildNodes.get(i).usedLine)
								{
									//									System.out.println("Function \""+
									//											node.functionChildNodes.get(counter).functionName+"'s\" \""+
									//											node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
									//											"\" Parameter is NOT validated!!");
									//System.out.println("Possible param1: "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
									node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
									System.out.println("CUBURUX - 1");
									String newLine = "";
									newLine = newLine + tokens[0] + " " + tokens[1]+ " color=red " + str.substring((tokens[0].length()+tokens[1].length()+2));
									fileContent.append(newLine);
									fileContent.append("\n");
									a=1;
								}
								else
								{
									node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
									System.out.println("CUBURUX - 2");
								}
								//								if(validationCheck == -1)
								//								{
								//									node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
								//								}
							}
							//							System.out.println("OUT THE OPTIONAL PART\n");
						}
						else if(!str.contains("if[") && str.contains("read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'"))
						{

							String s = "read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"',";
							u_reg1 = str.indexOf(s);
							u_reg1 = str.indexOf(",", u_reg1)+1;
							u_reg2 = str.indexOf(",", u_reg1);

							ifConstraints = str.substring(u_reg1, u_reg2);

							rightInIf = str.lastIndexOf("]");
							leftInIf = str.lastIndexOf("[", rightInIf)+1;

							nextConstraint = str.substring(leftInIf, rightInIf);
							//System.out.println(nextConstraint);
							//System.out.println("Regs ASSIGNED: "+leftInIf+" "+rightInIf+" "+ifRegL+" "+ifRegR);
							System.out.println("Possible param2: "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
							//							System.out.println(ifRegister+ " ...... "+ifConstraints);
							//							System.out.println(IfRegisters);
							//							ifRegister = nextConstraint.substring(nextConstraint.lastIndexOf(",")+1, nextConstraint.lastIndexOf("]"));




							//System.out.println(ifRegister);
							if(!ifRegister.equals("") && !ifConstraints.equals(""))
							{
								//System.out.println("NO ZEROS");

								//																System.out.println(node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'s register: "+str.substring(u_reg1, u_reg2));
								if(nextConstraint.contains(ifRegister) && nextConstraint.contains(ifConstraints))
								{

									//									System.out.println("This parameter is also validated: "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
									ifRegister="";
									ifConstraints="";

									if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine == 0)
									{
										node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine = validationCheck;

										//										System.out.println("Function \""+
										//												node.functionChildNodes.get(counter).functionName+"'s\" \""+
										//												node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
										//												"\" Parameter is validated");
										node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.VALIDATED;
										String newLine = "";
										//										System.out.println("F::: "+node.functionChildNodes.get(counter).functionName+" .-. "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
										newLine = newLine + tokens[0] + " " + tokens[1]+ " color=green " + str.substring((tokens[0].length()+tokens[1].length()+2));
										fileContent.append(newLine);
										fileContent.append("\n");
										a=1;
									}
								}

							}
							else
							{

								for(int k=0; k<IfRegisters.size(); k++)
								{
									System.out.println("U gotta be kidding me?"+IfRegisters.get(k).toString());
									System.out.println(str);
									if(str.contains(IfRegisters.get(k).toString()+"]"))
									{
										int lastPoint = str.indexOf(IfRegisters.get(k).toString()+"]");
										int checkLastCons = str.lastIndexOf("]", lastPoint+(IfRegisters.get(k).toString()+"]").length()+1);
										int checkFirstCons = str.lastIndexOf("[", checkLastCons);
										String chekinIf = str.substring(checkFirstCons, checkLastCons);

										if(chekinIf.contains(IfRegisters.get(k).toString()) && 
												chekinIf.contains(ifConstraints))
										{
											node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine = validationCheck;
											System.out.println("Is it workin?? ");
											node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.VALIDATED;
											String newLine = "";
											//											System.out.println("F::: "+node.functionChildNodes.get(counter).functionName+" .-. "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
											newLine = newLine + tokens[0] + " " + tokens[1]+ " color=green " + str.substring((tokens[0].length()+tokens[1].length()+2));
											fileContent.append(newLine);
											fileContent.append("\n");
											a=1;
										}
									}
								}
							}
						}


						/**
						 * Find the parameters that are used before validation
						 */
						if(!str.contains("if[") && str.contains("read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'"))
						{
							valIndex = str.indexOf(": read-variable['"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+"'");
							int tmp1 = str.lastIndexOf("|", valIndex);
							if(tmp1 == -1)
								tmp1 = str.lastIndexOf("{", valIndex);

							//							System.out.println("Problem? --> "+tmp1);
							//							System.out.println("Used number --> "+str.substring(tmp1+1, valIndex)+ " "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);

							int tmpu = Integer.parseInt(str.substring(tmp1+1, valIndex));

							if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).usedLine == 0)
							{
								node.functionChildNodes.get(counter).parameterChildNodes.get(i).usedLine = tmpu;
							}

							if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine > node.functionChildNodes.get(counter).parameterChildNodes.get(i).usedLine)
							{
								//																System.out.println("Function \""+
								//																		node.functionChildNodes.get(counter).functionName+"'s\" \""+
								//																		node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
								//																		"\" Parameter is NOT validated!!");
								System.out.println("CUBURUX - 3");
								node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
								String newLine = "";
								newLine = newLine + tokens[0] + " " + tokens[1]+ " color=red " + str.substring((tokens[0].length()+tokens[1].length()+2));
								fileContent.append(newLine);
								fileContent.append("\n");
								a=1;
							}
							else if(node.functionChildNodes.get(counter).parameterChildNodes.get(i).validatedLine == 0)
							{
								//																System.out.println("Function \""+
								//																		node.functionChildNodes.get(counter).functionName+"'s\" \""+
								//																		node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+
								//																		"\" Parameter is NOT validated!!");
								node.functionChildNodes.get(counter).parameterChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;

								//								for(int k=0; k<vulnerabilityRules.size(); k++)
								//								{
								//									Object[] vulIV = vulnerabilityRules.keySet().toArray();
								//									//for(int j=0; j<vulnerabilityRules.get(vulIV[k])
								//									System.out.println(vulIV[k].toString());
								//									for(int m=0; m<vulnerabilityRules.get(vulIV[k].toString()).size(); m++)
								//									{
								//										System.out.println(vulnerabilityRules.get(vulIV[k].toString()).get(m));
								//									}
								//								}

								System.out.println("CUBURUX - 4");
								String newLine = "";
								System.out.println(node.functionChildNodes.get(counter).functionName+ " --- "+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName);
								newLine = newLine + tokens[0] + " " + tokens[1]+ " color=red " + str.substring((tokens[0].length()+tokens[1].length()+2));
								fileContent.append(newLine);
								fileContent.append("\n");
								a=1;
							}
						}


						/**
						 * Start Checking validations if they have used 
						 * the rules for input validation
						 * */
						if(str.contains("call-"+node.functionChildNodes.get(counter).parameterChildNodes.get(i).parameterName+".indexOf"))
						{

						}
					}

					/***************************************************************/
					/******** END OF FUNCTION PARAMETER INPUT VAL ANALYSIS  ********/
					/***************************************************************/
				}

				if(str.contains("//#$~"))
				{
					String tokenize[] = str.split(" ");

					node.functionChildNodes.get(counter).endLine = Integer.parseInt(tokenize[1]);
				}

				if(a!=1)
				{
					String newline=str;
					fileContent.append(newline);
					fileContent.append("\n");
				}


			}
			node.functionChildNodes.get(counter).numOfCallgraphNodes = callgraphNodes.size();

			node = assignValidationStatus(node);

			FileWriter fstreamWrite = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write(fileContent.toString());
			out.close();
		}
		catch(IOException e)
		{
			System.err.println("Cannot open File!!!");
		}
		BayesianNetworkExecuter bne = new BayesianNetworkExecuter();
		bne.calculateFuncProbs(funcNodeProb, numOfParam, vulnerabilityRules);
		System.out.println("RIVERSIDE: "+funcNodeProb);
		return node;
	}

	public static RootFunctionNode assignValidationStatus(RootFunctionNode node)
	{
		for(int i=0; i<node.functionChildNodes.size(); i++)
		{
			for(int j=0; j<node.functionChildNodes.get(i).parameterChildNodes.size(); j++)
			{
				if(node.functionChildNodes.get(i).parameterChildNodes.get(j).validatedLine == 0 && node.functionChildNodes.get(i).parameterChildNodes.get(j).usedLine>0)
				{
					//					node.functionChildNodes.get(i).parameterChildNodes.get(j).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
				}
				else if(node.functionChildNodes.get(i).parameterChildNodes.get(j).usedLine < node.functionChildNodes.get(i).parameterChildNodes.get(j).validatedLine)
				{
					//					node.functionChildNodes.get(i).parameterChildNodes.get(j).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
				}
				else if(node.functionChildNodes.get(i).parameterChildNodes.get(j).usedLine > node.functionChildNodes.get(i).parameterChildNodes.get(j).validatedLine)
				{
					node.functionChildNodes.get(i).parameterChildNodes.get(j).status = ValidationStatus.VALIDATED;
				}
				else
				{
					node.functionChildNodes.get(i).parameterChildNodes.get(j).status = ValidationStatus.UNKNOWN;
				}
			}
		}

		return node;

	}

	public static RootVariableNode assignValidationStatus(RootVariableNode node)
	{
		for(int i=0; i<node.varibleChildNodes.size(); i++)
		{
			if(node.varibleChildNodes.get(i).vType != VariableType.LOCAL)
			{
				if(node.varibleChildNodes.get(i).validatedLine == 0 && node.varibleChildNodes.get(i).usedLine>0)
				{
					node.varibleChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
				}
				else if(node.varibleChildNodes.get(i).usedLine < node.varibleChildNodes.get(i).validatedLine)
				{
					node.varibleChildNodes.get(i).status = ValidationStatus.NOT_VALIDATED_BEFORE_USAGE;
				}
				else if(node.varibleChildNodes.get(i).usedLine > node.varibleChildNodes.get(i).validatedLine)
				{
					node.varibleChildNodes.get(i).status = ValidationStatus.VALIDATED;
				}
				else
				{
					node.varibleChildNodes.get(i).status = ValidationStatus.UNKNOWN;
				}
			}
			else
			{
				node.varibleChildNodes.get(i).status = ValidationStatus.NOT_GLOBAL_VARIABLE;
			}
		}

		return node;

	}

}
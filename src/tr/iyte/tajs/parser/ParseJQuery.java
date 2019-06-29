package tr.iyte.tajs.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParseJQuery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = null;
		BufferedReader br1 = null;
		BufferedReader brr = null;
		BufferedReader brVars = null, bp = null, bp1=null;

		FileInputStream fstream;
		FileInputStream fstream1;
		FileInputStream fs, fis, fis1;
		FileInputStream fStreamVars;

		int openP = 0;
		boolean flag = true;

		Map <Integer, Integer> lineInterval = new HashMap<Integer, Integer>();

		int lineNum=0;

		/**
		 * Match function names with source code lines an assign Function names which are "null"
		 * **/
		try {

			fstream1 = new FileInputStream("variableInfo\\FunctionNames.txt");
			br1 = new BufferedReader(new InputStreamReader(fstream1));
			StringBuilder fileContent = new StringBuilder();

			String strLine;
			String strLine1;


			while((strLine1 = br1.readLine()) != null)
			{
				lineNum = 0;
				String token1[] = strLine1.split(" ");
				//				System.out.println("HEY: "+token1[0]);

				fstream = new FileInputStream("clean\\jquery-1.9.1.js");
				br = new BufferedReader(new InputStreamReader(fstream));
				flag = true;
				openP = 0;

				/**
				 * Create a Map for each function to define their boundries
				 * The boundries will be used as intervals to search and match 
				 * the declared variables in the function
				 * **/
				try{
					while((strLine = br.readLine()) != null)
					{
						lineNum = lineNum + 1;
						String token[] = strLine.split(" ");
						//						System.out.println("Line: "+lineNum+ " WHAT IS: "+Integer.parseInt(token1[0]));

						if(lineNum == Integer.parseInt(token1[0]))
						{
							String newline="";
							newline = newline + token1[0] + " " + token1[1] + " " + strLine;
							fileContent.append(newline);
							fileContent.append("\n");
						}

						if(lineNum >= Integer.parseInt(token1[0]) && flag == true)
						{
							//							System.out.println(Integer.parseInt(token1[0]));
							for(int i=0; i<token.length; i++)
							{	
								//								System.out.println("CHECKING "+ token[i]);
								if(token[i].contains("{") && !token[i].contains("{}"))
								{
									//									System.out.println("OPEN Line: "+lineNum);
									openP++;
									//									System.out.println("+1 Points: "+openP);
								}

								if(token[i].contains("}") && !token[i].contains("{}"))
								{
									//									System.out.println("CLOSE Line: "+lineNum);
									openP--;
									//									System.out.println("-1 Points: "+openP);
									if(openP == 0)
									{
										//										System.out.println("GAPCURE!");
										flag = false;
										/**
										 * Create Map of a function with its boundries
										 * */
										lineInterval.put(Integer.parseInt(token1[0]),lineNum);
									}
								}

							}
						}

					}
					FileWriter fstreamWrite = new FileWriter("clean\\FunctionNamesUpdated.txt");
					BufferedWriter out = new BufferedWriter(fstreamWrite);
					out.write(fileContent.toString());
					out.close();
				}catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				//				br.close();

			}
			br1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("TOTAL FUNCS: "+lineInterval.size());
		System.out.println(lineInterval);
		int f = 0, count = 0, g=0;

		Object bLine[] = lineInterval.keySet().toArray();
		Object eLine[] = lineInterval.values().toArray();

		//		for(int i=0; i<lineInterval.size(); i++)
		//		{
		//			System.out.println("KEYS: "+bLine[i]);
		//			System.out.println("VALS: "+eLine[i]);
		//		}

		int k,countF=0;


		/**
		 * Capture the Variables that are Defined in the Function
		 * **/
//		System.out.println("Finding variables Declared in Function...");
//		try {
//
//			FileWriter vif = new FileWriter("files\\VariablesDefinedInFunction.txt");
//			BufferedWriter bw = new BufferedWriter(vif);
//
//			String content;
//			String contentFunc;
//
//			for(k=0; k<lineInterval.size(); k++)
//			{
//				fs = new FileInputStream("variableInfo\\FunctionNames.txt");
//				brr = new BufferedReader(new InputStreamReader(fs));
//
//				while((contentFunc = brr.readLine()) != null)
//				{
//					String tokenFunc[] = contentFunc.split(" ");
//
//					g=0;
//					fStreamVars= new FileInputStream("clean\\DeclaredVarNames.txt");
//					brVars = new BufferedReader(new InputStreamReader(fStreamVars));
//					while((content = brVars.readLine()) != null)
//					{
//						String tokenLine[] = content.split(" ");
//
//						if( Integer.parseInt(bLine[k].toString()) <= Integer.parseInt(tokenLine[0]) && 
//								Integer.parseInt(eLine[k].toString()) >= Integer.parseInt(tokenLine[0])) 
//						{
//							if(Integer.parseInt(tokenFunc[0]) == Integer.parseInt(bLine[k].toString()))
//							{
//								if(g == 0)
//								{
//									bw.write(tokenFunc[0]+": "+tokenFunc[1]+"\n");
//									countF++;
//									//									System.out.println("Function Name: "+ tokenFunc[1]+", at "+tokenFunc[0]);
//								}
//								bw.write("\t"+tokenLine[0]+": "+tokenLine[1]+"\n");
//								//								System.out.println("\tVar Name     : "+tokenLine[1]+", at "+tokenLine[0]);
//								g = 1;
//								count++;
//							}
//						}
//					}
//				}
//			}
//			bw.close();
//			vif.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace(); 
//		}
//		System.out.println("Done...");
//		System.out.println("\"VariablesInDefinedFunction.txt\" file Created");
//		System.out.println("Total Functions with Vars Declared: "+countF);
//		System.out.println("Total Declared Vars: "+count);

		/**
		 * Matching Functions and its Parameters
		 * **/
//		countF =0;
//		System.out.println("\nNow Matching Functions with its Parameters....");
//		try {
//			fis = new FileInputStream("variableInfo\\ParamNames.txt");
//			bp = new BufferedReader(new InputStreamReader(fis));
//
//			//Create file that keeps functions and its' parameters
//			FileWriter fwp = new FileWriter("files\\FunctionsWithParameters.txt");
//			BufferedWriter o = new BufferedWriter(fwp);
//
//			String contP;
//
//			while((contP = bp.readLine()) != null)
//			{
//				String tokenP[] = contP.split(" ");
//
//				fis1= new FileInputStream("variableInfo\\FunctionNames.txt");
//				bp1 = new BufferedReader(new InputStreamReader(fis1));
//
//				String contF;
//
//				while((contF = bp1.readLine()) != null)
//				{
//					String tokenF[] = contF.split(" ");
//
//					if(Integer.parseInt(tokenP[0]) == Integer.parseInt(tokenF[0]) && !tokenP[1].equals("[]"))
//					{
//						countF++;
//						o.write(tokenF[0]+": "+tokenF[1]+" : ");
//						//						System.out.println("Function Name: "+tokenF[1]);
//						//						System.out.print("\tParameter Name: ");
//						for(int i = 1; i<tokenP.length; i++)
//						{
//							int b,e;
//							if(tokenP[i].contains("[") && tokenP[i].contains(","))
//							{
//								b = tokenP[i].indexOf("[")+1;
//								e = tokenP[i].indexOf(",");
//								o.write(" "+tokenP[i].substring(b, e));
//							}
//							else if(tokenP[i].contains("[") && tokenP[i].contains("]"))
//							{
//								b = tokenP[i].indexOf("[")+1;
//								e = tokenP[i].indexOf("]");
//								o.write(" "+tokenP[i].substring(b, e));
//							}
//							else if(tokenP[i].contains(","))
//							{
//								e = tokenP[i].indexOf(",");
//								o.write(" "+tokenP[i].substring(0, e));
//							}
//							else if(tokenP[i].contains("]"))
//							{
//								e = tokenP[i].indexOf("]");
//								o.write(" "+tokenP[i].substring(0, e));
//							}
//
//							//							System.out.print(tokenP[i]);
//						}
//						o.write("\n");
//						//						System.out.println();
//					}
//
//				}
//			}
//			o.close();
//			fwp.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		System.out.println("Total Function with Parameters: "+countF);
//		System.out.println("\"FunctionWithParameters.txt\" File Created!");
//
		/********************END OF CREATING FUNCTION WITH PARAMETERS*************************************/

		/**
		 * Finding the variables that are used in each function
		 * **/
		//		countF =0;
		//		FileInputStream avm, fn;
		//		br = null;
		//		BufferedReader bb=null;
		//
		//		System.out.println("\nFinding variables used in the Function");
		//		try {
		//
		//			FileWriter vif = new FileWriter("files\\VariablesUsedInFunction.txt");
		//			BufferedWriter bw = new BufferedWriter(vif);
		//
		//			String content;
		//			String contentFunc;
		//
		//			for(k=0; k<lineInterval.size(); k++)
		//			{
		//				fs = new FileInputStream("variableInfo\\FunctionNames.txt");
		//				brr = new BufferedReader(new InputStreamReader(fs));
		//
		//				while((contentFunc = brr.readLine()) != null)
		//				{
		//					String tokenFunc[] = contentFunc.split(" ");
		//
		//					g=0;
		//					fStreamVars= new FileInputStream("clean\\AssignedVarNames.txt");
		//					brVars = new BufferedReader(new InputStreamReader(fStreamVars));
		//					while((content = brVars.readLine()) != null)
		//					{
		//						String tokenLine[] = content.split(" ");
		//
		//						if( Integer.parseInt(bLine[k].toString()) <= Integer.parseInt(tokenLine[0]) && 
		//								Integer.parseInt(eLine[k].toString()) >= Integer.parseInt(tokenLine[0])) 
		//						{
		//							if(Integer.parseInt(tokenFunc[0]) == Integer.parseInt(bLine[k].toString()))
		//							{
		//								if(g == 0)
		//								{
		//									bw.write(tokenFunc[0]+": "+tokenFunc[1]+"\n");
		//									countF++;
		//									//									System.out.println("Function Name: "+ tokenFunc[1]+", at "+tokenFunc[0]);
		//								}
		//								bw.write("\t"+tokenLine[0]+": "+tokenLine[1]+"\n");
		//								//								System.out.println("\tVar Name     : "+tokenLine[1]+", at "+tokenLine[0]);
		//								g = 1;
		//								count++;
		//							}
		//						}
		//					}
		//				}
		//			}
		//			bw.close();
		//			vif.close();
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace(); 
		//		}
		//
		//		System.out.println("Total Functions that has variable used: "+countF);
		//		System.out.println("\"VariablesUsedInFunction.txt\" File Created!");
		//		
		/****************************END OF FINDING VARIABLES USED IN FUNCTION*************************/

		/**
		 * Create a file that has the used parameters in the function
		 * */
		FileInputStream fstreamj;
		BufferedReader brj = null;
		int c=0,p;

		try {

			FileWriter vif = new FileWriter("files\\UsedParameters.txt");
			BufferedWriter bw = new BufferedWriter(vif);
			//Size is the number of functions
			for(int i=0; i<lineInterval.size(); i++)
			{
				fstream = new FileInputStream("files\\FunctionAndParams.txt");
				br = new BufferedReader(new InputStreamReader(fstream));
				p=0;
				String content;

				while((content = br.readLine()) != null)
				{
					int lines = 0;
					String token[] = content.split(" ");
					p++;
					fstreamj = new FileInputStream("clean\\jquery-1.9.1.js");
					brj = new BufferedReader(new InputStreamReader(fstreamj));

					String line;

					while((line = brj.readLine()) != null)
					{
						lines++;
						String tokenP[] = line.split(" ");
						if( (Integer.parseInt(bLine[i].toString()) < lines && 
								Integer.parseInt(eLine[i].toString()) >= lines) && 
								bLine[i].toString().equals(token[0])) 
						{
							
							if(!content.contains("( )"))
							{
								if(token[2].equals("("))
								{
									for(int j=3; j<token.length-1; j++)
									{
										if((line.contains(" "+token[j]+" ") && !line.contains("//")) 
												|| (line.contains("("+token[j]+")") && !line.contains("//"))
												|| (line.contains("("+token[j]+",") && !line.contains("//"))
												|| (line.contains(" "+token[j]+")") && !line.contains("//"))
												|| (line.contains(" "+token[j]+",") && !line.contains("//"))
												|| (line.contains(token[j]+".") && !line.contains("//")))
										{
//											System.out.println("I: "+i+" FLINE: "+bLine[i]+"-"+token[0]+" LINE: "+lines+" "+token[j]+" used as: "+line);
											System.out.println(i);
											bw.write("FLINE: "+token[0]+"\n");
											bw.write("\tPLINE: "+lines+"- "+token[j]+" USAGE: "+line+"\n");
											c++;
										}
									}
								}
								else if(token[3].equals("("))
								{
									for(int j=4; j<token.length-1; j++)
									{
										if((line.contains(" "+token[j]+" ") && !line.contains("//")) 
												|| (line.contains("("+token[j]+")") && !line.contains("//"))
												|| (line.contains("("+token[j]+",") && !line.contains("//"))
												|| (line.contains(" "+token[j]+")") && !line.contains("//"))
												|| (line.contains(" "+token[j]+",") && !line.contains("//"))
												|| (line.contains(token[j]+".") && !line.contains("//")))
										{
//											System.out.println("I: "+i+" FLINE: "+bLine[i]+" LINE: "+lines+" "+token[j]+" used as: "+line);
											System.out.println(i);
											bw.write("FLINE: "+token[0]+"\n");
											bw.write("\tPLINE: "+lines+"- "+token[j]+" USAGE: "+line+"\n");
											c++;
										}
									}
								}

							}

						}

					}
				}
			}
			bw.close();
			vif.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total HITS: "+c);
		/*************************END OF FINDING USED PARAMETERS IN FUNCTION*******************/
	}

}

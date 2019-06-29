package tr.iyte.tajs.iv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;

/**
 * This class is to detect the function names that are not found by flow graph
 * The algorithm works by targeting the functions with no names in the list.
 * Than directly points to the line and column where it was declared.
 * This process is done before generating the adjacency matrix and call graph
 * */

public class SourceCodeParser {

	BufferedReader reader = null;

	public RootFunctionNode parseSourceCode (String filename, RootFunctionNode nodeF)
	{
		try{
			reader = new BufferedReader(new FileReader(filename));

			String str;
			boolean flag = true;
			int lineNum = 0, assignable=0, withName=0, notAssigned=0;;
			while((str = reader.readLine()) != null)
			{
				lineNum++;
				String tokens[] = str.split(" ");

				for(int i=0; i<nodeF.functionChildNodes.size(); i++)
				{
					if(lineNum == nodeF.functionChildNodes.get(i).funcLine)
					{
						if(nodeF.functionChildNodes.get(i).functionName.equals("NO NAME"))
						{
							for(int j=0; j<tokens.length; j++)
							{
								if(str.contains(":") && str.contains("function"))
								{
									if(tokens[j].contains(":"))
									{
										System.out.println("F: "+tokens[j].substring(0, tokens[j].length()-1).trim()+" - "+lineNum+": GOTCHA: "+str);
										String funcName = tokens[j].substring(0, tokens[j].length()-1).trim();
										nodeF.functionChildNodes.get(i).functionName = funcName;
										assignable++;
										flag = false;
									}
								}
//								else if(str.contains("= function") && tokens[j].contains("function"))
//								{
//									System.out.println("F: "+tokens[0].substring(0, tokens[0].length()).trim()+" - "+lineNum+": GOTCHA: "+str);
//									String funcName = tokens[0].substring(0, tokens[0].length()).trim();
//									nodeF.functionChildNodes.get(i).functionName = funcName;
//									assignable++;
//									flag = false;
//								}
							}
							//System.out.println(lineNum+": GOTCHA: "+str);
							if(flag)
							{
								//System.out.println(lineNum+": COULDNT PARSE NAME: "+str);
								notAssigned++;
							}
						}
						else
						{
							//System.out.println(lineNum+": NO NEED: "+str);
							withName++;
						}
						flag = true;
					}
				}
			}
			System.out.println("ASSIGNABLE     : "+assignable);
			System.out.println("NOT ASSIGNABLE : "+notAssigned);
			System.out.println("WITH NAME      : "+withName);
		}
		catch(IOException e)
		{

		}
		
		return nodeF;
	}

}

package tr.iyte.tajs.iv.parameter.relation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ParameterRelationExtractorDot {

	public void writeDotFile(HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> functionsParamsRels)
	{
		int totalParamCount = 0;
		FileWriter fw = null;
		BufferedWriter bw = null; 
		try
		{
			fw = new FileWriter("ASTJson\\ASTDot.dot");
			bw = new BufferedWriter(fw);

			bw.write("digraph{\n"+
					"compound=true\n");

			Set<HashMap<String, ArrayList<String>>> relationKeys = functionsParamsRels.keySet();
//			System.out.println(functionsParamsRels.values());
//			System.out.println("My keys  --> "+ relationKeys);
			Object[] relations = relationKeys.toArray();
			Iterator it = relationKeys.iterator();

			ArrayList<HashMap<String, ArrayList<String>>> set = new ArrayList();
			set.addAll(relationKeys);



			int subgraphCounter = 0;

			for(int i=0; i<relations.length; i++)
			{

//				System.out.println("is this it? "+relations[i].toString());
//				System.out.println();
				bw.write("subgraph cluster"+subgraphCounter+" {\n");
				subgraphCounter++;
				bw.write("label="+"\"function "+functionsParamsRels.get(relations[i])+"\";\n");
				bw.write("labelloc=\"t\";\n"+
						"fontsize=18;\n"+
						"rankdir=\"TD\"\n");
				bw.write("BB_entry"+subgraphCounter+" [shape=none,label=\"\"];\n");

				HashMap<String, String> funcNodes = new HashMap<String, String>();

				HashMap valueHash = functionsParamsRels.get(relations[i]);

				Set funcKeys = valueHash.keySet();
				Object[] f = funcKeys.toArray();
				//				valueHash.get(f[j]).
				//				System.out.println("<<<<<<<< "+f);
				//				parameters = valueHash.values();
				//				parameters.
				//Object[] parameters = p.

				for(int j=0; j<f.length; j++)
				{
					//					bw.write("\n\nTesting\n\n");
					ArrayList parameters = functionsParamsRels.get(relations[i]).get(f[j]);
					System.out.println("\t"+f[j]+" -> "+functionsParamsRels.get(relations[i]).get(f[j]));
					for(int k=0; k<parameters.size(); k++)
					{
						funcNodes.put(parameters.get(k).toString(), "BB"+totalParamCount);
						bw.write("BB"+totalParamCount+" [shape=record label=\""+parameters.get(k).toString()+"\" ]\n");
						totalParamCount++;
						//System.out.println("p: "+parameters.get(k));

						//						System.out.println("life is hard "+relations[i]);
					}

				}


//				System.out.println("Relkey: "+set.get(i).keySet().toArray());
				Object[] source = set.get(i).keySet().toArray();
				Object[] outgoings = set.get(i).values().toArray();

				
				for(int k=0; k<outgoings.length; k++)
				{
//					System.out.println("Zource: "+source[k].toString());
					//ArrayList objSource = (ArrayList) source[k];
					ArrayList objOut = (ArrayList) outgoings[k];
//					System.out.println("Outs: "+objOut);
					for(int n=0; n<objOut.size(); n++)
					{
						//if()
						bw.write(funcNodes.get(source[k].toString())+" -> "+funcNodes.get(objOut.get(n).toString())+"\n");
//						System.out.println("C'mon! --> "+objOut.get(n));
					}
				}


				//System.out.println("hedehed --<<:>>"+valueHash.values());

				bw.write("}\n");
			}

			bw.write("}");
			bw.flush();
			bw.close();
			fw.flush();
			fw.close();
		}catch(IOException e)
		{

		}
	}

}

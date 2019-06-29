package tr.iyte.edu.bn.file;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import tr.iyte.edu.bn.stables.VulSysNPTable;
import tr.iyte.edu.bn.stables.VulnerabilityStats;
import tr.iyte.edu.bn.vtables.FunctVulNPTable;

public class BayesNetworkCreator {

	public void createFile(int[] funcs, ArrayList<FunctVulNPTable> fvTable, ArrayList<VulSysNPTable> vsTable, ArrayList<VulnerabilityStats> vulns, ArrayList<String> fNames, double initialIVVals, HashMap<String, Double> functionNodeProbs) {

		try {
			FileWriter fstreamUse = new FileWriter("files\\BNetwork.pgmx");
			BufferedWriter bw = new BufferedWriter(fstreamUse);
			String intro = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
					"<ProbModelXML formatVersion=\"0.2.0\">"+
					"\n\t<ProbNet type=\"BayesianNetwork\">"+
					"\n\t<Comment showWhenOpeningNetwork=\"false\"><![CDATA[<<Double click to add/modify comment>>]]></Comment>"+
					"\n\t<Variables>"+
					"\n\t<Variable name=\"Application\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
					"\n\t<Coordinates x=\"682\" y=\"37\" />"+
					"\n\t<AdditionalProperties>"+
					"\n\t<Property name=\"Purpose\" value=\"riskfactor\" />"+
					"\n\t</AdditionalProperties>"+
					"\n\t<Unit />"+
					"\n\t<Precision>0.01</Precision>"+
					"\n\t<States>"+
					"\n\t <State name=\"Not Robust\" />"+
					"\n\t <State name=\"Robust\" />"+
					"\n\t</States>"+
					"\n\t</Variable>";

					for(int i=0; i<vulns.size(); i++)
					{
						intro = intro + "\n\t<Variable name=\""+vulns.get(i).getVul()+"\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
								"\n\t<Coordinates x=\""+(126+(i*220))+"\" y=\"186\" />"+
								"\n\t<Unit />"+
								"\n\t<Precision>0.01</Precision>"+
								"\n\t<States>"+
								"\n\t<State name=\"Contains Validation Code\"/>"+
								"\n\t<State name=\"Not Contains Validation Code\" />"+
								"\n\t</States>"+
								"\n\t</Variable>";

					}
//					"\n\t<Variable name=\"OS Command Injection\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
//					"\n\t<Coordinates x=\"126\" y=\"186\" />"+
//					"\n\t<Unit />"+
//					"\n\t<Precision>0.01</Precision>"+
//					"\n\t<States>"+
//					"\n\t<State name=\"Not Contains\"/>"+
//					"\n\t<State name=\"Contains\" />"+
//					"\n\t</States>"+
//					"\n\t</Variable>"+
//					
//					"\n\t<Variable name=\"Path Traversal\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
//					"\n\t<Coordinates x=\"352\" y=\"187\" />"+
//					"\n\t<Unit />"+
//					"\n\t<Precision>0.01</Precision>"+
//					"\n\t<States>"+
//					"\n\t<State name=\"Not Contains\" />"+
//					"\n\t<State name=\"Contains\" />"+
//					"\n\t</States>"+
//					"\n\t</Variable>"+
//					
//					"\n\t<Variable name=\"Code Injection\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
//					"\n\t<Coordinates x=\"571\" y=\"186\" />"+
//					"\n\t<Unit />"+
//					"\n\t<Precision>0.01</Precision>"+
//					"\n\t<States>"+
//					"\n\t 	<State name=\"Not Contains\" />"+
//					"\n\t 	<State name=\"Contains\" />"+
//					"\n\t</States>"+
//					"\n\t</Variable>"+
//					
//					"\n\t<Variable name=\"Input Validation\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
//					"\n\t<Coordinates x=\"789\" y=\"190\" />"+
//					"\n\t<Unit />"+
//					"\n\t<Precision>0.01</Precision>"+
//					"\n\t<States>"+
//					"\n\t<State name=\"Not Contains\" />"+
//					"\n\t<State name=\"Contains\" />"+
//					"\n\t</States>"+
//					"\n\t</Variable>"+
//
//					"\n\t<Variable name=\"SQL Injection\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
//					"\n\t<Coordinates x=\"1009\" y=\"186\" />"+
//					"\n\t<Unit />"+
//					"\n\t<Precision>0.01</Precision>"+
//					"\n\t<States>"+
//					"\n\t<State name=\"Not Contains\" />"+
//					"\n\t<State name=\"Contains\" />"+
//					"\n\t</States>"+
//					"\n\t </Variable>"+
//					
//					"\n\t<Variable name=\"XSS\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
//					"\n\t<Coordinates x=\"1242\" y=\"186\" />"+
//					"\n\t<Unit />"+
//					"\n\t<Precision>0.01</Precision>"+
//					"\n\t<States>"+
//					"\n\t  <State name=\"Not Contains\" />"+
//					"\n\t  <State name=\"Contains\" />"+
//					"\n\t</States>"+
//					"\n\t </Variable>";


			/**
			 * PUT A LOOP HERE TO CREATE THE FUNCTION NODES
			 * CREATES THE NODES FOR EACH FUNCTION
			 * */
			for(int i=0; i<vulns.size(); i++)
			{
				String copy = "";
				for(int j=0; j<i; j++)
					copy = copy + "'";
				
				intro = intro + "\n<Variable name=\""+fNames.get(0)+copy+"\" type=\"finiteStates\" role=\"chance\" isInput=\"false\">"+
						"\n\t<Coordinates x=\""+(126+(i*220))+"\" y=\"415\" />"+
						"\n\t<Unit />"+
						"\n\t<Precision>0.01</Precision>"+
						"\n\t<States>"+
						"\n\t<State name=\"Not Validated\" />"+
						"\n\t<State name=\"Validated\" />"+
						"\n\t</States>"+
						"\n</Variable>";
			}
			intro = intro +"\n\t</Variables>" +"\n<Links>";


			/**
			 * LINK VULNERABILITIES TO SYSTEM
			 * *
			 */

			System.out.println("Creating Links Vulnerabilities to System...");
			for(int i = vulns.size()-1; i>=0; i--)
			{
				intro = intro + "\n\t<Link directed=\"true\">"+
						"\n\t\t<Variable name=\""+vulns.get(i).getVul()+"\" />"+
						"\n\t\t<Variable name=\"Application\" />"+
						"\n\t\t</Link>";
			}

			System.out.println("Links To System Created!");
			System.out.println("Creating Links Functions to Vulnerabilities...");


			/**
			 * LINK FUNCIONS TO VULNERABILITIES
			 * */
			System.out.println(vulns.size());
			System.out.println(funcs.length);
//			for(int i = 0; i<funcs.length; i++)
//			{
				for(int i=0; i<vulns.size(); i++)
				{			
					String copy = "";
					for(int j=0; j<i; j++)
						copy = copy + "'";
					
					intro = intro + "\n\t<Link directed=\"true\">"+
							"\n\t\t<Variable name=\""+fNames.get(0)+copy+"\" />"+
							"\n\t\t<Variable name=\""+vulns.get(i).getVul()+"\" />"+
							"\n\t\t</Link>";
				}
//			}

			System.out.println("Links To Vulnerabilities Created!");


			System.out.println("Creating Values...");
			/**
			 * ADD VALUES TO THE TABLE AND BAYESIAN NETWORK
			 * */
			intro = intro + "\n</Links>\n<Potentials>";

			intro = intro + "\n\t<Potential type=\"Table\" role=\"conditionalProbability\">"+
					"\n\t<Variables>"+
					"\n\t\t<Variable name=\"Application\" />";

			for(int i=vulns.size()-1; i>=0; i--)
			{
				intro = intro + "\n\t\t<Variable name=\""+vulns.get(i).getVul()+"\" />";
			}

			intro = intro + "\n</Variables>\n<Values>";


			/**
			 * VALUES FROM VULNERABILITIES TO SYSTEM
			 * */

			for(int i=0; i<vsTable.size(); i++)
			{
				intro = intro + " "+ vsTable.get(i).getYes() + " " + vsTable.get(i).getNo(); 
			}

			intro = intro + "</Values>\n</Potential>";

			/**
			 * VALUES FROM FUNCTIONS TO VULNERABILITIES
			 * */

			int index = 0;
			for(int i=0; i<vulns.size(); i++)
			{
				intro = intro + "\n\t<Potential type=\"Table\" role=\"conditionalProbability\">"+
						"\n\t<Variables>"+
						"\n\t\t<Variable name=\""+vulns.get(i).getVul()+"\" />";
				String copy = "";
				for(int j=0; j<i; j++)
					copy = copy + "'";
				
				for(int j=funcs.length-1; j>=0; j--)
				{
					intro = intro + "\n\t\t<Variable name=\""+fNames.get(j)+copy+"\" />";
				}

				intro = intro + "\n</Variables>\n<Values>";

				for(int k=index; k<(index+Math.pow(2, funcs.length)); k++)
				{
					intro = intro + " "+ fvTable.get(k).getNo() + " " + fvTable.get(k).getYes();
					//System.out.println("Index: "+(k)+" YES: "+fvTable.get(k).getYes());
				}
				index = index + (int)Math.pow(2, funcs.length);
				intro = intro + "</Values>\n</Potential>";
				//index = index + (int) Math.pow(2, funcs.length);	
			}
			//System.out.println("FVTable:: "+ fvTable.size());


			/**
			 * VALUES OF FUNCTIONS
			 * */
			System.out.println(functionNodeProbs);
			
			Object[] functionProb = functionNodeProbs.keySet().toArray();
			for(int i=0; i<vulns.size(); i++)
			{
				String copy = "";
				for(int j=0; j<i; j++)
					copy = copy + "'";
				double init = functionNodeProbs.get(functionProb[i].toString()).doubleValue();
				intro = intro + "\n\t<Potential type=\"Table\" role=\"conditionalProbability\">"+
						"\n\t<Variables>"+
						"\n\t\t<Variable name=\""+fNames.get(0)+copy+"\" />"+
						//"\n</Variables>\n<Values>"+(1-initialIVVals)+" "+initialIVVals+"</Values>\n</Potential>";
						"\n</Variables>\n<Values>"+(1-init)+" "+init+"</Values>\n</Potential>";
			}

			intro = intro + "\n</Potentials>\n<AdditionalProperties />\n</ProbNet>\n</ProbModelXML>";
			System.out.println("Values Created!");


			bw.write(intro);
			//fstreamUse.close();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
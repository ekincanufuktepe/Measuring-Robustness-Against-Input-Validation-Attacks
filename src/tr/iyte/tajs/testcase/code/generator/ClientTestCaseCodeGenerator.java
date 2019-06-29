package tr.iyte.tajs.testcase.code.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;

public class ClientTestCaseCodeGenerator {


	/**
	 * This Function creates a .js file that includes test cases specific to
	 * the analyzed JavaScript file. This function will work generic depending
	 * on the analyzed JavaScript file.
	 * */

	FileWriter fw = null;
	BufferedWriter bw = null;

	public void clientSideCodeGenerator(RootFunctionNode nodeF, String path)
	{	
		int fileNo = 1;
		int incrementor = 10; 
		int numOfTestedFuncs = incrementor, mm = 0;
		boolean stopSign = true;
		while(stopSign)
		{
			try {
				int totalTestRun = 0;
				fw = new FileWriter("testCases\\testCase"+fileNo+".html");
				bw = new BufferedWriter(fw);

				bw.write("<html>\n<head>\n");
				bw.write("\t<script src=\""+path+"\"></script>\n");
				bw.write("</head>\n\n");
				bw.write("<script>\n");

				bw.flush();
				bw.write("function assertEqual(a, b, message)\n"+
						"{\n"+
						"\ttry {\n"+
						"\t\tif (a != b)\n"+ 
						"\t\t\tthrow new Error(message + \" mismatch: \" + a + \" != \" + b);\n"+
						"\t\telse if(a == RangeError)\n"+
						"\t\t\tthrow new Error(message + \" mismatch: \" + a + \" != \" + b);\n"+
						"\t\telse\n"+
						"\t\t\tconsole.log(\"passed\");\n"+
						"\t} catch (e) {\n"+
						//"\t\tconsole.log(e.name);     // 'MyError'\n"+
						"\t\tconsole.log(e.message);  // 'Default Message'\n"+
						"\t\tcountErrs++;\n"+
						"\t}\n"+   
						"}\n\n");


				bw.flush();
				bw.write("Array.prototype.allValuesSame = function() {\n"+
						"\tfor(var i = 1; i < this.length; i++)\n"+
						"\t{\n"+
						"\t\tif(this[i] !== this[0])\n"+
						"\t\t\treturn false;\n"+
						"\t}\n"+
						"\treturn true;\n"+
						"}\n\n");


				bw.flush();
				bw.write("function mode(array)\n"+
						"{\n"+
						"\tif(array.length == 0)\n"+
						"\t\treturn null;\n"+
						"\tvar modeMap = {};"+
						"\tvar maxEl = array[0], maxCount = 1;\n"+
						"\tfor(var i = 0; i < array.length; i++)\n"+
						"\t{\n"+
						"\t\tvar el = array[i];\n"+
						"\t\tif(modeMap[el] == null)\n"+
						"\t\t\tmodeMap[el] = 1;\n"+
						"\t\telse\n"+
						"\t\t\tmodeMap[el]++;\n"+	
						"\t\tif(modeMap[el] > maxCount)\n"+
						"\t\t{\n"+
						"\t\t\tmaxEl = el;\n"+
						"\t\t\tmaxCount = modeMap[el];\n"+
						"\t\t}\n"+
						"\t}\n"+
						"\treturn maxEl;\n"+
						"}\n\n");



				bw.flush();
				bw.write("var ar = [];\n");
				bw.write("var testCases = [undefined, null, true, false, 'abcde', NaN, -Infinity, Infinity, 5, -5, 0.15, -0.15, Error, RangeError, ReferenceError, SyntaxError, TypeError, URIError, 1.3987869234598501e+308];\n");
				bw.write("document.write('Length of Test Cases: '+testCases.length+'<br><br>');\n");
				bw.write("var testRes = [];\n");
				bw.write("var countPassed = 0, countNotExist = 0, countFailed = 0;\n\n");
				//String str = "NO NAME";
				
				for(int i=mm; i<numOfTestedFuncs; i++)
				{
					if(!nodeF.functionChildNodes.get(i).functionName.equals("NO NAME") && 
							nodeF.functionChildNodes.get(i).parameterChildNodes.size() != 0)
					{
						for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
						{
							for(int k=0; k<j; k++)
								bw.write("\t");

							bw.write("for(i"+j+"=0; i"+j+"<testCases.length; i"+j+"++)\n");

							for(int k=0; k<j; k++)
								bw.write("\t");

							bw.write("{\n");
						}


						for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
						{
							bw.write("\t");				
						}

						bw.write("try{\n");


						for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
						{
							bw.write("\t");				
						}
						//Standart Code
						//bw.write("\tvar x = (typeof "+nodeF.functionChildNodes.get(i).functionName+"(");
						
						//jQuery Code
						//bw.write("var x = (typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");

						bw.write("var x = (typeof $."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
						for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
						{
							if(j == nodeF.functionChildNodes.get(i).parameterChildNodes.size()-1)
								bw.write("testCases[i"+j+"]));");
							else
								bw.write("testCases[i"+j+"],");
						}

						bw.write("\n");
						bw.flush();

						for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
						{
							bw.write("\t");				
						}

						bw.write("\tar.push(x);\n");

						for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
						{
							bw.write("\t");				
						}

						bw.write("} catch(e){}\n");

						bw.flush();

						for(int j=nodeF.functionChildNodes.get(i).parameterChildNodes.size()-1; j>=0; j--)
						{
							for(int k=0; k<j; k++)
								bw.write("\t");

							bw.write("}\n");
						}
						
						totalTestRun++;
						bw.write("\n");
						bw.write("//document.write(ar+'<br>');\n");
						bw.write("document.write(\"FUNCTION: ["+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"] Result: \"+ar.allValuesSame()+ ', Expected Return: '+mode(ar)+'<br><br>');\n");
						bw.write("if(ar.allValuesSame() == true && mode(ar) == null)\n{\n");
						bw.write("\tcountNotExist++;\n}\n");
						bw.write("else if(ar.allValuesSame() == true && mode(ar) != null)\n{\n");
						bw.write("\tcountPassed++;\n}\n");
						bw.write("else if(ar.allValuesSame() == false)\n{\n");
						bw.write("\tcountFailed++;\n}");
						bw.write("\n\n");
						bw.write("ar.length = 0;\n");
						bw.flush();	
					}
				}
				//bw.write();
				

				bw.write("document.write('Total Test Run      : '+"+totalTestRun+"+'<br>');");
				bw.write("document.write('Total Test Failed   : '+countFailed+'<br>');");
				bw.write("document.write('Total Test Passed   : '+countPassed+'<br>');");
				bw.write("document.write('Total Test Not Exist: '+countNotExist+'<br><br>');");
				bw.flush();
				
				bw.write("</script>\n");
				bw.write("</html>");
				bw.flush();
				bw.close();
				fw.close();
				
				if(nodeF.functionChildNodes.size()-numOfTestedFuncs >= incrementor)
				{
					numOfTestedFuncs = numOfTestedFuncs + incrementor;
					mm = mm + incrementor;
				}
				else if (nodeF.functionChildNodes.size()-numOfTestedFuncs < incrementor &&
						(nodeF.functionChildNodes.size()-numOfTestedFuncs) > 0)
				{
					mm = mm + incrementor;
					numOfTestedFuncs = numOfTestedFuncs + (nodeF.functionChildNodes.size()-numOfTestedFuncs);
					
				}
				else
				{
					stopSign = false;
				}
				
				fileNo = fileNo + 1;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
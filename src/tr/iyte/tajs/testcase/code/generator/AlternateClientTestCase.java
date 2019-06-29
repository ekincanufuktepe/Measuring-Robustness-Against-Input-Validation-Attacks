package tr.iyte.tajs.testcase.code.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import tr.iyte.tajs.iv.FunctionParameterTree;
import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;

public class AlternateClientTestCase {

	FunctionParameterTree functionTree = new FunctionParameterTree();
	HashMap<Integer, Integer> parameterInfo = new HashMap<>();
	/**
	 * This Function creates a .js file that includes test cases specific to
	 * the analyzed JavaScript file. This function will work generic depending
	 * on the analyzed JavaScript file.
	 * */

	FileWriter fw = null;
	BufferedWriter bw = null;

	public void clientSideCodeGenerator(RootFunctionNode nodeF, String path)
	{	
		TestCaseConfigParser testCases = new TestCaseConfigParser();
		/**
		 * testCase #1 ---> jQuery
		 * testCase #2 ---> midori
		 * testCase #3 ---> Zepto
		 * testCase #4 ---> D3
		 * testCase #5 ---> Joose
		 * testCase #6 ---> Backbone
		 * testCase #7 ---> Masonry
		 * testCase #8 ---> Hammer
		 * testCase #9 ---> Reveal
		 * testCase #10---> AngularJS
		 * */
		int fileNo = 10; //change the testcase number
		int largeComplexity = 0;
		int numOfTestCases = 19;

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


			System.out.println("Test Cases: ");
			System.out.println(testCases.parseConf());
			bw.flush();
			bw.write("var ar = [];\n");
			bw.write("var testCases = "+testCases.parseConf()+";\n");
			bw.write("document.write('Length of Test Cases: '+testCases.length+'<br><br>');\n");
			bw.write("var testRes = [];\n");
			bw.write("var countPassed = 0, countNotExist = 0, countFailed = 0;\n\n");
			//String str = "NO NAME";

			for(int i=0; i<nodeF.functionChildNodes.size(); i++)
			{
				if(!nodeF.functionChildNodes.get(i).functionName.equals("NO NAME") && 
						nodeF.functionChildNodes.get(i).parameterChildNodes.size() != 0)
				{
					if((nodeF.functionChildNodes.get(i).functionName.replace("\"", "").length() != 0) && 
							(!(nodeF.functionChildNodes.get(i).functionName.contains("*")) && !(nodeF.functionChildNodes.get(i).functionName.contains("'"))))
					{
						parameterInfo = functionTree.printFunctionParameterInfo(nodeF.functionChildNodes.get(i), parameterInfo);
						if(nodeF.functionChildNodes.get(i).parameterChildNodes.size() <= 4)
						{
							//Standart Code!!!
							//bw.write("\tvar x = (typeof "+nodeF.functionChildNodes.get(i).functionName+"(");

							//jQuery Library Code (testCase1.html)
							//bw.write("if((typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//AngularJS Library Code (testCase1.html)
							bw.write("if((typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//Midori Library Code (testCase2.html)
							//bw.write("if((typeof midori."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") != 'function')\n{\n");
							//bw.write("if((typeof midoriFX."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") != 'function')\n{\n");
							
							//Hammer Library Code (testCase8.html) remove the ":"
							//bw.write("if((typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") != 'function')\n{\n");
							
							//Reveal Library Code (testCase8.html)
							//bw.write("if((typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") != 'function')\n{\n");
							
							//Zepto Library Code (testCase3.html)
							//bw.write("if((typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//UIKit Library Code!!!
							//bw.write("if((typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//Backbone Library Code (testCase1.html)!!!!
							//bw.write("if((typeof React."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//Chaplin Library Code (testCase1.html)!!!!
							//bw.write("if((typeof Application."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//D3 Library Code (testCase4.html)!!!
							//bw.write("if((typeof d3."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//Handlebars Library Code (testCase4.html)!!!
							//bw.write("if((typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							
							//Joose Library Code (testCase5.html)!!!
							//bw.write("if((typeof Joose."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							//bw.write("if((typeof Joose.Class."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+") == 'function')\n{\n");
							for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
							{
								for(int k=0; k<j; k++)
									bw.write("\t");

								bw.write("\tfor(i"+j+"=0; i"+j+"<testCases.length; i"+j+"++)\n");

								for(int k=0; k<j; k++)
									bw.write("\t");

								bw.write("\t{\n");
							}


							for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
							{
								bw.write("\t");				
							}

							bw.write("\ttry{\n");


							for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
							{
								bw.write("\t");				
							}
							
							/**
							 * JavaScript Library Usage differ in this part of the code
							 * */
							
							//Standart Code
							//bw.write("\tvar x = (typeof "+nodeF.functionChildNodes.get(i).functionName+"(");

							//jQuery Library Code (testCase1.html)
							//bw.write("var x = (typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Backbone Library Code (testCase5.html)!!!
							//bw.write("var x = (typeof React."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//UIKit Library Code
							//bw.write("var x = (typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Chaplin Library Code (testCase5.html)!!!
							//bw.write("var x = (typeof Application."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Midori Library Code (testCase2.html)
							//bw.write("var x = (typeof midori."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							//bw.write("var x = (typeof midoriFX."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Hammer Library Code (testCase2.html)
							//bw.write("var x = (typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Reveal Library Code (testCase2.html)
							//bw.write("var x = (typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Zepto Library Code (testCase3.html)
							//bw.write("var x = (typeof $()."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//D3 Library Code (testCase4.html)!!!
							//bw.write("var x = (typeof d3."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Handlebars Library Code (testCase5.html)!!!
							//bw.write("var x = (typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//AngularJS Library Code (testCase5.html)!!!
							bw.write("var x = (typeof "+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							
							//Joose Library Code (testCase5.html)!!!
							//bw.write("var x = (typeof Joose."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							//bw.write("var x = (typeof Joose.Class."+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"(");
							for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
							{
								if(j == nodeF.functionChildNodes.get(i).parameterChildNodes.size()-1)
									bw.write("\ttestCases[i"+j+"]));");
								else
									bw.write("\ttestCases[i"+j+"],");
							}

							bw.write("\n");
							bw.flush();

							for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
							{
								bw.write("\t");				
							}

							bw.write("\t\tar.push(x);\n");

							for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
							{
								bw.write("\t");				
							}

							bw.write("\t} catch(e){}\n");

							bw.flush();

							for(int j=nodeF.functionChildNodes.get(i).parameterChildNodes.size()-1; j>=0; j--)
							{
								for(int k=0; k<j; k++)
									bw.write("\t");

								bw.write("\t}\n");
							}

							totalTestRun++;
							bw.write("\n");
							bw.write("\t//document.write(ar+'<br>');\n");
							bw.write("\tdocument.write('"+(i+1)+". FUNCTION @"+nodeF.functionChildNodes.get(i).funcLine+": ["+nodeF.functionChildNodes.get(i).functionName.replace("\"", "")+"] Result: '+ar.allValuesSame()+ ', Expected Return: '+mode(ar)+'<br><br>');\n");
							bw.write("\tif(ar.length != "+Math.pow(numOfTestCases, nodeF.functionChildNodes.get(i).parameterChildNodes.size())+" )\n");
							bw.write("\t{\n\t\tcountFailed++;\n\t}\n");
							bw.write("\telse if(ar.allValuesSame() == true && mode(ar) != null)\n\t{\n");
							bw.write("\t\tcountPassed++;\n\t}\n");
							bw.write("\telse if(ar.allValuesSame() == false)\n\t{\n");
							bw.write("\t\tcountFailed++;\n\t}\n");
							bw.write("}\nelse\n{");
							bw.write("\t\n\tcountNotExist++;\n}\n");
							bw.write("ar.length = 0;\n");
							bw.flush();	
						}
						else
						{
							largeComplexity++;
						}
					}
				}
			}
			//bw.write();


			bw.write("document.write('Total Test Run             : '+"+totalTestRun+"+'<br>');");
			bw.write("document.write('Tests Skipped (Complexity) : '+"+largeComplexity+"+'<br>');");
			bw.write("document.write('Total Test Failed          : '+countFailed+'<br>');");
			bw.write("document.write('Total Test Passed   		 : '+countPassed+'<br>');");
			bw.write("document.write('Total Test Not Exist		 : '+countNotExist+'<br><br>');");
			bw.flush();

			bw.write("</script>\n");
			bw.write("</html>");
			bw.flush();
			bw.close();
			fw.close();

			System.out.println("Parameter Info With Test Applied: "+parameterInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
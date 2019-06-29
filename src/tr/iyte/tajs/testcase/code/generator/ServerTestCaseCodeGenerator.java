package tr.iyte.tajs.testcase.code.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tr.iyte.tajs.iv.FunctionParameterTree.RootFunctionNode;

public class ServerTestCaseCodeGenerator {
	
	/**
	 * This Function creates a .js file that includes test cases specific to
	 * the analyzed JavaScript file. This function will work generic depending
	 * on the analyzed JavaScript file.
	 * */
	
	FileWriter fw = null;
	BufferedWriter bw = null;
	
	public void codeGenerator(RootFunctionNode nodeF, String path)
	{	
		try {
			fw = new FileWriter("testCases\\testCase2.js");
			bw = new BufferedWriter(fw);
			
			bw.write("var assert = require('assert'),\n"+
				    "\trandom = require('JavaScript-fuzz'),\n"+
					"\tfs = require('fs'),\n"+
				    "\tvm = require('vm');\n"+
					"var countErrs = 0;\n\n");
			
			bw.write("function include(path) {\n"+
					"\t var code = fs.readFileSync(path, 'utf-8');\n"+
					"\t vm.runInThisContext(code, path);\n"+
					"}\n");

			bw.write("include('"+path+"');\n\n");
			
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
			bw.write("var testCases = [undefined, null, true, false, random.string(5), NaN, -Infinity, Infinity, 5, -5, 0.15, -0.15, random.object.date(), Error, RangeError, ReferenceError, SyntaxError, TypeError, URIError, 1.3987869234598501e+308, random.object.regexp()];\n\n");
			
			//String str = "NO NAME";
			bw.flush();
			for(int i=0; i<nodeF.functionChildNodes.size(); i++)
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
					
					bw.write("ar.push(typeof "+nodeF.functionChildNodes.get(i).functionName+"(");
					
					
					for(int j=0; j<nodeF.functionChildNodes.get(i).parameterChildNodes.size(); j++)
					{
						if(j == nodeF.functionChildNodes.get(i).parameterChildNodes.size()-1)
							bw.write("testCases[i"+j+"]));");
						else
							bw.write("testCases[i"+j+"],");
					}
					
					bw.write("\n");
					
					for(int j=nodeF.functionChildNodes.get(i).parameterChildNodes.size()-1; j>=0; j--)
					{
						for(int k=0; k<j; k++)
							bw.write("\t");
						
						bw.write("}\n");
					}
					bw.write("\n");
					bw.write("//console.log(ar);\n");
					bw.write("console.log(\"FUNCTION: ["+nodeF.functionChildNodes.get(i).functionName+"] Result: \"+ar.allValuesSame()+ ', Expected Return: '+mode(ar));\n");
					bw.write("ar.length = 0;\n");
					bw.write("\n\n");
					
					
					bw.flush();
				}
			}
			//bw.write();
			
			
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package tr.iyte.tajs.iv.parameter.relation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

public class ASTExecuter {
	
	public static void ASTExecutionCodeGenerator(String file)
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try{
			fw = new FileWriter("ASTJson\\ASTTest.js");
			bw = new BufferedWriter(fw);
			
			bw.write("var esprima = require('esprima'),\n"
						+"\tfs = require('fs');\n"
						+"var code = fs.readFileSync('"+file.replace("\\", "/")+"');\n"
						+"var syntax = esprima.parse(code);\n"
						+"console.log(JSON.stringify(syntax, null, 4));\n");
			bw.flush();
			bw.close();
			fw.close();
			
		}catch(IOException e)
		{
			
		}
		
	}
	
	public static void ASTTriggerExecution(String file)
	{
		ParameterRelationExtractorDot dotWriter = new ParameterRelationExtractorDot();
		Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec("cmd.exe /c cd \""+file+"\" & node ASTTest.js >AST.json\"");
			//Process p=Runtime.getRuntime().exec("node \"\\"+file+"\\ASTTest.js\" >\""+file+"\\AST.json\""); 
			//System.out.println("Command:"+"cmd /c node \"\\"+file+"\\ASTTest.js\" >\""+file+"\\AST.json\"");
			p.waitFor();
			//rt.wait(2000);
			dotWriter.writeDotFile(ASTJsonParser.parseASTJson());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}

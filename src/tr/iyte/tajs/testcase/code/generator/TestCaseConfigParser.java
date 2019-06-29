package tr.iyte.tajs.testcase.code.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TestCaseConfigParser {
	
	private static String fileName = "testCases/config.conf";

	private String COMMENT_TOKEN = "#";
	
	public ArrayList<String> parseConf()
	{
		ArrayList<String> testCases = new ArrayList<>();
	
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = reader.readLine()) != null)
			{
				line = line.trim();
				if(line.startsWith(COMMENT_TOKEN) || line.isEmpty())
				{
					continue;
				}
				else
				{
					testCases.add(line);
				}
			}
		}
		catch(IOException e){
			System.err.println("Can't open the config file: " + fileName);
		}
		return testCases;
		
	}
}

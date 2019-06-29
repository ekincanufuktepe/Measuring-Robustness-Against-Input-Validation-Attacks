package tr.iyte.tajs.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {

	/**
	 * @param args
	 */

	private static String fileNameUse = "use_variables.txt";
	private static String fileNameDefine = "define_variables.txt";
	private static String fileNameRead = "read_variables.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;

		try {
			FileInputStream fstream = new FileInputStream("out\\flowgraphs\\final.dot");
			br = new BufferedReader(new InputStreamReader(fstream));

			FileWriter fstreamUse = new FileWriter("variableInfo\\"+fileNameUse);
			BufferedWriter outUse = new BufferedWriter(fstreamUse);

			FileWriter fstreamDefine = new FileWriter("variableInfo\\"+fileNameDefine);
			BufferedWriter outDefine = new BufferedWriter(fstreamDefine);

			FileWriter fstreamRead = new FileWriter("variableInfo\\"+fileNameRead);
			BufferedWriter outRead = new BufferedWriter(fstreamRead);

			String strLine;

			while((strLine = br.readLine()) != null)
			{
				int from, to;

				String token[] = strLine.split(" ");
				/**Catch the initialized/declared variable */
				for(int i=0; i<token.length; i++)
				{

					if(token[i].contains("vardecl["))
					{
						from = token[i].indexOf("vardecl[");
						to = token[i].indexOf("]", from)+1;
						outDefine.write(token[i].substring(from, to)+"\n");
						//System.out.println("DECLARE!!!");
					}
					/**Catch the assignment of a variable*/
					else if(token[i].contains("write-variable["))
					{
						from = token[i].indexOf("write-variable[");
						to = token[i].indexOf("]", from)+1;
						outUse.write(token[i].substring(from, to)+"\n");
						//System.out.println("WRITE!!!");
					}
					/**Catch the read variable*/
					else if(token[i].contains("read-variable["))
					{
						from = token[i].indexOf("read-variable[");
						to = token[i].indexOf("]", from)+1;
						outRead.write(token[i].substring(from, to)+"\n");
						//System.out.println("READ!!!");
					}
				}
			}
			outDefine.close();
			outUse.close();
			outRead.close();
			br.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}

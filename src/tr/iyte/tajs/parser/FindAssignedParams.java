package tr.iyte.tajs.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FindAssignedParams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = null;

		int pCount = 0, line=0;
		int pfCount = 0, fNoParams = 0;
		
		try {
			FileInputStream fstream = new FileInputStream("files\\FunctionAndParams.txt");
			br = new BufferedReader(new InputStreamReader(fstream));
			
			String strLine;
			
			while((strLine = br.readLine()) != null)
			{
				line++;
				String token[] = strLine.split(" ");
				pCount = pCount + token.length - 4;
				
				if((token.length - 4) == 0)
				{
					fNoParams++;
				}
				else if((token.length - 4) > 0)
				{
					pfCount++;
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total Functions Detected: "+line);
		System.out.println("Total Functions With Parameters Detected: "+pfCount);
		System.out.println("Total Functions Without Parameters Detected: "+fNoParams);
		System.out.println("Total Parameters Detected: "+pCount);
	}
		// TODO Auto-generated method stub

		//		Scanner in = new Scanner(System.in);
		//		
		//		String text = in.nextLine();
		////		
		//		System.out.println("Text: "+text);
		//		
		//		String str = in.next("\n");
		//		
		//		System.out.println("Str: "+);
//		String text;
//		int a;
//		Scanner in = new Scanner(System.in);
//		Scanner inner = new Scanner(System.in);
//		a = inner.nextInt();
//		System.out.println("A: "+a);
//		for(int i = 0; i<3; i++)
//		{
//			text = in.nextLine();
//			System.out.println("Text: "+text);
//		}
//	}
	//		FileInputStream fstream, fstreamj;
	//		BufferedReader br=null, brj=null;
	//		
	//		try {
	//			fstream = new FileInputStream("files\\FunctiosWithParameters.txt");
	//			br = new BufferedReader(new InputStreamReader(fstream));
	//			
	//			String content;
	//			
	//			while((content = br.readLine()) != null)
	//			{
	//				String token[] = content.split(" ");
	//				
	//				fstreamj = new FileInputStream("clean\\jquery-1.9.1.js");
	//				brj = new BufferedReader(new InputStreamReader(fstreamj));
	//				
	//				String line;
	//				
	//				while((line = brj.readLine()) != null)
	//				{
	//					String tokenP[] = line.split(" ");
	//					
	//					
	//				}
	//			}
	//			
	//		} catch (FileNotFoundException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//	}

}

package tr.iyte.tajs.iv.parameter.relation;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class ASTJsonParser {


	boolean relControl = false;
	public static HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> functionsParamsRels = new HashMap<>();

	static HashMap<String, ArrayList<String>> TMPFuncParamsRel = new HashMap<>();

	@SuppressWarnings("null")
	public static HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> parseASTJson()
	{
		int fcounter = 0;
		try {
			// read the json file
			FileReader reader = new FileReader("ASTJson/AST.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			// get an array from the JSON object
			JSONArray body = (JSONArray) jsonObject.get("body");

			// take the elements of the json array
			//			for(int i=0; i<body.size(); i++){
			//				System.out.println("The " + i + " element of the array: "+body.get(i));
			//			}
			Iterator i = body.iterator();
			String functionName = "";
			// take each value from the json array separately
			while (i.hasNext()) 
			{
				HashMap<String, ArrayList<String>> functions = new HashMap<>();
				ASTJsonParser ast = new ASTJsonParser();
				JSONObject innerObj1 = (JSONObject) i.next();
				ArrayList <String> parameters =  new ArrayList<>();
				if(innerObj1.get("type").equals("FunctionDeclaration") ||
						innerObj1.get("type").equals("FunctionExpression"))
				{
					HashMap<String, ArrayList<String>> singleFuncParamsRel = new HashMap<>();

					fcounter++;
					JSONObject innerObj2name = (JSONObject) innerObj1.get("id");

					JSONObject innerObj2 = (JSONObject) innerObj1.get("body");

					if(innerObj2.get("type").equals("BlockStatement"))
					{ 

						functionName = innerObj2name.get("name").toString();
						System.out.println("\n=========================================");
						System.out.println("Function: "+ innerObj2name.get("name") +" type "+ innerObj1.get("type") + 
								" with init " + innerObj1.get("params"));
						//						System.out.println(innerObj2.get("body")+"\n");

						JSONArray innerObj3 = (JSONArray) innerObj1.get("params");

						for(int j=0; j<innerObj3.size(); j++){
							//							System.out.println("The " + j + " element of the array: "+innerObj3.get(j));
							JSONObject parameter = (JSONObject) innerObj3.get(j);
							//							System.out.println(parameter.get("name"));
							parameters.add(parameter.get("name").toString());
						}
						functions.put(functionName, parameters);
					}

					ast.traceASTree(innerObj2, parameters, 1, singleFuncParamsRel);
				}
				/**
				 * This part is to adapt the code for JS Libraries
				 * */
				else if(innerObj1.get("type").equals("ExpressionStatement"))
				{
					HashMap<String, ArrayList<String>> singleFuncParamsRel = new HashMap<>();
					
					JSONObject exp = (JSONObject) innerObj1.get("expression");
					JSONObject left = (JSONObject) exp.get("left");
					JSONObject right = (JSONObject) exp.get("right");

					if(left != null)
					{
						if(left.get("type").equals("FunctionDecleration") ||
								left.get("type").equals("FunctionExpression"))
						{
							//									JSONObject fName = (JSONObject) left.get("id");
							parameters =  new ArrayList<>();
							JSONArray p = (JSONArray) left.get("params");
							
							for(int j=0; j<p.size(); j++){
								//							System.out.println("The " + j + " element of the array: "+innerObj3.get(j));
								JSONObject parameter = (JSONObject) p.get(j);
								//							System.out.println(parameter.get("name"));
								parameters.add(parameter.get("name").toString());
							}
							System.out.println("Function foundL, "+p.toString());	
							fcounter++;
						}
					}
					
					
					if(right != null)
					{
						if(right.get("type").equals("FunctionDecleration") ||
								right.get("type").equals("FunctionExpression"))
						{
							parameters =  new ArrayList<>();
							//								JSONObject fName = (JSONObject) right.get("id");
							JSONArray p = (JSONArray) right.get("params");
							for(int j=0; j<p.size(); j++){
								//							System.out.println("The " + j + " element of the array: "+innerObj3.get(j));
								JSONObject parameter = (JSONObject) p.get(j);
								//							System.out.println(parameter.get("name"));
								parameters.add(parameter.get("name").toString());
							}
							
							System.out.println("Function foundR1, "+p.toString());
							fcounter++;
						}
						
						ast.traceASTree(right, parameters, 1, singleFuncParamsRel);
						
						if(right.get("type").equals("ObjectExpression"))
						{
							JSONArray rightProp = (JSONArray) right.get("properties");
							for(int j=0; j<rightProp.size(); j++)
							{
								parameters =  new ArrayList<>();
								JSONObject info = (JSONObject) rightProp.get(j);
								JSONObject key = (JSONObject) info.get("key");
								JSONObject value = (JSONObject) info.get("value");
								if(value.get("type").equals("FunctionExpression") ||
									value.get("type").equals("FunctionDecleration"))
								{
									JSONArray p = (JSONArray) value.get("params");
									
									for(int k=0; k<p.size(); k++){
										//							System.out.println("The " + j + " element of the array: "+innerObj3.get(j));
										JSONObject parameter = (JSONObject) p.get(k);
										//							System.out.println(parameter.get("name"));
										parameters.add(parameter.get("name").toString());
									}
									
									fcounter++;
									System.out.println("Function foundR2, "+key.get("name").toString()+" Params:"+p.toString());
								}
								JSONObject body2 = (JSONObject) value.get("body");
								if(body2 != null)
								{
									ast.traceASTree(body2, parameters, 1, singleFuncParamsRel);
								}
							}
						}
					}
				
					
					//					JSONObject innerObj2name = (JSONObject) innerObj1.get("declarations");
					//					JSONObject innerObj3 = (JSONObject) innerObj2name.get("init");
					//					JSONObject innerObj4 = (JSONObject) innerObj3.get("properties");
				}

				functionsParamsRels.put(TMPFuncParamsRel, functions);
				//TMPFuncParamsRel

			}
			// handle a structure into the json object
			System.out.println(functionsParamsRels);
			System.out.println("Funkies found: "+fcounter);
			reader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		return functionsParamsRels;
	}


	public void traceASTree(JSONObject innerObj, ArrayList<String> params, int sign, HashMap<String, ArrayList<String>> singleFuncParamsRel)
	{
		if(innerObj.get("type").equals("BlockStatement"))
		{
			JSONArray innerObj2 = (JSONArray) innerObj.get("body");
			for(int i=0; i<innerObj2.size(); i++)
			{
				traceASTree((JSONObject)innerObj2.get(i), params, sign, singleFuncParamsRel);
			}
		}
		else if(innerObj.get("type").equals("IfStatement") ||
				innerObj.get("type").equals("WhileStatement") ||
				innerObj.get("type").equals("ForStatement"))
		{
			boolean ifState = innerObj.get("type").equals("IfStatement");
			boolean whileState = innerObj.get("type").equals("WhileStatement");
			boolean forState = innerObj.get("type").equals("ForStatement");

			//System.out.println("in here 1?");
			JSONObject innerObj2 = (JSONObject)innerObj.get("test");
			//traceASTree(innerObj2, params);
			String content = innerObj2.toJSONString();
			//			System.out.println("contento: "+content);
			ArrayList<Integer> multiIf = new ArrayList<>();
			for(int i=0; i<params.size(); i++)
			{
				//				System.out.println("\"name\":\""+params.get(i)+"\",\"type\":\"Identifier\"");
				if(content.contains("\"name\":\""+params.get(i)+"\",\"type\":\"Identifier\""))
				{
					multiIf.add(i);
					if(sign != 0)
						sign--;
					//System.out.print(params.get(multiIf.get(i))+" is the Parent "+sign);
				}
			}
			System.out.println(params.toString());
			if(multiIf.size() != 0)
				System.out.print("Parent/s: ");

			for(int i=0; i<multiIf.size(); i++)
			{
				if(!singleFuncParamsRel.containsKey(params.get(multiIf.get(i))))
					singleFuncParamsRel.put(params.get(multiIf.get(i)), new ArrayList<String>());
				System.out.print(params.get(multiIf.get(i))+" ");
			}
			if(multiIf.size() != 0)
				System.out.println(" -> Tree Level: "+sign);

			if(ifState == true)
			{
				JSONObject innerObj3 = (JSONObject) innerObj.get("consequent");
				if(innerObj3 != null)
				{
					String contento = innerObj3.toJSONString();
					//					System.out.println("contento: "+contento);
					for(int i=0; i<params.size(); i++)
					{
						if(contento.contains("\"name\":\""+params.get(i)+"\",\"type\":\"Identifier\""))
						{
							for(int k=0; k<multiIf.size(); k++)
							{
								ArrayList<String> tmpArray = new ArrayList<String>();
								tmpArray = singleFuncParamsRel.get(params.get(multiIf.get(k)));
								tmpArray.add(params.get(i));
								singleFuncParamsRel.replace(params.get(multiIf.get(k)), tmpArray);
							}
							sign++;
							System.out.println("\t"+params.get(i)+" is the Child"+sign);
						}
					}

					//JSONObject innerObj3 = (JSONObject) innerObj.get("consequent");
					//					for(int i=0; i<innerObj3.size(); i++)
					//					{
					traceASTree(innerObj3, params, sign, singleFuncParamsRel);
					//					}
				}
			}
			else if(whileState == true)
			{
				JSONObject innerObj3 = (JSONObject) innerObj.get("body");
				if(innerObj3 != null)
				{
					String contento = innerObj3.toJSONString();
					//					System.out.println("contento: "+contento);
					for(int i=0; i<params.size(); i++)
					{
						if(contento.contains("\"name\":\""+params.get(i)+"\",\"type\":\"Identifier\""))
						{
							for(int k=0; k<multiIf.size(); k++)
							{
								ArrayList<String> tmpArray = new ArrayList<String>();
								tmpArray = singleFuncParamsRel.get(params.get(multiIf.get(k)));
								tmpArray.add(params.get(i));
								singleFuncParamsRel.replace(params.get(multiIf.get(k)), tmpArray);
							}
							sign++;
							System.out.println("\t"+params.get(i)+" is the Child"+sign);
						}
					}

					//JSONObject innerObj3 = (JSONObject) innerObj.get("consequent");
					//					for(int i=0; i<innerObj3.size(); i++)
					//					{
					traceASTree(innerObj3, params, sign, singleFuncParamsRel);
					//					}
				}
			}
			else if(forState == true)
			{
				JSONObject innerObj3 = (JSONObject) innerObj.get("body");
				if(innerObj3 != null)
				{
					String contento = innerObj3.toJSONString();
					//					System.out.println("contento: "+contento);
					for(int i=0; i<params.size(); i++)
					{
						if(contento.contains("\"name\":\""+params.get(i)+"\",\"type\":\"Identifier\""))
						{
							for(int k=0; k<multiIf.size(); k++)
							{
								ArrayList<String> tmpArray = new ArrayList<String>();
								tmpArray = singleFuncParamsRel.get(params.get(multiIf.get(k)));
								tmpArray.add(params.get(i));
								singleFuncParamsRel.replace(params.get(multiIf.get(k)), tmpArray);
							}
							sign++;
							System.out.println("\t"+params.get(i)+" is the Child"+sign);
						}
					}

					//JSONObject innerObj3 = (JSONObject) innerObj.get("consequent");
					//					for(int i=0; i<innerObj3.size(); i++)
					//					{
					traceASTree(innerObj3, params, sign, singleFuncParamsRel);
					//					}
				}
			}


		}
		else
		{
//			System.out.println("THERE ARE NOT RELATIONS BETWEEN ");
		}

		//		else if(innerObj.get("type").equals("ExpressionStatement"))
		//		{
		//			//System.out.println("in here 2?");
		//			JSONObject innerObj2 = (JSONObject)innerObj.get("expression");
		//			String content = innerObj2.toJSONString();
		////			System.out.println("contento: "+content);
		//			for(int i=0; i<params.size(); i++)
		//			{
		//				if(content.contains("\"name\":\""+params.get(i)+"\",\"type\":\"Identifier\""))
		//				{
		//					sign++;
		//					System.out.println("\t"+params.get(i)+" is the Child"+sign);
		//				}
		//			}
		//			traceASTree(innerObj2, params,sign);
		//		}

		TMPFuncParamsRel = singleFuncParamsRel;
	}

}

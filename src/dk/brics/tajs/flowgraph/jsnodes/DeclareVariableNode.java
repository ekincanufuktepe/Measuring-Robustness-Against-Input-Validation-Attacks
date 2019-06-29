/*
 * Copyright 2009-2013 Aarhus University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dk.brics.tajs.flowgraph.jsnodes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import dk.brics.tajs.flowgraph.BasicBlock;
import dk.brics.tajs.flowgraph.SourceLocation;
import dk.brics.tajs.util.AnalysisException;
import dk.brics.tajs.util.Strings;

/**
 * Variable declaration node.
 * <p>
 * var <i>x</i>
 */
public class DeclareVariableNode extends Node {
	
	private String varname;
	private int lineNum;

	/**
	 * Constructs a new variable declaration node.
	 */
	public DeclareVariableNode(String varname, SourceLocation location) {
		super(location);
		this.lineNum = location.getLineNumber();
		this.varname = varname;
	}

	/**
	 * Returns the variable name.
	 * Creates a map that holds each variable's name 
	 */
	Map<Integer, String> varName = new HashMap <Integer, String>();
	public String getVariableName() {
		//System.out.println("VAR DECLARED: "+lineNum+" "+varname);
		File file = new File("clean\\DeclaredVarNames.txt");
		if(!varName.containsKey(lineNum) && !varName.containsValue(varname))
		{
			varName.put(lineNum, varname);
			
			try{
				if(file.exists() == false)
				{
					file.createNewFile();
				}
				PrintWriter out = new PrintWriter(new FileWriter(file, true));
				
				out.append(lineNum+" "+varname+"\n");
				out.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return varname;
	}
	
	@Override
	public String toString() {
		return "vardecl['" + Strings.escape(varname) + "']";
	}

	@Override
	public <ArgType> void visitBy(NodeVisitor<ArgType> v, ArgType a) {
		v.visit(this, a);
	}

	@Override
	public boolean canThrowExceptions() {
		return false;
	}

    @Override
    public void check(BasicBlock b) {
        if (varname == null || varname.isEmpty())
            throw new AnalysisException("Empty variable name:" + toString());
    }
}

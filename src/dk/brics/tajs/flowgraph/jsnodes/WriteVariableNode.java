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
 * Write variable node.
 * <p>
 * <i>x</i> = <i>v</i>
 */
public class WriteVariableNode extends Node {

	private String varname;
	private int lineNum;
	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	private int value_reg;

	/**
	 * Constructs a new write variable node.
	 */
	public WriteVariableNode(int value_reg, String varname, SourceLocation location) {
		super(location);
		// Get the line number
		this.lineNum = location.getLineNumber();
		this.value_reg = value_reg;
		this.varname = varname;
	}

	/**
	 * Returns the destination variable name.
	 */
	Map<Integer, String> varName = new HashMap <Integer, String>();
		
	public String getVariableName() {
		//System.out.println("1_LINE NUM: "+lineNum+" - write-variable[v" + value_reg + ",'" + Strings.escape(varname) + "']");
		return varname;
	}
	
	/**
	 * Returns the register.
	 */
	public int getValueRegister() {
		return value_reg;
	}

    /**
     * Sets the register.
     */
    public void setRegister(int value_reg) {
        this.value_reg = value_reg;
    }
	
    
    /**
     * Write all the "USED" variables with their location to a
     * file called "AssignedVarNames.txt"
     * **/
	@Override
	public String toString() {
		File file = new File("clean\\AssignedVarNames.txt");
		if(!varName.containsKey(lineNum) && !varName.containsValue(Strings.escape(varname)))
		{
			varName.put(lineNum, Strings.escape(varname));
			
			try{
				if(file.exists() == false)
				{
					file.createNewFile();
				}
				PrintWriter out = new PrintWriter(new FileWriter(file, true));
				
				out.append(lineNum+" "+Strings.escape(varname)+"\n");
				out.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		//System.out.println("2_LINE NUM: "+lineNum+" - write-variable[v" + value_reg + ",'" + Strings.escape(varname) + "']");
		return "write-variable[v" + value_reg + ",'" + Strings.escape(varname) + "']";
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
        if (value_reg == NO_VALUE)
            throw new AnalysisException("Invalid source register: " + toString());
        if (varname == null || varname.isEmpty())
            throw new AnalysisException("Variable name is null: " + toString());
    }
}

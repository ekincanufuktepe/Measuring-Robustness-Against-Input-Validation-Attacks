package tr.iyte.tajs.iv;

import java.math.BigDecimal;
import java.math.MathContext;

public class MatrixOperations {

	/**
	 * Matrix Multiplication by Using BigDecimal
	 * JAMA was using double and thats why it was 
	 * failing 
	 * */
	public BigDecimal[][] multiply(BigDecimal firstArr[][], BigDecimal secondArr[][])
	{
		int matSize = firstArr.length;
		
		BigDecimal multiply[][] = new BigDecimal[matSize][matSize];
		BigDecimal sum = new BigDecimal(0);
		MathContext mc = new MathContext(4);
		
		for (int c = 0 ; c < matSize ; c++ )
        {
           for (int d = 0 ; d < matSize ; d++ )
           {   
              for (int k = 0 ; k < matSize ; k++ )
              {     	  
                 sum.add(firstArr[c][k].multiply(secondArr[k][d], mc));
              }

              multiply[c][d] = sum;
              sum = new BigDecimal(0);
           }
        }
		
		return multiply;
	}
	
	
	/**
	 * Matrix Summation by Using BigDecimal
	 * JAMA was using double and thats why it was 
	 * failing 
	 * */
	public BigDecimal[][] sum(BigDecimal firstArr[][], BigDecimal secondArr[][])
	{
		int matSize = firstArr.length;
		
		BigDecimal summation[][] = new BigDecimal[matSize][matSize];
		
		for(int i=0; i<matSize; i++)
		{
			for(int j=0; j<matSize; j++)
			{
				summation[i][j] = firstArr[i][j].add(secondArr[i][j]);
			}
		}
		
		return summation;
	}
}

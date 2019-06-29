package dk.brics.tajs.test;

import org.junit.Before;
import org.junit.Test;

import dk.brics.tajs.options.Options;

@SuppressWarnings("static-method")
public class TestD3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("dk.brics.tajs.test.TestD3");
		}
		
		@Before
		public void init() {
	        Options.reset();
			Options.enableTest();
		}

		@Test
		public void D3_1() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/ascending.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
		@Test
		public void D3_2() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/bisect.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
		@Test
		public void D3_3() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/d3.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
//		@Test
//		public void D3_4() throws Exception
//		{
//			Misc.init();
//			Misc.captureSystemOutput();
//			String[] args = {"test/D3/d3.min.js"};
//			Misc.run(args);
//			Misc.checkSystemOutput();
//		}
//		
		@Test
		public void D3_5() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/descending.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
		@Test
		public void D3_6() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/entries.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
		@Test
		public void D3_7() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/index-browserify.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
		@Test
		public void D3_8() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/index.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
		@Test
		public void D3_9() throws Exception
		{
			Misc.init();
			Misc.captureSystemOutput();
			String[] args = {"test/D3/keys.js"};
			Misc.run(args);
			Misc.checkSystemOutput();
		}
		
}

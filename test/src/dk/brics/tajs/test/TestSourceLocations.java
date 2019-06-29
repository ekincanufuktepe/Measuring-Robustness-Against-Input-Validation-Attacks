package dk.brics.tajs.test;

import org.junit.Before;
import org.junit.Test;

import dk.brics.tajs.options.Options;

@SuppressWarnings("static-method")
public class TestSourceLocations {
	
	@Before
	public void before() {
		Options.reset();
	}

	@Test
	public void sourcelocations_javascript() {
		// expect line 1
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/javascript.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithInternalJavascript() {
		// expect line 9
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithInternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithExternalJavascript() {
		// expect line 1
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithExternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithInternalAndExternalJavascript() {
		// expect line 9 for html
		// expect line 1 for js
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithInternalAndExternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithExternalAndInternalJavascript() {
		// expect line 13 for html
		// expect line 1 for js
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithExternalAndInternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithInternalAndInternalJavascript() {
		// expect line 9 for html
		// expect line 14 for html2
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithInternalAndInternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithExternalAndExternalJavascript() {
		// expect line 1 for js
		// expect line 2 for js2
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithExternalAndExternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_eval() {
		// expect line 1
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/eval.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_evalSource() {
		// expect line 2
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/evalSource.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_eventhandlers() {
		// expect line 8
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/eventhandlers.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_eventhandlerWithMultiLineEvents() {
		// expect line 10
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/eventhandlerWithMultiLineEvents.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithFunctionInternalJavascript() {
		// expect line 9
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/htmlWithFunctionInternalJavaScript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithEvalInternalJavascript() {
		// expect line 9
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/htmlWithEvalInternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithMultiLineEvalInternalJavascript() {
		// expect line 10
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/htmlWithMultiLineEvalInternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlWithFunctionWithMultiLineEvalInternalJavascript() {
		// expect line 11, 13
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/htmlWithFunctionWithMultiLineEvalInternalJavascript.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_htmlProblematicCase() {
		// TODO:
		// odd line number 6 (compared to testFlowgraphBuilder0156.out which is slightly better):
		// test/sourcelocations/htmlProblematicCase.tidy.html:6: [maybe] Uncaught exception, constructed at [test/sourcelocations/htmlProblematicCase.tidy.html:17:16]

		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-uneval", "-test", "test/sourcelocations/htmlProblematicCase.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_doubleVar() {
		// expect column 5, 12
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/doubleVar.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void sourcelocations_tripleVar() {
		// expect column 5, 12
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "test/sourcelocations/tripleVar.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}
	
	@Test
	public void sourcelocations_testCase_H() {
		// expect column 5, 12
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "clean/TestCase_1.html" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}
	
	@Test
	public void sourcelocations_testCase_JS() {
		// expect column 5, 12
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = { "-quiet", "-test", "clean/TestCase_1.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

}

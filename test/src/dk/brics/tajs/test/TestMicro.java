package dk.brics.tajs.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dk.brics.tajs.options.Options;
import dk.brics.tajs.util.AnalysisException;

@SuppressWarnings("static-method")
public class TestMicro {
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("dk.brics.tajs.test.TestMicro");
	}

	@Before
	public void init() {
		Options.reset();
		Options.enableTest();
		Options.enableContextSensitiveHeap();
		Options.enableParameterSensitivity();
		Options.enableNumericVariableSensitivity();
		//Options.enableForInSpecialization();
		//Options.enableNoPolymorphic();
		//Options.enableNoModified();
		//Options.enableNoLazy();
		//Options.enableDebug();
		//Options.enableNoRecency();
	}

	@Test
	public void micro_00() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test00.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_01() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test01.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_02() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test02.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_03() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test03.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_04() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test04.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_05() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test05.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_06() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test06.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_07() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test07.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_08() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test08.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_09() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test09.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_10() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test10.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_11() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test11.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_12() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test12.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_13() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test13.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_14() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test14.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_15() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test15.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_16() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test16.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_17() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test17.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_18() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test18.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_19() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test19.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_20() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test20.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_21() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test21.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_22() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test22.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_23() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test23.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_24() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test24.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_25() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test25.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_26() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test26.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_27() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test27.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_28() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test28.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_29() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test29.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_30() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test30.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_31() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test31.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_32() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test32.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_33() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test33.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_34() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test34.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_35() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test35.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_36() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test36.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_37() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test37.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_38() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test38.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_39() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test39.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_40() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test40.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_41() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test41.js", "test/micro/test41b.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_42() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test42.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_43() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test43.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_44() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test44.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_45() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test45.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_46() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test46.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_47() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test47.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_48() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test48.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_49() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test49.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_50() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test50.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // TODO: can't specialize for-in due to r/w conflict
	@Test
	public void micro_50ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test50.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_51() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test51.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_51ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test51.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_52() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test52.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_53() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test53.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_54() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test54.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_55() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test55.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_56() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test56.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_57() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test57.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_58() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test58.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_59() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test59.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_60() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test60.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_61() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test61.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_62() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test62.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_63() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test63.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_63ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test63.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_64() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test64.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_65() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test65.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_66() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test66.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_67() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test67.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_68() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test68.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_69() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test69.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_70() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test70.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_71() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test71.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_72() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test72.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_73() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test73.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_74() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test74.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_74ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test74.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_75() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test75.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_76() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test76.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_77() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test77.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_78() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test78.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_79() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test79.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_80() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test80.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_81() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test81.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_82() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test82.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_83() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test83.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_84() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test84.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_85() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test85.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_86() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test86.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_87() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test87.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_88() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test88.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_89() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test89.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_90() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test90.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_91() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test91.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_92() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test92.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_93() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test93.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_94() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test94.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_95() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test95.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_96() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test96.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_97() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test97.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_98() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test98.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_99() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test99.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_100() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test100.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_101() throws Exception { // TODO: should insert assume node automatically
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test101.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_102() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test102.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_103() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test103.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_104() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test104.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_105() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test105.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_106() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test106.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_107() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test107.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_108() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test108.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_109() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test109.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_110() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test110.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_111() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test111.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_112() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test112.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_113() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test113.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	// Should be runtime error, but Sec 16 p.149 allows us to report the error early.
	@Test(expected=AnalysisException.class)
	public void micro_114() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test114.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_115() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test115.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_116() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test116.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_117() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test117.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_118() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test118.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_119() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test119.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_120() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test120.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: for-in with break/continue/delete
	@Test
	public void micro_120ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test120.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_121() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test121.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_121ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test121.js"}; // can't specialize for-in due to r/w conflict
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_122() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test122.js"}; // FIXME: flow graph wrong!
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_123() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test123.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_123ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test123.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_126() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test126.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_127() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test127.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_128() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test128.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_129() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test129.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_130() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test130.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_131() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test131.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_132() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test132.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_133() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test133.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_134() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test134.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_135() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test135.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_136() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test136.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_137() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test137.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_138() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test138.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_139() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test139.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test(expected=AnalysisException.class)
	public void micro_140() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test140.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_141() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test141.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_142() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test142.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // TODO: getters/setters
	@Test
	public void micro_143() throws Exception {
		Assert.fail("Add support for getters/setters");
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test143.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // TODO: getters/setters
	@Test
	public void micro_144() throws Exception {
		Assert.fail("Add support for getters/setters");
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test144.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_145() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test145.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: BlockState (readPropertyRaw?) currently doesn't model ES5-style string index properties
	@Test
	public void micro_146() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test146.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_147() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test147.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_148() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test148.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_148b() throws Exception {
		Options.enableNoModified(); // maybe-modified can make a difference, even with lazy prop
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test148.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_149() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test149.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_150() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test150.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_151() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test151.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_152() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test152.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_153() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test153.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_154() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test154.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_155() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test155.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_156() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test156.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_157() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test157.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_158() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test158.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_159() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test159.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_160() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test160.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_161() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test161.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_162() throws Exception { 
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test162.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_163() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test163.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_164() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test164.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_165() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test165.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_166() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test166.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_167() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test167.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_168() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test168.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_169() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test169.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_170() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test170.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_171() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test171.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_172() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test172.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_173() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test173.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_174() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test174.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_175() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test175.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}
	@Test
	public void micro_176() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test176.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_176ct() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test176.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_177() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test177.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_178() throws Exception { // FIXME: don't report "Reading absent property" when the read is used as a branch condition (see Monitoring.visitReadProperty)
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test178.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: for-in, iterating over the chars of a string
	@Test
	public void micro_179() throws Exception { 
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test179.js"};
		Misc.run(args);
		fail(); // Misc.checkSystemOutput();
	}

	@Test
	public void micro_180() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test180.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_181() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test181.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: toString
	@Test
	public void micro_182() throws Exception { 
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test182.js"};
		Misc.run(args);
		fail(); // Misc.checkSystemOutput();
	}

	@Ignore // FIXME: valueOf
	@Test
	public void micro_183() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test183.js"};
		Misc.run(args);
		fail(); // Misc.checkSystemOutput();
	}

	@Test
	public void micro_184() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test184.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: Rounding error.
	@Test
	public void micro_185() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test185.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_186() throws Exception { // TODO: Improve precision, should give 0.
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test186.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_187() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test187.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_188() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test188.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_188un() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableUnrollOneAndAHalf();
		String[] args = {"test/micro/test188.js"};
		Misc.run(args);
		Options.enableUnrollOneAndAHalf();
		Misc.checkSystemOutput();
	}

	@Ignore
	@Test
	public void micro_189() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/test189.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Misc.checkSystemOutput();
	}

	@Ignore
	@Test
	public void micro_189un() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableForInSpecialization();
		Options.enableUnrollOneAndAHalf();
		String[] args = {"test/micro/test189.js"};
		Misc.run(args);
		Options.disableForInSpecialization();
		Options.disableUnrollOneAndAHalf();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_190() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test190.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_191() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test191.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_192() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test192.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_200() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test200.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_201() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test201.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_202() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test202.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_203() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test203.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_204() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test204.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_205() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/test205.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	public void micro_testArray() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testArray.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testBoolean() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testBoolean.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test(expected=AnalysisException.class) // TODO: unevalable eval
	public void micro_testEval() throws Exception { 
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableUnevalizer();
		String[] args = {"test/micro/testEval.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testFunction() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		Options.enableUnevalizer();
		String[] args = {"test/micro/testFunction.js"};
		Misc.run(args);
		Options.disableUnevalizer();
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testFunctionApply() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testFunctionApply.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testFunctionCall() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testFunctionCall.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testNumber() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testNumber.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testObject() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testObject.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testOO() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testOO.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testRegExp() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testRegExp.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testString() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testString.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test // TODO: Check output; Handle valueOf/toString correctly. testToPrimitive.js
	public void micro_testToPrimitive() throws Exception { // TODO: testToPrimitive.js:19:1: [info] Abstract value: Str => could be "xy*"
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testToPrimitive.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testPaper() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testPaper.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testForIn1() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testForIn1.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: need new flowgraph builder...
	@Test
	public void micro_testForIn2() throws Exception { // FIXME: for-in with break
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testForIn2.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Ignore // FIXME: need new flowgraph builder...
	@Test
	public void micro_testForIn3() throws Exception { // FIXME: for-in with exception
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = { "test/micro/testForIn3.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testForIn4() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = { "test/micro/testForIn4.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testForIn5() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = { "test/micro/testForIn5.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testForIn6() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = { "test/micro/testForIn6.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testForIn7() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		Misc.captureSystemOutput();
		String[] args = { "test/micro/testForIn7.js" };
		Misc.run(args);
		Misc.checkSystemOutput();
	}

	@Test
	public void micro_testForInHasOwn() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		String[] args = { "test/micro/testForInHasOwn.js" };
		Misc.run(args);
	}

	@Test
	public void micro_testForInPrototypeProperties() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		String[] args = { "test/micro/testForInPrototypeProperties.js" };
		Misc.run(args);
	}
	
	@Test
	public void micro_testForInNoEnumerablePrototypeProperties() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		String[] args = { "test/micro/testForInNoEnumerablePrototypeProperties.js" };
		Misc.run(args);
	}
	
	@Test
	public void micro_testForInEach() throws Exception {
		Misc.init();
		Options.enableForInSpecialization();
		String[] args = {"test/micro/testForInEach.js"};
		Misc.run(args);	
	}
	
	@Test
	public void micro_testCall1() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testCall1.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}
	
	@Test
	public void micro_testCall2() throws Exception {
		Misc.init();
		Misc.captureSystemOutput();
		String[] args = {"test/micro/testCall2.js"};
		Misc.run(args);
		Misc.checkSystemOutput();
	}
}

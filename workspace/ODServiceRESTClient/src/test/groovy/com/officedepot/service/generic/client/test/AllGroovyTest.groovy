package com.officedepot.service.generic.client.test;

import junit.framework.TestSuite;

/**
 * Need not use.
 * Please run java based test cases if groovy is not installed
 */

class AllGroovyTest extends TestSuite {
	
	static TestSuite suite() throws Exception {
		def suite = new TestSuite();
		def currentDir =new File(".").canonicalPath
		def gsuite = new GroovyTestSuite();
		def TEST_ROOT = "/src/test/groovy/com/officedepot/service/generic/client/test/"
		//suite.addTestSuite(FooTest.class);  // non-groovy test cases welcome, too.
		suite.addTestSuite(gsuite.compile(currentDir + TEST_ROOT + "TestCircuitBreakerGroovy.groovy"));
		suite.addTestSuite(gsuite.compile(currentDir+ TEST_ROOT + "TestGenericClientHealthCheckGroovy.groovy"));
		
		return suite;
	}
}
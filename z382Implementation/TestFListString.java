/*
Emil Abraham
eabraham@ccs.neu.edu
What is your favorite Internet Browser?
*/

public class TestFListString {

    // Runs the tests.
               
    public static void main(String args[]) {
        TestFListString test = new TestFListString();

        // Test with 0-argument FSet.emptySet().

        System.out.println("Testing 0-argument emptyList()");
        test.creation();
        test.accessors(0);
        test.usual();
        test.accessors(0);    // test twice to detect side effects
        test.usual();

        test.summarize();
    }

    // Prints a summary of the tests.

    private void summarize () {
        System.out.println();
        System.out.println (totalErrors + " errors found in " +
                            totalTests + " tests.");
    }

    public TestFListString () { }

    // Double objects to serve as elements.
    
    //String objects to serve as elements.

    private final String alpha = "alpha";
    private final String beta = "beta";
    private final String gamma = "gamma";
    private final String delta = "delta";
    private final String epsilon = "epsilon";
    private final String zeta = "zeta";

    // FSet objects to be created and then tested.

    private FListString f0;    // { }
    private FListString f1;    // { alpha }
    private FListString f2;    // { beta, alpha }
    private FListString f3;    // { gamma, beta, alpha }
    private FListString f4;    // { delta, gamma, beta, alpha }
    private FListString f5;    // { alpha, beta, alpha }
    private FListString f6;    // { gamma, delta, beta, alpha }
    private FListString f7;    // { alpha, beta, beta, alpha }

    private FListString s1; //{beta}
    private FListString s2; //{beta, zeta}
    private FListString s3; //{gamma, zeta, alpha}
    private FListString s4; //{alpha}
    private FListString s5; //{ delta, gamma, beta, alpha }
    private FListString s6; //{ }

    // Creates some FSet objects.

    private void creation () {
        try {
            f0 = FListString.emptyList();
            f1 = FListString.add (f0, alpha);
            f2 = FListString.add (f1, beta);
            f3 = FListString.add (f2, gamma);
            f4 = FListString.add (f3, delta);
            f5 = FListString.add (f4, epsilon);
            f6 = FListString.add (FListString.add (f2, delta), gamma);

            f7 = FListString.add (f0, alpha);
            f7 = FListString.add (f7, beta);
            f7 = FListString.add (f7, beta);
            f7 = FListString.add (f7, alpha);

	    s1 = FListString.add(f0, beta);
	    s2 = FListString.add(FListString.add(f0, zeta), beta);
	    s3 = FListString.add(FListString.add(f1, zeta), gamma);
	    s4 = FListString.add(f0, alpha);
	    s5 = FListString.add(FListString.add(FListString.add
		 (FListString.add(f0, alpha), beta), gamma), delta);
	    s6 = FListString.emptyList();

        }
        catch (Exception e) {
            System.out.println("Exception thrown during creation tests:");
            System.out.println(e);
            assertTrue ("creation", false);
        }
    }

    // Tests the accessors.

    private void accessors (int nargs) {
        try {
            assertTrue ("empty", FListString.isEmpty (f0));
            assertFalse ("nonempty", FListString.isEmpty (f1));
            assertFalse ("nonempty", FListString.isEmpty (f3));

            assertTrue ("f0.size()", FListString.size (f0) == 0);
            assertTrue ("f1.size()", FListString.size (f1) == 1);
            assertTrue ("f2.size()", FListString.size (f2) == 2);
            assertTrue ("f3.size()", FListString.size (f3) == 3);
            assertTrue ("f4.size()", FListString.size (f4) == 4);
            assertTrue ("f5.size()", FListString.size (f5) == 5 );
            assertTrue ("f7.size()", FListString.size (f7) == 4);
	    
	    //Assuming you can't run get on EmpyList
	    assertTrue("f1.get()", FListString.get(f1, 0) == "alpha");
	    assertTrue("f2.get()", FListString.get(f2, 0) == "beta");
	    assertTrue("f3.get()", FListString.get(f3, 2) == "alpha");
	    assertTrue("f7.get()2", FListString.get(f7, 2) == "beta");
	    assertTrue("f7.get()3", FListString.get(f7, 3) == "alpha");
	    
	    //Assuming you can't run set on EmptyList
	    assertTrue("f0.set()", FListString.set(f1, 0, "beta").equals(s1));
	    assertTrue("f1.set()", FListString.set(f2, 1, "zeta").equals(s2));
	    assertTrue("f2.set()", FListString.set(f3, 1, "zeta").equals(s3));
        }
        catch (Exception e) {
            System.out.println("Exception thrown during accessors tests:");
            System.out.println(e);
            assertTrue ("accessors", false);
        }
    }

    // Tests the usual overridden methods.

    private void usual () {
        try {
            assertTrue ("toString0",
                        f0.toString().equals("[]"));
            assertTrue ("toString1",
                        f1.toString().equals("[alpha]"));
            assertTrue ("toString7",
                        f2.toString().equals("[beta, alpha]"));
	    assertTrue ("toString3",
			f3.toString().equals("[gamma, beta, alpha]"));

            assertTrue ("equals00", f0.equals(f0));
            assertTrue ("equals33", f3.equals(f3));
            assertTrue ("equals55", f5.equals(f5));

            assertFalse ("equals01", f0.equals(f1));
            assertFalse ("equals02", f0.equals(f2));
            assertFalse ("equals10", f1.equals(f0));
            assertFalse ("equals12", f1.equals(f2));
            assertFalse ("equals21", f2.equals(f1));
            assertFalse ("equals23", f2.equals(f3));
            assertFalse ("equals35", f3.equals(f5));

	    assertTrue ("equals1s4", f1.equals(s4));
	    assertTrue ("equals4s5", f4.equals(s5));
	    assertTrue ("equalsee", f0.equals(s6));

            assertFalse ("equals0string", f0.equals("just a string"));
            assertFalse ("equals4string", f4.equals("just a string"));

            assertFalse ("equals0null", f0.equals(null));
            assertFalse ("equals1null", f1.equals(null));

            assertTrue ("hashCode00", f0.hashCode() == f0.hashCode());
            assertTrue ("hashCode44", f4.hashCode() == f4.hashCode());
        }
        catch (Exception e) {
            System.out.println("Exception thrown during usual tests:");
            System.out.println(e);
            assertTrue ("usual", false);
        }
    }

////////////////////////////////////////////////////////////////

    private int totalTests = 0;       // tests run so far
    private int totalErrors = 0;      // errors so far

    // For anonymous tests.  Deprecated.

    private void assertTrue (boolean result) {
      assertTrue ("anonymous", result);
    }

    // Prints failure report if the result is not true.

    private void assertTrue (String name, boolean result) {
        if (! result) {
            System.out.println ();
            System.out.println ("***** Test failed ***** "
                                + name + ": " +totalTests);
            totalErrors = totalErrors + 1;
        }
        totalTests = totalTests + 1;
    }

    // For anonymous tests.  Deprecated.

    private void assertFalse (boolean result) {
        assertTrue (! result);
    }

    // Prints failure report if the result is not false.

    private void assertFalse (String name, boolean result) {
        assertTrue (name, ! result);
    }

}

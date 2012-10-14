





public abstract class FListString {

    

    public static FListString emptyList () { return new EmptyList(); }

    
    

    public static FListString add (FListString f, String x) {
	return new Add (f, x);
    }

    

    public static boolean isEmpty (FListString f) { return f.isAnEmptyList(); }

    

    public static String get (FListString f, int n) {
	return f.getListElement (n);
    }

    
    

    public static FListString set (FListString f, int n, String y) {
	return f.setPolitely (n, y);
    }

    

    public static int size (FListString f) { return f.computeSize(); }

    

    @Override
    public int hashCode () { return 0; }

    

    abstract boolean isAnEmptyList();
    abstract String getListElement (int n);
    abstract FListString setPolitely (int n, String y);
    abstract int computeSize ();
}

class EmptyList extends FListString {

    EmptyList () { }

    boolean isAnEmptyList () { return true; }

    String getListElement (int n) {
	throw new IndexOutOfBoundsException("get");
    }

    FListString setPolitely (int n, String y) {
	throw new IndexOutOfBoundsException("set");
    }

    int computeSize () { return 0; }

    @Override
    public String toString () { return "[]"; }

    public boolean equals (Object x) {
	if (x instanceof FListString)
	    return FListString.isEmpty ((FListString) x);
	else
	    return false;
    }
}

class Add extends FListString {

    FListString f;   
    String x;        

    Add (FListString f, String x) {
	this.f = f;
	this.x = x;
    }

    boolean isAnEmptyList () { return false; }

    String getListElement (int n) {
	if (n == 0)
	    return x;
	else
	    return FListString.get (f, n - 1);
    }

    FListString setPolitely (int n, String y) {
	if (n == 0)
	    return FListString.add (f, y);
	else
	    return FListString.add (FListString.set (f, n - 1, y), x);
    }

    int computeSize () { return 1 + FListString.size (f); }

    @Override
    public String toString () {
	if (FListString.isEmpty (f))
	    return "[" + x.toString() + "]";
	else
	    return "[" + x.toString() + ", "
		+ f.toString().substring(1, f.toString().length());
    }

    public boolean equals (Object x) {
	if (x instanceof FListString) {
	    FListString that = (FListString) x;
	    if (FListString.size (this) == FListString.size (that)) {
		for (int i = 0; i < FListString.size (this); i = i + 1) {
		    String s1 = FListString.get (this, i);
		    String s2 = FListString.get (that, i);
		    if (s1.equals(s2))
			;
		    else
			return false;
		}
		return true;
	    }
	    else
		return false;
	}
	else
	    return false;
    }
}


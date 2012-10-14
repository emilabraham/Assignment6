

public abstract class FListString {
    abstract boolean isEmptyMethod(); 
    abstract String getMethod(int n); 
    
    
    abstract FListString setMethod(int n, String y); 
    abstract int sizeMethod(); 

    
    public static FListString emptyList() {
		return new EmptyList();
    }
    
    public static FListString add(FListString f, String x) {
		return new Add(f, x);
    }
    
    public static boolean isEmpty(FListString f) {
		return f.isEmptyMethod();
    }
    
    public static String get(FListString f, int n) {
		return f.getMethod(n);
    }
    
    public static FListString set(FListString f, int n, String y) {
		return f.setMethod(n, y);
    }
    
    public static int size(FListString f) {
		return f.sizeMethod();
    }
    
    public String toString() {
		if (FListString.isEmpty(this)) {
		    return "[]";
		}
		else if (FListString.isEmpty(((Add) this).f)) {
		    Add list = (Add) this;
		    return "[" + list.x.toString() + "]";
		}
		else {
		    Add list = (Add) this;
		    return "[" + list.x.toString() + ", "
			+ list.f.toString().substring(1, list.f.toString().length());
		}
    }
    
    public boolean equals(Object x) {
		if (x instanceof FListString) {
		    FListString f2 = (FListString) x;
		    if (FListString.isEmpty(this)) {
				return FListString.isEmpty(f2);
		    }
		    else if (FListString.size(this) == FListString.size(f2)) {
				boolean answer = true;
				int si = (FListString.size(this) - 1);
				for (int i = si; (((i > 0) || (i == 0)) && answer); i--) {
				    String f1elem = FListString.get(this, i);
				    String f2elem = FListString.get(f2, i);
				    answer = f1elem.equals(f2elem);
				}
				return answer;
			}
		    else {
				return false;
		    }
		}
		else {
		    return false;
		}
    }
    
    public int hashCode() {
    	int answer = 0;
    	int si = FListString.size(this) - 1;
		for (int i = si; ((i > 0) || (i == 0)); i--) {
			String current = FListString.get(this, i);
			answer = answer + (i * current.hashCode());
    	}
    	return answer;
	}
}

class EmptyList extends FListString {
    EmptyList() {
    }
    
    boolean isEmptyMethod() {
		return true;
    }
    
    String getMethod(int n) {
		throw new RuntimeException
			("Index error: Tried to access nonexistent elements");
    }
    
    FListString setMethod(int n, String y) {
		throw new RuntimeException
			("List is empty: Cannot set String to a nonexistent index");
    }
    
    int sizeMethod() {
		return 0;
    }
}
class Add extends FListString {
    FListString f;
    String x;
    Add(FListString f, String x) {
	this.f = f;
	this.x = x;
    }
    
    boolean isEmptyMethod() {
		return false;
    }
    
    String getMethod(int n) {
		if (((FListString.size(this) - 1) < n) || (n < 0)) {
	    	throw new RuntimeException
	    		("Index error: Tried to access nonexistent element");
		}
		else if (n == 0) {
	    	return this.x;
		}
		else {
	    	return FListString.get(f, (n - 1));
		}
    }
    
    FListString setMethod(int n, String y) {
		if (((FListString.size(this) - 1) < n) || (n < 0)) {
	    	throw new RuntimeException
	    		("Index error: Tried to access nonexistent element");
		}
		else if (n == 0) {
	    	return FListString.add(this.f, y);
		}
		else {
	    	return FListString.add(FListString.set(this.f, (n - 1), y), this.x);
		}	
    }
    
    int sizeMethod() {
		return 1 + FListString.size(this.f);
    }
}




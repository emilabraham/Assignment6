

public abstract class FListString {
    
	
	static public FListString emptyList() {
		return new EmptyList();
	}
	
    
	
	static public FListString add(FListString list, String s) {
		return new InsertString(list, s);
	}
	
    
	
	static public boolean isEmpty(FListString list) {
		return list.isEmpty();
	}
    
	
	
	
	static public String get(FListString list, int pos) {
		return list.get(pos);
	}
    
	
	
	
	static public FListString set(FListString list, int pos, String s) {
		return list.set(pos, s);
	}
    
	
	
	static public int size(FListString list) {
		return list.size();
	}

    
	
	public int hashCode() {
		return 0;
	}
	
	
	abstract boolean isEmpty();
	abstract String get(int pos);
	abstract FListString set(int pos, String s);
	abstract int size();
	public abstract boolean equals(Object e);
	abstract public String toString();
	abstract String toStringHelper();
}

class EmptyList extends FListString {
	
	
	boolean isEmpty() {
		return true;
	}

	
	
	String get(int pos) {
		throw new IndexOutOfBoundsException("Position requested is larger than list size");
	}

	
	
	FListString set(int pos, String s) {
		throw new IndexOutOfBoundsException("Position requested is larger than list size");
	}

	
	
	int size() {
		return 0;
	}

	
	
	public boolean equals(Object e) {
		return e instanceof EmptyList;
	}

	
	
	public String toString() {
		return "[]";
	}
	
	
	
	
	String toStringHelper() {
		return "";
	}
}

class InsertString extends FListString {
	public final String s;
	public final FListString rest;
	
	public InsertString(FListString rest, String s) {
		this.s = s;
		this.rest = rest;
	}
	
	
	
	boolean isEmpty() {
		return false;
	}

	
	
	
	String get(int pos) {
		if (pos == 0)
			return s;
		else
			return rest.get(pos - 1);
	}

	
	
	
	FListString set(int pos, String s) {
		if (pos == 0)
			return new InsertString(rest, s);
		else
			return new InsertString(rest.set(pos - 1, s), this.s);
	}

	
	
	int size() {
		return 1 + rest.size();
	}

	
	
	public boolean equals(Object e) {
		return e instanceof InsertString 
			&& ((InsertString)e).s.equals(s) 
			&& rest.equals(((InsertString)e).rest);
	}

	
	
	public String toString() {
		if (this.size() == 1) 
			return "[" + s + "]";
		else
			return "[" + s + rest.toStringHelper() + "]";
	}

	
	
	
	String toStringHelper() {
		return ", " + s + rest.toStringHelper();
	}

}

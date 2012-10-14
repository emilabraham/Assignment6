



public abstract class FListString{
	
	
	static FListString emptyList(){
		return new EmptyFListString();
	}
	
	
	static FListString add(FListString f, String s){
		return new AddFListString(f, s);
	}
	
	
	
	
	abstract boolean isEmpty();
	
	
	
	
	abstract String get(int n);
	
	
	
	abstract FListString set(int n, String y);
	
	
	abstract int size();
	
	
	
	
	static boolean isEmpty(FListString s){
		return s.isEmpty();
	}
	
	
	
	static String get(FListString s, int n){
		return s.get(n);
	}
	
	
	
	static FListString set(FListString s, int n, String y){
		return s.set(n, y);
	}
	
	
	static int size(FListString s){
		return s.size();
	}
	
	
	
	public boolean equals(Object x){
		if (x instanceof FListString){
			FListString s = (FListString) x;
			if (this.isEmpty() && s.isEmpty())
				return true;
			else if (this.size() == s.size()){
				boolean val = false;
				for (int i = this.size(); i >= 0; i--){
					if (this.get(i).equals(s.get(i)))
						val = true;
					else
						val = false;
				}
				return val;
			}
			else
				return false;
		}
		else 
			return false;
	}		
	
	
	public int hashcode(){
		return this.size() * 25;
	}
	
}



class EmptyFListString extends FListString{
	
	
	EmptyFListString(){}
	
	
	
	boolean isEmpty(){
		return true;
	}
		
	
	
	
	String get(int n){
		throw new RuntimeException("This FListString is empty!");
	}
		
	
	
	FListString set(int n, String y){
		throw new RuntimeException("This FListString is empty!");
	}
		
	
	int size(){
		return 0;
	}
	
	
	
	public String toString(){
		return "[]";	
	}	
}



class AddFListString extends FListString{
	FListString f;
	String s;
	
	
	
	AddFListString(FListString f, String s){
		this.f = f;
		this.s = s;
	}
	
	
	
	boolean isEmpty(){
		return false;
	}
		
	
	
	
	String get(int n){
		if (n == 0)
			return s;
		else if ( n > 0)
			return FListString.get (f, n - 1);
		else
			throw new RuntimeException("Integer must be greater than or equal to zero");
	}
		
	
	
	FListString set(int n, String y){
		if (n == 0)
			return FListString.add (f, s);
		else if (n > 0)
			return FListString.add (FListString.set (f, n - 1, y), s);
		else
			throw new RuntimeException("Integer must be greater than or equal to zero");
	}
		
	
	int size(){
		return 1 + FListString.size (f);
	}
	
	
	
	public String toString(){
		if (FListString.isEmpty (f))
			return "[" + s.toString() + "]";
		else
			return "[" + s.toString() + ", " 
		+ f.toString().substring(1, f.toString().length());		
	}
	
}



public abstract class FListString{

	

	
	
	public static FListString emptyList(){
		return new EmptyFList();
	}
	
	
	
	public static FListString add(FListString f, String x){
		return new FList(f, x);
	}

  

  
	public static boolean isEmpty(FListString f){
		return f.isEmpty();
	}
	
	
	public static String get(FListString f, int n){
		return f.get(n);
	}
	
	
	public static FListString set(FListString f, int n, String y){
		return f.set(n, y);
	}
	
	
	public static int size(FListString f){
		return f.size();
	}

  

  
  
  abstract boolean isEmpty();

  
  
  abstract String get(int n);

  
  
  
  abstract FListString set(int n, String x);

  
  
  
  abstract int size();

  
  
  public abstract String toString();

  
  
  public abstract boolean equals(Object o);

  
  
  public abstract int hashCode();

}


class EmptyFList extends FListString{

  
	public EmptyFList(){}

	public boolean isEmpty() {
		return true;
	}

	public String get(int n) {
		throw new RuntimeException();
	}

	public FListString set(int n, String y) {
		throw new RuntimeException();
	}

	public int size() {
		return 0;
	}

	public String toString() {
		return "[]";
	}

	
	public boolean equals(Object o) {
		if(o instanceof FListString){
			FListString x = (FListString) o;
			if(x.isEmpty()) return true;
		}else{
			return false;
		}
		return false;
	}

	public int hashCode() {
		return this.toString().hashCode();
	}
}


class FList extends FListString{
	FListString f;
	String x;
	
  
	public FList(FListString f, String x){
		this.f = f;
		this.x = x;
	}

	public boolean isEmpty() {
		return false;
	}

	public String get(int n) {
		if(n > 0){
			return FListString.get(f,n-1);
		}else{
			return this.x;
		}
	}

	public FListString set(int n, String y) {
		if(n >	0){
			return FListString.add(FListString.set(this.f,n-1, y), this.x);
		}else{
			return FListString.add(this.f,y);
		}
	}

	public int size() {
		return 1 + this.f.size();
	}

	public String toString() {
		if(f.isEmpty()){
			return "[" + x + "]";
		}else{
			return "[" + x.toString() + ", "
		       + f.toString().substring(1, f.toString().length());
		}
	}

	public boolean equals(Object o) {
		if(o instanceof FListString){
			FListString x = (FListString) o;
			if (this.size() == x.size()){
				
				for(int i = 0; i < this.size(); i++){
					 if(!(this.get(i).equals(x.get(i)))) return false;
				}
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}	
	}	
	
	public int hashCode() {
		return this.toString().hashCode();
	}
}

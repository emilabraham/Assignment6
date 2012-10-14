



public abstract class FListString {
	
  FListString(){}
	
	
	
	
	public static FListString emptyList(){
		return new Empty();
	}
	
	
	public static boolean isEmpty(FListString list) {
	  return list instanceof Empty;
	}
	
	
	public static FListString add(FListString list, String x) {
		  return new NonEmpty(x, list);
	}
	
	
	public static int size(FListString list){
	  if (list instanceof Empty)
	    return 0;
	  else {
	    NonEmpty NE = (NonEmpty)list;
	    return 1 + FListString.size(NE.rest);
	  }
	}
	
	
	public static String get(FListString list, int index) {
	  if (FListString.isEmpty(list))
	    return "";
	  else if (index == 0) {
	    NonEmpty NE = (NonEmpty)list;
	    return NE.val;
	  }
	  else {
	    NonEmpty NE = (NonEmpty)list;
	    return FListString.get(NE.rest, index - 1);
	  }
	  
	 }
	
	
	 
	 
	 public static FListString set(FListString list, int index, String x) {
	   
	     
	   if (!FListString.isEmpty(list)) {
	     NonEmpty NE = (NonEmpty)list;
	       if (index == 0) {
	         return new NonEmpty(x, NE.rest);
	       }
	       else {
	         return new NonEmpty(NE.val, FListString.set(NE.rest, index - 1, x));
	       }
	     }
	   else
	     throw new RuntimeException("Index out of bounds");
	  }
	
  
  
  public abstract String toString();
  
  
  public abstract String toStringHelp();
  
  
  public abstract boolean equals(Object o);
  
  
  public abstract int hashCode();
}
	
	
	



class Empty extends FListString{

  Empty(){}
	
	
	
	
	public String toString(){
	  return "[" + this.toStringHelp() + "]";
	}
	
	
  public String toStringHelp(){
    return "";
  }
	
  
  public boolean equals(Object o){
    return (o instanceof Empty);
  }
  
  
  public int hashCode(){
    return 0;
  }
}


class NonEmpty extends FListString{
  
  String val; 
  FListString rest; 
 
  
  NonEmpty(String val, FListString rest) {
    this.val = val;
    this.rest = rest;
  }
  
  
  
  
  public String toString(){
    return "[" + this.toStringHelp() + "]";
  }
  
  
  public String toStringHelp(){
    if (FListString.size(this) == 1)
      return this.val;
    else
      return this.val + ", " + this.rest.toStringHelp();
  }
  
  
  public boolean equals(Object o){
    if (o instanceof NonEmpty){
      NonEmpty NE = (NonEmpty)o;
      
      if (this.toString().equals(NE.toString())) 
        return true;
      else 
        return false;
    }
      else
        return false;
      }
  
  
  public int hashCode() {
    return FListString.size(this) * this.val.length() + this.rest.hashCode();
  }

}
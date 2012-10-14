

public abstract class FListString{
    
    static FListString emptyList(){
        return new Empty();
    }
    
    static FListString add(FListString f, String x){
        return new ListString(x,f);
    }
    
    static boolean isEmpty(FListString f){
        return f.isEmptyMethod();
    }
    
    abstract boolean isEmptyMethod();
    
    static String get(FListString f,int n){
        return f.getMethod(n);
    }
    
    abstract String getMethod(int n);
    
    
    static FListString set(FListString f,int n, String x){
        return f.setMethod(n,x);
    }
    
    
    abstract FListString setMethod(int n, String x);
    
    static int size(FListString f){
        return f.sizeMethod();
    }
    
    abstract int sizeMethod();
    
    public String toString(){
        return toStringMethod();
    }
    
    abstract String toStringMethod();
    
    public boolean equals(Object o){
        if(o instanceof FListString){
            FListString x= (FListString) o;
            if (FListString.isEmpty(this)){
                return FListString.isEmpty(x);
            }
            if(FListString.size(this)==FListString.size(x)){
                for(int i=0;i<FListString.size(this);i++){
                    if(FListString.get(this,i).toString().equals(
                       FListString.get(x,i).toString())){
                        }
                    else{
                            return false;
                        }
                }
                return true;
            }
           	return false;
        }
        return false;
    }
    
    public int hashCode(){
        if(FListString.isEmpty(this)){
            return 0;
        }
        int temp=0;
        for(int i=0;i<FListString.size(this);i++){
            if(i%2==0){
                temp+=FListString.get(this,i).hashCode();
            }
            else{
                temp-=FListString.get(this,i).hashCode();
            }
        }
        return temp;
    }
}

class Empty extends FListString{
    
    boolean isEmptyMethod(){
        return true;
    }
    
    String getMethod(int n){
        throw new RuntimeException("Cannot call get on empty Flist.");
    }
    
    FListString setMethod(int n, String x){
        throw new RuntimeException("Cannot call set on empty Flist.");
    }
    
    int sizeMethod(){
        return 0;
    }
    
    String toStringMethod(){
        return "[]" ;
    }
}

class ListString extends FListString{
    String curr; 
    FListString prev; 
    
    ListString(String a,FListString b){
        curr=a;
        prev=b;
    }
    
    boolean isEmptyMethod(){
        return false;
    }
    
    String getMethod(int n){
        if(n==0){
            return curr;
        }
        return FListString.get(prev, n-1);
    }
    
    
    FListString setMethod(int n,String x){
        if(n==0){
            return new ListString(x,prev);
        }
        return new ListString(curr,FListString.set(prev, n-1, x));
    }
    
    int sizeMethod(){
        return 1+FListString.size(prev);
    }
    
    String toStringMethod(){
        if(FListString.isEmpty(prev)){
            return "[" + curr.toString() + "]";
        }
        return "[" + 
               curr.toString() + 
               ", " + 
               prev.toString().substring(1, prev.toString().length());
    }
}
		
           

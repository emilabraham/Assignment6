



public abstract class FListString{
    
    
    abstract int sizeMethod();
    abstract boolean isEmptyMethod();
    abstract String getMethod(int n);
    abstract FListString setMethod(int n, String x);
    
    
    public static FListString emptyList() {
        return new Empty();
    }

    
    public static FListString add(FListString f, String x) {
        
        return new Add(f, x);
    }

    
    public static String get(FListString f, int n) {
        
        return f.getMethod(n);
    }

    
    public static FListString set(FListString f, int n, String x){
        return f.setMethod(n, x);
    }

    
    public static int size(FListString f){
        return f.sizeMethod();  
    }
    
    
    public static boolean isEmpty(FListString f){
        return f.isEmptyMethod();
    }

}

class Empty extends FListString{
    
    Empty () { }
    
    
    String getMethod(int n){
        throw new RuntimeException("An empty FListString can't get anything");
    }
    
    
    FListString setMethod(int n, String s){
        throw new RuntimeException("An empty FListString can't set anything");
    }
    
    
    int sizeMethod() {
        return 0;
    }
    
    
    boolean isEmptyMethod() {
        return true;
    }

    
    public String toString(){
        return "[]";
    }
  
    
    public boolean equals(Object obj){
        if (! (obj instanceof FListString))
            return false;
        
        FListString f = (FListString)obj;
        return f.isEmptyMethod();
    }
  
    
    public int hashCode(){
        return this.sizeMethod();
    }
}

class Add extends FListString{
    
    private FListString f;   
    private String x;        
    
    Add (FListString f, String x) {
        this.f = f;
        this.x = x;
    }
    
    
    String getMethod(int n){
        if (n == 0){
            return this.x;
        }
        else{
            return this.f.getMethod(n-1);
        }
    }
    
    
    FListString setMethod(int n, String new_x){
        if (n == 0){
            return new Add(f, new_x);
        }
        else{
            return new Add(f.setMethod(n-1, new_x), x);
        }
    }
    
    
    int sizeMethod() {
            return 1 + f.sizeMethod();
    }
    
    
    boolean isEmptyMethod() {
        return false;
    }

    
    public String toString(){
        if (f.isEmptyMethod()){
            return "[" + x.toString() + "]";
        }
        else{
            return "[" + x.toString() + ", " + f.toString().substring(1, f.toString().length());
        }
    }  
    
    
    public boolean equals(Object obj){
        
        if (! (obj instanceof FListString))
            return false;
        
        
        FListString f2 = (FListString)obj;
        
        
        if (this.sizeMethod() != f2.sizeMethod())
            return false;
        else{
            for (int i = 0; i < f.sizeMethod(); i++){
                if (! this.getMethod(i).equals(f2.getMethod(i)))
                    return false;
            }
            return true;
        }
    }
  
    
    public int hashCode(){
        int n = this.sizeMethod();
        for (int i = 0; i < this.sizeMethod(); i++){
            n += this.getMethod(i).hashCode();
        }
        return n;
    }

}

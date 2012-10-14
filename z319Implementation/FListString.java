


public abstract class FListString {
        
        FListString () {}

        abstract boolean isEmpty();
        abstract String Get(int i);
        abstract FListString Set(int i, String s);
        abstract int Size();
        

static FListString emptyList(){
        return new emptyList();
}
static boolean isEmpty(FListString FLS){
        return FLS.isEmpty();
}
static String Get(FListString FLS, int i){
        return FLS.Get(i);
}
static FListString Set(FListString FLS, int i, String s){
        return FLS.Set(i, s);
}
static int Size(FListString FLS){
        return FLS.Size();
   } 
static FListString Add(FListString fls, String s){
return new Add (fls, s);
}




public boolean equals(Object e){
        
        if (e instanceof FListString){
                FListString o = (FListString) e;
                if (this.Size() == o.Size()){
                        
                int u = 0;
                for(int i = 0; i < this.Size() ; i++)
                        if (this.Get(i).equals(o.Get(i))){ 
                                u++;
                                }
                return this.Size() == u;
                }
                else{ return false;
                }
        }
        return false;
}



public int hashCode(){
        String str = new String(this.toString());
        int o = 0; 
        for( int i = 0; i < str.length(); i++ ) {  
          o += (int) str.charAt(i);  
                                     
        }
        return o;
}
}


class emptyList extends FListString{
        FListString Add(String s) {
                return this;
        }

        boolean isEmpty() {
                return true;
        }

        String Get(int i) {
                return "Not found in list";
        }

        FListString Set(int i, String s) {
                return this;
        }

        int Size() {
                return 0;
        }

        public String toString(){
                return "[]";
        }
}



class Add extends FListString{
        FListString fls;
        String s;
        Add (FListString fls, String s){
                this.fls = fls;
                this.s = s;
        }

        boolean isEmpty() {
                return false;
        }

        String Get(int i) {
                if (this.Size() == i)
                {return s;
                }else {
                        return fls.Get(i);
                }
        }

        FListString Set(int i, String s) {
                if (i == 0){
                        return FListString.Add(fls, s);
                        }
                else{
                        return FListString.Add(fls.Set((i - 1), s), s);
                        } 
                }

        int Size() {
                return 1 + fls.Size();
        }
        
        public String toString(){
                if (this.isEmpty()){
                        return "[]";}
                if (this.fls.isEmpty())
                        {return "[" + s.toString() + "]";}
                else {
                        return "[" + s.toString() + ", "
                     + fls.toString().substring(1, fls.toString().length());
                }
                }
        

        
}

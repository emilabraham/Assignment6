public abstract class FListString {
    
    abstract FListString add(String str);
    
    abstract boolean isEmpty();

    abstract String get(int i);

    abstract FListString set(int i, String str);

    abstract int size();

    abstract String getContents();

    public static FListString emptyList() {
        return new EmptyList();
    }

    public boolean elementsAgree(FListString f2) {
        FListString f1 = this;
        boolean agree = true;

        for (int i = 0; i < f1.size(); i++) {
            agree = agree && f1.get(i).equals(f2.get(i));
        }
        return agree;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FListString) {
            FListString f1 = this;
            FListString f2 = (FListString) obj;

            return f1.isEmpty() && f2.isEmpty()
		|| f1.size() == f2.size()
		&& f1.elementsAgree(f2);

        } else {
            return false;
        }
    }

    @Override
	public String toString() {
        String build = "[" + this.getContents() + "]";

        return build;
    }
}

class EmptyList extends FListString {

    public FListString add(String str) {
        return new AddList(str, this);
    }

    public boolean isEmpty() {
        return true;
    }

    public String get(int i) {
        throw new RuntimeException("Should not get from the EmptyList");
    }

    public FListString set(int i, String str) {
        throw new RuntimeException("Should not set in an EmptyList");
    }

    public int size() {
        return 0;
    }

    public String getContents() {
        return "";
    }

    @Override
	public int hashCode() {
        return 246810;
    }
}

class AddList extends FListString {

    private String x;
    private FListString next;

    public AddList(String x, FListString next) {
        this.x = x;
        this.next = next;
    }

    public FListString add(String str) {
        return new AddList(str, this);
    }

    public boolean isEmpty() {
        return false;
    }

    public String get(int i) {
        if (i == this.next.size()) {
            return this.x;
        } else if (i < this.next.size()) {
            return this.next.get(i);
        } else {
            throw new RuntimeException("Index out of bounds: get.");
        }
    }

    public FListString set(int i, String str) {
        if (i == this.next.size()) {
            return this.next.add(str);
        } else if (i < this.next.size()) {
            return this.next.set(i, str).add(this.x);
        } else {
            throw new RuntimeException("Index out of bounds: set.");
        }
    }

    public int size() {
        return 1 + this.next.size();
    }

    String getContents() {
        String build = "";

        if (!this.next.isEmpty()) {
            build += this.next.getContents() + ", ";
        }

        build += this.x.toString();

        return build;
    }

    @Override
	public int hashCode() {
        return this.size() * this.x.hashCode()
	    + this.next.hashCode();
    }
}
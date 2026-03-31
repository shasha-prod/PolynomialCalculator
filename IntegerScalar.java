public class IntegerScalar implements Scalar{
    private int number;

    public IntegerScalar(int number){
        this.number = number;
    }
    public int reduce(){
        return number;
    }
    public Scalar add(Scalar s){
        this.number = number + s.reduce();
        return this.number;
    }
    public Scalar mul(Scalar s){
        this.number = number * s.reduce();
        return this.number;
    }
    public Scalar neg(){
        this.number = this.number * -1;
        return this.number;
    }
    public Scalar power(int exponent){
        if(exponent = 0){
            this.number = 1;
        }
        if(exponent =1){
            this.number;
        }
        for(int i=2; i = exponent; i++){
            this.number = number * number;
        }
        return this.number;
    }
    public int sign(){
        if (this.number<0){
            return -1;
        }        
        if (this.number>0){
            return 1;
        }
        return 0;
    }
    public boolean equals(Object o){
        if(o instanceOf Scalar){
            if (s.reduce == (Scalar)o.reduce){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        return "" + number;
    }
}
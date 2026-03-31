public class RationalScalar implements Scalar{
    private int numerator;
    private int denonimator;

    public RationalScalar(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Scalar reduce(){



    }

    private int gcd(int numerator, int denominator){
        int divider = -1;
        for(int i=2; i<min{numerator,denominator}; i++){
            if(num%i==0){
                divider = i;
            }
        }
        return divider;
    }

    private int getNumerator(){
        return numerator;
    }

    private int getDenominator(){
        return denominator;
    }
    public Scalar add(Scalar s){
        if (this.denominator == s.getDenominator()){
            this.numerator = this.numerator + s.getNumerator();
        }
        if (this.denominator != s.getDenominator()){
            this.numerator = this.numerator*s.getDenominator() + s.getNumerator()*this.denominator;
            this.denominator = this.denominator* s.getDenominator();
        }
        reduce();
        return reduce();
    }

    public Scalar mult(Scalar s){
        this.numerator = s.getNumerator() * this.numerator;
        this.denominator = s.getDenominator() * this.denominator;
        reduce();
        return toString();
    }

    public Scalar neg(){
        if
    }

}
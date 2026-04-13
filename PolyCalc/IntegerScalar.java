package PolyCalc;

public class IntegerScalar extends Scalar {
    private int number;

    public IntegerScalar(int number) {
        this.number = number;
    }

    public int getNumerator() {
        return number;
    }
    public int getDenominator() {
        return 1;
    }

    public Scalar add(Scalar s) {
        return new RationalScalar(this.number, 1).add(s);
    }

    public Scalar mul(Scalar s) {
        return new RationalScalar(this.number, 1).mul(s);
    }

    public Scalar neg() {
        return new IntegerScalar(this.number * -1);
    }

    public Scalar power(int exponent) {
        int newNumber = this.number;
        if (exponent == 0) {
            newNumber = 1;
        }
        for (int i = 2; i <= exponent; i++) {
            newNumber = newNumber * number;
        }
        return new IntegerScalar(newNumber);
    }

    public int sign() {
        if (this.number < 0) {
            return -1;
        }
        if (this.number > 0) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object o) {
        if (o instanceof IntegerScalar) {
            Scalar other = (IntegerScalar) o;
            if (this.number == other.getNumerator()) {
                return true;
            }
            return false;
        }
        if (o instanceof RationalScalar) {
            RationalScalar other = (RationalScalar) o;
            return other.equals(new RationalScalar(this.number,1));
        }
        return false;
    }

    public String toString() {
        return "" + number;
    }
}
package PolyCalc;

public class RationalScalar extends Scalar {
    private int numerator;
    private int denominator;

    public RationalScalar(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    public int[] getNumber() {
        return new int[]{numerator, denominator};
    }

    public Scalar reduce() {
        checkZero();
        negOrganizer();
        simplify();
        if (denominator == 1) {
            IntegerScalar fullNumber = new IntegerScalar(numerator);
            return fullNumber;
        }
        return this;
    }

    private void negOrganizer() {
        if (denominator < 0) {
            this.numerator = -1 * numerator;
            this.denominator = -1 * denominator;
        }
    }

    private void simplify() {
        int min = Math.min(Math.abs(numerator), Math.abs(denominator));
        int index = 2;
        while (index <= min) {
            if (this.numerator % index == 0 && this.denominator % index == 0) {
                this.numerator = this.numerator / index;
                this.denominator = this.denominator / index;
            } else {
                index++;
            }
        }
    }

    public Scalar checkZero() {
        if (numerator == 0) {
            this.denominator = 1;
        }
        return this;
    }


    public Scalar add(Scalar s) {
        RationalScalar temp;
        if (this.denominator == s.getNumber()[1]) {
            temp = new RationalScalar(this.numerator + s.getNumber()[0], this.denominator);
        } else {
            temp = new RationalScalar(this.numerator * s.getNumber()[1] + s.getNumber()[0] * this.denominator, this.denominator * s.getNumber()[1]);
        }
        return temp;
    }

    public Scalar mul(Scalar s) {
        RationalScalar temp = new RationalScalar(s.getNumber()[0] * this.numerator, s.getNumber()[1] * this.denominator);
        return temp;
    }

    public Scalar neg() {
        return new RationalScalar(this.numerator * -1,this.denominator);
    }

    public int sign() {
        if (this.numerator > 0) return 1;
        else if (this.numerator < 0) return -1;
        else return 0;
    }

    public Scalar power(int exponent) {
        int newNumerator = this.numerator;
        int newDenominator = this.denominator;
        if (exponent == 0) {
            newNumerator = 1;
            newDenominator = 1;
        }
        for (int i = 2; i <= exponent; i++) {
            newNumerator = newNumerator * numerator;
            newDenominator = newDenominator * denominator;
        }
        return new RationalScalar(newNumerator,newDenominator).reduce();
    }

    public boolean equals(Object o) {
        if (o instanceof Scalar) {
            Scalar other = (Scalar) o;
            if (numerator == other.getNumber()[0] && denominator == other.getNumber()[1]) {
                return true;
            }
            return false;
        }
        return false;
    }

    public String toString() {
        if (denominator == 1 || denominator == 0) {
            return "" + this.numerator;
        }
        return "" + this.numerator + "/" + this.denominator;
    }
}

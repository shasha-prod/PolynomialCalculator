package PolyCalc;

public class RationalScalar extends Scalar {
    private int numerator;
    private int denominator;

    public RationalScalar(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Scalar reduce() {
        checkZero();
        negOrganizer();
        simplify();
        if (denominator == 1) {
            return new IntegerScalar(numerator);
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

    @Override
    public double getDouble() {
        return (double) numerator / denominator;
    }

    // double dispatch

    @Override
    public Scalar add(Scalar s) {
        return s.addRational(this);
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mulRational(this);
    }

    // combos

    @Override
    public Scalar addInteger(IntegerScalar s) {
        int newNumerator = this.numerator + (s.getNumber() * this.denominator);
        return new RationalScalar(newNumerator, this.denominator).reduce();
    }

    @Override
    public Scalar addRational(RationalScalar s) {
        int newNumerator = (this.numerator * s.getDenominator()) + (s.getNumerator() * this.denominator);
        int newDenominator = this.denominator * s.getDenominator();
        return new RationalScalar(newNumerator, newDenominator).reduce();
    }

    @Override
    public Scalar addReal(RealScalar s) {
        return s.addRational(this);
    }

    @Override
    public Scalar mulInteger(IntegerScalar s) {
        return new RationalScalar(this.numerator * s.getNumber(), this.denominator).reduce();
    }

    @Override
    public Scalar mulRational(RationalScalar s) {
        return new RationalScalar(this.numerator * s.getNumerator(), this.denominator * s.getDenominator()).reduce();
    }

    @Override
    public Scalar mulReal(RealScalar s) {
        return s.mulRational(this);
    }

    @Override
    public Scalar neg() {
        return new RationalScalar(this.numerator * -1, this.denominator).reduce();
    }

    @Override
    public int sign() {
        if (this.numerator > 0) return 1;
        else if (this.numerator < 0) return -1;
        else return 0;
    }

    @Override
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
        return new RationalScalar(newNumerator, newDenominator).reduce();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Scalar) {
            RealScalar other = new RealScalar(((Scalar) o).getDouble());
            return other.equals(new RealScalar(this.getDouble()));
        }
        return false;
    }

    @Override
    public String toString() {
        if (denominator == 1 || denominator == 0) {
            return "" + this.numerator;
        }
        return "" + this.numerator + "/" + this.denominator;
    }
}
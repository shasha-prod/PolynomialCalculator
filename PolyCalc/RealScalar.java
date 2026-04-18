package PolyCalc;

public class RealScalar extends Scalar {
    private double number;

    public RealScalar(double number) {
        this.number = number;
    }

    @Override
    public int getNumerator() {
        return getNumerator(this.number);
    }

    private int getNumerator(double doubleNumber) {
        int temp = (int) doubleNumber;
        if (doubleNumber == temp) {
            return temp;
        } else {
            getNumerator(doubleNumber * 10);
        }
        return 0;
    }

    @Override
    public int getDenominator() {
        return getDenominator(this.number, 10);
    }

    private int getDenominator(double doubleNumber, int counter) {
        int temp = (int) doubleNumber;
        if (doubleNumber == temp) {
            return counter;
        } else {
            counter = counter * 10;
            getDenominator(doubleNumber * 10, counter);
        }
        return 0;
    }

    @Override
    Scalar add(Scalar s) {
        return new RationalScalar(this.getNumerator(), this.getDenominator()).add(s);
    }

    @Override
    Scalar mul(Scalar s) {
        return new RationalScalar(this.getNumerator(), this.getDenominator()).mul(s);
    }

    @Override
    Scalar neg() {
        return new RealScalar(this.number * -1);
    }

    @Override
    Scalar power(int exponent) {
        return new RationalScalar(this.getNumerator(), this.getDenominator()).power(exponent);
    }

    @Override
    int sign() {
        if (this.number < 0) {
            return -1;
        }
        if (this.number > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Scalar) {
            RationalScalar other = new RationalScalar(((Scalar) o).getNumerator(), ((Scalar) o).getDenominator());
            return other.equals(new RationalScalar(this.getNumerator(), this.getDenominator()));
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + this.number;
    }
}


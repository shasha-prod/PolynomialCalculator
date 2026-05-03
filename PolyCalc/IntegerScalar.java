package PolyCalc;

public class IntegerScalar extends Scalar {
    private int number;

    public IntegerScalar(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public double getDouble() {
        return number;
    }

    // double dispatch

    @Override
    public Scalar add(Scalar s) {
        return s.addInteger(this);
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mulInteger(this);
    }

    // combos

    @Override
    public Scalar addInteger(IntegerScalar s) {
        return new IntegerScalar(this.number + s.getNumber());
    }

    @Override
    public Scalar addRational(RationalScalar s) {
        return s.addInteger(this);
    }

    @Override
    public Scalar addReal(RealScalar s) {
        return s.addInteger(this);
    }

    @Override
    public Scalar mulInteger(IntegerScalar s) {
        return new IntegerScalar(this.number * s.getNumber());
    }

    @Override
    public Scalar mulRational(RationalScalar s) {
        return s.mulInteger(this);
    }

    @Override
    public Scalar mulReal(RealScalar s) {
        return s.mulInteger(this);
    }

    @Override
    public Scalar neg() {
        return new IntegerScalar(this.number * -1);
    }

    @Override
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

    @Override
    public int sign() {
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
            RealScalar other = new RealScalar(((Scalar) o).getDouble());
            return other.equals(new RealScalar(this.getDouble()));
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
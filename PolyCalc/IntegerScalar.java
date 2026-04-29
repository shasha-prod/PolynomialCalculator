package PolyCalc;

public class IntegerScalar extends Scalar {
    private int number;

    public IntegerScalar(int number) {
        this.number = number;
    }

    @Override
    public double getDouble() {
        return number;
    }

    public Scalar add(Scalar s) {
        return new RealScalar(this.number).add(s);
    }

    public Scalar mul(Scalar s) {
        return new RealScalar(this.number).mul(s);
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
        if (o instanceof Scalar) {
            RealScalar other = new RealScalar(((Scalar) o).getDouble());
            return other.equals(new RealScalar(this.getDouble()));
        }
        return false;
    }

    public String toString() {
        return "" + number;
    }
}
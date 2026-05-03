package PolyCalc;

public class RealScalar extends Scalar {
    private double number;

    public RealScalar(double number) {
        this.number = round(number);
    }

    private double round(double number) {
        return Math.round(number * 1000000.0)/1000000.0 ;
    }

    @Override
    public double getDouble() {
        return number;
    }

    // double dispatch

    @Override
    public Scalar add(Scalar s) {
        return s.addReal(this);
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mulReal(this);
    }

    // combos

    @Override
    public Scalar addInteger(IntegerScalar s) {
        return new RealScalar(this.number + s.getNumber());
    }

    @Override
    public Scalar addRational(RationalScalar s) {
        return new RealScalar(this.number + s.getDouble());
    }

    @Override
    public Scalar addReal(RealScalar s) {
        return new RealScalar(this.number + s.getDouble());
    }

    @Override
    public Scalar mulInteger(IntegerScalar s) {
        return new RealScalar(this.number * s.getDouble());
    }

    @Override
    public Scalar mulRational(RationalScalar s) {
        return new RealScalar(this.number * s.getDouble());
    }

    @Override
    public Scalar mulReal(RealScalar s) {
        return new RealScalar(this.number * s.getDouble());
    }

    @Override
    public Scalar neg() {
        return new RealScalar(this.number * -1);
    }

    @Override
    public Scalar power(int exponent) {
        return new RealScalar(Math.pow(this.number, exponent));
    }

    @Override
    public int sign() {
        if (this.number < 0) return -1;
        if (this.number > 0) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Scalar) {
            double diff = Math.abs(this.number - ((Scalar) o).getDouble());
            return diff < 0.000001;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
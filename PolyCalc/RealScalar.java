package PolyCalc;


public class RealScalar extends Scalar {
    public static final double EPSILON = 1e-6;
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

    @Override
    Scalar add(Scalar s) {
        return new RealScalar(this.number + s.getDouble());
    }

    @Override
    Scalar mul(Scalar s) {
        return new RealScalar(this.number * s.getDouble());
    }

    @Override
    Scalar neg() {
        return new RealScalar(this.number * -1);
    }

    @Override
    Scalar power(int exponent) {
        if(exponent == 0){
            return new RealScalar(1);
        }
        double temp = this.number;
        for (int i = 1; i < exponent; i++) {
            temp = temp * this.number;
        }
        return new RealScalar(temp);
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
        if (o instanceof RealScalar) {
            RealScalar other = (RealScalar) o;
            if (this.number == other.number) {
                return true;
            }
            return false;
        }
        if (o instanceof Scalar) {
            RealScalar other = new RealScalar(((Scalar) o).getDouble());
            return other.equals(this);
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + this.number;
    }
}


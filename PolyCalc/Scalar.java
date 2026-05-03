package PolyCalc;

abstract class Scalar {
//    //getting the numerator
//    public abstract int getNumerator();
//
//    //getting the denominator
//    public abstract int getDenominator();

    //changing numbers into doubles
    public abstract double getDouble();
    //Returns the addition between two scalars
    abstract Scalar add(Scalar s);

    //Returns the multiplication between two scalars
    abstract Scalar mul(Scalar s);

    //Returns the negative of a scalar
    abstract Scalar neg();

    //Returns the power to the exponent of a scalar if exponent>0
    abstract Scalar power(int exponent);

    //Returns the sign of the scalar: -1 if negative, 1 if positive, else 0.
    abstract int sign();

    //override
    public abstract boolean equals(Object o);

    //override
    public abstract String toString();

    // signatures for double dispatch
    public abstract Scalar addInteger(IntegerScalar s);
    public abstract Scalar addRational(RationalScalar s);
    public abstract Scalar addReal(RealScalar s);

    public abstract Scalar mulInteger(IntegerScalar s);
    public abstract Scalar mulRational(RationalScalar s);
    public abstract Scalar mulReal(RealScalar s);

}

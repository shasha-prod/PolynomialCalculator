package PolyCalc;

abstract class Scalar {
    //getting the numerator
    public abstract int getNumerator();

    //getting the denominator
    public abstract int getDenominator();

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

}

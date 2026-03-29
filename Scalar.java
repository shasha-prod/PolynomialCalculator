public interface Scalar {
    //Returns the addition between two scalars
    public Scalar add(Scalar s)

    //Returns the multiplication between two scalars
    public Scalar mul(Scalar s)

    //Returns the negative of a scalar
    public Scalar neg()

    //Returns the power to the exponent of a scalar if exponent>0
    public Scalar power(int exponent)

    //Returns the sign of the scalar: -1 if negative, 1 if positive, else 0.
    public int sign()

    //override
    public boolean equals(Object o)

    //override
    public String toString()

}

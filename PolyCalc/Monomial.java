package PolyCalc;

public class Monomial {
    private int exponent;
    private Scalar coefficient;

    public Monomial(int exponent, Scalar coefficient) {
        this.exponent = exponent;
        this.coefficient = coefficient;
    }

    public Monomial add(Monomial m) {
        if (this.exponent != m.exponent) {
            return null;
        }
        Scalar newCoefficient = this.coefficient.add(m.coefficient);
        return new Monomial(this.exponent, newCoefficient);
    }

    public Monomial mul(Monomial m) {
        int newExponent = this.exponent + m.exponent;
        Scalar newCoefficient = this.coefficient.mul(m.coefficient);
        return new Monomial(newExponent, newCoefficient);
    }

    public Scalar evaluate(Scalar s) {
        Scalar sToThePower = s.power(this.exponent);
        return this.coefficient.mul(sToThePower);
    }

    public Monomial derivative() {
        if (this.exponent == 0) {
            return new Monomial(0, new IntegerScalar(0));
        }
        Scalar exponentAsScalar = new IntegerScalar(this.exponent);
        Scalar newCoefficient = this.coefficient.mul(exponentAsScalar);
        int newExponent = this.exponent - 1;
        return new Monomial(newExponent, newCoefficient);
    }

    public int sign() {
        return this.coefficient.sign();
    }

    public boolean equals(Object o) {
        if (o instanceof Monomial) {
            Monomial other = (Monomial) o;
            return this.exponent == other.exponent && this.coefficient.equals(other.coefficient);
        }
        return false;
    }

    public String toString() {
        if (this.coefficient.sign() == 0) {
            return "0";
        }

        if (this.exponent == 0) {
            return this.coefficient.toString();
        }

        String result = "";

        if (this.coefficient.equals(new IntegerScalar(-1))) {
            result += "-";
        } else if (!this.coefficient.equals(new IntegerScalar(1))) {
            result += this.coefficient.toString(); // Only add the number if it's NOT 1
        }

        if (this.exponent == 1) {
            result += "x";
        } else {
            result += "x^" + this.exponent;
        }

        return result;
    }


    public int getExponent() {
        return this.exponent;
    }

    public Scalar getCoefficient() {
        return this.coefficient;
    }
}
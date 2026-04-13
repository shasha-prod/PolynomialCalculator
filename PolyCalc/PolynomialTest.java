package PolyCalc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    void polynomial_build_allInteger() {
        // "1 2 3" -> 1 + 2x + 3x^2
        Polynomial p = Polynomial.build("1 2 3");
        Polynomial expected = new Polynomial();
        expected.getMonomials().add(new Monomial(0, new IntegerScalar(1)));
        expected.getMonomials().add(new Monomial(1, new IntegerScalar(2)));
        expected.getMonomials().add(new Monomial(2, new IntegerScalar(3)));
        assertEquals(expected, p);
    }

    @Test
    void polynomial_build_leadingZero() {
        // "0 1 2 3" -> x + 2x^2 + 3x^3 (zero-coeff monomial at index 0)
        Polynomial p = Polynomial.build("0 1 2 3");
        // Evaluate at x=1: 0+1+2+3=6
        assertEquals(new IntegerScalar(6), p.evaluate(new IntegerScalar(1)));
    }

    @Test
    void polynomial_build_withRationals() {
        // "0 1/2 3 -5/3" -> 0 + (1/2)x + 3x^2 + (-5/3)x^3
        Polynomial p = Polynomial.build("0 1/2 3 -5/3");
        assertNotNull(p);
        // Spot-check: evaluate at x=0 -> 0
        assertEquals(new IntegerScalar(0), p.evaluate(new IntegerScalar(0)));
    }

    @Test
    void polynomial_add_basic() {
        // (1 + 2x) + (3 + 4x) = 4 + 6x
        Polynomial a = Polynomial.build("1 2");
        Polynomial b = Polynomial.build("3 4");
        Polynomial result = a.add(b);
        assertEquals(Polynomial.build("4 6"), result);
    }

    @Test
    void polynomial_add_differentDegrees() {
        // (1 + 2x) + (3x^2) = 1 + 2x + 3x^2
        Polynomial a = Polynomial.build("1 2");
        Polynomial b = Polynomial.build("0 0 3");
        Polynomial result = a.add(b);
        assertEquals(Polynomial.build("1 2 3"), result);
    }

    @Test
    void polynomial_add_cancellingTerms() {
        // (5 + 3x) + (-5 + -3x) = 0
        Polynomial a = Polynomial.build("5 3");
        Polynomial b = Polynomial.build("-5 -3");
        Polynomial result = a.add(b);
        // All coefficients are zero
        assertEquals("0", result.toString());
    }

    @Test
    void polynomial_mul_basic() {
        // (1 + x) * (1 + x) = 1 + 2x + x^2
        Polynomial a = Polynomial.build("1 1");
        Polynomial b = Polynomial.build("1 1");
        Polynomial result = a.mul(b);
        assertEquals(Polynomial.build("1 2 1"), result);
    }

    @Test
    void polynomial_mul_byZero() {
        Polynomial a = Polynomial.build("1 2 3");
        Polynomial b = Polynomial.build("0");
        Polynomial result = a.mul(b);
        assertEquals("0", result.toString());
    }

    @Test
    void polynomial_evaluate_atZero() {
        // 5 + 3x + 2x^2 at x=0 -> 5
        Polynomial p = Polynomial.build("5 3 2");
        assertEquals(new IntegerScalar(5), p.evaluate(new IntegerScalar(0)));
    }

    @Test
    void polynomial_evaluate_atOne() {
        // 1 + 2 + 3 = 6
        Polynomial p = Polynomial.build("1 2 3");
        assertEquals(new IntegerScalar(6), p.evaluate(new IntegerScalar(1)));
    }

    @Test
    void polynomial_evaluate_rationalInput() {
        // 2x at x=1/2 -> 1
        Polynomial p = Polynomial.build("0 2");
        assertEquals(new IntegerScalar(1), p.evaluate(new RationalScalar(1, 2)));
    }

    @Test
    void polynomial_derivative_basic() {
        // d/dx(1 + 2x + 3x^2) = 2 + 6x
        Polynomial p = Polynomial.build("1 2 3");
        Polynomial dp = p.derivative();
        // Evaluate at x=1: 2 + 6 = 8
        assertEquals(new IntegerScalar(8), dp.evaluate(new IntegerScalar(1)));
    }

    @Test
    void polynomial_derivative_constant() {
        // d/dx(5) = 0
        Polynomial p = Polynomial.build("5");
        Polynomial dp = p.derivative();
        assertEquals("0", dp.toString());
    }

    @Test
    void polynomial_equals_samePolynomials() {
        Polynomial a = Polynomial.build("1 2 3");
        Polynomial b = Polynomial.build("1 2 3");
        assertEquals(a, b);
    }

    @Test
    void polynomial_equals_differentPolynomials() {
        Polynomial a = Polynomial.build("1 2 3");
        Polynomial b = Polynomial.build("1 2 4");
        assertNotEquals(a, b);
    }

    @Test
    void polynomial_toString_basic() {
        Polynomial p = Polynomial.build("1 2 3");
        assertEquals("1 + 2x + 3x^2", p.toString());
    }

    @Test
    void polynomial_toString_negativeMiddleTerm() {
        // "1 -2 3" -> "1 -2x + 3x^2"
        Polynomial p = Polynomial.build("1 -2 3");
        assertEquals("1 -2x + 3x^2", p.toString());
    }

    @Test
    void polynomial_toString_empty() {
        Polynomial p = new Polynomial();
        assertEquals("0", p.toString());
    }
    public static void main(String[] args) {
        System.out.println("Hello, welcome to the Polynomial Calculator! We will now calculate:");
        Polynomial a = Polynomial.build("1 2 3");
        System.out.print(a.toString());
        System.out.print(" + ");
        Polynomial b = Polynomial.build("1 0 3");
        System.out.print(b.toString() + " = ");
        System.out.println(a.add(b));
        System.out.println("This happens because:");
        Scalar c = new IntegerScalar(1);
        Scalar d = new IntegerScalar(1);
        System.out.print(c.toString() + " + " + d.toString() + " = ");
        System.out.println(c.add(d));
        Monomial e = new Monomial(1,new IntegerScalar(2));
        Monomial f = new Monomial(1,new IntegerScalar(0));
        System.out.print(e.toString() + " + " + f.toString() + " = ");
        System.out.println(e.add(f));
        Monomial g = new Monomial(2,new IntegerScalar(3));
        Monomial h = new Monomial(2,new IntegerScalar(3));
        System.out.print(g.toString() + " + " + h.toString() + " = ");
        System.out.println(g.add(h));

    }
}


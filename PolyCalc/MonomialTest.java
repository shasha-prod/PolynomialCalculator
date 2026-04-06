package PolyCalc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MonomialTest {

    @Test
    void monomial_add_sameExponent() {
        Monomial a = new Monomial(2, new IntegerScalar(3));
        Monomial b = new Monomial(2, new IntegerScalar(4));
        Monomial result = a.add(b);
        assertNotNull(result);
        assertEquals(new Monomial(2, new IntegerScalar(7)), result);
    }

    @Test
    void monomial_add_differentExponent_returnsNull() {
        Monomial a = new Monomial(2, new IntegerScalar(3));
        Monomial b = new Monomial(3, new IntegerScalar(4));
        assertNull(a.add(b));
    }

    @Test
    void monomial_mul_basic() {
        // 3x^2 * 4x^3 = 12x^5
        Monomial a = new Monomial(2, new IntegerScalar(3));
        Monomial b = new Monomial(3, new IntegerScalar(4));
        assertEquals(new Monomial(5, new IntegerScalar(12)), a.mul(b));
    }

    @Test
    void monomial_mul_exponentZero() {
        // 5x^0 * 3x^2 = 15x^2
        Monomial a = new Monomial(0, new IntegerScalar(5));
        Monomial b = new Monomial(2, new IntegerScalar(3));
        assertEquals(new Monomial(2, new IntegerScalar(15)), a.mul(b));
    }

    @Test
    void monomial_evaluate_basic() {
        // 3x^2 at x=2 -> 12
        Monomial m = new Monomial(2, new IntegerScalar(3));
        assertEquals(new IntegerScalar(12), m.evaluate(new IntegerScalar(2)));
    }

    @Test
    void monomial_evaluate_exponentZero() {
        // 5x^0 at any value -> 5
        Monomial m = new Monomial(0, new IntegerScalar(5));
        assertEquals(new IntegerScalar(5), m.evaluate(new IntegerScalar(99)));
    }

    @Test
    void monomial_evaluate_rationalScalar() {
        // 2x^2 at x=1/2 -> 2*(1/4) = 1/2
        Monomial m = new Monomial(2, new IntegerScalar(2));
        assertEquals("1/2", m.evaluate(new RationalScalar(1, 2)).toString());
    }

    @Test
    void monomial_derivative_basic() {
        // d/dx(3x^2) = 6x
        Monomial m = new Monomial(2, new IntegerScalar(3));
        assertEquals(new Monomial(1, new IntegerScalar(6)), m.derivative());
    }

    @Test
    void monomial_derivative_exponentOne() {
        // d/dx(5x) = 5
        Monomial m = new Monomial(1, new IntegerScalar(5));
        assertEquals(new Monomial(0, new IntegerScalar(5)), m.derivative());
    }

    @Test
    void monomial_derivative_constant() {
        // d/dx(7) = 0
        Monomial m = new Monomial(0, new IntegerScalar(7));
        assertEquals(new Monomial(0, new IntegerScalar(0)), m.derivative());
    }

    @Test
    void monomial_sign_positive() {
        Monomial m = new Monomial(2, new IntegerScalar(3));
        assertEquals(1, m.sign());
    }

    @Test
    void monomial_sign_negative() {
        Monomial m = new Monomial(2, new IntegerScalar(-3));
        assertEquals(-1, m.sign());
    }

    @Test
    void monomial_sign_zero() {
        Monomial m = new Monomial(2, new IntegerScalar(0));
        assertEquals(0, m.sign());
    }

    @Test
    void monomial_equals_sameValues() {
        Monomial a = new Monomial(3, new IntegerScalar(5));
        Monomial b = new Monomial(3, new IntegerScalar(5));
        assertEquals(a, b);
    }

    @Test
    void monomial_equals_differentExponent() {
        Monomial a = new Monomial(2, new IntegerScalar(5));
        Monomial b = new Monomial(3, new IntegerScalar(5));
        assertNotEquals(a, b);
    }

    @Test
    void monomial_toString_coefficientOne() {
        // 1 * x^3 -> "x^3"
        Monomial m = new Monomial(3, new IntegerScalar(1));
        assertEquals("x^3", m.toString());
    }

    @Test
    void monomial_toString_coefficientNegativeOne() {
        // -1 * x^7 -> "-x^7"
        Monomial m = new Monomial(7, new IntegerScalar(-1));
        assertEquals("-x^7", m.toString());
    }

    @Test
    void monomial_toString_exponentOne() {
        // 4 * x^1 -> "4x"
        Monomial m = new Monomial(1, new IntegerScalar(4));
        assertEquals("4x", m.toString());
    }

    @Test
    void monomial_toString_exponentZero() {
        // 5 * x^0 -> "5"
        Monomial m = new Monomial(0, new IntegerScalar(5));
        assertEquals("5", m.toString());
    }

    @Test
    void monomial_toString_zeroCoefficient() {
        Monomial m = new Monomial(3, new IntegerScalar(0));
        assertEquals("0", m.toString());
    }

}
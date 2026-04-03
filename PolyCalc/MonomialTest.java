package PolyCalc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MonomialTest {

    @Test
    void testAdd() {
        // Create 3x^2
        Monomial m1 = new Monomial(2, new IntegerScalar(3));
        // Create 4x^2
        Monomial m2 = new Monomial(2, new IntegerScalar(4));

        // Add them to get 7x^2
        Monomial result = m1.add(m2);

        // Verify the result matches our expectation
        assertEquals(2, result.getExponent());
        assertEquals(new IntegerScalar(7), result.getCoefficient());
    }

    @Test
    void testMul() {
        // Create 3x^2
        Monomial m1 = new Monomial(2, new IntegerScalar(3));
        // Create 4x^3
        Monomial m2 = new Monomial(3, new IntegerScalar(4));

        // Multiply them to get 12x^5
        Monomial result = m1.mul(m2);

        // Verify the result: exponent should be 5, coefficient should be 12
        assertEquals(5, result.getExponent());
        assertEquals(new IntegerScalar(12), result.getCoefficient());
    }
}
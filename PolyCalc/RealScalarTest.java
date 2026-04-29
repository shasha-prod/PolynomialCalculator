

package PolyCalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealScalarTest {


    @Test
    void testAdd() {
        // adding 8.25 + 0 = 8.25
        assertEquals(new RealScalar(8.25), new RealScalar(8.25).add(new RationalScalar(0, 1)));
        //adding 0.66 + 0.22 = 0.88
        assertEquals(new RealScalar(0.88), new RealScalar(0.66).add(new RealScalar(0.22)));
        //adding  0.25 + 0.5 = 0.75
        assertEquals(new RealScalar(0.75), new RealScalar(0.5).add(new RealScalar(0.25)));
        // adding fractions to get a whole number
        assertEquals(new RealScalar(1), new RealScalar(0.12).add(new RealScalar(0.88)));
    }

    @Test
    void testMul() {
        //multiplying by zero
        assertEquals(new RealScalar(0), new RationalScalar(100, 6).mul(new RealScalar(0)));
        //multiplying by one
        assertEquals(new RealScalar(0.25), new RealScalar(0.25).mul(new RealScalar(1)));
        //multiplying two RationalScalars
        assertEquals(new RationalScalar(18,16), new RealScalar(0.25).mul(new RealScalar(4.5)));
        //multiplying an IntegerScalar with a RationalScalar
        assertEquals(new RationalScalar(17, 4), new IntegerScalar(17).mul(new RealScalar(0.25)));
        //multiplying RealScalars to get RationalScalar
        assertEquals(new RationalScalar(18, 16), new RealScalar(0.25).mul(new RealScalar(4.5)));
        //multiplying RealScalar fractions
        assertEquals(new RealScalar(2.5), new RealScalar(0.5).mul(new RealScalar(5.0)));
    }

    @Test
    void testNeg() {
        //testing neg on a positive number
        assertEquals(new RealScalar(-50), new RationalScalar(100, 2).neg());
        //testing neg on a negative number
        assertEquals(new RealScalar(1.5), new RealScalar(-1.5).neg());
        //checking zeros sign
        assertEquals(new RealScalar(0), new RealScalar(0).neg());
    }

    @Test
    void testSigns() {
        //testing positive sign
        assertEquals(1, new RealScalar(3).sign());
        //testing negative sign
        assertEquals(-1, new RealScalar(-100000000).sign());
        //testing 0 sign
        assertEquals(0, new RealScalar(0).sign());
    }

    @Test
    void testPower() {
        //checking fraction power = (0.5)^2 = 0.25
        assertEquals(new RealScalar(0.25), new RealScalar(0.5).power(2));
        //checking (-1)^15 = -1
        assertEquals(new RealScalar(-1), new RationalScalar(100, -100).power(15));
        //checking a RealScalar to the power of 0
        assertEquals(new RealScalar(1), new RealScalar(100).power(0));
        //cross checking Scalar types
        assertEquals(new RealScalar(1), new RationalScalar(56, -77).power(0));

    }

    @Test
    void testEquals() {
        //testing equality between classes
        assertTrue(new RealScalar(9).equals(new IntegerScalar(9)));
        assertTrue(new RealScalar(9).equals(new RationalScalar(81,9)));

        //testing type errors in equals
        assertFalse(new RealScalar(81).equals("Hello"));
        assertFalse(new RealScalar(81).equals(9));
        //testing that 0.5=1/2
        assertTrue(new RationalScalar(1,2).equals(new RealScalar(0.5)));
        assertTrue(new RealScalar(0.5).equals(new RationalScalar(1,2)));

    }

    @Test
    void testToString() {
        //testing toString a fraction
        assertEquals("12.24", new RealScalar(12.24).toString());
        //testing toString a whole number
        assertEquals("9.0", new RealScalar(9).toString());
        //testing toString zero
        assertEquals("0.777778", new RealScalar(0.777777777777777777777).toString());
    }
}
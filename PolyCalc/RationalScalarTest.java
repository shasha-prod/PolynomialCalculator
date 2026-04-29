package PolyCalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalScalarTest {


    @Test
    void testReduce() {
        //testing a number that needs a lot of reduction steps
        RationalScalar superUnredused = new RationalScalar(2048, -64);
        assertEquals(new RationalScalar(-32, 1), superUnredused.reduce());
        //testing an unreduced fraction
        RationalScalar unreducedfraction = new RationalScalar(4, 8);
        assertEquals(new RationalScalar(1, 2), unreducedfraction.reduce());
        //testing unredused full number
        assertEquals(new RationalScalar(3, 1), new RationalScalar(6, 2));
    }

    @Test
    void testAdd() {
        // adding 100/6+ 0 = 50/3
        assertEquals(new RationalScalar(50, 3), new RationalScalar(100, 6).add(new RationalScalar(0, 1)));
        //adding 33/50+22/100 = 22/25
        assertEquals(new RationalScalar(22, 25), new RationalScalar(33, 50).add(new RationalScalar(22, 100)));
        //adding -33/50+22/100 = -22/25
        assertEquals(new RationalScalar(-11, 25), new RationalScalar(-33, 50).add(new RationalScalar(22, 100)));
        // adding fractions to get a whole number
        assertEquals(new RationalScalar(1, 1), new RationalScalar(4, 9).add(new RationalScalar(5, 9)));

    }

    @Test
    void testMul() {
        //multiplying by zero
        assertEquals(new RationalScalar(0, 1), new RationalScalar(100, 6).mul(new RationalScalar(0, 1)));
        //multiplying by one
        assertEquals(new RationalScalar(1, 4), new RationalScalar(1, 4).mul(new RationalScalar(1, 1)));
        //multiplying two RationalScalars
        assertEquals(new RationalScalar(7, 36), new RationalScalar(1, 4).mul(new RationalScalar(7, 9)));
        //multiplying an IntegerScalar with a RationalScalar
        assertEquals(new RationalScalar(17, 9), new IntegerScalar(17).mul(new RationalScalar(1, 9)));

    }

    @Test
    void testNeg() {
        assertEquals(new RationalScalar(-50, 3), new RationalScalar(100, 6).neg());

        assertEquals(new RationalScalar(3, 2), new RationalScalar(-3, 2).neg());

        assertEquals(new RationalScalar(-5, 1), new RationalScalar(-10, -2).neg());
        assertEquals(new RationalScalar(3, 1), new RationalScalar(9, -3).neg());
        //checking zeros sign
        assertEquals(new RationalScalar(0, 1), new RationalScalar(0, 1).neg());
    }

    @Test
    void testSigns() {
        //testing positive sign
        assertEquals(1, new RationalScalar(3, 1).sign());
        //testing negative sign
        assertEquals(-1, new RationalScalar(-21, 9).sign());
        //testing 0 sign
        assertEquals(0, new RationalScalar(0, 1).sign());
        //testing a Rational number with a negative in the numerator and denominator (so secret positive)
        assertEquals(1, new RationalScalar(-3, -1).sign());
        //testing a Rational number with a negative in the numerator and denominator (so secret positive)
        assertEquals(-1, new RationalScalar(21, -9).sign());
    }

    @Test
    void testPower() {
        //checking fraction power = (-2/9)^2 = 4/81
        assertEquals(new RationalScalar(4, 81), new RationalScalar(2, -9).power(2));
        //checking (-1)^15 = -1
        assertEquals(new RationalScalar(-1, 1), new RationalScalar(100, -100).power(15));
        //checking a RationalNumber to the power of 0
        assertEquals(new RationalScalar(1, 1), new RationalScalar(100, 99).power(0));
    }

    @Test
    void testEquals() {
        //testing equality between classes
        assertTrue(new RationalScalar(81, 9).equals(new IntegerScalar(9)));
        //testing type errors in equals
        assertFalse(new RationalScalar(81, 9).equals("Hello"));
        assertFalse(new RationalScalar(81, 9).equals(9));

    }

    @Test
    void testToString() {
        //testing toString a fraction
        assertEquals("-2/9", new RationalScalar(2, -9).toString());
        //testing toString a whole number
        assertEquals("9", new RationalScalar(81, 9).toString());
        //testing toString zero
        assertEquals("0", new RationalScalar(0, 0).toString());
    }
}
package PolyCalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalScalarTest {

    @Test
    void testGetRedusedNumber() {
    }

    @Test
    void testGetRedusedFullNumber() {
        Scalar unreducedfull = new RationalScalar(6, 2);
        assertEquals(3, unreducedfull.getNumber()[0]);
    }

    @Test
    void testReduce() {
        //testing a number that needs a lot of reduction steps
        RationalScalar superUnredused = new RationalScalar(2048, -64);
        assertEquals(new RationalScalar(-32, 1), superUnredused.reduce());
        //testing an unreduced fraction
        RationalScalar unreducedfraction = new RationalScalar(4, 8);
        assertEquals(new RationalScalar(1, 2), unreducedfraction.reduce());
    }

    @Test
    void addFraction() {
        // adding 100/6+ 0 = 50/3
        assertEquals(new RationalScalar(50, 3), new RationalScalar(100, 6).add(new RationalScalar(0, 1)));
        //adding 33/50+22/100 = 22/25
        assertEquals(new RationalScalar(22, 25), new RationalScalar(33, 50).add(new RationalScalar(22, 100)));
        //adding -33/50+22/100 = -22/25
        assertEquals(new RationalScalar(-22, 25), new RationalScalar(-33, 50).add(new RationalScalar(22, 100)));

    }

    @Test
    void testMul() {
        assertEquals(new RationalScalar(0, 1), new RationalScalar(100, 6).mul(new RationalScalar(0, 1)));
    }

    @Test
    void testNeg() {
        assertEquals(new RationalScalar(-50, 3), new RationalScalar(100, 6).neg());

        assertEquals(new RationalScalar(3, 2), new RationalScalar(-3, 2).neg());

        assertEquals(new RationalScalar(-5, 1), new RationalScalar(-10, -2));
        assertEquals(new RationalScalar(3, 1), new RationalScalar(9,-3).neg());
        //checking zeros sign
        assertEquals(0, new IntegerScalar(0).neg());
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
        assertEquals(new RationalScalar(4, 81), new RationalScalar(2, -9).power(2));

        assertEquals(new RationalScalar(-1, 1), new RationalScalar(100, -100).power(15));
    }

    @Test
    void testEquals() {
        Scalar rational = new RationalScalar(81, 9);
        Scalar full = new IntegerScalar(9);
        assertTrue(rational.equals(full));
    }

    @Test
    void testToString() {
        assertEquals("-2/9", new RationalScalar(2, -9).toString());
    }

    @Test
    void testFullNumberToString() {
        assertEquals("9", new RationalScalar(81, 9).toString());
    }

    @Test
    void testZeroToString() {
        assertEquals("0", new RationalScalar(0, 0).toString());
    }
}
package PolyCalc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalScalarTest {

    @Test
    void testGetRedusedNumber() {
        Scalar unreducedfraction = new RationalScalar(4, 8);
        assertEquals(1, unreducedfraction.getNumber()[0]);
        assertEquals(2, unreducedfraction.getNumber()[1]);
    }

    @Test
    void testGetRedusedFullNumber() {
        Scalar unreducedfull = new RationalScalar(6, 2);
        assertEquals(3, unreducedfull.getNumber()[0]);
    }

    @Test
    void reduce() {
        RationalScalar superUnredused = new RationalScalar(2048, -64);
        assertEquals(new RationalScalar(-32, 1), superUnredused.reduce());
    }

    @Test
    void addFraction() {
        assertEquals(new RationalScalar(50, 3), new RationalScalar(100, 6).add(new RationalScalar(0, 1)));
    }

    @Test
    void addBiggerFraction() {
        assertEquals(new RationalScalar(22, 25), new RationalScalar(33, 50).add(new RationalScalar(22, 100)));
    }

    @Test
    void mul() {
        assertEquals(new RationalScalar(0, 0), new RationalScalar(100, 6).mul(new RationalScalar(0, 1)));

    }

    @Test
    void testPostoNegNum() {
        assertEquals(new RationalScalar(-50, 3), new RationalScalar(100, 6).neg());
    }

    @Test
    void testNegToPosNum() {
        assertEquals(new RationalScalar(3, 2), new RationalScalar(-3, 2).neg());
    }

    @Test
    void testTrickPostoNegNum() {
        Scalar tricky = new RationalScalar(-10, -2);
        assertEquals(new RationalScalar(-5, 1), tricky.neg());
    }

    @Test
    void testTrickNegtoPosNum() {
        Scalar switched = new RationalScalar(9, -3);
        assertEquals(new RationalScalar(3, 1), switched.neg());
    }

    @Test
    void PosSign() {
        assertEquals(1, new RationalScalar(3, 1).sign());
    }

    @Test
    void NegSign() {
        assertEquals(-1, new RationalScalar(-21, 9).sign());
    }

    @Test
    void PosTrickSign() {
        assertEquals(1, new RationalScalar(-3, -1).sign());
    }

    @Test
    void NegTrickSign() {
        assertEquals(-1, new RationalScalar(21, -9).sign());
    }

    @Test
    void power() {
        assertEquals(new RationalScalar(4, 81), new RationalScalar(2, -9).power(2));
    }

    @Test
    void Trickpower() {
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
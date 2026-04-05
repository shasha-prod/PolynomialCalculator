package PolyCalc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class IntegerScalarTest {
    Scalar positiveNumber;
    Scalar negativeNumber;

    @BeforeEach
    void setUp() {
        positiveNumber = new IntegerScalar(300);
        negativeNumber = new IntegerScalar(-5);
    }

    @Test
    void TestAdd() {
        //adding 300+-5 = 295
        assertEquals(new IntegerScalar(295), positiveNumber.add(negativeNumber));
        //adding negative numbers -5+-5 = -10
        assertEquals(new IntegerScalar(-10), negativeNumber.add(negativeNumber));
        //adding 0
        assertEquals(new IntegerScalar(8), new IntegerScalar(8).add(new IntegerScalar(0)));
        //adding RationalNumber fraction to a wholeInteger number
        assertEquals(new RationalScalar(152,17), new IntegerScalar(8).add(new RationalScalar(16,17)));

    }

    @Test
    void testMul() {
        // multiplying 300*-5 = -1500
        assertEquals(new IntegerScalar(-1500), positiveNumber.mul(negativeNumber));
        //multiplying -3000*-5 = 1500
        assertEquals(new IntegerScalar(1500), new IntegerScalar(-300).mul(negativeNumber));
        //multiplying 0
        assertEquals(new IntegerScalar(0), new IntegerScalar(345).mul(new IntegerScalar(0)));
        //multiplying 1
        assertEquals(new IntegerScalar(-78), new IntegerScalar(-78).mul(new IntegerScalar(1)));
    }

    @Test
    void CheckNumberSign() {
        // checking a positiveNumbers sign
        assertEquals(1, positiveNumber.sign());
        // checking a negativeNumbers sign
        assertEquals(-1, negativeNumber.sign());
        //checking zeros sign
        assertEquals(0, new IntegerScalar(0).sign());
    }
    @Test
    void testNeg() {
        // testing the negative of 300 =>-300
        assertEquals(new IntegerScalar(-300), positiveNumber.neg());
        // testing the negative of -5 =>-5
        assertEquals(new IntegerScalar(5), negativeNumber.neg());
        //checking zeros sign
        assertEquals(new IntegerScalar(0), new IntegerScalar(0).neg());
    }


    @Test
    void testPower() {
        //testing power 2^2 = 4
        assertEquals(new IntegerScalar(4), new IntegerScalar(2).power(2));
        //testing bigger number 2^5 = 32
        assertEquals(new IntegerScalar(32), new IntegerScalar(2).power(5));
        //testing negative number -3^4 = 81
        assertEquals(new IntegerScalar(81), new IntegerScalar(-3).power(4));
        //testing power of 0 = 45^0 = 1
        assertEquals(new IntegerScalar(1), new IntegerScalar(45).power(0));

    }

    @Test
    void testEquals() {
        //testing simple equality
        assertTrue(new IntegerScalar(32).equals(new IntegerScalar(32)));
        assertTrue(positiveNumber.equals(new IntegerScalar(300)));
        assertTrue(negativeNumber.equals(new IntegerScalar(-5)));

        //testing equality between equal numbers but different classes
        assertTrue(new RationalScalar(32, 1).equals(new IntegerScalar(32)));

        //testing throw errors between types
        assertFalse(new RationalScalar(32, 1).equals(32));
        assertFalse(new RationalScalar(32, 1).equals("Check32"));
        assertFalse(new RationalScalar(32, 1).equals(null));

    }

    @Test
    void testToString() {
        //Testing toString
        assertEquals("32", new IntegerScalar(32).toString());
        //testing to String with a negative number
        assertEquals("-32", new IntegerScalar(-32).toString());

    }

}
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
        assertEquals(new IntegerScalar(295), positiveNumber.add(negativeNumber));
    }

    @Test
    void TestNegAdd() {
        assertEquals(new IntegerScalar(-10), negativeNumber.add(negativeNumber));
    }

    @Test
    void mul() {
        assertEquals(new IntegerScalar(-1500), positiveNumber.mul(negativeNumber));
    }

    @Test
    void TwoNegMul() {
        assertEquals(new IntegerScalar(1500), new IntegerScalar(-300).mul(negativeNumber));
    }

    @Test
    void CheckPositiveNumberSign() {
        assertEquals(1, positiveNumber.sign());
    }

    @Test
    void CheckNegativeNumberSign() {
        assertEquals(-1, negativeNumber.sign());
    }

    @Test
    void CheckZeroNumberSign() {
        assertEquals(0, new IntegerScalar(0).sign());
    }

    @Test
    void power() {
        assertEquals(new IntegerScalar(4), new IntegerScalar(2).power(2));
    }

    @Test
    void Morepower() {
        assertEquals(new IntegerScalar(32), new IntegerScalar(2).power(5));
    }

    @Test
    void testEquals() {
        assertTrue(new IntegerScalar(32).equals(new IntegerScalar(32)));
        assertTrue(positiveNumber.equals(new IntegerScalar(300)));
        assertTrue(negativeNumber.equals(new IntegerScalar(-5)));

    }

    @Test
    void testRationalToIntegerEquals() {
        assertTrue(new RationalScalar(32, 1).equals(new IntegerScalar(32)));
    }

    @Test
    void testtoString() {
        assertEquals("32", new IntegerScalar(32).toString());
    }

}
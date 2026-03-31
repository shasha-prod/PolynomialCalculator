package PolyCalc;

import static org.junit.jupiter.api.Assertions.*;

class IntegerScalarTest {
    Scalar positiveNumber;
    Scalar negativeNumber;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        positiveNumber = new IntegerScalar(300);
        negativeNumber = new IntegerScalar(-5);
    }

    @org.junit.jupiter.api.Test
    void TestAdd() {
        assertEquals(new IntegerScalar(295),positiveNumber.add(negativeNumber));
    }
    @org.junit.jupiter.api.Test
    void TestNegAdd() {
        assertEquals(new IntegerScalar(-10),negativeNumber.add(negativeNumber));
    }

    @org.junit.jupiter.api.Test
    void mul() {
        assertEquals(new IntegerScalar(-1500),positiveNumber.mul(negativeNumber));
    }

    void TwoNegMul() {
        assertEquals(new IntegerScalar(1500),new IntegerScalar(-300).mul(negativeNumber));
    }

    @org.junit.jupiter.api.Test
    void CheckPositiveNumberSign() {
        assertEquals(1,positiveNumber.sign());
    }

    @org.junit.jupiter.api.Test
    void CheckNegativeNumberSign() {
        assertEquals(-1,negativeNumber.sign());
    }
    @org.junit.jupiter.api.Test
    void CheckZeroNumberSign() {
        assertEquals(0,new IntegerScalar(0).sign());
    }

    @org.junit.jupiter.api.Test
    void power() {
        assertEquals(new IntegerScalar(4),new IntegerScalar(2).power(2));
    }

    @org.junit.jupiter.api.Test
    void Morepower() {
        assertEquals(new IntegerScalar(32),new IntegerScalar(2).power(5));
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        assertTrue(new IntegerScalar(32).equals(new IntegerScalar(32)));
        assertTrue(positiveNumber.equals(new IntegerScalar(300)));
        assertTrue(negativeNumber.equals(new IntegerScalar(-5)));

    }

    @org.junit.jupiter.api.Test
    void testtoString() {
        assertEquals("32",new IntegerScalar(32).toString());
    }

}
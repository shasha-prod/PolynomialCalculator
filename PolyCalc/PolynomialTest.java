package PolyCalc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.Iterator;

class PolynomialTest {

    @Test
    void testBuildStandard() {
        // Test parsing "1 2 3" -> 1 + 2x + 3x^2
        Polynomial p = Polynomial.build("1 2 3");
        Collection<Monomial> monomials = p.getMonomials();

        // It should have exactly 3 terms
        assertEquals(3, monomials.size());

        // We use an Iterator to look at the items inside the Collection one by one
        Iterator<Monomial> it = monomials.iterator();

        // Term 1: 1x^0
        Monomial m1 = it.next();
        assertEquals(0, m1.getExponent());
        assertEquals(new IntegerScalar(1), m1.getCoefficient());

        // Term 2: 2x^1
        Monomial m2 = it.next();
        assertEquals(1, m2.getExponent());
        assertEquals(new IntegerScalar(2), m2.getCoefficient());

        // Term 3: 3x^2
        Monomial m3 = it.next();
        assertEquals(2, m3.getExponent());
        assertEquals(new IntegerScalar(3), m3.getCoefficient());
    }

    @Test
    void testBuildWithFractionsAndZeroes() {
        // Test parsing "0 1/2 -5/3" -> 1/2x - 5/3x^2
        Polynomial p = Polynomial.build("0 1/2 -5/3");
        Collection<Monomial> monomials = p.getMonomials();

        // It should only have 2 terms! The 0 should have been skipped.
        assertEquals(2, monomials.size());

        Iterator<Monomial> it = monomials.iterator();

        // First term should be the 1x exponent (1/2x)
        Monomial m1 = it.next();
        assertEquals(1, m1.getExponent());
        assertEquals(new RationalScalar(1, 2), m1.getCoefficient());

        // Second term should be the 2x exponent (-5/3x^2)
        Monomial m2 = it.next();
        assertEquals(2, m2.getExponent());
        assertEquals(new RationalScalar(-5, 3), m2.getCoefficient());
    }
}
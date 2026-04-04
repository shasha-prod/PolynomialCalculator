package PolyCalc;

import java.util.Collection;
import java.util.ArrayList;

public class Polynomial {
    private Collection<Monomial> monomials;

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    public static Polynomial build(String input) {
        Polynomial p = new Polynomial();

        String[] parts = input.split(" ");

        for (int i = 0; i < parts.length; i++) {
            String term = parts[i];
            Scalar coefficient;
            if (term.contains("/")) {
                String[] fraction = term.split("/");
                int num = Integer.parseInt(fraction[0]);
                int den = Integer.parseInt(fraction[1]);
                coefficient = new RationalScalar(num, den);
            } else {
                int num = Integer.parseInt(term);
                coefficient = new IntegerScalar(num);
            }
            if (coefficient.sign() != 0) {
                Monomial m = new Monomial(i, coefficient);
                p.monomials.add(m);
            }
        }
        return p;
    }

    // Helper method so we can test what's inside
    public Collection<Monomial> getMonomials() {
        return this.monomials;
    }
}
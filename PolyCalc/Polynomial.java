package PolyCalc;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    private Collection<Monomial> monomials;

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    public static Polynomial build(String input) {
        Polynomial p = new Polynomial();
        String[] tokens = input.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            Scalar coeff;
            if (tokens[i].contains("/")) {
                String[] parts = tokens[i].split("/");
                coeff = new RationalScalar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            } else {
                coeff = new IntegerScalar(Integer.parseInt(tokens[i]));
            }
            if (coeff.sign() != 0) {
                p.getMonomials().add(new Monomial(i, coeff));
            }
        }
        return p;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();
        Map<Integer, Monomial> termMap = new HashMap<>();

        for (Monomial m : this.monomials) {
            if (m.sign() != 0) {
                termMap.put(m.getExponent(), m);
            }
        }

        for (Monomial n : p.getMonomials()) {
            if (n.sign() == 0) continue;

            if (termMap.containsKey(n.getExponent())) {
                Monomial sum = termMap.get(n.getExponent()).add(n);
                if (sum.sign() == 0) {
                    termMap.remove(n.getExponent());
                } else {
                    termMap.put(n.getExponent(), sum);
                }
            } else {
                termMap.put(n.getExponent(), n);
            }
        }

        result.getMonomials().addAll(termMap.values());
        return result;
    }

    public Polynomial mul(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Monomial m : this.monomials) {
            for (Monomial n : p.getMonomials()) {
                Monomial product = m.mul(n);
                Polynomial tinyPoly = new Polynomial();
                tinyPoly.getMonomials().add(product);
                result = result.add(tinyPoly);
            }
        }
        return result;
    }

    public Scalar evaluate(Scalar s){
        Scalar total = new IntegerScalar(0);
        for (Monomial m : this.monomials){
            total = total.add(m.evaluate(s));
        }
        return total;
    }

    private Polynomial negate() {
        Polynomial result = new Polynomial();
        for (Monomial m : this.monomials) {
            result.getMonomials().add(new Monomial(m.getExponent(), m.getCoefficient().neg()));
        }
        return result;
    }

    public Polynomial derivative(){
        Polynomial result = new Polynomial();
        for (Monomial m : this.monomials){
            result.monomials.add(m.derivative());
        }
        return result;
    }

    public boolean equals(Object o) {
        if (o instanceof Polynomial) {
            Polynomial other = (Polynomial) o;

            Polynomial difference = this.add(other.negate());
            return difference.getMonomials().isEmpty();
        }
        return false;
    }

    public String toString() {
        if (this.monomials.isEmpty()) {
            return "0";
        }

        String result = "";
        boolean isFirstTerm = true;

        for (Monomial m : this.monomials) {
            if (m.sign() == 0) {
                continue;
            }

            if (isFirstTerm == true) {
                result = result + m.toString();
                isFirstTerm = false;
            } else {
                if (m.sign() > 0) {
                    result = result + " + " + m.toString();
                } else {
                    result = result + " " + m.toString();
                }
            }
        }

        if (result.equals("")) {
            return "0";
        }

        return result;
    }

    public Collection<Monomial> getMonomials(){
        return this.monomials;
    }
}
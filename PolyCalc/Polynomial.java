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
        String[] tokens = input.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            Scalar coeff;
            if (tokens[i].contains("/")) {
                String[] parts = tokens[i].split("/");
                coeff = new RationalScalar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            } else {
                coeff = new IntegerScalar(Integer.parseInt(tokens[i]));
            }
            p.getMonomials().add(new Monomial(i, coeff));
        }
        return p;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();

        for (Monomial m : this.monomials) {
            result.monomials.add(m);
        }

        for (Monomial n : p.getMonomials()) {
            boolean foundMatch = false;
            for (Monomial resMonomial : result.monomials) {
                if (resMonomial.getExponent() == n.getExponent()) {
                    result.monomials.remove(resMonomial);
                    Monomial newMonomial = resMonomial.add(n);
                    result.monomials.add(newMonomial);
                    foundMatch = true;
                    break;
                }
            }
            if (foundMatch == false) {
                result.monomials.add(n);
            }
        }
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
            if (this.monomials.size() != other.getMonomials().size()) {
                return false;
            }
            for (Monomial m : this.monomials) {
                boolean found = false;

                for (Monomial n : other.getMonomials()) {
                    if (m.equals(n)) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    return false;
                }
            }
            return true;
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
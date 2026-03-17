package labs;

import static java.lang.System.arraycopy;

public class Polynomial implements Comparable<Polynomial> {
    private final int[] coeff;

    public Polynomial(int[] coefficients) {
        // Remove trailing 0s
        int trail = 0;
        for (int i = coefficients.length - 1; 0 <= i; i--) {
            if (coefficients[i] == 0) {
                trail++;
                continue;
            }
            break;
        }
        coeff = new int[coefficients.length - Math.clamp(trail, 0, coefficients.length)];
        arraycopy(coefficients, 0, coeff, 0, coeff.length);
    }

    public int getDegree() {
        return (coeff.length - 1 >= 0) ? coeff.length - 1 : 0;
    }

    public int getCoefficient(int k, boolean padded) {
        return (k >= 0 && k < coeff.length) ? (coeff[k] != 0 ? coeff[k] : 1) : 1;
    }

    public int getCoefficient(int k) {
        return (k >= 0 && k < coeff.length) ? coeff[k] : 0;
    }

    public int[] getCoefficients() {
        return this.coeff;
    }

    public long evaluate(int x) {
        long ans = 0;
        for (int i = 0; i < coeff.length; i++) {
            ans += coeff[i] * ((i > 0) ? ((float) Math.pow(x, i)) : (1));
        }
        return ans;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = coeff.length - 1; 0 <= i; i--) {
            if (coeff[i] == 0) {
                continue;
            }
            boolean isFirst = i == coeff.length - 1;
            boolean isNeg = coeff[i] < 0;
            String out = (isFirst ? "" : " ") + (isNeg ? "-" : isFirst ? "" : "+") + (isFirst ? "" : " ")
                    + Math.abs(coeff[i])
                    + "%s%s%s".formatted(i >= 1 ? "x" : "", i > 1 ? "^" : "", i > 1 ? i : "");
            str += out;
        }
        if (str.length() == 0) {
            str += "0";
        }
        return str;
    }

    public Polynomial add(Polynomial other) {
        int otherLegnth = other.getDegree() + 1;
        int largest = (otherLegnth > this.getDegree() + 1) ? otherLegnth : this.getDegree() + 1;
        int[] merge = new int[largest];

        for (int i = largest - 1; 0 <= i; i--) {
            merge[i] = this.getCoefficient(i) + other.getCoefficient(i);
        }

        return new Polynomial(merge);
    }

    public Polynomial multiply(Polynomial other) {
        int[] merge = new int[this.getCoefficients().length + other.getCoefficients().length - 1];

        for (int i = 0; i < this.getCoefficients().length; i++) {
            for (int j = 0; j < other.getCoefficients().length; j++) {
                merge[i + j] += this.getCoefficients()[i] * other.getCoefficients()[j];
            }
        }

        return new Polynomial(merge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != getClass())
            return false;

        Polynomial other = (Polynomial) o;

        if (this.getDegree() != other.getDegree()) {
            return false;
        }
        for (int k = 0; k <= this.getDegree(); k++) {
            if (this.getCoefficient(k) != other.getCoefficient(k)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Polynomial other) {

        // Check for degree
        if (this.getDegree() > other.getDegree())
            return 1;
        else if (this.getDegree() < other.getDegree()) {
            return -1;
        }
        // Iterate degrees
        for (int k = 0; k <= this.getDegree(); k++) {
            int x = (this.getCoefficient(k));
            int y = (other.getCoefficient(k));
            if (x < y)
                return 1;
            else if (x > y) {
                return -1;
            }
        }

        return this.equals(other) ? 0 : 1;
    }
}

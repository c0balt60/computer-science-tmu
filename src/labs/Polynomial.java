package labs;

import static java.lang.System.arraycopy;

public class Polynomial {

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

    public int getCoefficient(int k) {
        return (k >= 0 && k < coeff.length) ? coeff[k] : 0;
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
}

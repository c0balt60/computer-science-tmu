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

    public Polynomial add(Polynomial other) {

    }
}

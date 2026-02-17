package labs;

public class BigO extends Polynomial {

    private String category;
    private String[] cats = {
            "constant",
            "linear",
            "quadratic",
            "cubic"
    };

    public BigO(int[] coefficients) {
        super(coefficients);

        int deg = getDegree();
        category = (deg > cats.length - 1 ? "polynomial" : cats[deg]);
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        int deg = getDegree();
        return String.format(
                "O(%s%s)",
                (deg == 0 ? "1" : "n"), (deg >= 2 ? "^" + deg : ""));
    }
}

public class App {
    public static void main(String[] args) {
        int a = 12 % 10;
        System.out.println(a);

        evenlySpaced(4, 6, 3);
    }

    public static boolean evenlySpaced(int a, int b, int c) {
        int x = Math.min(a, b);
        int y = Math.min(b, c);
        int z = Math.min(a, c);

        int i = x == a ? (z == a ? a : c) : b;
        int j = y == b ? (x == b ? b : a) : c;
        int k = z == c ? (y == c ? c : b) : a;

        int d1 = Math.abs(i - j);
        int d2 = Math.abs(j - k);
        System.out.println(d1 + " " + d2);
        return d1 == d2;
    }

}

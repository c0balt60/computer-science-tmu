public class App {
    public static void main(String[] args) {
        int a = 5 * (13 / 5);
        System.out.println(a);

        // evenlySpaced(4, 6, 3);
    }

    public static boolean evenlySpaced(int a, int b, int c) {
        int l = Math.min(a, Math.min(b, c));
        int h = Math.max(a, Math.max(b, c));
        int m = (a + b + c) - l - h;
        return Math.abs(l - m) == Math.abs(m - h);
    }

}

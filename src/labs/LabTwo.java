package labs;

import java.util.Arrays;

public class LabTwo {
    /*
     * Fill in the methods below according to the descriptions given
     * in the lab document. See the PDF in this archive.
     *
     * You should test your code by calling your methods in the main
     * method provided. One sample call is given, but you should write
     * your own test cases in main() and try them out as you go.
     *
     * Once you're satisfied that your methods are producing the correct
     * results, you can scroll down to the very bottom of this file
     * for instructions on how to run the unit tests.
     *
     * Note that your official lab grade will be based on the unit tests
     * and not what you wrote in main(), but it's still a good idea
     * to practice writing your own main() method. You won't always be
     * given unit tests.
     */

    public static void main(String[] args) {
        // Add your own tests here!

        int[] a1 = { 42, 99, 17, 33 };
        int[] b1 = everyOther(a1);

        System.out.println();
        System.out.println(Arrays.toString(b1));

        // int[][] arr = createZigZag(5, 2, 1);// createZigZag(4, 1, 42);
        // for (int[] i : arr)
        // System.out.println(Arrays.toString(i));

        // [ 99, 18, 4, 100, 71, 72, 67, 66, 42 ]
        // { 99, 18, 4, 100, 72, 71, 67, 66, 42 } --> Answer
        int[] arr = { 4, 18, 99, 67, 71, 72, 100, 42, 66 };
        reverseAscendingSubarrays(arr);
        System.out.println("final: " +
                Arrays.toString(arr));

    }

    public static int[] everyOther(int[] a) {
        int[] res = new int[a.length % 2 == 0 ? a.length / 2 : a.length / 2 + 1];

        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (i % 2 != 0)
                continue;
            res[j] = a[i];
            j++;
        }

        System.out.println(Arrays.stream(res).toString());

        return res;
    }

    public static int[][] createZigZag(int rows, int cols, int start) {
        int[][] res = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res[i][j] = start + (i % 2 == 0 ? (j + (i * cols)) : (((i + 1) * cols) - 1) - j);

        return res;
    }

    public static void reverseAscendingSubarrays(int[] items) {
        int s = 0; // start index
        // Parse
        for (int i = 0; i < items.length; i++) {
            // Check for size
            if (i < items.length - 1 && items[i] < items[i + 1])
                continue;

            // Re order previous
            int mag = i + 1 - s; // magnitude of items
            if (mag < 4) {
                int first = items[s];
                items[s] = items[i];
                items[i] = first;
                s = i + 1;
                continue;
            } // 2/3 items, swap them

            // Re order in loop
            for (int j = 0; j < mag / 2; j++) {
                int near = items[j + s];
                items[j + s] = items[i - j]; // swap
                items[i - j] = near;
            }
            // Start index reset to current
            s = i + 1;
        }
    }

    public static String pancakeScramble(String text) {
        String res = new String(text);

        for (int i = 2; i <= res.length(); i++) {
            // Inverse
            String section = res.substring(0, i);
            res = new StringBuilder(section).reverse().toString() + res.substring(i, res.length());
        }

        return res;
    }
}

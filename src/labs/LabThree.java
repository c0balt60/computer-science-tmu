package labs;

public class LabThree {
    /*
     * Fill in the method below according to the description given
     * in the lab document. See the PDF in this archive.
     *
     * You should test your code by calling your method in the main
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

    public static void main(String args[]) {
        String s = reverseVowels("Alex Ufkes");
        System.out.println(s);
    }

    // Lookup table
    private static final boolean[] VOWEL = new boolean[128];
    static {
        VOWEL['a'] = VOWEL['e'] = VOWEL['i'] = VOWEL['o'] = VOWEL['u'] = true;
        VOWEL['A'] = VOWEL['E'] = VOWEL['I'] = VOWEL['O'] = VOWEL['U'] = true;
    }

    public static String reverseVowels(String text) {
        if (text.length() < 2)
            return text;

        char[] arr = text.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Scan in
            while (left < right && !VOWEL[arr[left]])
                left++;
            while (left < right && !VOWEL[arr[right]])
                right--;

            char l = arr[left];
            char r = arr[right];

            int leftUp = l >= 'A' && l <= 'Z' ? 1 : 0;
            int rightUp = r >= 'A' && r <= 'Z' ? 1 : 0;

            // Swap vowels
            // Force casing with ASCII 0x20 bit
            arr[left] = (char) ((r & 0xDF) | (leftUp == 0 ? 0x20 : 0));
            arr[right] = (char) ((l & 0xDF) | (rightUp == 0 ? 0x20 : 0));

            left++;
            right--;
        }

        return new String(arr);
    }
}

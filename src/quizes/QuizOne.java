package quizes;

public class QuizOne {

    public static void main(String args[]) {

        int a, b, c;
        a = 3;
        b = 3;
        c = 3;
        // Verify the variables can be part of a triangle=====================

        System.out.println("Can integers: a: " + a + ", b: " + b + ", and c: " + c + " form a triangle? "
                + canBeTriangle(a, b, c));
        a = 1;
        b = 2;
        c = 3;
        System.out.println("Can integers: a: " + a + ", b: " + b + ", and c: " + c + " form a triangle? "
                + canBeTriangle(a, b, c));
        // ====================================================================

        // Execute FizzBuzz function

        fizzBuzz(10000);
        fizzBuzz(6);

    }

    public static boolean canBeTriangle(int num1, int num2, int num3) {
        return (num1 + num2 > num3) && (num2 + num3 > num1) && (num3 + num1 > num2);
    }

    public static void fizzBuzz(int N) {
        if (N < 1)
            System.out.println("N must be more than 0");
        else if (N > 100)
            System.out.println("N must be less than 100");
        else
            for (int i = 1; i <= N; i++)
                System.out.println(
                        (i % 3 == 0 || i % 5 == 0) ? (i % 3 == 0 ? "Fizz" : "") + (i % 5 == 0 ? "Buzz" : "") : i);
    }

}

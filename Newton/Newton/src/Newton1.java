import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Repeatedly asks the user whether they wish to calculate another square root.
 *
 * @author Shafin Alam
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * final int for the amount of iterations.
     */
    public static final int SEQUENCE = 10;

    /**
     * computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square of
     * @return estimate of square root
     */

    private static double sqrt(double x) {
        int r = 1;
        double squareRoot = 1;
        while (r <= SEQUENCE) {
            squareRoot = (squareRoot + x / squareRoot) / 2;
            r++;
        }
        return squareRoot;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.print("Do you wish to calculate a square root?: ");
        String proceed = in.nextLine();
        while (proceed.equals("y")) {
            out.print("Enter a number to find out the square root: ");
            double input = in.nextDouble();
            out.println(sqrt(input));
            out.print("Do you wish to calculate another square root?: ");
            proceed = in.nextLine();
        }
        out.print("Bye loser!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser2 {
    /**
     * list of exponents for abcd.
     */
    final static double[] EXPONENTS = { -5, -4, -3, -2, -1, -0.5, -0.333, -0.25,
            0, 0.25, 0.333, 0.5, 1, 2, 3, 4, 5 };
    /**
     * multiplier for percentage.
     */
    final static int HUNDRED = 100;
    /**
     * percent of error it must be less than.
     */
    final static int PERCENT = 1;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.print("Please enter a positive real value: ");
        String constant = in.nextLine();
        int ct = 0;
        double u = 0;
        while (ct <= 0) {
            if (FormatChecker.canParseDouble(constant)) {
                u = Double.parseDouble(constant);
                ct++;
            } else {
                out.print("Please enter a positive real value: ");
                constant = in.nextLine();
            }
        }
        return u;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.print("Please enter a positive real value greater than 1: ");
        String constant = in.nextLine();
        int ct = 0;
        double u = 0;
        while (ct <= 0) {
            if (FormatChecker.canParseDouble(constant)
                    && (Double.parseDouble(constant) != 1)) {
                u = Double.parseDouble(constant);
                ct++;
            } else {
                out.print(
                        "Please enter a positive real value greater than 1: ");
                constant = in.nextLine();
            }
        }
        return u;
    }

    /**
     * error calculation for estimate of u.
     *
     * @param u
     *            mew
     * @param estimate
     *            estimate of mew
     * @return a positive percentage thats less than 1
     */
    private static double percentError(double u, double estimate) {
        double error = Math.abs((u - estimate) / u) * HUNDRED;
        return error;
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
        double u = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double guess = 0;
        double estimate = 0;
        double indexW = 0;
        double indexX = 0;
        double indexY = 0;
        double indexZ = 0;
        for (int ctw = 0; ctw < EXPONENTS.length; ctw++) {
            a = Math.pow(w, EXPONENTS[ctw]);
            for (int ctx = 0; ctx < EXPONENTS.length; ctx++) {
                b = Math.pow(x, EXPONENTS[ctx]);
                for (int cty = 0; cty < EXPONENTS.length; cty++) {
                    c = Math.pow(y, EXPONENTS[cty]);
                    for (int ctz = 0; ctz < EXPONENTS.length; ctz++) {
                        d = Math.pow(z, EXPONENTS[ctz]);
                        guess = Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c)
                                * Math.pow(z, d);
                        if (Math.abs(u - guess) < Math.abs(u - estimate)) {
                            if (Math.abs((guess - u) / u) * HUNDRED > PERCENT) {
                                estimate = guess;
                                indexW = EXPONENTS[ctw];
                                indexX = EXPONENTS[ctx];
                                indexY = EXPONENTS[cty];
                                indexZ = EXPONENTS[ctz];
                            }
                        }
                    }
                }
            }
        }
        out.println(indexW + " " + indexX + " " + indexY + " " + indexZ);
        out.println(estimate);
        double error = percentError(u, estimate);
        out.println(error);
        in.close();
        out.close();

    }
}

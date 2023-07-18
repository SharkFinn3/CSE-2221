import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Model class.
 *
 * @author Shafin Alam
 */
public final class NNCalcModel1 implements NNCalcModel {

    /**
     * Model variables.
     */
    private final NaturalNumber top, bottom;

    /**
     * Default constructor.
     */
    public NNCalcModel1() {
        this.top = new NaturalNumber2();
        this.bottom = new NaturalNumber2();
    }

    @Override
    public NaturalNumber top() {

        /*
         * Should return the top NaturalNumber.
         */
        return this.top;
    }

    @Override
    public NaturalNumber bottom() {

        /*
         * Should return the bottom NaturalNumber.
         */
        return this.bottom;
    }

}

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.Random;


public class RandomNumber {

    // container all digits
    private ArrayList<Integer> digits = new ArrayList<>();

    private Random generator = new Random();

    // default values
    private int maxDigit = 4;
    private int maxValue = 10;


    // constructors
    public RandomNumber() {}


    /*
     * Generate different random numbers, digit by digit and saves in array
     */
    public int create() {

        int randomDigit;

        for (int i = 0; i < this.maxDigit; i++) {

            if (i == 0) {
                // avoid number 0 in first digit
                this.digits.add(generator.nextInt(this.maxValue - 1) + 1);
            }


            do {
                randomDigit = this.generator.nextInt(this.maxValue);
            } while (this.digits.contains(randomDigit));

            this.digits.add(randomDigit);
        }

        return this.joinDigitsFromArray();
    }


    /*
    * Join all digits from array and return an only number
    */
    private int joinDigitsFromArray() {

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < this.maxDigit; i++) {
            number.append(this.digits.get(i));
        }

        return Integer.parseInt(number.toString());
    }


    // setters
    public void setMaxDigit(int maxDigit) throws LimitExceededException {
        if (maxDigit < 1 || maxDigit > 9) {
            throw new LimitExceededException("Rango aceptado: 1-9");
        }

        this.maxDigit = maxDigit;
    }


    // getters
    public int getMaxDigit() {
        return this.maxDigit;
    }


    public ArrayList<Integer> getDigits() {
        return this.digits;
    }


}

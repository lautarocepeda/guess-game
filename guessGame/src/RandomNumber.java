import java.util.ArrayList;
import java.util.Random;


public class RandomNumber {

    // container all digits
    private ArrayList<Integer> digits = new ArrayList<>();

    private Random generator = new Random();



    // constructors
    public RandomNumber() {}



    /*
     * Generate different random numbers, digit by digit and saves in array
     */
    public int create() {

        int randomDigit;

        for (int i = 0; i < Setting.maxDigit; i++) {

            if (i == 0) {
                // avoid number 0 in first digit
                this.digits.add(generator.nextInt(Setting.RANDOM_MAX_VALUE - 1) + 1);
            }


            do {
                randomDigit = this.generator.nextInt(Setting.RANDOM_MAX_VALUE);
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

        for (int i = 0; i < Setting.maxDigit; i++) {
            number.append(this.digits.get(i));
        }

        return Integer.parseInt(number.toString());
    }


    public ArrayList<Integer> getDigits() {
        return this.digits;
    }


}

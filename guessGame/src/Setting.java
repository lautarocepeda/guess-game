import javax.naming.LimitExceededException;

public class Setting {

    // maximun value to limit the random number when generate an number
    public static final int RANDOM_MAX_VALUE = 10; // no change

    // maximun digits in number (default 4)
    public static int maxDigit = 4;



    // to change maxDigit, only use this method
    public static void setMaxDigit(int maxDigit) throws LimitExceededException {
        if (maxDigit < 1 || maxDigit > 9) {
            throw new LimitExceededException("Rango aceptado: 1-9");
        }

        Setting.maxDigit = maxDigit;
    }
}

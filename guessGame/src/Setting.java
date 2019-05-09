public class Setting {

    // maximun value to limit the random number when generate an number
    public static final int RANDOM_MAX_VALUE = 10; // no change

    // maximun digits in number. Use range 1-9 (default 4)
    private int maxDigit = 4;

    private static Setting mySettings;


    // constructor
    private Setting(int maxDigit) {
        if(maxDigit > 9) {
            this.maxDigit = 9;
        } else if(maxDigit < 1) {
            this.maxDigit = 1;
        } else {
            this.maxDigit = maxDigit;
        }
    }


    public static Setting getSingletonInstance(int maxDigit) {
        if(mySettings == null) {
            mySettings = new Setting(maxDigit);
        }

        return mySettings;
    }


    @Override
    public Setting clone() {
        try {
            throw new CloneNotSupportedException("No ha sido posible clonar la configuración.");
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static int getMaxDigit() {
        return mySettings.maxDigit;
    }
}

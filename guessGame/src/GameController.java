import java.util.ArrayList;
import java.util.Stack;

public class GameController {

    private Player thinker;
    private Player divider;

    private int good;
    private int regular;


    // save the permutation numbers
    private Stack permutationDigits = new Stack();

    // digits unnecessary
    ArrayList<Character> blackListDigits = new ArrayList();

    // possibles digits
    Stack regularsDigits = new Stack();

    // all goods positions
    ArrayList<Integer> positionsGoods = new ArrayList();


    // variables to compare with good, regular and know when change
    int actualGoodDigits;
    int actualRegularDigits;



    // Contructors
    public GameController(Player thinker, Player divider) {
        this.thinker = thinker;
        this.divider = divider;
    }



    //Methods

    public void permutaciones(String[] digitos, String act, int n, int r) {
        if (n == 0) {
            this.permutationDigits.push(act);
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(digitos[i])) { // avoid repeats
                    permutaciones(digitos, act + digitos[i], n - 1, r);
                }

            }
        }
    }


    public void ordenRegularsDigitsByPermutations() {

        String[] digitos = this.divider.getNumber().split("");
        int n = Setting.getMaxDigit();
        int r = Setting.getMaxDigit();


        this.permutaciones(digitos, "", n, r);


        while (this.regular != 0) {

            // the computer use permutations until get the same number of regular digits
            this.divider.setNumber((String) this.permutationDigits.pop());

            this.calculateDigits();
        }

        // clear the stack
        this.permutationDigits.clear();
    }


    public boolean computerVsHuman() {

        char digitToReplace;
        char replace;

        int i = 0;


        // calculate the good and regular digits
        this.calculateDigits();


        while (this.good != Setting.getMaxDigit()) {

            if (positionsGoods.contains(i)){
                if (i == Setting.getMaxDigit() - 1) {
                    i = 0;
                } else {
                    i++;
                }
                continue;
            }


            // refresh variables to new iteration
            actualGoodDigits = this.good;
            actualRegularDigits = this.regular;


            digitToReplace = this.divider.getNumber().charAt(i);


            do {
                if (i == 0) {
                    replace = (char)(Math.floor(Math.random() * 9 + 1)  + '0');
                } else {
                    replace = (char)(Math.floor(Math.random() * 10) + '0');

                }

            } while (this.divider.getNumber().contains(String.valueOf(replace)) || blackListDigits.contains(replace));


            this.divider.setNumber(this.divider.getNumber().replace(digitToReplace, replace));

            this.calculateDigits();


            if (this.good == actualGoodDigits - 1) {

                this.positionsGoods.add(i);
                this.blackListDigits.add(digitToReplace);

                this.divider.setNumber(this.divider.getNumber().replace(replace, digitToReplace)); // revert change

                this.good++;

            } else if (this.good == actualGoodDigits + 1) {

                this.positionsGoods.add(i);
                this.blackListDigits.add(replace);

            } else if (this.regular == actualRegularDigits - 1) {
                this.divider.setNumber(this.divider.getNumber().replace(replace, digitToReplace)); // revert change
            }


            if (this.regular == actualRegularDigits + 1) {
                this.regularsDigits.add(replace);
            }


            if (this.regular > 1) {
                this.ordenRegularsDigitsByPermutations();
            }


            if (i == Setting.getMaxDigit() - 1) {
                i = 0;
            } else {
                i++;
            }
        }


        // end game
        if (this.good == Setting.getMaxDigit()) {
            System.out.println("End Game.");
            return true;
        }

        return false;
    }


    public void calculateDigits() {

        this.good = 0;
        this.regular = 0;


        for (int i = 0; i < Setting.getMaxDigit(); i++) {

            if (this.thinker.getDigits().get(i).equals(this.divider.getDigits().get(i))) {
                this.good++;
            }

            if (this.thinker.getDigits().contains(this.divider.getDigits().get(i))) {
                this.regular++;
            }
        }

        this.regular = this.regular - this.good;

        System.out.println("COMPUTADORA => " + this.divider.getNumber());
        System.out.println("Bien => " + good);
        System.out.println("Regular => " + regular);
    }


    public boolean humanVsComputer() {

        // calculate the good and regular digits
        this.calculateDigits();

        if (this.good == Setting.getMaxDigit()) {
            return true;
        }

        return false;
    }


    public int getGood() {
        return good;
    }

    public int getRegular() {
        return regular;
    }



}

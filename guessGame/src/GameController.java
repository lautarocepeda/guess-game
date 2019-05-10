import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

public class GameController {

    private Player thinker;
    private Player divider;

    private int good;
    private int regular;

    // save the permutation numbers
    private Stack regularsDigits = new Stack();



    public GameController(Player thinker, Player divider) {
        this.thinker = thinker;
        this.divider = divider;
    }


    public void permutaciones(String[] digitos, String act, int n, int r) {
        if (n == 0) {
            this.regularsDigits.push(act);
        } else {
            for (int i = 0; i < r; i++) {

                if (act.equals("0")) {
                    continue;
                }

                if (!act.contains(digitos[i])) { // Controla que no haya repeticiones
                    permutaciones(digitos, act + digitos[i], n - 1, r);
                }

            }
        }
    }


    public boolean computerVsHuman() {

        String divinerNumber = this.divider.getNumber();

        // calculate the good and regular digits
        this.calculateDigits();

        System.out.println("COMPUTADOR ELIGIO -> " + divinerNumber);


        // end game
        if (this.good == Setting.getMaxDigit()) {
            System.out.println("WIN");
            return true;
        }

        if (this.regular == 0 && this.good == 0) {
            // try with another number
            this.divider.computerThinkNumber();
            System.out.println("GENERANDO OTRO NUMERO");
        }


        if (this.regular > 1) {

            int countRegular = this.regular;

            System.out.println("DO SOMETHING HERE");

            String[] digitos = divinerNumber.split("");
            int n = Setting.getMaxDigit();
            int r = Setting.getMaxDigit();

            this.permutaciones(digitos, "", n, r);


            while (this.good != countRegular) {
                // the computer use permutations until get the same number of regular digits
                this.divider.setNumber((String) this.regularsDigits.pop());

                System.out.println("COMPUTADOR ELIGIO -> " + this.divider.getNumber());

                this.calculateDigits();
            }

            // clear the stack
            this.regularsDigits.clear();

            return true;
        }


        return false;
    }


    private void calculateDigits() {

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


    public int calculateFactorial(int number) {
        return (number == 1 || number == 0) ? 1 : number * calculateFactorial(number - 1);
    }






}

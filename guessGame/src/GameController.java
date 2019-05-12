import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

public class GameController {

    private Player thinker;
    private Player divider;

    private int good;
    private int regular;


    // contain the number with more good digits
    private String maybeNumber = new String();


    // save the permutation numbers
    private Stack regularsDigits = new Stack();


    // digits incorrects
    ArrayList<Character> blackListDigits = new ArrayList();

    // possibles digits
    Stack possibleRegularDigits = new Stack();

    // positions than cant change
    ArrayList<Integer> positionsGoods = new ArrayList();


    // variables to compare with good and regular and know when change
    int actualGoodDigits;
    int actualRegularDigits;



    public GameController(Player thinker, Player divider) {
        this.thinker = thinker;
        this.divider = divider;
    }


    public void permutaciones(String[] digitos, String act, int n, int r) {
        if (n == 0) {
            this.regularsDigits.push(act);
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(digitos[i])) { // Controla que no haya repeticiones
                    permutaciones(digitos, act + digitos[i], n - 1, r);
                }

            }
        }
    }


    public void ordenRegularsDigitsByPermutations() {

        String divinerNumber = this.divider.getNumber();

        int countRegular = this.regular + this.good;


        String[] digitos = divinerNumber.split("");
        int n = Setting.getMaxDigit();
        int r = Setting.getMaxDigit();

        //System.out.println("start permutations");
        this.permutaciones(digitos, "", n, r);


        while (this.good != countRegular) {
            // the computer use permutations until get the same number of regular digits
            this.divider.setNumber((String) this.regularsDigits.pop());

            System.out.println("PERMUTACIONES -> " + this.divider.getNumber());

            this.calculateDigits();
        }


        System.out.println("[beneficio] Se evitaron " + this.regularsDigits.size() + " interaciones.");
        // clear the stack
        this.regularsDigits.clear();


        // guarda el numero que tiene mas digitos buenos.
        this.maybeNumber = this.divider.getNumber();
    }


    public boolean computerVsHuman() {



        // calculate the good and regular digits
        this.calculateDigits();

        // end game
        if (this.good == Setting.getMaxDigit()) {
            System.out.println("WIN");
            return true;
        }


        /* Agregar condiciones para cuando existan regular y good a la vez
        *  ver como hacer para que una vez que de regular pasamos a consegir los good
        *  mantener esos good y cambiar los que estarian incorrectamente para probar con otros numeros.
        */


        if (this.regular > 0) {
            this.ordenRegularsDigitsByPermutations();
        }



        if (this.maybeNumber.length() > 0) {


            char actualDigit;
            char replaceDigit;

            boolean useRegularsDigits = false;




            int i = 0, j = 0;
            while (this.good != Setting.getMaxDigit()) {

                // actualiza las variables para la nueva iteracion
                actualGoodDigits = this.good;
                actualRegularDigits = this.regular;


                // active flag to use the regular digits in next iteration
                if (this.possibleRegularDigits.size() == 1) {
                    useRegularsDigits = true;
                }

                //System.out.println("Diviner Number => " + this.divider.getNumber());

                if (positionsGoods.contains(i)){
                    //System.out.println("[CONTINUE] POSICION #" + i);
                    if (i == Setting.getMaxDigit() - 1) {
                        i = 0;
                    } else {
                        i++;
                    }

                    continue;
                }

                actualDigit = this.divider.getNumber().charAt(i);
                //System.out.println("[TESTING] DIGITO A SER REEMPLAZADO => " + actualDigit);


                // Quiere decir que 1 solo digito esta mal ubicado
                if (this.positionsGoods.size() == Setting.getMaxDigit() - 1) {

                    //System.out.println("FALTA 1 SOLO DIGITO");

                    int position = new Integer(0);

                    for (int k = 0; k < this.divider.getNumber().length(); k++) {
                        if(!this.positionsGoods.contains(k)) {
                            position = k;
                        }
                    }

                    actualDigit = this.divider.getNumber().charAt(position);
                    this.blackListDigits.add(actualDigit);
                    //System.out.println("[badOneDigit] DIGITO A SER REEMPLAZADO => " + actualDigit);
                }


                if(useRegularsDigits) {

                    replaceDigit = (char) this.possibleRegularDigits.pop();
                    //System.out.println("----------- Regular Digits--------> " + replaceDigit);
                    useRegularsDigits = false;

                } else {
                    do {
                        if (i == 0) {
                            replaceDigit = (char)(Math.floor(Math.random() * 9 + 1)  + '0');
                        } else {
                            replaceDigit = (char)(Math.floor(Math.random() * 10) + '0');

                        }

                    } while (this.divider.getNumber().contains(String.valueOf(replaceDigit)) || blackListDigits.contains(replaceDigit));
                }



                //System.out.println("[TESTING] DIGITO DE REEMPLAZO => " + replaceDigit);


                maybeNumber = this.divider.getNumber();
                String modifiedDivinerNumber = this.divider.getNumber().replace(actualDigit, replaceDigit);
                this.divider.setNumber(modifiedDivinerNumber);

                //System.out.println("[TESTING] NUMERO NUEVO A SER PROBADO => " + this.divider.getNumber());




                this.calculateDigits();


                // Posicion correcta.
                if (this.good == actualGoodDigits - 1) {

                    positionsGoods.add(i); // guarda la posicion
                    blackListDigits.add(actualDigit); // agrega digito a blacklist

                    this.divider.setNumber(maybeNumber);

                    //System.out.println("[condition] Posicion correcta => posicion: " + i + " - good number: " + actualDigit);
                    //System.out.println("agregado a la blacklist: , " + actualDigit);

                } else if (this.good == actualGoodDigits && this.regular == actualRegularDigits) {

                    blackListDigits.add(actualDigit);
                    this.divider.setNumber(maybeNumber);

                    //System.out.println("[conditions] numero incorrecto, agregado a la blacklist, " + actualDigit);

                }


                if (this.regular == actualRegularDigits + 1) {

                    if (!possibleRegularDigits.contains(replaceDigit) && !this.divider.getNumber().contains(String.valueOf(replaceDigit))) {
                        possibleRegularDigits.push(replaceDigit);
                    }

                    //System.out.println("[condition] Nuevo regular => posicion: " + i + " - regular number: " + replaceDigit);
                }



                if (this.regular > 0) {
                    this.ordenRegularsDigitsByPermutations();
                }



                if (i == Setting.getMaxDigit() - 1) {
                    i = 0;
                } else {
                    i++;
                }
            }

            // ended
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

        System.out.println("NUMERO COMPUTADORA => "  + this.divider.getNumber());
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
}

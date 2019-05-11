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

            int countRegular = this.regular + this.good;


            String[] digitos = divinerNumber.split("");
            int n = Setting.getMaxDigit();
            int r = Setting.getMaxDigit();

            System.out.println("start permutations");
            this.permutaciones(digitos, "", n, r);


            while (this.good != countRegular) {
                // the computer use permutations until get the same number of regular digits
                this.divider.setNumber((String) this.regularsDigits.pop());

                System.out.println("PERMUTACIONES -> " + this.divider.getNumber());

                this.calculateDigits();
            }


            System.out.println("[beneficio] Se ahorraron " + this.regularsDigits.size() + " interaciones.");
            // clear the stack
            this.regularsDigits.clear();


            // guarda el numero que tiene mas digitos buenos.
            this.maybeNumber = this.divider.getNumber();


            // guardar el numero que ya tiene al menos algunos digitos ordenados correctamente.
        }


        // UNA VEZ QUE ORDENO LOS NUMEROS REGULARES Y LOS PASO A GOOD, TENEMOS QUE OBTENER QUE NUMEROS SON LOS
        // CORECTOS CON EL SIGUIENTE CODIGO.
        if (this.maybeNumber.length() > 0) {

            int actualGoodDigits = this.good;
            int actualRegularDigits = this.regular;
            char actualDigit;
            char replaceDigit;

            // digits incorrects
            ArrayList<Character> blackListDigits = new ArrayList();

            // possibles digits
            ArrayList<Character> possibleRegularDigits = new ArrayList();

            // positions than cant change
            ArrayList<Integer> positionsGoods = new ArrayList();


            String originalDivinerNumber = this.divider.getNumber();

            int i = 0;
            while (this.good != Setting.getMaxDigit()) {

                if(positionsGoods.contains(i)){
                    System.out.println("[CONTINUE] POSICION #" + i);
                    if(i == Setting.getMaxDigit() - 1) {
                        i = 0;
                    } else {
                        i++;
                    }
                    continue;
                }

                actualDigit = this.divider.getNumber().charAt(i);

                System.out.println("[TESTING] DIGITO A SER REEMPLAZADO => " + actualDigit);

                do {
                    if (i == 0) {
                        replaceDigit = (char)(Math.floor(Math.random() * 9 + 1)  + '0');
                    } else {
                        replaceDigit = (char)(Math.floor(Math.random() * 9) + '0');
                    }

                } while (this.divider.getNumber().contains(String.valueOf(replaceDigit)) || blackListDigits.contains(replaceDigit)); // verificar el retorno de valueOf


                System.out.println("[TESTING] DIGITO DE REEMPLAZO => " + replaceDigit);

                String modifiedDivinerNumber = this.divider.getNumber().replace(actualDigit, replaceDigit);
                this.divider.setNumber(modifiedDivinerNumber);

                System.out.println("[TESTING] NUMERO NUEVO A SER PROBADO => " + this.divider.getNumber());


                this.calculateDigits();

                // Si se cumple es porque el numero que cambiamos, estaba en el lugar correcto
                if (actualGoodDigits > this.good) {
                    this.divider.setNumber(originalDivinerNumber);
                    positionsGoods.add(i);
                    System.out.println("[conditions] numero cambiado, estaba en lugar correcto.");
                }

                if (actualGoodDigits == this.good && actualRegularDigits == this.regular) {
                    // el numero en la posicion i, es un numero incorrecto, lo agrega a la lista negra.
                    this.divider.setNumber(originalDivinerNumber);
                    blackListDigits.add(replaceDigit);
                    System.out.println("[conditions] numero irrelevante, agregado a la blacklist " + replaceDigit);
                }

                if (actualRegularDigits < this.regular) {
                    if(!positionsGoods.contains(i)) {
                        this.divider.setNumber(modifiedDivinerNumber);
                        possibleRegularDigits.add(replaceDigit);
                        System.out.println("[conditions] nuevo regular, agregado a posibles digitos " + replaceDigit);
                    }
                }

                if(actualGoodDigits+actualRegularDigits == Setting.getMaxDigit()) {
                    System.out.println("HACER PERMUTACIONES");
                }




                // actualiza las variables para la nueva iteracion
                actualGoodDigits = this.good;
                actualRegularDigits = this.regular;

                if(i == Setting.getMaxDigit() - 1) {
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

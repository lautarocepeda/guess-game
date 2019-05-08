import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

    private Scanner scanner = new Scanner(System.in);
    private String number;



    // constructors
    public Player() {}

    public Player(String number) {
        this.number = number;
    }



    public void guessNumber() {

        boolean validData = false;

        do {

            try {
                System.out.println("Ingresa un numero de " + Setting.maxDigit + " digitos -> ");

                this.number = scanner.next();


                if (!this.number.matches("[0-9]*")) {
                    throw new NumberFormatException("El dato ingresado no es válido!");
                } else if (this.number.length() != Setting.maxDigit) {
                    throw new NumberFormatException("Debes ingresar un numero de " + Setting.maxDigit + " digitos!");
                }


                validData = true;
            } catch(NumberFormatException e) {
                System.out.println("Ups! " + e.getMessage() + " Intenta de nuevo");
            }

        } while (!validData);

    }


    public int getNumber() {
        return Integer.parseInt(this.number);
    }
}

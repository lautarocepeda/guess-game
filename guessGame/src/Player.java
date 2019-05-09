import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private String number;
    private Category type;



    // constructors
    public Player(Category type) {
        this.type = type;
    }



    public void humanGuessNumber() {

        Scanner scanner = new Scanner(System.in);

        boolean validData = false;

        do {
            try {
                System.out.println("Ingresa un numero de " + Setting.getMaxDigit() + " digitos -> ");

                this.number = scanner.next();


                if (!this.number.matches("[0-9]*")) {
                    throw new NumberFormatException("El dato ingresado no es válido!");
                } else if (this.number.length() != Setting.getMaxDigit()) {
                    throw new NumberFormatException("Debes ingresar un numero de " + Setting.getMaxDigit() + " digitos!");
                }


                validData = true;
            } catch(NumberFormatException e) {
                System.out.println("Ups! " + e.getMessage() + " Intenta de nuevo");
            }
        } while (!validData);
    }



    public void computerThinkNumber() {
        RandomNumber randomNumber = new RandomNumber();
        this.number = randomNumber.create();
    }


    public ArrayList<Integer> getDigits() {
        ArrayList<Integer> digits = new ArrayList();

        for (int i = 0; i < this.number.length(); i++) {
            digits.add(Integer.parseInt(String.valueOf(this.number.charAt(i))));
        }

        return digits;
    }


    public String getNumber() {
        return this.number;
    }


    public Category getType() {
        return this.type;
    }


    public void setType(Category type) {
        this.type = type;
    }



}

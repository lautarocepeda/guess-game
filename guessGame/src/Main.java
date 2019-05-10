import java.util.ArrayList;

public class Main {

    public static GameController gameController;



    public static void main(String[] args) {

        // set max digit to guess number.
        Setting setting = Setting.getSingletonInstance(4);


        //startThinkerComputer();

        startThinkerHuman();
    }



    // useless
    public static int factorial(int n) {
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }


    // COMPUTADORA PIENSA NUMERO Y HUMANO ADIVINA
    public static void startThinkerComputer() {

        // Pensador
        Player computadora = new Player();
        computadora.computerThinkNumber();


        // Adivinador
        Player lautaro = new Player();


        do {
            lautaro.humanGuessNumber();

            gameController = new GameController(computadora, lautaro);

        } while (!gameController.humanVsComputer());

        System.out.println(computadora.getNumber());

    }


    // HUMANO PIENSA NUMERO Y COMPUTADORA ADIVINA
    public static void startThinkerHuman() {

        // Pensador
        Player lautaro = new Player();
        lautaro.humanGuessNumber();

        // Adivinador
        Player computadora = new Player();
        computadora.computerThinkNumber();

        do {


            gameController = new GameController(lautaro, computadora);

        } while(!gameController.computerVsHuman());


    }



}

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // set max digit to guess number.
        Setting setting = Setting.getSingletonInstance(4);

        // Pensador
        Player computadora = new Player(Category.THINKER);
        computadora.computerThinkNumber();

        // Adivinador
        Player lautaro = new Player(Category.DIVINER);


        // controller game
        GameController gcontroller;

        do {
            lautaro.humanGuessNumber();

            gcontroller = new GameController(computadora.getDigits(), lautaro.getDigits());

        } while (!gcontroller.humanVsComputer());

        System.out.println(computadora.getNumber());

    }




}

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
        lautaro.humanGuessNumber();

        System.out.println("Computadora Numero => " + computadora.getNumber());
        System.out.println("Lautaro Numero => " + lautaro.getNumber());
    }




}

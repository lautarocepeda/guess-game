import java.util.Scanner;

public class Player {

    private Scanner scanner = new Scanner(System.in);
    private int number;


    // constructors
    public Player() {}

    public Player(int number) {
        this.number = number;
    }



    public void guessNumber() {
        System.out.println("Ingresa el numero -> ");
        this.number = scanner.nextInt();
    }


    public int getNumber() {
        return this.number;
    }
}

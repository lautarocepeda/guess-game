import java.util.ArrayList;

public class GameController {

    private ArrayList<Integer> thinkerNumber;
    private ArrayList<Integer> dividerNumber;



    public GameController(ArrayList<Integer> thinkerNumber, ArrayList<Integer> dividerNumber) {
        this.thinkerNumber = thinkerNumber;
        this.dividerNumber = dividerNumber;
    }



    public boolean humanVsComputer() {

        int good = 0;
        int regular = 0;


        for (int i = 0; i < this.thinkerNumber.size(); i++) {
            if(this.thinkerNumber.get(i).equals(this.dividerNumber.get(i))) {
                good++;
                System.out.println("[TEST] G - POSICION #" + i );
            }
        }

        if(good == Setting.getMaxDigit()) {
            return true;
        }

        if(good != Setting.getMaxDigit()) {
            for (int i = 0; i < this.thinkerNumber.size(); i++) {
                if(this.thinkerNumber.contains(this.dividerNumber.get(i))) {
                    regular++;
                    System.out.println("[TEST] R - POSICION #" + i );
                }
            }

            regular = regular - good;
        }

        System.out.println("Bien => " + good);
        System.out.println("Regular => " + regular);


        return false;
    }



}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameControllerTest {

    Setting setting = Setting.getSingletonInstance(4);

    GameController gameController;
    Player persona;
    Player computadora;



    @Before
    public void before() {
        // Pensador
        persona = new Player();

        // Adivinador
        computadora = new Player();

        gameController = new GameController(persona, computadora);
    }


    @Test
    public void testResultDigits() {
        persona.setNumber("1234");
        computadora.setNumber("1342");

        gameController.calculateDigits();


        int goodExpected = 1;
        int regularExpected = 3;

        assertEquals(goodExpected, gameController.getGood());
        assertEquals(regularExpected, gameController.getRegular());
    }



    @Test
    public void testResultGood() {

        persona.setNumber("1234");
        computadora.setNumber("1230");

        gameController.calculateDigits();

        int goodExpected = 3;

        assertEquals(goodExpected, gameController.getGood());
    }



    @Test
    public void testResultRegular() {
        persona.setNumber("4012");
        computadora.setNumber("1263");

        gameController.calculateDigits();

        int regularExpected = 2;

        assertEquals(regularExpected, gameController.getRegular());
    }



}

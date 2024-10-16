import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        enigma.console.Console cn = Enigma.getConsole("Cube", 145, 50, 15, 3);

        Cube myCube = new Cube(cn);
        Piece myPiece = new Piece(cn);
        Robot myRobot = new Robot(cn);

        Game myGame = new Game(cn);






    }

}
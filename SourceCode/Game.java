import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    public enigma.console.Console cn;
    public KeyListener klis;
    // ------ Standard Variables for Keyboard ------
    public int keypr;   // Key Pressed ?
    public int rkey;    // Key   (For Press/Release)
    // ----------------------------------------------------
    // Robots
    private Robot human_robot_1;
    private Robot human_robot_2;
    private Robot computer_robot_1;
    private Robot computer_robot_2;

    // Game End Colors
    enigma.console.TextAttributes red = new enigma.console.TextAttributes(Color.RED);
    enigma.console.TextAttributes blue = new enigma.console.TextAttributes(Color.CYAN);
    enigma.console.TextAttributes yellow = new enigma.console.TextAttributes(Color.YELLOW);
    // Cursor Colors
    private enigma.console.TextAttributes cursor = new enigma.console.TextAttributes(Color.ORANGE);
    private enigma.console.TextAttributes white = new enigma.console.TextAttributes(Color.WHITE);

    // Game End Strings
    String[] winRedStr = {"    ____  __________    _______________    __  ____       _______   _______",
            "   / __ \\/ ____/ __ \\  /_  __/ ____/   |  /  |/  / |     / /  _/ | / / ___/",
            "  / /_/ / __/ / / / /   / / / __/ / /| | / /|_/ /| | /| / // //  |/ /\\__ \\ ",
            " / _, _/ /___/ /_/ /   / / / /___/ ___ |/ /  / / | |/ |/ // // /|  /___/ / ",
            "/_/ |_/_____/_____/   /_/ /_____/_/  |_/_/  /_/  |__/|__/___/_/ |_//____/  "};
    String[] winBLueStr = {"    ____  __    __  ________ _______________    __  ____       _______   _______",
            "   / __ )/ /   / / / / ____//_  __/ ____/   |  /  |/  / |     / /  _/ | / / ___/",
            "  / __  / /   / / / / __/    / / / __/ / /| | / /|_/ /| | /| / // //  |/ /\\__ \\ ",
            " / /_/ / /___/ /_/ / /___   / / / /___/ ___ |/ /  / / | |/ |/ // // /|  /___/ / ",
            "/_____/_____/\\____/_____/  /_/ /_____/_/  |_/_/  /_/  |__/|__/___/_/ |_//____/ "};
    String[] teamRed = {"    ____  __________    _______________    __  ___",
                        "   / __ \\/ ____/ __ \\  /_  __/ ____/   |  /  |/  /",
                        "  / /_/ / __/ / / / /   / / / __/ / /| | / /|_/ / ",
                        " / _, _/ /___/ /_/ /   / / / /___/ ___ |/ /  / /  ",
                        "/_/ |_/_____/_____/   /_/ /_____/_/  |_/_/  /_/   "};
    String[] teamBLue = {"    ____  __    __  ________ _______________    __  ___",
                         "   / __ )/ /   / / / / ____//_  __/ ____/   |  /  |/  /",
                         "  / __  / /   / / / / __/    / / / __/ / /| | / /|_/ / ",
                         " / /_/ / /___/ /_/ / /___   / / / /___/ ___ |/ /  / /  ",
                         "/_____/_____/\\____/_____/  /_/ /_____/_/  |_/_/  /_/   "};
    String[] cool_dash = {" ______","/_____/"};

    String[] cool_0 = {"   ____ ",
                       "  / __ \\",
                       " / / / /",
                       "/ /_/ / ",
                       "\\____/  "};
    String[] cool_1 = {"   ___",
                       "  <  /",
                       "  / / ",
                       " / /  ",
                       "/_/   "};

    String[] cool_2 = {"   ___ ",
                       "  |__ \\",
                       "  __/ /",
                       " / __/ ",
                       "/____/ "};
    String[] cool_3 = {"   _____",
                       "  |__  /",
                       "   /_ < ",
                       " ___/ / ",
                       "/____/  "};
    String[][] cool_nums = new String[][]{cool_0,cool_1,cool_2,cool_3};

    int[][] pieceCoords = {{50, 3}, {68, 3}, {86, 3}, {104, 3}, {50, 18}, {68, 18}, {86, 18}, {104, 18}, {50, 33}, {64, 33}, {78, 33}, {92, 33}, {106, 33}, {50, 44}, {60, 44}, {70, 44}, {80, 44}, {90, 44}, {100, 44}, {110, 44}};
    int[] piecesGridCounts = new int[]{4, 4, 5, 7};

    Piece[][] pieces = new Piece[4][];
    // Piece Cursor Start Positions
    int ps_start_x = 1;
    int ps_start_y = 30;

    Game(enigma.console.Console cn) throws Exception {   // --- Contructor
        this.cn = cn;
        human_robot_1 = new Robot(1);
        Robot robot = human_robot_1;
        robot.printGrid();
        infoScreen();
        GeneratePieces();

        // ------ Creating Keyboard Stream Listener ------
        klis = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
                if (keypr == 0) {
                    keypr = 1;
                    rkey = e.getKeyCode();
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        };
        cn.getTextWindow().addKeyListener(klis);
        // ----------------------------------------------------
        // Piece Cursor Start Position
        int p_cursor_x = 0, p_cursor_y = 0;
        // Robot Cursor Start Position
        int r_cursor_x = 10, r_cursor_y = 2;

        // Printing Cursors at the Begging of Game
        piecesStatus();
        updatePieceCursor(p_cursor_x, p_cursor_y, '#', cursor);
        updateRobotCursor(r_cursor_x, r_cursor_y, '#');
        // ----------------------------------------------------

        // Listening Keyboard Stream for Cursors and Moves
        while (true) {
            // If Keyboard Button Pressed
            if (keypr == 1) {
                // Clearing Previous Piece Cursor
                if (pieces[p_cursor_y][p_cursor_x].isPreSelected()) {
                    updatePieceCursor(p_cursor_x, p_cursor_y, '=', white);
                }
                else if (pieces[p_cursor_y][p_cursor_x].isSelected()) {
                    updatePieceCursor(p_cursor_x, p_cursor_y, '-', white);
                } else {
                    updatePieceCursor(p_cursor_x, p_cursor_y, ' ', white);
                }
                // ----------------------------------------------------
                updateRobotCursor(r_cursor_x, r_cursor_y, ' ');

                // Piece Info Static Parts
                cn.getTextWindow().setCursorPosition(1, 27);
                cn.getTextWindow().output("Current piece (#):     ");
                cn.getTextWindow().setCursorPosition(1, 28);
                cn.getTextWindow().output("  X:    Y:               ");

                // Piece Cursor Control
                if (rkey == KeyEvent.VK_LEFT) {
                    if (p_cursor_x > 0) {
                        p_cursor_x--;
                    }
                }
                if (rkey == KeyEvent.VK_RIGHT) {
                    if (pieces[p_cursor_y].length > p_cursor_x + 1) {
                        p_cursor_x++;
                    }
                }
                if (rkey == KeyEvent.VK_UP) {
                    if (p_cursor_y > 0) {
                        if (p_cursor_x >= pieces[p_cursor_y - 1].length) {
                            p_cursor_x = pieces[p_cursor_y - 1].length - 1;
                        }
                        p_cursor_y--;
                    }
                }
                if (rkey == KeyEvent.VK_DOWN) {
                    if (p_cursor_y < 3) {
                        p_cursor_y++;
                    }

                }
                // ----------------------------------------------------
                // Clear Previous Robot Cursor
                updateRobotCursor(r_cursor_x, r_cursor_y, ' ');
                // Robot Cursor Control
                if (rkey == KeyEvent.VK_A) {
                    if (r_cursor_x > 2) {
                        if (robot.getGrid()[(r_cursor_y - 2) / 4][(r_cursor_x - 6) / 4] != null) {
                            r_cursor_x -= 4;
                        }

                    }

                }
                if (rkey == KeyEvent.VK_D) {
                    if (18 > r_cursor_x) {
                        if (robot.getGrid()[(r_cursor_y - 2) / 4][(r_cursor_x + 2) / 4] != null) {
                            r_cursor_x += 4;
                        }

                    }
                }
                if (rkey == KeyEvent.VK_W) {
                    if (r_cursor_y > 2) {
                        if (robot.getGrid()[(r_cursor_y - 6) / 4][(r_cursor_x - 2) / 4] != null) {
                            r_cursor_y -= 4;
                        }
                    }
                }
                if (rkey == KeyEvent.VK_S) {
                    if (r_cursor_y < 18) {
                        if (robot.getGrid()[(r_cursor_y + 2) / 4][(r_cursor_x - 2) / 4] != null) {
                            r_cursor_y += 4;
                        }
                    }

                }
                // ----------------------------------------------------

                // Updating Robot Designing Area
                robot.printRobot(robot.getCube(r_cursor_x,r_cursor_y).getCubeNum());

                // --Move Actions
                // -Placing Piece
                if (rkey == KeyEvent.VK_1) {
                    if (!pieces[p_cursor_y][p_cursor_x].isSelected()) {
                        robot.placePiece(pieces[p_cursor_y][p_cursor_x], (r_cursor_x - 2) / 4, (r_cursor_y - 2) / 4);
                    }
                    robot.fillRobot();
                    UpdateInfoScreen(robot);
                    updateUsed();
                    updateSelections();

                }
                // -Erasing Piece
                if (rkey == KeyEvent.VK_2) {

                    int curr_piece_num = robot.erasePiece((r_cursor_x - 2) / 4, (r_cursor_y - 2) / 4);



                    for (int i = 0; i < pieces.length; i++) {
                        for (int j = 0; j < pieces[i].length; j++) {
                            if (pieces[i][j].getPiece_num() == curr_piece_num) {
                                pieces[i][j].setSelected(false);
                                break;
                            }
                        }
                    }
                    robot.fillRobot();
                    UpdateInfoScreen(robot);
                    updateUsed();
                    updateSelections();
                }
                // -Rotating Piece to Left
                if (rkey == KeyEvent.VK_3) {
                    if (!pieces[p_cursor_y][p_cursor_x].isSelected()) {
                        pieces[p_cursor_y][p_cursor_x].turnLeft();
                    }
                }
                // -Rotating Piece to Right
                if (rkey == KeyEvent.VK_4) {
                    if (!pieces[p_cursor_y][p_cursor_x].isSelected()) {
                        pieces[p_cursor_y][p_cursor_x].turnRight();
                    }
                }
                // -Reversing Piece
                if (rkey == KeyEvent.VK_5) {
                    if (!pieces[p_cursor_y][p_cursor_x].isSelected()) {
                        pieces[p_cursor_y][p_cursor_x].reversePiece();
                    }
                }
                // -Completing Current Robot
                if (rkey == KeyEvent.VK_ENTER) {
                    // Actions For First Robot Compilation
                    if (!human_robot_1.isRobotCompleted()) {
                        if (human_robot_1.isRobotFilled()) {
                            // Completing First Robot
                            computer_robot_1 = new Robot();
                            human_robot_2 = new Robot(1);
                            human_robot_1.setRobotCompleted(true);
                            // Preparing The Screen for Second Robot
                            robot = human_robot_2;
                            robot.printGrid();
                            infoScreen();
                            infoSecondStage();
                            UpdateInfoScreen(robot);
                            updatePreSelects();
                            updateSelections();
                            updateUsed();
                        }
                    // Actions For First Robot Compilation
                    } else {
                        if (human_robot_2.isRobotFilled()) {
                            computer_robot_2 = new Robot();
                            break;
                        }

                    }
                }
                // ----------------------------------------------------

                // Updating Robot Cursor
                updateRobotCursor(r_cursor_x, r_cursor_y, '#');

                // Updating Piece Coordinates
                cn.getTextWindow().setCursorPosition(3, 28);
                cn.getTextWindow().output("X:" + (p_cursor_x + 1) + "   Y:" + (p_cursor_y + 1));

                // Updating Piece Cursor
                if (pieces[p_cursor_y][p_cursor_x].isPreSelected()) {
                    updatePieceCursor(p_cursor_x, p_cursor_y, '=', cursor);
                }
                else if (pieces[p_cursor_y][p_cursor_x].isSelected()) {
                    updatePieceCursor(p_cursor_x, p_cursor_y, '-', cursor);
                } else {
                    updatePieceCursor(p_cursor_x, p_cursor_y, '#', cursor);
                }
                // ----------------------------------------------------
                keypr = 0;
            }
            Thread.sleep(2);
        }

        // Preparing the Screen for RoboGames
        clearScreen();
        // Creating Teams
        Team teamh = new Team("red", human_robot_1, human_robot_2);
        Team teamc = new Team("blue", computer_robot_1, computer_robot_2);
        RoboGames rbGames = new RoboGames(teamh, teamc);
        // Playing RoboChess Game
        rbGames.playRoboChess(0, 0, cn);
        Thread.sleep(1000);
        // Playing RoboPingPong Game
        rbGames.playRoboPingPong(0, 15, cn);
        Thread.sleep(1000);
        // Playing RoboRun
        rbGames.playRoboRun(0, 30, cn);

        // Printing the Games Result
        printCool(cool_nums[teamh.getTeampoint()], blue,90,10);
        printCool(cool_dash, yellow, 100, 12);
        printCool(cool_nums[teamc.getTeampoint()], red,109,10);

        // Printing which Team Won
        if (teamh.getTeampoint()>=2){
            printCool(winBLueStr, blue,65,20);
        } else if (teamc.getTeampoint()>=2) {
            printCool(winRedStr, red,65,20);
        }

    }

    // Printing ASCII Art Texts
    void printCool(String[] str, enigma.console.TextAttributes color, int x, int y){
        for (int i = 0;i<str.length;i++){
            cn.getTextWindow().setCursorPosition(x,y+i);
            cn.getTextWindow().output(str[i], color);
        }
    }
    // Generating 20 Pieces at Beginning of the Game
    void GeneratePieces() {
        Color[] colors = {Color.CYAN, Color.MAGENTA, Color.GREEN, Color.RED};
        int count = 0;
        for (int i = 0; i < piecesGridCounts.length; i++) {
            Piece[] arr = new Piece[piecesGridCounts[i]];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = new Piece(pieceCoords[count][0], pieceCoords[count][1], 4 - i, count + 1, colors[3 - i]);
                count++;
            }
            pieces[i] = arr;
        }


        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j].printPiece();
            }
        }
    }
    // Displaying Info About Currently Selected Piece
    void piecesStatus() {
        cn.getTextWindow().setCursorPosition(1, 27);
        cn.getTextWindow().output("Current piece (#):");
        cn.getTextWindow().setCursorPosition(1, 28);
        cn.getTextWindow().output("  X:    Y:   ");
        cn.getTextWindow().setCursorPosition(1, 29);
        cn.getTextWindow().output("Used pieces (=/-): ");
    }
    // Displaying Piece Cursor
    void updatePieceCursor(int cx, int cy, char ch, enigma.console.TextAttributes color) {
        Piece piece = pieces[cy][cx];
        int px = piece.getPiece_x();
        int py = piece.getPiece_y();

        cn.getTextWindow().setCursorPosition(px - 3, py - 1);
        cn.getTextWindow().output(String.format("%c%c%c", ch, ch, ch), color);
        cn.getTextWindow().setCursorPosition(px - 3, py);
        cn.getTextWindow().output(ch, color);
        cn.getTextWindow().setCursorPosition(px - 3, py + 1);
        cn.getTextWindow().output(String.format("%c%c%c", ch, ch, ch), color);
        cn.getTextWindow().setCursorPosition(20, 27);
        cn.getTextWindow().output("   ");
        cn.getTextWindow().setCursorPosition(20, 27);
        cn.getTextWindow().output(" " + piece.getPiece_num(),color);


        cn.getTextWindow().setCursorPosition(5, 28);
        cn.getTextWindow().output("" + (cx + 1) + "   Y:" + (cy + 1));
    }
    // Displaying Robot Cursor
    void updateRobotCursor(int r_cursor_x, int r_cursor_y, char ch) {
        cn.getTextWindow().setCursorPosition(r_cursor_x+1, r_cursor_y+1);
        cn.getTextWindow().output(ch, cursor);
        cn.getTextWindow().setCursorPosition(r_cursor_x+3, r_cursor_y+1);
        cn.getTextWindow().output(ch, cursor);
        cn.getTextWindow().setCursorPosition(r_cursor_x+2, r_cursor_y+2);
        cn.getTextWindow().output(ch, cursor);
        cn.getTextWindow().setCursorPosition(r_cursor_x+1, r_cursor_y+3);
        cn.getTextWindow().output(ch, cursor);
        cn.getTextWindow().setCursorPosition(r_cursor_x+3, r_cursor_y+3);
        cn.getTextWindow().output(ch, cursor);


    }
    // Printing Static Parts of the Game (Pieces title, Piece Coords Title etc. )
    void infoScreen() {
        cn.getTextWindow().setCursorPosition(50, 1);
        cn.getTextWindow().output("------------------------  P  I  E  C  E  S  ------------------------");
        cn.getTextWindow().setCursorPosition(1, 30);
        cn.getTextWindow().output(" 01   02   03   04");
        cn.getTextWindow().setCursorPosition(1, 31);
        cn.getTextWindow().output(" 05   06   07   08");
        cn.getTextWindow().setCursorPosition(1, 32);
        cn.getTextWindow().output(" 09   10   11   12   13");
        cn.getTextWindow().setCursorPosition(1, 33);
        cn.getTextWindow().output(" 14   15   16   17   18   19   20");
        cn.getTextWindow().setCursorPosition(1, 35);
        cn.getTextWindow().output("Human Robot 1 (HR1)");
        cn.getTextWindow().setCursorPosition(3, 36);
        cn.getTextWindow().output("Intelligence : ");
        cn.getTextWindow().setCursorPosition(3, 37);
        cn.getTextWindow().output("Skill : ");
        cn.getTextWindow().setCursorPosition(5, 38);
        cn.getTextWindow().output("Left arm : ");
        cn.getTextWindow().setCursorPosition(5, 39);
        cn.getTextWindow().output("Right arm: ");
        cn.getTextWindow().setCursorPosition(3, 40);
        cn.getTextWindow().output("Speed : ");
        cn.getTextWindow().setCursorPosition(5, 41);
        cn.getTextWindow().output("Left leg : ");
        cn.getTextWindow().setCursorPosition(5, 42);
        cn.getTextWindow().output("Right leg: ");

    }
    void clearScreen() {
        for (int i = 0; i < 49; i++) {
            cn.getTextWindow().setCursorPosition(0, i);
            cn.getTextWindow().output("                                                                           ");
            cn.getTextWindow().output("                                                ");
        }
    }
    // Displaying Info About Current Robot
    void UpdateInfoScreen(Robot robot) {
        cn.getTextWindow().setCursorPosition(3, 36);
        cn.getTextWindow().output("Intelligence :      ");
        cn.getTextWindow().setCursorPosition(3, 37);
        cn.getTextWindow().output("Skill :           ");
        cn.getTextWindow().setCursorPosition(5, 38);
        cn.getTextWindow().output("Left arm :          ");
        cn.getTextWindow().setCursorPosition(5, 39);
        cn.getTextWindow().output("Right arm:          ");
        cn.getTextWindow().setCursorPosition(3, 40);
        cn.getTextWindow().output("Speed :      ");
        cn.getTextWindow().setCursorPosition(5, 41);
        cn.getTextWindow().output("Left leg :          ");
        cn.getTextWindow().setCursorPosition(5, 42);
        cn.getTextWindow().output("Right leg:          ");


        cn.getTextWindow().setCursorPosition(3, 36);
        cn.getTextWindow().output("Intelligence : " + robot.getIntelligence());
        cn.getTextWindow().setCursorPosition(3, 37);
        cn.getTextWindow().output("Skill : " + robot.getSkill());
        cn.getTextWindow().setCursorPosition(5, 38);
        cn.getTextWindow().output("Left arm : " + robot.getLa());
        cn.getTextWindow().setCursorPosition(5, 39);
        cn.getTextWindow().output("Right arm: " + robot.getRa());
        cn.getTextWindow().setCursorPosition(3, 40);
        cn.getTextWindow().output("Speed : " + robot.getSpeed());
        cn.getTextWindow().setCursorPosition(5, 41);
        cn.getTextWindow().output("Left leg : " + robot.getLl());
        cn.getTextWindow().setCursorPosition(5, 42);
        cn.getTextWindow().output("Right leg: " + robot.getRl());

    }
    // Changing Info Area According to Second Robot Creating Part
    void infoSecondStage() {
        cn.getTextWindow().setCursorPosition(1, 35);
        cn.getTextWindow().output("Human Robot 2 (HR2)");
        cn.getTextWindow().setCursorPosition(1, 44);
        cn.getTextWindow().output("Human Robot 1 (HR1)");
        cn.getTextWindow().setCursorPosition(1, 45);
        cn.getTextWindow().output(" In: " + human_robot_1.getIntelligence() +
                "   Sk: " + human_robot_1.getSkill() +
                "   Sp: " + human_robot_1.getSpeed() + "   ");
        cn.getTextWindow().setCursorPosition(1, 47);
        cn.getTextWindow().output("Computer Robot 1 (CR1)");
        cn.getTextWindow().setCursorPosition(1, 48);
        cn.getTextWindow().output(" In: " + computer_robot_1.getIntelligence() +
                "   Sk: " + computer_robot_1.getSkill() +
                "   Sp: " + computer_robot_1.getSpeed() + "   ");

    }
    // Changing Piece Info Area According to Current State of Pieces
    void updateUsed() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                cn.getTextWindow().setCursorPosition(ps_start_x+(j*5), ps_start_y+i);
                if(pieces[i][j].isPreSelected()){
                    cn.getTextWindow().output('=');
                }
                else if (pieces[i][j].isSelected()) {
                    cn.getTextWindow().output('-');
                } else {
                    cn.getTextWindow().output(' ');
                }
            }
        }
    }
    // Changing Piece Cursor According to Current State of Pieces
    void updateSelections(){
        int index  = 0;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].isPreSelected()) {
                    updatePieceCursor(j,i,'=', white);
                }
                else if (pieces[i][j].isSelected()) {
                    updatePieceCursor(j,i,'-',white);
                } else {
                    updatePieceCursor(j,i,' ',white);
                }
                index++;
            }
        }
    }

    // Marking First Robot Pieces as PreSelected when Human Start to Design Second Robot
    void updatePreSelects(){
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].isSelected()) {
                    pieces[i][j].setPreSelected(true);
                }
            }
        }
    }
}

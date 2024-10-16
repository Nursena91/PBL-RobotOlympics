import enigma.console.TextAttributes;

import java.util.Random;
import java.awt.*;

public class Piece {

    private static enigma.console.Console cn;
    private Random rnd = new Random();
    private Cube cube = new Cube();
    private Cube[][] grid;
    private int piece_x;
    private int piece_y;
    private int piece_size;
    private int piece_num;
    private boolean isPreSelected = false;
    private boolean isSelected = false;
    private TextAttributes piece_color = new TextAttributes(Color.WHITE);


    public Piece(enigma.console.Console cn) {
        this.cn = cn;
    }

    public Piece(int x, int y, int piece_size, int piece_num , Color color) {
        this.piece_x = x;
        this.piece_y = y;
        this.piece_num = piece_num;
        this.piece_size = piece_size;
        piece_color = new TextAttributes(color);
        grid = shapeSelector();

        // piece are randomly rotated
        for (int j = 0; j < rnd.nextInt(4); j++) {
            turnRight();
        }

        for (int j = 0; j < rnd.nextInt(2); j++) {
            reversePiece();
        }
    }

    public int getPiece_num() {
        return piece_num;
    }

    public int getPiece_x() {
        return piece_x;
    }

    public int getPiece_y() {
        return piece_y;
    }

    public Cube[][] getGrid() {
        return grid;
    }

    public Cube[][] shapeSelector() {
        int piecePoint;
        int shape;

        switch (piece_size) {
            case 4:
                piecePoint = 24;//extra piece score
                shape = rnd.nextInt(4) + 1;
                //shapes with 4 cube
                switch (shape) {

                    case 1:
                        Cube[][] shapeL = {{new Cube(piecePoint, piece_num, piece_color), null, null},
                                           {new Cube(piecePoint, piece_num, piece_color), null, null},
                                           {new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), null}};

                        return shapeL;

                    case 2:
                        Cube[][] shapeT = {{null, new Cube(piecePoint, piece_num, piece_color), null}, {new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color)}, {null, null, null}};

                        return shapeT;

                    case 3:
                        Cube[][] shapeZ = {{null, new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color)}, {new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), null}, {null, null, null}};

                        return shapeZ;

                    case 4:
                        Cube[][] shapeO = {{new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), null}, {new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), null}, {null, null, null}};

                        return shapeO;

                }
                break;


            case 3:
                piecePoint = 12;//extra piece score
                shape = rnd.nextInt(2) + 1;
                //shapes with 3 cube
                switch (shape) {

                    case 1:
                        Cube[][] shapeI = {{new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color)}, {null, null, null}, {null, null, null}};

                        return shapeI;

                    case 2:
                        Cube[][] shapeL = {{new Cube(piecePoint, piece_num, piece_color), new Cube(piecePoint, piece_num, piece_color), null}, {new Cube(piecePoint, piece_num, piece_color), null, null}, {null, null, null}};

                        return shapeL;

                }
                break;
            case 2:
                piecePoint = 6;//extra piece score
                //shape with 2 cubes
                Cube[][] shapeI = {{new Cube(piecePoint, piece_num, piece_color), null},
                                   {new Cube(piecePoint, piece_num, piece_color), null}};
                return shapeI;
            case 1:
                Cube[][] shape1 = {{new Cube(0, piece_num, piece_color)}};
                return shape1;


        }

        return null;
    }

    public void printPoint() {
        int[] row = {0, 0, 0};
        int[] column = {0, 0, 0};
        // 4 ve 3 parçalı olanlar
        switch (piece_size) {
            case 4,3:
                //row points are calculated
                for (int i = 0; i < 3; i++) {
                    int counter = 0;
                    for (int j = 0; j < 3; j++) {

                        if (grid[i][j] != null) {
                            row[i] = row[i] + grid[i][j].getX_force();
                            counter++;
                        }
                    }
                    if (row[i] != 0) {
                        row[i] = row[i] / counter;
                    }
                }
                // column points are calculated
                for (int i = 0; i < 3; i++) {
                    int counter = 0;
                    for (int j = 0; j < 3; j++) {

                        if (grid[j][i] != null) {
                            column[i] = column[i] + grid[j][i].getY_force();
                            counter++;
                        }
                    }
                    if (column[i] != 0) {
                        column[i] = column[i] / counter;
                    }
                }
                // column points are printed
                for (int i = 0; i < 3; i++) {
                    cn.getTextWindow().setCursorPosition(piece_x + 2 + (i * 4), piece_y + 13);
                    if (column[i] != 0) {
                        System.out.print(column[i]);
                    } else
                        System.out.print("00");

                }
                // row points are printed
                for (int i = 0; i < 3; i++) {
                    cn.getTextWindow().setCursorPosition(piece_x + 13, piece_y + 2 + (i * 4));
                    if (row[i] != 0)
                        System.out.print(row[i]);
                    else
                        System.out.print("00");
                }
                break;
            // 2 parçalı olanlar
            case 2:


                for (int i = 0; i < 2; i++) {
                    int counter = 0;
                    for (int j = 0; j < 2; j++) {

                        if (grid[i][j] != null) {
                            row[i] = row[i] + grid[i][j].getX_force();
                            counter++;
                        }


                    }
                    if (row[i] != 0) {
                        row[i] = row[i] / counter;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    int counter = 0;
                    for (int j = 0; j < 2; j++) {

                        if (grid[j][i] != null) {
                            column[i] = column[i] + grid[j][i].getY_force();
                            counter++;
                        }

                    }
                    if (column[i] != 0) {
                        column[i] = column[i] / counter;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    cn.getTextWindow().setCursorPosition(piece_x + 2 + (i * 4), piece_y + 9);
                    if (column[i] != 0) {
                        System.out.print(column[i]);
                    } else
                        System.out.print("00");

                }
                for (int i = 0; i < 2; i++) {
                    cn.getTextWindow().setCursorPosition(piece_x + 9, piece_y + 2 + (i * 4));
                    if (row[i] != 0)
                        System.out.print(row[i]);
                    else
                        System.out.print("00");
                }
                break;
        }
    }

    public void printPiece() {
        switch (piece_size) {
            case 4,3:
                //piece number is printed
                cn.getTextWindow().setCursorPosition(piece_x - 2, piece_y);
                cn.getTextWindow().output("0" + (piece_num));
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //if there is no cube, empty cube shape is printed
                        if (grid[i][j] == null) {
                            cube.emptyBox(piece_x + (j * 4), piece_y + (i * 4));
                        }

                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (grid[i][j] != null) {
                            //the cubes are printed
                            grid[i][j].show(piece_x + (j * 4), piece_y + (i * 4),piece_color);
                        }
                    }
                }
                printPoint();
                break;

            case 2:
                //piece number is printed
                if (piece_num == 9) {
                    cn.getTextWindow().setCursorPosition(piece_x - 2, piece_y);
                    cn.getTextWindow().output("0" + (piece_num));
                } else {
                    cn.getTextWindow().setCursorPosition(piece_x - 2, piece_y);
                    cn.getTextWindow().output("" + (piece_num));
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {

                        if (grid[i][j] == null) {
                            //if there is no cube, empty cube shape is printed
                            cube.emptyBox(piece_x + (j * 4), piece_y + (i * 4));
                        }

                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {

                        if (grid[i][j] != null) {
                            //cubes are printed
                            grid[i][j].show(piece_x + (j * 4), piece_y + (i * 4),piece_color);
                        }
                    }
                }
                printPoint();
                break;
            case 1:
                //piece number is printed
                cn.getTextWindow().setCursorPosition(piece_x - 2, piece_y);
                cn.getTextWindow().output("" + (piece_num));
                //cube is printed
                grid[0][0].show(piece_x, piece_y,piece_color);
                break;
        }
    }

    private void shiftCorner() {
        switch (piece_size) {

            case 4,3:
                //cubes are shifted up
                while (grid[0][0] == null && grid[1][0] == null && grid[2][0] == null) {
                    grid[0][0] = grid[0][1];
                    grid[0][1] = grid[0][2];
                    grid[0][2] = null;

                    grid[1][0] = grid[1][1];
                    grid[1][1] = grid[1][2];
                    grid[1][2] = null;

                    grid[2][0] = grid[2][1];
                    grid[2][1] = grid[2][2];
                    grid[2][2] = null;
                }
                //cubes are shifted to left
                while (grid[0][0] == null && grid[0][1] == null && grid[0][2] == null) {
                    grid[0][0] = grid[1][0];
                    grid[1][0] = grid[2][0];
                    grid[2][0] = null;

                    grid[0][1] = grid[1][1];
                    grid[1][1] = grid[2][1];
                    grid[2][1] = null;

                    grid[0][2] = grid[1][2];
                    grid[1][2] = grid[2][2];
                    grid[2][2] = null;
                }
                break;
            case 2:
                //cubes are shifted to up
                if (grid[0][0] == null && grid[1][0] == null) {
                    grid[0][0] = grid[0][1];
                    grid[0][1] = null;
                    grid[1][0] = grid[1][1];
                    grid[1][1] = null;
                }
                //cubes are shifted to left
                if (grid[0][0] == null && grid[0][1] == null) {
                    grid[0][0] = grid[1][0];
                    grid[1][0] = null;
                    grid[0][1] = grid[1][1];
                    grid[1][1] = null;
                }
                break;
        }
    }

    public void turnRight() {
        Cube temp;

        switch (piece_size) {
            case 4, 3:
                // corners are shifted to right
                temp = grid[0][0];
                grid[0][0] = grid[2][0];
                grid[2][0] = grid[2][2];
                grid[2][2] = grid[0][2];
                grid[0][2] = temp;
                // edges are shifted to right
                temp = grid[0][1];
                grid[0][1] = grid[1][0];
                grid[1][0] = grid[2][1];
                grid[2][1] = grid[1][2];
                grid[1][2] = temp;
                //cubes are rotated
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (grid[i][j] != null) {
                            grid[i][j].rotate();
                        }
                    }
                }
                shiftCorner();

                printPiece();
                break;

            case 2:
                temp = grid[0][0];
                grid[0][0] = grid[1][0];
                grid[1][0] = grid[1][1];
                grid[1][1] = grid[0][1];
                grid[0][1] = temp;

                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {

                        if (grid[i][j] != null) {
                            grid[i][j].rotate();
                        }
                    }
                }

                shiftCorner();
                printPiece();
            case 1:
                grid[0][0].rotate();
                printPiece();
        }
    }

    public void turnLeft() {
        Cube temp;

        switch (piece_size) {
            case 4, 3:
                // corners are shifted to left
                temp = grid[0][0];
                grid[0][0] = grid[0][2];
                grid[0][2] = grid[2][2];
                grid[2][2] = grid[2][0];
                grid[2][0] = temp;
                // edges are shifted to left
                temp = grid[0][1];
                grid[0][1] = grid[1][2];
                grid[1][2] = grid[2][1];
                grid[2][1] = grid[1][0];
                grid[1][0] = temp;
                //cubes are rotated
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (grid[i][j] != null) {
                            grid[i][j].rotate();
                        }
                    }
                }

                shiftCorner();

                printPiece();
                break;
            case 2:
                // corners are shifted to left
                temp = grid[0][0];
                grid[0][0] = grid[0][1];
                grid[0][1] = grid[1][1];
                grid[1][1] = grid[1][0];
                grid[1][0] = temp;


                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {

                        if (grid[i][j] != null) {
                            grid[i][j].rotate();
                        }
                    }
                }

                shiftCorner();

                printPiece();
                break;
            case 1:
                grid[0][0].rotate();
                printPiece();
        }
    }

    public void reversePiece() {//it changes left and right side cubes
        Cube temp;

        switch (piece_size) {
            case 4:

                temp = grid[0][0];
                grid[0][0] = grid[0][2];
                grid[0][2] = temp;
                temp = grid[1][0];
                grid[1][0] = grid[1][2];
                grid[1][2] = temp;
                temp = grid[2][0];
                grid[2][0] = grid[2][2];
                grid[2][2] = temp;

                shiftCorner();

                printPiece();
                break;
            case 3:

                temp = grid[0][0];
                grid[0][0] = grid[0][2];
                grid[0][2] = temp;
                temp = grid[1][0];
                grid[1][0] = grid[1][2];
                grid[1][2] = temp;
                temp = grid[2][0];
                grid[2][0] = grid[2][2];
                grid[2][2] = temp;


                shiftCorner();

                printPiece();
                break;

            case 2:

                temp = grid[0][0];
                grid[0][0] = grid[0][1];
                grid[0][1] = temp;
                temp = grid[1][0];
                grid[1][0] = grid[1][1];
                grid[1][1] = temp;

                shiftCorner();

                printPiece();
                break;
            case 1:
                printPiece();
        }
    }

    public boolean isPreSelected() {
        return isPreSelected;
    }

    public void setPreSelected(boolean preSelected) {
        isPreSelected = preSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
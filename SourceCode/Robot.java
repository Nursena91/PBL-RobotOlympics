import enigma.console.Console;

import java.awt.*;

import static java.lang.Math.min;

public class Robot {
    private static Console cn;
    private Cube[] cubes = new Cube[14];
    private enigma.console.TextAttributes white = new enigma.console.TextAttributes(Color.WHITE);
    private final int[][][] cubes_cor = new int[][][]{{{0, 2}, {1, 2}, {2, 2}, {3, 2}},
            {{1, 0}, {1, 1}, {1, 3}, {1, 4}},
            {{2, 1}, {3, 1}, {4, 1}, {2, 3}, {3, 3}, {4, 3}}};
    private Cube[][] grid;
    private int intelligence;
    private int speed;
    private int skill;
    private boolean is_robot_completed = false;
    // Left Arm
    private int la;
    // Right Arm
    private int ra;
    // Left Leg
    private int ll;
    // Right Leg
    private int rl;

    // Computer Constructor
    Robot() {
        for (int i = 0; i < 14; i++) {
            Cube c = new Cube();
            cubes[i] = c;
        }
        calculateIntel();
        calculateSkill();
        calculateSpeed();

    }
    // Human Constructor
    Robot(int a) {
        grid = new Cube[][]{{null, null, new Cube(true), null, null},
                {new Cube(true), new Cube(true), new Cube(true), new Cube(true), new Cube(true)},
                {null, new Cube(true), new Cube(true), new Cube(true), null},
                {null, new Cube(true), new Cube(true), new Cube(true), null},
                {null, new Cube(true), null, new Cube(true), null}};
    }

    // Console Define Constructor
    Robot(Console cn) {
        this.cn = cn;
    }

    // Printing Robot Design Area
    public void printGrid() {
        new Cube(cn);
        int start_x = 2;
        int start_y = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[j][i] != null) {
                    new Cube().emptyBox(start_x + (i * 4), start_y + (j * 4));
                }

            }
        }
    }

    public void printRobot(int piece_num) {
        int start_x = 2;
        int start_y = 2;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[j][i] != null) {
                    new Cube().emptyBox(start_x + (i * 4), start_y + (j * 4));
                }

            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null && !grid[i][j].isEmpty()) {
                    grid[i][j].show(start_x + (j * 4), start_y + (i * 4), white);
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null && !grid[i][j].isEmpty() && grid[i][j].getCubeNum() == piece_num) {
                    grid[i][j].show(start_x + (j * 4), start_y + (i * 4), grid[i][j].getCube_color());
                }
            }
        }

    }

    // Placing a Piece to Robot
    public void placePiece(Piece piece, int x, int y) {
        // Setting Pointer

        boolean ref_assigned = false;
        for (int i = 0; i < piece.getGrid().length; i++) {
            for (int j = 0; j < piece.getGrid()[i].length; j++) {
                if (piece.getGrid()[i][j] != null) {
                    x -= j;
                    y -= i;
                    ref_assigned = true;
                    break;
                }
            }
            if (ref_assigned) {
                break;
            }
        }

        // Checking for that piece exceeds robot grid limit
        for (int i = 0; i < piece.getGrid().length; i++) {
            for (int j = 0; j < piece.getGrid()[i].length; j++) {
                if (piece.getGrid()[i][j] != null && ((i + y >= grid[0].length) || (j + x >= grid[0].length))) {
                    // Place is outside of grid so terminate the method
                    return;
                }
            }
        }

        // Checking for the piece is valid in the robot grid
        for (int i = 0; i < piece.getGrid().length; i++) {
            for (int j = 0; j < piece.getGrid()[i].length; j++) {
                if (piece.getGrid()[i][j] != null) {
                    // Place is not in robot limits or not empty so terminate the method
                    if (grid[i + y][j + x] == null || !grid[i + y][j + x].isEmpty()) {
                        return;
                    }
                }
            }
        }

        // Placing cubes to the robot
        for (int i = 0; i < piece.getGrid().length; i++) {
            for (int j = 0; j < piece.getGrid()[i].length; j++) {
                if (piece.getGrid()[i][j] != null) {
                    grid[i + y][j + x] = piece.getGrid()[i][j];
                    piece.setSelected(true);
                }
            }
        }

        printRobot(piece.getPiece_num());
    }
    // Erasing a Piece from Robot
    public int erasePiece(int x, int y) {
        int curr_piece_num = grid[y][x].getCubeNum();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null && grid[i][j].getCubeNum() == curr_piece_num) {
                    grid[i][j] = new Cube(true);
                }
            }
        }
        printRobot(curr_piece_num);
        return curr_piece_num;
    }

    // Filling Cube Array for Power Calculations
    public void fillRobot() {
        int index = 0;
        for (int i = 0; i < cubes_cor.length; i++) {
            for (int j = 0; j < cubes_cor[i].length; j++) {
                int x = cubes_cor[i][j][1];
                int y = cubes_cor[i][j][0];
                cubes[index] = grid[y][x];
                index++;
            }
        }

        calculateIntel();
        calculateSkill();
        calculateSpeed();
    }
    // Checking that Robot is Completely Filled
    public boolean isRobotFilled() {
        for (int i = 0; i < cubes_cor.length; i++) {
            for (int j = 0; j < cubes_cor[i].length; j++) {
                int x = cubes_cor[i][j][0];
                int y = cubes_cor[i][j][1];
                if (grid[y][x] != null && grid[y][x].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }


    // For Power Calculations
    public void calculateIntel() {
        intelligence = 0;
        for (int i = 0; i < 4; i++) {
            if (cubes[i] != null) {
                intelligence += cubes[i].getY_force();
            }
        }
    }

    public void calculateSkill() {
        la = 0;
        ra = 0;
        skill = 0;
        double armbalance = 0;

        for (int i = 5; i < 7; i++) {
            if (cubes[i] != null) {
                la += cubes[i].getX_force();
            }
        }
        for (int i = 7; i < 9; i++) {
            if (cubes[i] != null) {
                ra += cubes[i].getY_force();
            }
        }
        armbalance = (double) Math.max(ra, la) / min(ra, la);
        skill = (int) ((la + ra) / armbalance);
    }

    public void calculateSpeed() {
        ll = 0;
        rl = 0;
        double legbalance = 0;
        speed = 0;
        for (int i = 5; i < 7; i++) {
            if (cubes[i] != null) {
                ll += cubes[i].getY_force();
            }
        }
        for (int i = 7; i < 9; i++) {
            if (cubes[i] != null) {
                rl += cubes[i].getY_force();
            }
        }
        legbalance = (double) Math.max(rl, ll) / min(rl, ll);
        speed = (int) ((ll + rl) / legbalance);
    }


    // Getters and Setters
    public Cube getCube(int cursor_x, int cursor_y) {
        return grid[(cursor_y - 2) / 4][(cursor_x - 2) / 4];
    }
    public int getIntelligence() {
        return intelligence;
    }

    private void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSpeed() {
        return speed;
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSkill() {
        return skill;
    }

    private void setSkill(int skill) {
        this.skill = skill;
    }

    public int getLa() {
        return la;
    }

    public int getRa() {
        return ra;
    }

    public int getLl() {
        return ll;
    }

    public int getRl() {
        return rl;
    }

    public Cube[] getCubes() {
        return cubes;
    }

    private void setCubes(Cube[] cubes) {
        this.cubes = cubes;
    }
    public Cube[][] getGrid(){return grid;}
    public boolean isRobotCompleted() {
        return is_robot_completed;
    }

    public void setRobotCompleted(boolean is_robot_completed) {
        this.is_robot_completed = is_robot_completed;
    }
}
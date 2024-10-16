import java.awt.*;
import java.util.Random;

public class RoboGames {

    private Team teamh;
    private Team teamc;
    enigma.console.TextAttributes blue = new enigma.console.TextAttributes(Color.CYAN);
    enigma.console.TextAttributes red = new enigma.console.TextAttributes(Color.RED);

    enigma.console.TextAttributes yellow = new enigma.console.TextAttributes(Color.yellow);

    public RoboGames(Team teamh, Team teamc) {
        this.teamh = teamh;
        this.teamc = teamc;

    }

    public Team getTeamh() {
        return teamh;
    }


    public void setTeamh(Team teamh) {
        this.teamh = teamh;
    }


    public Team getTeamc() {
        return teamc;
    }


    public void setTeamc(Team teamc) {
        this.teamc = teamc;
    }


    public void playRoboChess(int x, int y, enigma.console.Console cn) {
        cn.getTextWindow().setCursorPosition(x, y);
        ;
        // gameplay screen
        cn.getTextWindow().output("====== " + "RoboChess" + " ========================================\n\n\n",yellow);
        cn.getTextWindow().output("HR1 - In:" + teamh.getR1().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 3);
        cn.getTextWindow().output("Sk:" + teamh.getR1().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 3);
        cn.getTextWindow().output("Sp:" + teamh.getR1().getSpeed() + "\n\n");

        cn.getTextWindow().output("HR2 - In:" + teamh.getR2().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 5);
        cn.getTextWindow().output("Sk:" + teamh.getR2().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 5);
        cn.getTextWindow().output("Sp:" + teamh.getR2().getSpeed() + "\n\n");

        cn.getTextWindow().output("CR1 - In:" + teamc.getR1().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 7);
        cn.getTextWindow().output("Sk:" + teamc.getR1().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 7);
        cn.getTextWindow().output("Sp:" + teamc.getR1().getSpeed() + "\n\n");

        cn.getTextWindow().output("CR2 - In:" + teamc.getR2().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 9);
        cn.getTextWindow().output("Sk:" + teamc.getR2().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 9);
        cn.getTextWindow().output("Sp:" + teamc.getR2().getSpeed() + "\n\n\n");


        cn.getTextWindow().output("->Winner: ");

        cn.getTextWindow().setCursorPosition(x + 33, y + 1);
        cn.getTextWindow().output("0    5   10    15   20");
        cn.getTextWindow().setCursorPosition(x + 33, y + 2);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 3);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 4);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 5);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 6);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 7);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 8);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 9);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 10);
        cn.getTextWindow().output("+----+----+----+----+");

        Random random = new Random();
        int hr1point = 0;
        int hr2point = 0;
        int cr1point = 0;
        int cr2point = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;


        int cc = teamh.getR1().getIntelligence() + teamh.getR2().getIntelligence() + teamc.getR1().getIntelligence() + teamc.getR2().getIntelligence();

        while (true) {
            // z is a variable for random number
            int z = random.nextInt(cc) + 1;
            


            // increasing  round score
            if (z <= teamh.getR1().getIntelligence()) {
                hr1point++;
                cn.getTextWindow().setCursorPosition(x + 34 + i, y + 3);
                cn.getTextWindow().output("X",yellow);
                i++;
            } else if (z <= teamh.getR1().getIntelligence() + teamh.getR2().getIntelligence()) {
                hr2point++;
                cn.getTextWindow().setCursorPosition(x + 34 + j, y + 5);
                cn.getTextWindow().output("X",yellow);
                j++;
            } else if (z <= teamh.getR1().getIntelligence() + teamh.getR2().getIntelligence() + teamc.getR1().getIntelligence()) {
                cr1point++;
                cn.getTextWindow().setCursorPosition(x + 34 + k, y + 7);
                cn.getTextWindow().output("X",yellow);
                k++;
            } else {
                cr2point++;
                cn.getTextWindow().setCursorPosition(x + 34 + l, y + 9);
                cn.getTextWindow().output("X",yellow);
                l++;
            }





            //ending game
            if (hr1point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("HR1 kazandı",blue);
                teamh.setTeampoint(teamh.getTeampoint() + 1);
                break;
            } else if (hr2point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("HR2 kazandı",blue);
                teamh.setTeampoint(teamh.getTeampoint() + 1);
                break;
            } else if (cr1point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("CR1 kazandı",red
                );
                teamc.setTeampoint(teamc.getTeampoint() + 1);
                break;
            } else if (cr2point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("CR2 kazandı",red);
                teamc.setTeampoint(teamc.getTeampoint() + 1);
                break;
            }

            //time that between rounds
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void playRoboPingPong(int x, int y, enigma.console.Console cn) {
        cn.getTextWindow().setCursorPosition(x, y);
        ;
        // gameplay screen
        cn.getTextWindow().output("====== " + "RoboPingPong" + " =====================================\n\n\n",yellow);
        cn.getTextWindow().output("HR1 - In:" + teamh.getR1().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 3);
        cn.getTextWindow().output("Sk:" + teamh.getR1().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 3);
        cn.getTextWindow().output("Sp:" + teamh.getR1().getSpeed() + "\n\n");

        cn.getTextWindow().output("HR2 - In:" + teamh.getR2().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 5);
        cn.getTextWindow().output("Sk:" + teamh.getR2().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 5);
        cn.getTextWindow().output("Sp:" + teamh.getR2().getSpeed() + "\n\n");

        cn.getTextWindow().output("CR1 - In:" + teamc.getR1().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 7);
        cn.getTextWindow().output("Sk:" + teamc.getR1().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 7);
        cn.getTextWindow().output("Sp:" + teamc.getR1().getSpeed() + "\n\n");

        cn.getTextWindow().output("CR2 - In:" + teamc.getR2().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 9);
        cn.getTextWindow().output("Sk:" + teamc.getR2().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 9);
        cn.getTextWindow().output("Sp:" + teamc.getR2().getSpeed() + "\n\n\n");


        cn.getTextWindow().output("->Winner: ");

        cn.getTextWindow().setCursorPosition(x + 33, y + 1);
        cn.getTextWindow().output("0    5   10    15   20");
        cn.getTextWindow().setCursorPosition(x + 33, y + 2);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 3);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 4);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 5);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 6);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 7);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 8);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 9);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 10);
        cn.getTextWindow().output("+----+----+----+----+");

        Random random = new Random();
        int hr1point = 0;
        int hr2point = 0;
        int cr1point = 0;
        int cr2point = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;


        int cc = teamh.getR1().getSkill() + teamh.getR2().getSkill() + teamc.getR1().getSkill() + teamc.getR2().getSkill();

        while (true) {
            // z is a variable for random number
            int z = random.nextInt(cc) + 1;
            

            // increasing  round score
            if (z <= teamh.getR1().getSkill()) {
                hr1point++;
                cn.getTextWindow().setCursorPosition(x + 34 + i, y + 3);
                cn.getTextWindow().output("X",yellow);
                i++;
            } else if (z <= teamh.getR1().getSkill() + teamh.getR2().getSkill()) {
                hr2point++;
                cn.getTextWindow().setCursorPosition(x + 34 + j, y + 5);
                cn.getTextWindow().output("X",yellow);
                j++;
            } else if (z <= teamh.getR1().getSkill() + teamh.getR2().getSkill() + teamc.getR1().getSkill()) {
                cr1point++;
                cn.getTextWindow().setCursorPosition(x + 34 + k, y + 7);
                cn.getTextWindow().output("X",yellow);
                k++;
            } else {
                cr2point++;
                cn.getTextWindow().setCursorPosition(x + 34 + l, y + 9);
                cn.getTextWindow().output("X",yellow);
                l++;
            }




             //ending game
            if (hr1point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("HR1 kazandı",blue);
                teamh.setTeampoint(teamh.getTeampoint() + 1);
                break;
            } else if (hr2point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("HR2 kazandı",blue);
                teamh.setTeampoint(teamh.getTeampoint() + 1);
                break;
            } else if (cr1point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("CR1 kazandı",red
                );
                teamc.setTeampoint(teamc.getTeampoint() + 1);
                break;
            } else if (cr2point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("CR2 kazandı",red
                );
                teamc.setTeampoint(teamc.getTeampoint() + 1);
                break;
            }

             //time that between rounds
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void playRoboRun(int x, int y, enigma.console.Console cn) {
        cn.getTextWindow().setCursorPosition(x, y);
        ;
        // gameplay screen
        cn.getTextWindow().output("====== " + "RoboRun" + " ==========================================\n\n\n",yellow);
        cn.getTextWindow().output("HR1 - In:" + teamh.getR1().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 3);
        cn.getTextWindow().output("Sk:" + teamh.getR1().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 3);
        cn.getTextWindow().output("Sp:" + teamh.getR1().getSpeed() + "\n\n");

        cn.getTextWindow().output("HR2 - In:" + teamh.getR2().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 5);
        cn.getTextWindow().output("Sk:" + teamh.getR2().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 5);
        cn.getTextWindow().output("Sp:" + teamh.getR2().getSpeed() + "\n\n");

        cn.getTextWindow().output("CR1 - In:" + teamc.getR1().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 7);
        cn.getTextWindow().output("Sk:" + teamc.getR1().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 7);
        cn.getTextWindow().output("Sp:" + teamc.getR1().getSpeed() + "\n\n");

        cn.getTextWindow().output("CR2 - In:" + teamc.getR2().getIntelligence());
        cn.getTextWindow().setCursorPosition(x + 14, y + 9);
        cn.getTextWindow().output("Sk:" + teamc.getR2().getSkill());
        cn.getTextWindow().setCursorPosition(x + 22, y + 9);
        cn.getTextWindow().output("Sp:" + teamc.getR2().getSpeed() + "\n\n\n");


        cn.getTextWindow().output("->Winner: ");

        cn.getTextWindow().setCursorPosition(x + 33, y + 1);
        cn.getTextWindow().output("0    5   10    15   20");
        cn.getTextWindow().setCursorPosition(x + 33, y + 2);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 3);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 4);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 5);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 6);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 7);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 8);
        cn.getTextWindow().output("+----+----+----+----+");
        cn.getTextWindow().setCursorPosition(x + 33, y + 9);
        cn.getTextWindow().output("|");
        cn.getTextWindow().setCursorPosition(x + 33, y + 10);
        cn.getTextWindow().output("+----+----+----+----+");

        Random random = new Random();
        int hr1point = 0;
        int hr2point = 0;
        int cr1point = 0;
        int cr2point = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;


        int cc = teamh.getR1().getSpeed() + teamh.getR2().getSpeed() + teamc.getR1().getSpeed() + teamc.getR2().getSpeed();

        while (true) {
            // z is a variable for random number
            int z = random.nextInt(cc) + 1;

            // increasing  round score
            if (z <= teamh.getR1().getSpeed()) {
                hr1point++;
                cn.getTextWindow().setCursorPosition(x + 34 + i, y + 3);
                cn.getTextWindow().output("X",yellow);
                i++;
            } else if (z <= teamh.getR1().getSpeed() + teamh.getR2().getSpeed()) {
                hr2point++;
                cn.getTextWindow().setCursorPosition(x + 34 + j, y + 5);
                cn.getTextWindow().output("X",yellow);
                j++;
            } else if (z <= teamh.getR1().getSpeed() + teamh.getR2().getSpeed() + teamc.getR1().getSpeed()) {
                cr1point++;
                cn.getTextWindow().setCursorPosition(x + 34 + k, y + 7);
                cn.getTextWindow().output("X",yellow);
                k++;
            } else {
                cr2point++;
                cn.getTextWindow().setCursorPosition(x + 34 + l, y + 9);
                cn.getTextWindow().output("X" ,yellow);
                l++;
            }




             //ending game
            if (hr1point == 20) {
                enigma.console.TextAttributes blue = new enigma.console.TextAttributes(Color.CYAN);
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("HR1 kazandı", blue);
                teamh.setTeampoint(teamh.getTeampoint() + 1);
                break;
            } else if (hr2point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("HR2 kazandı",blue);
                teamh.setTeampoint(teamh.getTeampoint() + 1);
                break;
            } else if (cr1point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("CR1 kazandı" ,red);
                teamc.setTeampoint(teamc.getTeampoint() + 1);
                break;
            } else if (cr2point == 20) {
                cn.getTextWindow().setCursorPosition(x + 11, y + 12);
                cn.getTextWindow().output("CR2 kazandı",red);
                teamc.setTeampoint(teamc.getTeampoint() + 1);
                break;
            }

             //time that between rounds
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}

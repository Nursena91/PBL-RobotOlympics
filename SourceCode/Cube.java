import enigma.console.TextAttributes;
import java.util.Random;


public class Cube{
	public static enigma.console.Console cn;
	public Random rnd= new Random();
	private TextAttributes cube_color;
	private int piece_num;
	private int x_force, y_force;
	private boolean isEmpty;

	public Cube(int piecePoint, int piece_num, TextAttributes cube_color){
		x_force = rnd.nextInt(75) + piecePoint;
		y_force = rnd.nextInt(75) + piecePoint;
		this.piece_num = piece_num;
		this.cube_color = cube_color;

	}

	public Cube(){
		x_force = rnd.nextInt(75);
		y_force = rnd.nextInt(75);
	}

	public Cube(boolean isEmpty){
		this.isEmpty = true;
	}

	public Cube(enigma.console.Console cn){
		this.cn = cn;

	}
	public int getCubeNum(){
		return this.piece_num;
	}

	public void show(int x , int y , TextAttributes piece_color) {
		//prints the colored cube
		cn.getTextWindow().setCursorPosition(x, y);
		cn.getTextWindow().output("+++++",piece_color);
		cn.getTextWindow().setCursorPosition(x, y+1);
		cn.getTextWindow().output("+ " +String.valueOf(y_force / 10) + " +",piece_color);
		cn.getTextWindow().setCursorPosition(x, y+2);
		cn.getTextWindow().output("+" +String.valueOf(x_force / 10) + " "+ String.valueOf(x_force % 10)+ "+",piece_color);
		cn.getTextWindow().setCursorPosition(x, y+3);
		cn.getTextWindow().output("+ " +String.valueOf(y_force % 10) + " +",piece_color);
		cn.getTextWindow().setCursorPosition(x, y+4);
		cn.getTextWindow().output("+++++",piece_color);


	}
	public int getX_force() {
		return x_force;
	}
	public int getY_force() {
		return y_force;
	}

	public void rotate() {
		int temp;
		//forces are changed
		temp = x_force;
		x_force = y_force;
		y_force = temp;

	}

	public void emptyBox(int x, int y) {

		//empty cube shape is printed
		cn.getTextWindow().setCursorPosition(x, y);
		cn.getTextWindow().output(".....");
		cn.getTextWindow().setCursorPosition(x, y +1);
		cn.getTextWindow().output(".   .");
		cn.getTextWindow().setCursorPosition(x, y +2);
		cn.getTextWindow().output(".   .");
		cn.getTextWindow().setCursorPosition(x, y +3);
		cn.getTextWindow().output(".   .");
		cn.getTextWindow().setCursorPosition(x, y+4);
		cn.getTextWindow().output(".....");

	}

	public TextAttributes getCube_color() {
		return cube_color;
	}

	public void setEmpty(boolean empty) {
		isEmpty = empty;
	}

	public boolean isEmpty() {
		return isEmpty;
	}
}

// team class for teams.there are two teams in this class.
public class Team {
	private String colour;
	private int teampoint;
	private Robot r1;
	private Robot r2;
	
	
	public Team(String colour,Robot r1,Robot r2) {
		this.colour = colour;
		this.r1 = r1;
		this.r2 = r2;
	    teampoint = 0;
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}


	public int getTeampoint() {
		return teampoint;
	}


	public void setTeampoint(int teampoint) {
		this.teampoint = teampoint;
	}


	public Robot getR1() {
		return r1;
	}


	public void setR1(Robot r1) {
		this.r1 = r1;
	}


	public Robot getR2() {
		return r2;
	}


	public void setR2(Robot r2) {
		this.r2 = r2;
	}
}


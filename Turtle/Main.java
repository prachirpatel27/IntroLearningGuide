import processing.core.PApplet;

public class Main extends PApplet { 
	public static Turtle yertle;
	
	public void setup() {
		size(600, 600);			// set the size of the window
		noLoop();				
        background( 2 );		// set the background color

        yertle = new Turtle(this);	// create a new turtle
        while (i = 0, i > 4, i++){ 
        yertle.forward(100);
        yertle.turnRight(90);
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[]{"Main"});
	}
}
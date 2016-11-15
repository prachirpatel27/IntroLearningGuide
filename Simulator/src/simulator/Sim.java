package simulator;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import robot.Robot;

public class Sim extends PApplet {
	private static Sim sim;

	public static final double fieldWidth = 54; // in feet
	public static final double fieldHeight = 27; // in feet

	public static final double PIXELS_PER_FOOT = 20;

	private float dt = 1.0f / 25f;
	private Robot robot;

	private double time = 0;

	private PImage field;
	
	public void setup() {
		size((int) (fieldWidth * PIXELS_PER_FOOT), (int) (fieldHeight * PIXELS_PER_FOOT));
		sim = this;

		field = this.loadImage("../images/field.png");
		field.resize((int) (fieldWidth * PIXELS_PER_FOOT), (int) (fieldHeight * PIXELS_PER_FOOT));
		robot = new Robot();
		robot.robotInit();
		robot.autonomousInit();
	}

	public void draw() {
		time += dt;

		background(field);
		robot.autonomousPeriodic();

		RobotModel.getInstance().update();
		RobotModel.getInstance().draw();
	}

	public float dt() {
		return dt;
	}

	public double getTime() {
		return time;
	}

	public static Sim getInstance() {
		return sim;
	}
}
package simulator;

import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;

/***
 * 
 * @author David
 *
 */
public class RobotModel {
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	
	private static RobotModel robotModel;
	private ArrayList<Integer> ports;
	
	private Sim simulator;

	private PVector pos;
	private float leftSpeed, rightSpeed;
	private float headingInRadians;
	
	private float width = 2;			// in feet
	private float wheelBase = 3; 		// in feet
	
	private float widthInPixels;
	private float wheelBaseInPixels;
	
	private float steerAngle = 0;

	private float elapsedDistLeft = 0;
	private float elapsedDistRight = 0;
	
	private PImage img;
	private int color;
	
	private float MAX_SPEED = 5f;

	private RobotModel(Sim sim) {
		super();
		this.simulator = sim;
		
		widthInPixels = (float) (width*sim.PIXELS_PER_FOOT);
		wheelBaseInPixels = (float) (wheelBase*sim.PIXELS_PER_FOOT);
		
		this.pos = new PVector(sim.width / 2, sim.height / 2);
		this.color = sim.color(255, 0, 0);
		
		img = sim.loadImage("../images/robotImage.png");
		img.resize((int)(wheelBaseInPixels), (int)(widthInPixels));
		
		this.ports = new ArrayList<Integer>();
		robotModel = this;
	}

	public static RobotModel getInstance() {
		if (robotModel != null)
			return robotModel;

		Sim sim = Sim.getInstance();

		robotModel = new RobotModel(sim);
		return robotModel;
	}

	public void draw() {
		simulator.pushMatrix();
		simulator.translate(pos.x + wheelBaseInPixels / 2, pos.y + widthInPixels / 2);
		simulator.rotate(headingInRadians);
		simulator.translate(-pos.x - wheelBaseInPixels / 2, -pos.y - widthInPixels / 2);

		if (img != null) {
			simulator.image(img, pos.x, pos.y);
		} else {
			simulator.fill(color);
			simulator.stroke(color);
			simulator.rect(pos.x, pos.y, width, wheelBaseInPixels);
		}

		simulator.popMatrix();
	}

	public void setImage(PImage i) {
		this.img = i;
		this.width = img.width;
		this.wheelBase = img.height;
	}

	public void update() {
		double adjRightSpeed = rightSpeed*MAX_SPEED ;
		double adjLeftSpeed = leftSpeed*MAX_SPEED;
		
		// update heading
		headingInRadians += ((adjRightSpeed - adjLeftSpeed)*simulator.dt())/wheelBase;
		
		double averageSpeed = (adjRightSpeed + adjLeftSpeed)/2;
		
		pos.x += averageSpeed*Math.cos(headingInRadians);
		pos.y += averageSpeed*Math.sin(headingInRadians);
		
		this.elapsedDistLeft += simulator.dt()*adjLeftSpeed;
		this.elapsedDistRight += simulator.dt()*adjRightSpeed;
	}
	
	public void realisticUpdate() {
	
		// calculate acceleration on each side
		
		// integrate to get velocity
		
		// then do regular update with the velocities of both sides
	}

	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return wheelBase;
	}

	public void setHeight(float height) {
		this.wheelBase = height;
	}

	public void setSpeed(float speed) {
		this.leftSpeed = speed;
		this.rightSpeed = speed;
	}

	public void setTurningAngle(double angle) {
		steerAngle = (float) angle;
	}

	private void setLeftSpeed(double s) {
		this.leftSpeed = (float)s;
	}

	private void setRightSpeed(double s) {
		this.rightSpeed = (float)s;
	}

	public double getRightSpeed() {
		return this.rightSpeed;
	}

	public double getLeftSpeed() {
		return this.leftSpeed;
	}

	public void registerPort(int port) {
		if (ports.contains(port)) {
			System.err.println("ERROR: PORT ALREADY IN USE!");
			System.exit(1);
		}
		
		ports.add(port);
	}

	public void setSpeedForPort(int actuatorPort, double speed) {
		if (actuatorPort == LEFT) {
			this.setLeftSpeed(speed);
		} else if (actuatorPort == RIGHT) {
			this.setRightSpeed(speed);
		}
	}

	public double getSpeedForPort(int port) {
		if (port == LEFT)
			return this.getLeftSpeed();
		else if (port == RIGHT)
			return this.getRightSpeed();
		else return 0;
	}

	public double getElapsedDistanceForPort(int port) {
		if (port == LEFT)
			return this.getLeftElapsedDist();
		else if (port == RIGHT)
			return this.getRightElapsedDist();
		else return 0;
	}

	private double getRightElapsedDist() {
		return this.elapsedDistRight;
	}

	private double getLeftElapsedDist() {
		return this.elapsedDistLeft;
	}
}

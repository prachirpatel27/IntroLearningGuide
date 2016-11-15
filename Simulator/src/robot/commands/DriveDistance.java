package robot.commands;

import robot.Robot;
import robot.RobotMap;
import simulator.Command;

/*
 * Drives a certain distance in inches
 */
public class DriveDistance extends Command {
	private double desiredDistance = 0;		// encoder reading to stop at
	private double initialDistance;			// encoder reading at command init
	private double distance;				// desired distance
	
	// debug constants
	private int count;
	
	/*
	 * @param Distance must be in inches
	 */
	public DriveDistance(double distance) {
		requires(Robot.driveTrain);
		this.distance = distance;
		this.count = 0;
	}

	protected void initialize() {
		RobotMap.debug(2, "move-initialize()");
	
		initialDistance = Robot.driveTrain.getAverageDistance();
		this.desiredDistance = distance + initialDistance;
		
		RobotMap.debug(3, "Elapsed dist: " + (Robot.driveTrain.getAverageDistance() - initialDistance) + " : stop dist: " + desiredDistance);
		
		Robot.driveTrain.setMotorSpeeds(0.6, 0.6);
	}

	protected void execute() {
		RobotMap.debug(3, "Elapsed dist: " + (Robot.driveTrain.getAverageDistance() - initialDistance) + " : stop dist: " + desiredDistance);
	}

	protected boolean isFinished() {
		return (Robot.driveTrain.getAverageDistance() - initialDistance) >= desiredDistance;
	}

	protected void end() {
		Robot.driveTrain.setMotorSpeeds(0, 0);
		RobotMap.debug(1, "move-end()" + "\tcommand ended");
	}
}
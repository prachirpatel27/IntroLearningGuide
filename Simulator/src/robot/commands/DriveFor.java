package robot.commands;

import robot.Robot;
import robot.RobotMap;
import robot.RobotMap.Direction;
import simulator.Command;
import simulator.Timer;

/**
 * This command takes a time in seconds which is how long it should run
 *
 */
public class DriveFor extends Command {
	private double seconds;
	private Timer timer;
	private Direction direction;
	
	public DriveFor(double seconds, Direction direction) {
		this.seconds = seconds;
		this.direction = direction;

	}
	@Override
	protected void initialize() {
		timer = new Timer();
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		if (direction == Direction.BACKWARD) {
			if (timer.getTime() < seconds *
			        0.2) { // for the first 20% of time, run the robot at -.5 speed
				Robot.driveTrain.setMotorSpeeds(-0.3, -0.3);
			} else if (timer.getTime() >= seconds * 0.2
			           && timer.getTime() <= seconds *
			           0.8) { // for the +20% - 75% time, move the robot at -.3 speed.
				Robot.driveTrain.setMotorSpeeds(-0.5, -0.5);
			} else if (timer.getTime() < seconds) {
				Robot.driveTrain.setMotorSpeeds(-0.25, -0.25);
			} else {
				Robot.driveTrain.setMotorSpeeds(0, 0);
			}
		} else if (direction == Direction.FORWARD) {
			if (timer.getTime() < seconds * 0.2) {
				Robot.driveTrain.setMotorSpeeds(0.3, 0.3);
			} else if (timer.getTime() >= seconds * 0.2 && timer.getTime() <= seconds * 0.8) {
				Robot.driveTrain.setMotorSpeeds(0.5, 0.5);
			} else if (timer.getTime() < seconds) {
				Robot.driveTrain.setMotorSpeeds(0.25, 0.25);
			} else {
				Robot.driveTrain.setMotorSpeeds(0, 0);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return timer.getTime() > seconds;
	}

	@Override
	protected void end() {
			Robot.driveTrain.setMotorSpeeds(0, 0);
	}
}
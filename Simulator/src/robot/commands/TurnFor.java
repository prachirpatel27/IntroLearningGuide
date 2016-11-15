package robot.commands;

import robot.Robot;
import robot.RobotMap.Direction;
import simulator.Command;
import simulator.Timer;

public class TurnFor extends Command {
	private double seconds;
	private Timer timer;
	private Direction direction;

	public TurnFor(double seconds, Direction direction) {
		this.seconds = seconds;
		this.direction = direction;
	}

	@Override
	protected void initialize() {
		timer = new Timer();
		timer.start();
	}

	@Override
	protected void execute() {
		if (direction == Direction.LEFT) {
			Robot.driveTrain.setMotorSpeeds(0.2, -0.2);
		} else if (direction == Direction.RIGHT) {
			Robot.driveTrain.setMotorSpeeds(-0.2, 0.2);
		} else {
			Robot.driveTrain.setMotorSpeeds(0, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		if (timer.get() > seconds) {
			Robot.driveTrain.setMotorSpeeds(0, 0);
		}
		return timer.get() > seconds;
	}

	@Override
	protected void end() {
	}
}

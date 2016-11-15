package robot.commands;

import robot.Robot;
import robot.RobotMap;
import simulator.Command;

/***
 * Rotate continuously forever
 * 
 * @author David
 */
public class ContinuousTurn extends Command {
	
	public ContinuousTurn() {
	
	}

	@Override
	public void initialize() {
		Robot.driveTrain.setMotorSpeeds(1, -1);
	}

	@Override
	public void execute() {
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public void end() {
	
	}

}
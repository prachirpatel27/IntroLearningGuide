package robot.commands;

import robot.RobotMap.Direction;
import simulator.CommandGroup;

public class AutonOne extends CommandGroup {

	public AutonOne() {
		this.addSequential(new DriveFor(2, Direction.FORWARD)); // the magic
																// number 2 is
																// bad! fix it!
		this.addSequential(new TurnFor(2, Direction.RIGHT));
	}
}

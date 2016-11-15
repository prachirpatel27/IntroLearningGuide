package robot.commands;

import robot.RobotMap.Direction;
import simulator.CommandGroup;

public class AutonOne extends CommandGroup {

 static final int Forward_Distance = 6;
	public static final double Turn_Distance = 4.78 ;
	
	
	
	/*
	 * This is a method 
	 */
	public AutonOne() {
		
		this.addSequential(new DriveFor(Forward_Distance, Direction.FORWARD)); // GO Forward to end of road
		this.addSequential(new TurnFor(Turn_Distance, Direction.RIGHT)); // Turn 90 degrees so faced the other side
		this.addSequential(new DriveFor(Forward_Distance, Direction.FORWARD)); // Go Forward to starting point
		this.addSequential(new TurnFor(Turn_Distance, Direction.RIGHT)); // Turn 90 degrees
		this.addSequential(new DriveFor(Forward_Distance, Direction.FORWARD));
		this.addSequential(new TurnFor(Turn_Distance, Direction.RIGHT));
		this.addSequential(new DriveFor(Forward_Distance, Direction.FORWARD));




		
		

		
	}
}

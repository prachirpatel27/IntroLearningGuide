package robot.subsystems;

import robot.RobotMap;
import simulator.CANJaguar;
import simulator.DoubleSolenoid;
import simulator.Subsystem;

public class Intake extends Subsystem {
	private DoubleSolenoid intakeSolenoid = RobotMap.intakeSolenoid;
	//private CANJaguar rollerMotors = RobotMap.rollerMotor;
	
	private double currentSpeed = 0.3;
	private boolean isIntakeDown = true; 
	private boolean toggleOn=false;

	public void raiseIntake() {
		intakeSolenoid.set(RobotMap.close);
		isIntakeDown = false;
	}

	public void lowerIntake() {
		intakeSolenoid.set(RobotMap.open);
		isIntakeDown = true;
	}

	public boolean isIntakeDown() {
		return isIntakeDown;
	}

	public void setIntakeRollers() {
		
	}
}
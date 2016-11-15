package robot.subsystems;

import robot.RobotMap;
import simulator.CANJaguar;
import simulator.Encoder;
import simulator.Subsystem;

public class DriveTrain extends Subsystem {
	public static double DRIVE_TRAIN_DEAD_ZONE = 0.1;
	public CANJaguar frontLeft = RobotMap.driveTrainFrontLeft,
	                 frontRight = RobotMap.driveTrainFrontRight,
	                 rearLeft = RobotMap.driveTrainRearLeft,
	                 rearRight = RobotMap.driveTrainRearRight;
	public Encoder leftEncoder = RobotMap.driveTrainLeftEncoder,
	               rightEncoder = RobotMap.driveTrainRightEncoder;
	double previousLeftDriveSpeed = 0;
	double previousRightDriveSpeed = 0;
	static final double DAMPENING_FACTOR = RobotMap.DAMPENING_FACTOR;
	
	public DriveTrain() {
		
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getRightEncoderSpeed() {
		// Returns in per second
		return rightEncoder.getRate();
	}

	public double getLeftEncoderSpeed() {
		return leftEncoder.getRate();
	}

	public double getAverageSpeed() {
		return (getLeftEncoderSpeed() + getRightEncoderSpeed()) / 2.0;
	}

	public double getRightEncoderDistance() {
		// Returns distance in in
		return rightEncoder.getDistance();
	}

	public double getLeftEncoderDistance() {
		// Returns distance in in
		return leftEncoder.getDistance();
	}

	public double getAverageDistance() {
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
	}

	public double getCurrentSpeed() {
		return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
	}

	public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
		if (Math.abs(leftSpeed) < DRIVE_TRAIN_DEAD_ZONE) {
			leftSpeed = 0;
		}
		if (Math.abs(rightSpeed) < DRIVE_TRAIN_DEAD_ZONE) {
			rightSpeed = 0;
		}
		this.frontLeft.set(leftSpeed);
		this.frontRight.set(rightSpeed);
		this.rearLeft.set(leftSpeed);
		this.rearRight.set(rightSpeed);
	}
}
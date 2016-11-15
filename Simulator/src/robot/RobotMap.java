package robot;

import robot.subsystems.DriveTrain;
import simulator.CANJaguar;
import simulator.Compressor;
import simulator.DoubleSolenoid;
import simulator.Encoder;
import simulator.DoubleSolenoid.Value;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int debugLevel = 3;

	// Joysticks
	public static final int X_AXIS = 0, Y_AXIS = 1, TWIST = 2;

	// Drivetrain
	public static boolean driveWithPID;
	public static CANJaguar driveTrainFrontLeft, driveTrainFrontRight, driveTrainRearLeft, driveTrainRearRight;
	public static final int DRIVETRAIN_FRONT_LEFT_PORT = 0, DRIVETRAIN_FRONT_RIGHT_PORT = 1,
			DRIVETRAIN_REAR_LEFT_PORT = 3, DRIVETRAIN_REAR_RIGHT_PORT = 6;

	public static Encoder driveTrainLeftEncoder, driveTrainRightEncoder;
	public static final int DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 0, DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 0,
			DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 1, DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 1;

	public static final double DISTANCE_FROM_CENTER_TO_WHEELS = 15.5;

	public static final double TOLERABLE_CHANGE_IN_SPEED = 6; // inches / second
	public static final double DAMPENING_FACTOR = 0.95;
	public static final double IDEAL_MAX_SPEED = 144;

	// Claw
	public static DoubleSolenoid intakeSolenoid;
	public final static simulator.DoubleSolenoid.Value open = DoubleSolenoid.Value.kForward, close = DoubleSolenoid.Value.kReverse;
	public static final int CLAW_FORWARD_PORT = 1, CLAW_REVERSE_PORT = 2;

	// Compressor
	public static final int COMPRESSOR_RELAY_CHANNEL = 7, PRESSURE_SWITCH_CHANNEL = 10;

	// Compressor
	public static Compressor compressor;

	/**
	 * Calculates the distance the wheels goes every time the encoder counts a
	 * pulse:
	 *
	 * This calculation is the ratio between the radius of the gear on the box
	 * and the small sprocket on the wheels, multiplied by the circumference of
	 * the wheel, and divided by the pulses per revolution of the encoder (256).
	 */
	public static final double DISTANCE_PER_PULSE = ((3.66 / 5.14) * 6 * Math.PI) / 256;

	// Used in speedAtDist
	private static final double CURVATURE = 10;

	public static void init() {
		driveTrainFrontLeft = new CANJaguar(DRIVETRAIN_FRONT_LEFT_PORT);
		driveTrainFrontRight = new CANJaguar(DRIVETRAIN_FRONT_RIGHT_PORT);
		driveTrainRearLeft = new CANJaguar(DRIVETRAIN_REAR_LEFT_PORT);
		driveTrainRearRight = new CANJaguar(DRIVETRAIN_REAR_RIGHT_PORT);

		driveTrainLeftEncoder = new Encoder(DRIVETRAIN_LEFT_ENCODER_CHANNEL_A, DRIVETRAIN_LEFT_ENCODER_CHANNEL_B);

		// driveTrainLeftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

		driveTrainRightEncoder = new Encoder(DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A, DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B);

		// driveTrainRightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

		intakeSolenoid = new DoubleSolenoid(0, 0, 1);
		compressor = new Compressor(0);
	}

	public static double getTargetSpeed(double joystickValue) {
		return Math.abs(joystickValue) * joystickValue * IDEAL_MAX_SPEED;
	}

	public static double toPWM(double speed) {
		return speed / IDEAL_MAX_SPEED;
	}

	public static double toSpeed(double PWMValue) {
		return PWMValue * IDEAL_MAX_SPEED;
	}

	public static void debug(int lvl, String msg) {
		if (lvl <= debugLevel) {
			System.out.println(msg);
		}
	}

	public static enum Direction {
		LEFT, RIGHT, DOWN, UP, FORWARD, BACKWARD;
	}

	/**
	 * Used to smoothly scale motor speed from 0 to 1 between initial and target
	 * positions. Calculated via modified normal distribution to make motor
	 * speed up from 0 to 1 and slow down back to 0 as it approaches its
	 * destination.
	 *
	 * @param dist
	 *            current position, from 0 to target
	 * @param target
	 *            target position
	 *
	 * @return a motor speed between 0 and 1
	 */
	public static double speedAtDist(double dist, double target) {
		double a = CURVATURE;
		double b = target / 2;
		double p = Math.exp((b * b) / (2 * a * a));
		double r = Math.exp(((dist - b) * (dist - b)) / (2 * a * a));
		double q = 1 / (p - 1);
		return ((1 / (r - (r / p))) - q);
	}
}

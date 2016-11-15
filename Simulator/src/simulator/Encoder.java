package simulator;

public class Encoder {
	private double elapsedDist = 0;
	private double distanceAtReset = 0;
	private int port;
	private static RobotModel robotModel;
	
	public Encoder(int drivetrainLeftEncoderChannelA, int drivetrainLeftEncoderChannelB) {
		robotModel = RobotModel.getInstance();
		port = drivetrainLeftEncoderChannelA;
	}

	public double getRate() {
		return robotModel.getSpeedForPort(port);
	}

	public double getDistance() {
		return robotModel.getElapsedDistanceForPort(port) - distanceAtReset;
	}

	public void reset() {
		distanceAtReset = robotModel.getElapsedDistanceForPort(port);
	}

}

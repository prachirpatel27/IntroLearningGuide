package simulator;

public class CANJaguar {
	private static RobotModel robotModel;
	private int actuatorPort;
	
	public CANJaguar(int port) {
		robotModel = RobotModel.getInstance();
		actuatorPort = port;
		
		robotModel.registerPort(port);
	}

	public void set(double speed) {
		robotModel.setSpeedForPort(actuatorPort, speed);
	}
}
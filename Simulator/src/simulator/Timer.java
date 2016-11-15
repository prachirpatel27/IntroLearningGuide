package simulator;

public class Timer {
	private Sim simulator;
	private double timeAtReset = 0;
	
	public Timer() {
		simulator = Sim.getInstance();
	}
	
	public void reset() {
		timeAtReset = simulator.getTime();
	}

	public void start() {
		reset();
	}

	public double getTime() {
		return simulator.getTime() - timeAtReset;
	}

	public double get() {
		return getTime();
	}
}

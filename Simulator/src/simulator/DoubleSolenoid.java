package simulator;

public class DoubleSolenoid {
	private Value state;
	
	public DoubleSolenoid(int i, int j, int k) {
		state = Value.kOff;
	}

	public void set(Value value) {
		state = value;
	}
	
	public Value get() {
		return state;
	}

	public enum Value {
		kForward, kReverse, kOff
	};
}

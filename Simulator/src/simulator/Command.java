package simulator;

/**
*
*/
public abstract class Command {
	private Timer timer = new Timer();
	private double timeOut;
	private State state = State.NOT_INITIALIZED;

	public Command() {

	}

	/***
	 * Adds this command to the Scheduler to be run. This this not immediately
	 * initialize or execute the Command. The Scheduler initializes and
	 * exectutes the object.
	 */
	public void start() {
		Scheduler.getInstance().add(this);
	}

	/***
	 * Run by the scheduler to have the command do the next step in its
	 * lifecycle.
	 */
	public void runOneStep() {
		if (state == State.NOT_INITIALIZED) {
			initialize();
			state = State.EXECUTING;
		} else if (state == State.EXECUTING) {
			execute();
			if (isFinished()) {
				end();
				state = State.FINISHED;
			}
		}
	}

	public boolean isRunning() {
		return state == State.EXECUTING;
	}

	// Called just before this Command runs the first time
	abstract protected void initialize();

	// Called repeatedly when this Command is scheduled to run
	abstract protected void execute();

	// Make this return true when this Command no longer needs to run execute()
	abstract protected boolean isFinished();

	// Called once after isFinished returns true
	abstract protected void end();

	protected void requires(Subsystem driveTrain) {
		// TODO Auto-generated method stub
	}

	protected void setTimeOut(double time) {
		timeOut = time;
	}

	protected boolean isTimedOut() {
		return (timeOut > 0 && timer.getTime() >= timeOut);
	}

	protected double timeSinceInitialized() {
		return timer.getTime();
	}

	protected void startTimer() {
		timer.start();
	}

	private enum State {
		NOT_INITIALIZED, EXECUTING, FINISHED, INTERRUPTED
	};

	protected static final Command emptyCommand = new Command() {
		@Override
		protected void initialize() {
		}

		@Override
		protected void execute() {
		}

		@Override
		protected boolean isFinished() {
			return true;
		}

		@Override
		protected void end() {
		}
	};

}
package simulator;

import java.util.ArrayList;

public class Scheduler {
	private static Scheduler scheduler;
	private ArrayList<Command> runningCommands;

	private Scheduler() {
		runningCommands = new ArrayList<Command>();		
	}
	
	/***
	 * Run all commands one step.  Remove any that are complete.
	 */
	public void run() {
		for (int i = 0; i < runningCommands.size(); i++) {
			Command command = runningCommands.get(i);
			
			command.runOneStep();
			
			if (command.isFinished() || command.isTimedOut()) {
				runningCommands.remove(i);
				i--;
			}
		}
	}
	
	public static Scheduler getInstance() {
		if (scheduler != null) return scheduler;
		
		scheduler = new Scheduler();
		return scheduler;
	}

	public void add(Command command) {
		runningCommands.add(command);
	}
	
	public void remove(Command c) {
		runningCommands.remove(c);
	}
	
	public void removeAll() {
		runningCommands.clear();
	}
}
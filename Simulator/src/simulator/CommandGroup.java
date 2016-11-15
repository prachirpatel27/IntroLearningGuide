package simulator;

import java.util.ArrayList;
import java.util.List;

/***
 * Currently untested! I tried to make this class mimic behavior described here:
 * https://wpilib.screenstepslive.com/s/4485/m/13809/l/599743-synchronizing-two-commands
 * 
 * @author David
 *
 */
public class CommandGroup extends Command {
	private Node root, tail, current;

	private ArrayList<Command> currentlyRunningParallel = new ArrayList<Command>();

	public CommandGroup() {
		root = new Node();
		root.command = emptyCommand;
		tail = root;
	}

	@Override
	protected void initialize() {
		current = root;
		this.doNextNode();
	}

	@Override
	protected void execute() {
		// Run all parallel commands
		for (int i = 0; i < currentlyRunningParallel.size(); i++) {
			Command command = currentlyRunningParallel.get(i);

			command.runOneStep();

			if (command.isFinished()) {
				currentlyRunningParallel.remove(i);
				i--;
			}
		}

		// Run sequential command
		if (current != null) {
			current.command.runOneStep();

			if (current.command.isFinished()) {
				this.doNextNode();
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return (current == null && currentlyRunningParallel.size() == 0);
	}

	@Override
	protected void end() {

	}

	public void addSequential(Command c) {
		// Create new node for this command
		Node n = new Node();
		n.command = c;

		// Add it as the next sequential node from current tail
		tail.runNextSequential = n;

		// Set it as the new tail
		tail = n;
	}

	public void addParallel(Command c) {
		tail.runNextInParallel.add(c);
	}

	// Run when current command is finished.
	// Loads all parallel commands which run next as well as next sequential
	// command.
	private void doNextNode() {
		this.currentlyRunningParallel.addAll(current.runNextInParallel);
		current = current.runNextSequential;
	}

	private class Node {
		Command command;
		List<Command> runNextInParallel = new ArrayList<Command>();
		Node runNextSequential;
	}
}
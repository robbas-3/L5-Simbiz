package event;
import model.CarWashState;
import model.SimState;
import simulator.Simulator;

/**
 * StartEvent is an Event that starts the simulation.
 * 
 * @author Robin
 * 
 */
public class StartEvent extends Event {
	private Simulator simulator;
	private CarWashState cWS;
	/**
	 * Skuggning
	 * 
	 * @param state
	 *            sending state so it can be changed accordingly.
	 * @param eventQueue
	 *            making it occur and jumps to next one
	 */
	public StartEvent(double time,String s,Simulator simulator) {
		super(time,s);
		this.simulator=simulator;
	}


	public void execEvent(SimState state,EventQueue eventQueue) {
		cWS = (CarWashState)state;
		simulator.start();
		state.setEvent(this);
		//createNextEvent(cWS.arrivalTime(),new ArriveEvent(cWS.arrivalTime(),"Arrive",eventQueue,cWS),eventQueue);
		
	}

	
	public void createNextEvent(Event event,EventQueue eventQueue) {
		
		createNewEvent(eventQueue.getSortedSequence(),event);
	}
}

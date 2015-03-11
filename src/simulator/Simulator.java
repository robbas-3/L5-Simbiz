package simulator;

import event.ArriveEvent;
import event.EventQueue;
import event.SortedSequence;
import event.StartEvent;
import event.StopEvent;
import gui.CarWashView;
import model.CarWashState;

public class Simulator {

	private boolean on;
	
	public Simulator(){
		on = true;
		Driver();
	}
	
	private void Driver(){
		int seed = 1234;
		int fastAmount = 2;
		int slowAmount = 2;
		int queueSize = 5;
		double fastLower = 2.8;
		double fastUpper = 4.6;
		double slowLower = 3.5;
		double slowUpper = 6.7;
		double lambda = 2.0;
		
		CarWashState cws = new CarWashState(seed, fastAmount, slowAmount, queueSize, fastLower, fastUpper, slowLower, slowUpper, lambda);
		CarWashView cwv = new CarWashView(cws);
		EventQueue eq = new EventQueue(new SortedSequence());
		eq.getSortedSequence().addNsort(new StartEvent(0,"Start", this));;
		eq.getSortedSequence().addNsort(new StopEvent(15,"Stop", this));;
		
		double time = 0;// Test
		while(time < 15.0){
			time += cws.arrivalTime();
			eq.getSortedSequence().addNsort(new ArriveEvent(time, "Arrive", eq, cws));		
		}
		while(on){
			if(eq.hasNext()){
				if(eq.getSortedSequence().getElement(0).getEventTime() < cws.getTime()){
					eq.next().execEvent(cws,eq);
				}
			}
		}
		
	}
	public void start(){
		on = true;
	}
	public void stop(){
		on = false;
	}
	
	public static void main(String[] args){
		Simulator simulator = new Simulator();
	}

	
}


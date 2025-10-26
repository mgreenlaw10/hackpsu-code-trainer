package game;

import java.lang.Runnable;

public class TimerEvent {

	Runnable func;
	long delay;
	long last;

	public TimerEvent(Runnable pFunc, long pDelay) {
		func = pFunc;
		delay = pDelay;
		last = 0;
	}

	public Runnable getEvent() {
		return func;
	}

	public long getDelay() {
		return delay;
	}

	public long getLast() {
		return last;
	}
	
	public void setLast(long time) {
		last = time;
	}
}
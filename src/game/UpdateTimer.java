package game;

import javafx.animation.AnimationTimer;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.lang.Runnable;

public final class UpdateTimer extends AnimationTimer {

	static List<Runnable> drawRoutines = new ArrayList<>();
	static List<TimerEvent> updateRoutines = new CopyOnWriteArrayList<>();

	public static void addDrawRoutine(Runnable func) {
		drawRoutines.add(func);
	}

	public static void addUpdateRoutine(TimerEvent func) {
		updateRoutines.add(func);
	}

	public static void removeUpdateRoutine(TimerEvent func) {
		TimerEvent match = null;
		for (TimerEvent event : updateRoutines) {
			if (event == func) match = func;
		}
		if (match != null) updateRoutines.remove(match);
	}

	Engine engine;
	public UpdateTimer(Engine pEngine) {
		engine = pEngine;
	}

	@Override
	public void handle(long now) {
		update();
		draw();
	}

	void update() {
		for (var timerEvent : updateRoutines) {
			long now = System.currentTimeMillis();
			if (now - timerEvent.getLast() > timerEvent.getDelay()) {
				timerEvent.getEvent().run();
				timerEvent.setLast(now);
			}
		}
	}

	void draw() {
		for (var func : drawRoutines) func.run();
	}
}
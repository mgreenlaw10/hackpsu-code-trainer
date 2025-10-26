package game;

import javafx.animation.AnimationTimer;

import java.util.List;
import java.util.ArrayList;
import java.lang.Runnable;

public final class UpdateTimer extends AnimationTimer {

	static List<Runnable> drawRoutines = new ArrayList<>();
	static List<Runnable> updateRoutines = new ArrayList<>();

	public static void addDrawRoutine(Runnable func) {
		drawRoutines.add(func);
	}

	public static void addUpdateRoutine(Runnable func) {
		updateRoutines.add(func);
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
		
	}

	void draw() {
		for (var func : drawRoutines) func.run();
	}
}
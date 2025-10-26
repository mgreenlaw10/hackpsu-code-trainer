package game.math;

public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;

	public static Direction parseString(String str) {
		switch (str) {
			case "UP" -> {return UP;}
			case "DOWN" -> {return DOWN;}
			case "LEFT" -> {return LEFT;}
			case "RIGHT" -> {return RIGHT;}
		}
		return null;
	}
}
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

	public static Direction rotate90(Direction dir) {
		switch (dir) {
			case UP -> {return RIGHT;}
			case DOWN -> {return LEFT;}
			case LEFT -> {return UP;}
			case RIGHT -> {return DOWN;}
		}
		return null;
	}
}
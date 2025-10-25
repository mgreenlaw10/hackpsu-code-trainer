package game.struct;

import game.math.Vec2i;
import game.math.Direction;

public class Level {

	int size;
	GameObject[][] tileMap;

	public Level(int pSize) {
		size = pSize;
		tileMap = new GameObject[size][size];
	}

	public int getSize() {
		return size;
	}

	public GameObject[][] getTileMap() {
		return tileMap;
	}

	public void insert(GameObject obj, int col, int row) {
		tileMap[row][col] = obj;
	}

	public void remove(GameObject obj) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (tileMap[i][j] == obj) {
					tileMap[i][j] = null;
				}
			}
		}
	}

	public GameObject getPlayer() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				var obj = tileMap[i][j];
				if (obj != null && obj.name.equals("dog")) {
					return obj;
				}
			}
		}
		// should never return null
		return null;
	}

	public Vec2i getPosition(GameObject obj) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (tileMap[i][j] == obj) {
					return new Vec2i(j, i);
				}
			}
		}
		return null;
	}

	public void movePlayer(Direction dir) {
		var player = getPlayer();
		var pos = getPosition(player);
		
		int dx = 0;
		int dy = 0;
		switch (dir) {
			case UP -> {
				if (pos.y == 0) return;
				dy = -1;
			}
			case DOWN -> {
				if (pos.y == size - 1) return;
				dy = 1;
			}
			case LEFT -> {
				if (pos.x == 0) return;
				dx = -1;
			}
			case RIGHT -> {
				if (pos.x == size - 1) return;
				dx = 1;
			}
		}
		
		remove(player);
		insert(player, pos.x + dx, pos.y + dy);
	}
}
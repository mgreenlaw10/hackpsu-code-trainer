package game.struct;

import game.math.Vec2i;
import game.math.Direction;

public class Level {

	// maintain a copy of the original state to return back to after running a script
	GameObject[][] originalState;
	GameObject[][] tileMap;
	int size;

	public Level(int pSize, GameObject[][] pTileMap) {
		size = pSize;
		tileMap = pTileMap;
	}

	public Level(int pSize) {
		this(pSize, new GameObject[pSize][pSize]);
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

	public void saveStateAsOriginal() {
		originalState = deepCopyTileMap(tileMap, size);
	}

	public void restoreOriginalState() {
		tileMap = deepCopyTileMap(originalState, size);
	}

	public GameObject[][] deepCopyTileMap(GameObject[][] map, int size) {
		GameObject[][] copy = new GameObject[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				GameObject obj = map[i][j];
				if (obj != null) 
					copy[i][j] = obj;
			}
		}
		return copy;
	}
}
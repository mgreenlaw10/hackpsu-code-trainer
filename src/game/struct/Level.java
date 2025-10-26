package game.struct;

import game.math.Vec2i;
import game.math.Direction;

import javafx.scene.image.Image;
import java.util.function.BooleanSupplier;

public class Level {

	// maintain a copy of the original state to return back to after running a script
	GameObject[][] originalState;
	GameObject[][] tileMap;
	int size;

	Image background;
	BooleanSupplier winCondition;
	String taskDescription;

	public void setTaskDescription(String text) {
		taskDescription = text;
	}
	public String getTaskDescription() {
		return taskDescription;
	}

	public BooleanSupplier getWinCondition() {
		return winCondition;
	}

	public void setWinCondition(BooleanSupplier func) {
		winCondition = func;
	}

	public Level(int pSize, GameObject[][] pTileMap) {
		size = pSize;
		tileMap = pTileMap;
	}

	public Level(int pSize) {
		this(pSize, new GameObject[pSize][pSize]);
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image image) {
		background = image;
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

	public GameObject getObjectAt(int col, int row) {
		return tileMap[row][col];
	}

	public Player getPlayer() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				var obj = tileMap[i][j];
				if (obj != null && obj instanceof Player p) {
					return p;
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
	// returns is the player still alive?
	public boolean movePlayer(Direction dir) {
		var player = getPlayer();
		var pos = getPosition(player);
		
		int dx = 0;
		int dy = 0;
		switch (dir) {
			case UP -> {
				if (pos.y == 0) return true;
				dy = -1;
			}
			case DOWN -> {
				if (pos.y == size - 1) return true;
				dy = 1;
			}
			case LEFT -> {
				if (pos.x == 0) return true;
				dx = -1;
			}
			case RIGHT -> {
				if (pos.x == size - 1) return true;
				dx = 1;
			}
		}
		// peek at the space we're about to move to
		GameObject peek = getObjectAt(pos.x + dx, pos.y + dy);
		if (peek != null) {
			if (peek.getName().equals("coin"))
				player.addCoin();
			else if (peek instanceof Monster m) {
				// if move into monster, die
				return false;
			}
			else if (peek.getName().equals("wall")) {
				// if hit wall, don't die but don't move either
				return true;
			}
			else if (peek.getName().equals("meat")) {
				player.updateEnergy(3);
			}
		}
		remove(player);
		insert(player, pos.x + dx, pos.y + dy);
		return true;
	}

	public boolean playerAttack(int num) {
		var player = getPlayer();
		var pos = getPosition(player);
		player.updateEnergy(-num);
		if (player.getEnergy() < 0) {
			return false;
		}
		int dx = 0;
		int dy = 0;
		switch (player.getDirection()) {
			case UP -> {
				if (pos.y == 0) return true;
				dy = -1;
			}
			case DOWN -> {
				if (pos.y == size - 1) return true;
				dy = 1;
			}
			case LEFT -> {
				if (pos.x == 0) return true;
				dx = -1;
			}
			case RIGHT -> {
				if (pos.x == size - 1) return true;
				dx = 1;
			}
		}
		GameObject peek = getObjectAt(pos.x + dx, pos.y + dy);
		if (peek instanceof Monster m) {
			m.damage(num);
			System.out.println(m.getHealth());
		}
		return true;
		//System.out.println("ATTACK");
	}

	public void saveStateAsOriginal() {
		originalState = deepCopyTileMap(tileMap, size);
	}

	public void restoreOriginalState() {
		tileMap = deepCopyTileMap(originalState, size);
		// find all monsters and reset their states
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				GameObject obj = tileMap[i][j];
				if (obj instanceof Monster m) {
					m.resetState();
				}
			}
		}
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

	public void removeDeadMonsters() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				GameObject obj = tileMap[i][j];
				if (obj instanceof Monster m && m.isDead()) {
					remove(m);
				}
			}
		}
	}
}
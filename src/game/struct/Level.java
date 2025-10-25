package game.struct;

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
}
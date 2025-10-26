package game;

import game.struct.Level;
import game.struct.GameObject;
import game.struct.Player;
import game.struct.Monster;

public class LevelTemplates {

	static Level level1;
	static Level level2;
	static Level level3;

	public static Level getLevel1() {
		if (level1 != null) return level1;

		level1 = new Level(6);

		Player player = new Player();
		GameObject coin = new GameObject("coin", Engine.makeImage("/res/image/coin.png"));

		level1.setTaskDescription("Collect 4 coins.");
		level1.setBackground(Engine.makeImage("/res/image/grass.png"));
		level1.insert(player, 1, 1);
		level1.insert(coin, 1, 4);
		level1.insert(coin.clone(), 2, 4);
		level1.insert(coin.clone(), 3, 4);
		level1.insert(coin.clone(), 4, 4);
		level1.saveStateAsOriginal();

		level1.setWinCondition( () -> {
			return player.getCoins() >= 4;
		});
		return level1;
	}

	public static Level getLevel2() {
		if (level2 != null) return level2;

		level2 = new Level(5);

		Player player = new Player();
		GameObject coin = new GameObject("coin", Engine.makeImage("/res/image/coin.png"));
		GameObject wall = new GameObject("wall", Engine.makeImage("/res/image/brick.png"));
		Monster monster = new Monster();

		level2.setTaskDescription("Defeat the monster and collect the coin.");
		level2.setBackground(Engine.makeImage("/res/image/stone.png"));
		level2.insert(player, 0, 0);
		level2.insert(wall.clone(), 2, 2);
		level2.insert(wall.clone(), 3, 2);
		level2.insert(wall.clone(), 4, 2);
		level2.insert(wall.clone(), 4, 3);
		level2.insert(wall.clone(), 4, 4);
		level2.insert(wall.clone(), 3, 4);
		level2.insert(wall.clone(), 2, 4);
		level2.insert(monster, 2, 3);
		level2.insert(coin, 3, 3);
		level2.saveStateAsOriginal();

		level2.setWinCondition( () -> {
			return player.getCoins() >= 1 && monster.isDead();
		});
		return level2;
	}

	public static Level getLevel3() {
		if (level3 != null) return level3;

		level3 = new Level(7);

		Player player = new Player();
		GameObject coin = new GameObject("coin", Engine.makeImage("/res/image/coin.png"));
		GameObject wall = new GameObject("wall", Engine.makeImage("/res/image/brick.png"));
		GameObject meat = new GameObject("meat", Engine.makeImage("/res/image/steak.png"));
		Monster monster = new Monster();

		level3.setTaskDescription("Get past the monsters and collect the coin.");
		level3.setBackground(Engine.makeImage("/res/image/wood.png"));

		level3.insert(player, 3, 1);
		level3.insert(wall.clone(), 3, 2);
		level3.insert(wall.clone(), 2, 2);
		level3.insert(wall.clone(), 1, 2);
		level3.insert(wall.clone(), 0, 2);
		level3.insert(wall.clone(), 3, 3);
		level3.insert(wall.clone(), 4, 3);
		level3.insert(wall.clone(), 3, 4);
		level3.insert(wall.clone(), 4, 2);
		level3.insert(wall.clone(), 5, 2);
		level3.insert(wall.clone(), 5, 3);
		level3.insert(wall.clone(), 5, 4);
		level3.insert(wall.clone(), 6, 2);
		level3.insert(monster.clone(), 4, 2);
		level3.insert(monster.clone(), 4, 3);
		level3.insert(meat, 0, 1);
		level3.insert(coin, 4, 4);
		level3.saveStateAsOriginal();

		level3.setWinCondition( () -> {
			return player.getCoins() >= 1;
		});
		return level3;
	}
}
package game;

import game.struct.Level;
import game.struct.GameObject;
import game.struct.Player;

public class LevelTemplates {

	static Level level1;
	static Level level2;

	public static Level getLevel1() {
		if (level1 != null) return level1;

		level1 = new Level(10);

		Player player = new Player();
		GameObject coin = new GameObject("coin", Engine.makeImage("/res/image/coin.png"));
		level1.setBackground(Engine.makeImage("/res/image/grass.png"));
		level1.insert(player, 1, 1);
		level1.insert(coin, 5, 5);
		level1.insert(coin.clone(), 6, 5);
		level1.insert(coin.clone(), 7, 5);
		level1.insert(coin.clone(), 8, 5);
		level1.saveStateAsOriginal();

		level1.setWinCondition( () -> {
			return player.getCoins() >= 4;
		});
		return level1;
	}

	public static Level getLevel2() {
		if (level2 != null) return level2;

		level2 = new Level(10);

		Player player = new Player();
		GameObject coin = new GameObject("coin", Engine.makeImage("/res/image/coin.png"));
		level2.setBackground(Engine.makeImage("/res/image/stone.png"));
		level2.insert(player, 1, 1);
		level2.insert(coin, 5, 0);
		level2.insert(coin.clone(), 1, 5);
		level2.insert(coin.clone(), 7, 7);
		level2.insert(coin.clone(), 8, 2);
		level2.insert(coin.clone(), 8, 3);
		level2.saveStateAsOriginal();

		level2.setWinCondition( () -> {
			return player.getCoins() >= 5;
		});
		return level2;
	}
}
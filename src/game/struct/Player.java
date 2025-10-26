package game.struct;

import game.Engine;

public class Player extends GameObject {
	int coins;

	public Player() {
		super("player", Engine.makeImage("/res/image/knight.png"));
		coins = 0;
	}

	public void addCoin() { 
		coins++;
	}

	public int getCoins() {
		return coins;
	}
}
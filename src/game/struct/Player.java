package game.struct;

import game.Engine;

import game.math.Direction;

public class Player extends GameObject {
	
	int coins;
	int energy;
	Direction direction;

	public Player() {
		super("player", Engine.makeImage("/res/image/knight.png"));
		direction = Direction.RIGHT;
		coins = 0;
	}

	public void addCoin() { 
		coins++;
	}

	public int getCoins() {
		return coins;
	}

	public void setDirection(Direction dir) {
		dir = direction;
	}
	public Direction getDirection() {
		return direction;
	}
}
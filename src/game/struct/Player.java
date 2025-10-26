package game.struct;

import game.Engine;
import game.math.Direction;

import javafx.scene.image.Image;

public class Player extends GameObject {
	
	int coins;
	int energy;
	Direction direction;

	final Image walkImage = Engine.makeImage("/res/image/knight.png");
	final Image attackImage = Engine.makeImage("/res/image/attack.png");

	public Player() {
		super("player", Engine.makeImage("/res/image/knight.png"));
		direction = Direction.RIGHT;
		coins = 0;
		energy = 5;
	}

	public void addCoin() { 
		coins++;
	}

	public int getCoins() {
		return coins;
	}

	public int getEnergy() {
		return energy;
	}

	public void resetState() {
		coins = 0;
		energy = 5;
		direction = Direction.RIGHT;
		setWalking();
	}

	public void setWalking() {
		image = walkImage;
	}

	public void setAttacking() {
		image = attackImage;
	}

	public void setDirection(Direction dir) {
		direction = dir;
	}
	public Direction getDirection() {
		return direction;
	}
}
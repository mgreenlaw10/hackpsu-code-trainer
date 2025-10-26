package game.struct;

import game.Engine;

public class Monster extends GameObject {
	
	int health;

	public Monster() {
		super("monster", Engine.makeImage("/res/image/monster.png"));
		health = 3;
	}

	public Monster(int health) {
		this();
		this.health = health;
	}

	public void damage(int amt) {
		health -= amt;
	}

	public boolean isDead() {
		return health <= 0;
	}

	public int getHealth() {
		return health;
	}

	public void resetState() {
		health = 3;
	}

	@Override
	public Monster clone() {
		return new Monster();
	}
}
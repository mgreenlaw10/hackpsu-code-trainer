package game.struct;

import game.Engine;

public class Monster extends GameObject {
	
	int health;

	public Monster() {
		super("monster", Engine.makeImage("/res/image/monster.png"));
		health = 3;
	}

	public void damage(int amt) {
		health -= amt;
	}

	public boolean isDead() {
		return health <= 0;
	}
}
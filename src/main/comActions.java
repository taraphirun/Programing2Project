package main;

public interface comActions {
	int attack();
	void dropItem();
	void decreaseHP(int damage);
	void upgrade(int unit);
	int getDamage();
	String getName();
	int getHP();
}

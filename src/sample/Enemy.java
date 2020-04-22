package sample;

public class Enemy {

    //Fields
    final protected String name;
    protected int hp;
    final protected Item item;
    final protected int damage;

    //Constructor
    public Enemy(String name, int hp, Item item, int damage) {
        this.name = name;
        this.hp = hp;
        this.item = item;
        this.damage = damage;
    }

    //Methods
    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public Item getItem() {
        return item;
    }
    public int getDamage() {
        return damage;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //Functions
    public int Fight(int hp) {
        if (damage > hp) return 0;
        else return hp - damage;
    }

    @Override
    public String toString() {
        if (hp != 0) return name + " " + hp;
        else return name;
    }
}

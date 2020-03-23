package sample;

public class Enemy {

    final protected String name;
    protected int hp;
    final protected Item item;
    final protected int damage;

    public Enemy(String name, int hp, Item item, int damage) {
        this.name = name;
        this.hp = hp;
        this.item = item;
        this.damage = damage;
    }

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

    public int Fight(int hp)
    {
        if (damage > hp) hp = 0;
        else hp -= damage;
        return hp; //TODO
    }
}

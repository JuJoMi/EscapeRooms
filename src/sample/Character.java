package sample;

public class Character {

    //Fields
    protected int maxHP;
    protected int hp;
    protected Weapon weapon;
    protected int damage;

    //Constructor
    public Character(int maxHP, Weapon weapon, int damage) {
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.weapon = weapon;
        this.damage = damage;
    }

    //Methods
    public int getMaxHP() {
        return maxHP;
    }
    public int getHp() {
        return hp;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public int getDamage() {
        return damage;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    //Function
    public int Fight(int hp) {
        if (damage > hp) return 0; //hp = 0;
        else return hp - damage;
    }
}

package sample;

public class Weapon extends Item {

    //Constructor
    public Weapon(String name, int value) {
        super(name, value);
    }

    //Methods
    //Function
    public int Fight(int hp)
    {
        if (value > hp) hp = 0;
        else hp = hp - value;

        return hp; //TODO
    }

    public int Damage(int damage)
    {
        damage += this.value;
        return damage;
    }
}

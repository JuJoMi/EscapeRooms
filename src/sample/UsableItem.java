package sample;

public class UsableItem extends Item {

    public UsableItem(String name, int value)
    {
        super(name, value);
    }

    public int Heal(int hp)
    {
        hp += this.value;
        return hp; //TODO
    }
}

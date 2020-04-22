package sample;

public class UsableItem extends Item {

    //Constructor
    public UsableItem(String name, int value)
    {
        super(name, value);
    }

    //Function
    @Override
    public int Recover(int hp, int maxhp) {
        return Math.min(hp + value, maxhp);
    }
}

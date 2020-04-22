package sample;

public class Item {

    //Fields
    final protected String name;
    protected int value;

    //Constructor
    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    //Methods
    public String getName() { return name; }
    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    //Functions
    public int Recover(int hp, int maxhp) {
        return 0;
    }

    public int Damage(int damage) { return 0; }

    @Override
    public String toString() { return name; }
}

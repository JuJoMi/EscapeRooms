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
    //Getter
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return (this.name);
    }
}

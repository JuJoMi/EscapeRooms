package sample;

public class Room {

    //Fields
    final protected int number;
    protected String description;
    protected boolean locked;
    protected Doors doors;  //TODO: where initialize?
    protected Enemy enemy;
    protected Item item;


    //Constructor
    public Room(int number) {
        this.number = number;
    }

    /*//no enemy, no item
    public Room(int number, String description, boolean locked, Doors doors) { //String description
        this.number = number;
        this.description = description;
        this.doors = doors;
    }*/

    //no enemy
    public Room(int number, String description, boolean locked, Doors doors, Item item) {
        this.number = number;
        this.description = description;
        this.locked = locked;
        this.doors = doors;
        this.item = item;
    }

    public Room(int number, String description, boolean locked, Doors doors, Enemy enemy, Item item) {
        this.number = number;
        this.description = description;
        this.locked = locked;
        this.doors = doors;
        this.enemy = enemy;
        this.item = item;
    }

    //Methods
    //Getter
    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLocked() {
        return locked;
    }

    public Doors getDoors() {
        return doors;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Item getItem() {
        return item;
    }

    //Setter
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDoors(Doors doors) {
        this.doors = doors;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //Functions
}

package sample;

import javafx.scene.control.*;

public class Map {

    protected Room currentRoom;
    //private Room room1, room2, room3, room4, room5;
    //private Doors door1, doors2, doors3, doors4, doors5;
    //Item, UsableItem, Weapon, Enemy,
    //public ListView<Doors> lstRooms; //TODO: how to deal with this?

    //Constructor
    /*public Map(int nr1, int nr2, int nr3, int nr4, int nr5)
    {
        this.currentRoom = currentRoom;
        this.room1 = new Room(nr1);
        this.room2 = new Room(nr2);
        this.room3 = new Room(nr3);
        this.room4 = new Room(nr4);
        this.room5 = new Room(nr5);
        this.door1 = new Doors(nr1);

    }*/

    //Methods
    /*public void createRoom(int number, String description, boolean locked, Doors door)
    {
        if (number < 1){
            throw new IllegalArgumentException("Die Tank Nummer darf nicht kleiner 1 oder größer 3 sein!");
        }
        else if(number == 1)
        {
            this.room1.setDescription(description);
            this.room1.setLocked(locked);
            this.room1.setDoors(door);
        }
        else if(number == 2)
        {
            this.room2.setDescription(description);
            this.room2.setLocked(locked);
            this.room2.setDoors(door);
        }
        else if(number == 3)
        {
            this.room3.setDescription(description);
            this.room3.setLocked(locked);
            this.room3.setDoors(door);
        }
        else if(number == 4)
        {
            this.room4.setDescription(description);
            this.room4.setLocked(locked);
            this.room4.setDoors(door);
        }
        else
        {
            this.room5.setDescription(description);
            this.room5.setLocked(locked);
            this.room5.setDoors(door);
        }
    }*/

    public Map(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /*public void nextRoom(Room nextRoom)
    {
        if(nextRoom == 0)
        {
            throw new IllegalArgumentException("you can't go there.");
        }
        if(nextRoom == 1)
        {
            this.currentRoom = 0;
        }
    }*/
}

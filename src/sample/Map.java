package sample;

public class Map {

    //Fields
    protected Room[] rooms;
    protected Doors[] doors;
    protected Weapon weapon0, weapon1, weapon2;
    protected UsableItem usableItem1, usableItem2, usableItem3, usableItem4;
    protected Item item0, item1, item2, item3;
    protected Enemy enemy0, enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7;
    protected Room currentRoom;

    //Constructor
    public Map() {
        rooms = new Room[16];
        doors = new Doors[16];
    }

    //Methods
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    //Function
    public void CreateMap() {
        weapon0 = new Weapon("",0); //dummy
        weapon1 = new Weapon("knife", 5); //character.damage += 5
        weapon2 = new Weapon("sword",10); //character.damage += 10

        usableItem1 = new UsableItem("key",0);
        usableItem2 = new UsableItem("water",5); //istHP += 5
        usableItem3 = new UsableItem("armor",10); //maxHP += 10
        usableItem4 = new UsableItem("food",10); //istHP += 10

        item0 = new Item("",0); //dummy
        item1 = new Item("rubin",0);
        item2 = new Item("sapphire",0);
        item3 = new Item("emerald",0);

        enemy0 = new Enemy("",0,item0,0); //dummy
        enemy1 = new Enemy("spider",10,weapon1,2);
        enemy2 = new Enemy("tree creature",15,weapon2,4);
        enemy3 = new Enemy("invisible",40,item3,4);
        enemy4 = new Enemy("wolf",50,usableItem3,6);
        enemy5 = new Enemy("angry wolf",60,usableItem1,6);
        enemy6 = new Enemy("lizard",5,usableItem3,2);
        enemy7 = new Enemy("gnome",100,item0,100); //move on if gems //weapon3

        doors[0] = new Doors(true, false, false, false, 2, 0, 0, 0);
        doors[1] = new Doors(false, false, false, true, 3, 0, 0, 1);
        doors[2] = new Doors(false, true, true, true, 0, 4, 6, 2);
        doors[3] = new Doors(false, false, true, false, 0, 0, 3, 0);
        doors[4] = new Doors(false, false, false, true, 8, 0, 0, 6);
        doors[5] = new Doors(false, true, false, false, 5, 3, 7, 0);
        doors[6] = new Doors(false, true, false, false, 0, 6, 0, 0);
        doors[7] = new Doors(false, false, false,true, 10, 9, 11, 5);
        doors[8] = new Doors(false, true, true, false, 0, 1, 8, 0);
        doors[9] = new Doors(false, false, false, true, 14, 0, 0, 8);
        doors[10] = new Doors(false, true, false, false, 0, 8, 12, 0);
        doors[11] = new Doors(false, true, false, false, 0, 11, 13, 0);
        doors[12] = new Doors(false, true, false, false, 0, 12, 0, 0);
        doors[13] = new Doors(true, false, false, true, 15, 0, 0, 10);
        doors[14] = new Doors(false, false, false, true, 16, 0, 0, 14);
        doors[15] = new Doors(false, false, false, true,0,0,0,15);

        rooms[0] = new Room(1,"empty room, cold and bloody.\n",false, doors[0], enemy0, item0);
        rooms[1] = new Room(2,"hugh hairy spider attacks.\n",false, doors[1], enemy1, item0);
        rooms[2] = new Room(3,"table has milky glass with some fluid.\nmaybe drink it?!\n",false, doors[2], enemy0, usableItem2);
        rooms[3] = new Room(4,"under some weird looking bones in a corner lays a key.\n",false, doors[3],enemy0, usableItem1);
        rooms[4] = new Room(5,"blood traces around the door, tracks lead away.\nat the side lays some armor.\n",true, doors[4], enemy0, usableItem3); //find key to move on
        rooms[5] = new Room(6,"giant tree-like creature attacks.\n",false, doors[5], enemy2, item0);
        rooms[6] = new Room(7,"out of nowhere there lies a rubin. (wtf)\n",false, doors[6], enemy0, item1);
        rooms[7] = new Room(8,"something invisible attacks. only a shadow can be seen.\n",false, doors[7], enemy3, item0);
        rooms[8] = new Room(9,"nearly stepped on some nasty looking food.\nmaybe eat it?!\n",false, doors[8], enemy0, usableItem4);
        rooms[9] = new Room(10,"small lizard sits there looking all cute and harmless.\nsuddenly it spits fire!\n",true, doors[9], enemy6, item0);
        rooms[10] = new Room(11,"gnarling wolf attacks.\n",false, doors[10], enemy4, item0);
        rooms[11] = new Room(12,"another wolf attacks looking all angry.\n",false, doors[11], enemy5, item0);
        rooms[12] = new Room(13,"plugged in a human skull you find a sapphire.\n",false, doors[12], enemy0, item2);
        rooms[13] = new Room(14,"just as needed some more food.\nsomehow looks like already eaten before.\n",false, doors[13], enemy0, usableItem4);
        rooms[14] = new Room(15,"ugly gnome blocks the way, speaking in a foreign language.\nbut it looks like he wants something.\n",true, doors[14], enemy7, item0);
        rooms[15] = new Room(16,"YOU WON! FREE TO GO NOW...\n",false,doors[15],enemy0,item0);

        currentRoom = rooms[0];
    }
}

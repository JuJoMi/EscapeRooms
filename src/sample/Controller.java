package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Duration;

public class Controller {
    public Button btnMoveOn;
    public Button btnFight;
    public Button btnSelectUse;
    public TextField txtInterface;
    public Label lblHP;
    public Label lblWeapon;
    public Label lblItems;
    public Label lblCurrentHP;
    public Label lblMaxHP;
    public TextArea txtAreaStory;
    public Button btnCollectItem;
    public ListView<Item> lstItems; //<Item>
    public Label lblCurrentRoom;
    public Button btnLeft;
    public Button btnBack;
    public Button btnRight;
    public Label lblnewItem;
    public Label lblCurrentWeapon;
    public Label lblCurrentEnemy;
    public Label lblCurrentDamage;
    public Label lblDamage;
    public Label lblCurrentArmor;
    public Label lblEnemyHP;
    public Label lblR1;
    public Label lblR2;
    public Label lblR3;
    public Label lblR4;
    public Label lblR5;
    public Label lblR6;
    public Label lblR7;
    public Label lblR8;
    public Label lblR9;
    public Label lblR10;
    public Label lblR11;
    public Label lblR12;
    public Label lblR13;
    public Label lblR14;
    public Label lblR15;
    public SplitPane gamepad;

    private Character character;
    private Map map;
    private Inventory inventory;
    private int counterGems = 0;

    //private Room[] arrRooms = new Room[5];

    private Weapon weapon0 = new Weapon("",0); //dummy
    private Weapon weapon1 = new Weapon("knife", 5); //character.damage += 5
    private Weapon weapon2 = new Weapon("sword",10); //character.damage += 10
    //private Weapon weapon3 = new Weapon("long sword",20); //character.damage += 20

    private UsableItem usableItem1 = new UsableItem("key",0);
    private UsableItem usableItem2 = new UsableItem("water",5); //istHP += 5
    private UsableItem usableItem3 = new UsableItem("armor",10); //maxHP += 10
    private UsableItem usableItem4 = new UsableItem("food",10); //istHP += 10

    private Item item0 = new Item("",0); //dummy
    private Item item1 = new Item("rubin",0);
    private Item item2 = new Item("sapphire",0);
    private Item item3 = new Item("emerald",0);

    private Enemy enemy0 = new Enemy("",0,item0,0); //dummy
    private Enemy enemy1 = new Enemy("spider",10,weapon1,2);
    private Enemy enemy2 = new Enemy("tree creature",15,weapon2,4);
    private Enemy enemy3 = new Enemy("invisible",30,item3,2);
    private Enemy enemy4 = new Enemy("wolf",30,usableItem3,4);
    private Enemy enemy5 = new Enemy("wolf",30,usableItem1,4);
    private Enemy enemy6 = new Enemy("lizard",5,usableItem3,10);
    private Enemy enemy7 = new Enemy("gnome",100,item0,100); //move on if gems //weapon3

    private Doors doors1 = new Doors(true, false, false, false, 2, 0, 0, 0);
    private Doors doors2 = new Doors(true, false, false, true, 3, 0, 0, 1);
    private Doors doors3 = new Doors(false, true, true, true, 0, 4, 6, 2);
    private Doors doors4 = new Doors(false, false, true, false, 0, 0, 3, 0);
    private Doors doors5 = new Doors(false, false, false, true, 8, 0, 0, 3);
    private Doors doors6 = new Doors(true, true, true, false, 5, 3, 7, 0);
    private Doors doors7 = new Doors(false, true, false, false, 0, 6, 0, 0);
    private Doors doors8 = new Doors(true, true, true,true, 10, 9, 11, 5);
    private Doors doors9 = new Doors(false, true, true, false, 0, 1, 8, 0);
    private Doors doors10 = new Doors(false, false, false, true, 14, 0, 0, 8);
    private Doors doors11 = new Doors(false, true, true, false, 0, 8, 12, 0);
    private Doors doors12 = new Doors(false, true, true, false, 0, 11, 13, 0);
    private Doors doors13 = new Doors(false, true, false, false, 0, 12, 0, 0);
    private Doors doors14 = new Doors(true, false, false, true, 15, 0, 0, 10);
    private Doors doors15 = new Doors(false, false, false, true, 16, 0, 0, 14);
    private Doors doors16 = new Doors(false, false, false, false,0,0,0,0);

    private Room room1 = new Room(1,"empty room, cold and bloody.\n",false, doors1, enemy0, item0);
    private Room room2 = new Room(2,"hugh hairy spider attacks.\n",false, doors2, enemy1, item0);
    private Room room3 = new Room(3,"table has milky glass with some fluid.\nmaybe drink it?!\n",false, doors3, enemy0, usableItem2);
    private Room room4 = new Room(4,"under some weird looking bones in a corner lays a key.\n",false, doors4,enemy0, usableItem1);
    private Room room5 = new Room(5,"blood traces around the door, tracks lead away.\nat the side lays some armor.\n",true, doors5, enemy0, usableItem3); //find key to move on
    private Room room6 = new Room(6,"giant tree-like creature attacks.\n",false, doors6, enemy2, item0);
    private Room room7 = new Room(7,"out of nowhere there lies a rubin. (wtf)\n",false, doors7, enemy0, item1);
    private Room room8 = new Room(8,"something invisible attacks. nothing but a shadow can be recognized.\n",false, doors8, enemy3, item0);
    private Room room9 = new Room(9,"going upstairs...\nnearly stepped on some nasty looking food.\nmaybe eat it?!\n",false, doors9, enemy0, usableItem4);
    private Room room10 = new Room(10,"small lizard sits there looking all cute and harmless.\nsuddenly it spits fire! getting hot in here..\n",true, doors10, enemy6, item0);
    private Room room11 = new Room(11,"gnarling wolf attacks.\n",false, doors11, enemy4, item0);
    private Room room12 = new Room(12,"another wolf attacks looking all angry and injured.\n",false, doors12, enemy5, item0);
    private Room room13 = new Room(13,"plugged in a human skull finding a sapphire.\n",false, doors13, enemy0, item2);
    private Room room14 = new Room(14,"just as needed some more food.\nsomehow looks like already eaten before.\n",false, doors14, enemy0, usableItem4);
    private Room room15 = new Room(15,"ugly gnome blocking the way.\nclueless which language he's speaking\nbut it looks like he wants something.\n",true, doors15, enemy7, item0);
    private Room room16 = new Room(16,"YOU WON! FREE TO GO NOW...\n",false,doors16,enemy0,item0);

    private Room[] arrRooms = {room1, room2, room3, room4,room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15, room16};

    public void initialize() //executed when program started
    {
        //TODO: this.map = new Map(); //sinnvolle Parameter?
        this.map = new Map(room1);
        this.character = new Character(20,weapon0,2);
        //this.inventory = new Inventory(xyz); //TODO: sinnvoll?
        this.lblCurrentArmor.setText("");
        this.txtAreaStory.setText("waking up. dizzy and shivering.\n" + map.currentRoom.getDescription() + "\n");
        updateStatus();
    }

    private void updateStatus()
    {
        this.lblCurrentHP.setText(String.valueOf(character.getHp()));
        this.lblMaxHP.setText(String.valueOf(character.getMaxHP()));
        this.lblCurrentDamage.setText(String.valueOf(character.getDamage()));
        this.lblCurrentWeapon.setText(String.valueOf(character.getWeapon()));
        this.lblCurrentRoom.setText("Room: " + map.currentRoom.getNumber());
        //this.lblnewItem.setText(map.currentRoom.item.getName());
        this.lblCurrentEnemy.setText(map.currentRoom.enemy.getName());
        if(this.map.currentRoom.getEnemy() == enemy0) {
            this.lblEnemyHP.setText("");
        } else {
            this.lblEnemyHP.setText(String.valueOf(this.map.currentRoom.enemy.getHp()));
        }
        if(this.map.currentRoom.getItem() == item0)
        {
            this.lblnewItem.setText("");
        }
        else
        {
            this.lblnewItem.setText(String.valueOf(map.currentRoom.item.getName()));
        }
        switch (this.map.currentRoom.getNumber())
        {
            case 1:
                lblR1.setText("@");
                break;
            case 2:
                lblR2.setText("@");
                break;
            case 3:
                lblR3.setText("@");
                break;
            case 4:
                lblR4.setText("@");
                break;
            case 5:
                lblR5.setText("@");
                break;
            case 6:
                lblR6.setText("@");
                break;
            case 7:
                lblR7.setText("@");
                break;
            case 8:
                lblR8.setText("@");
                break;
            case 9:
                lblR9.setText("@");
                break;
            case 10:
                lblR10.setText("@");
                break;
            case 11:
                lblR11.setText("@");
                break;
            case 12:
                lblR12.setText("@");
                break;
            case 13:
                lblR13.setText("@");
                break;
            case 14:
                lblR14.setText("@");
                break;
            case 15:
                lblR15.setText("@");
                break;
            default:
                break;
        }
    }

    public void moveon(ActionEvent actionEvent) {
        if(this.map.currentRoom.doors.isTop())
        {
            if(this.map.currentRoom.doors.getRoomTop() == 3)
            {
                this.room3.setItem(usableItem2);
            }
            else if(this.map.currentRoom.doors.getRoomTop() == 9 || this.map.currentRoom.doors.getRoomTop() == 14)
            {
                arrRooms[this.map.currentRoom.doors.getRoomTop() - 1].setItem(this.usableItem4);
            }
            this.map.setCurrentRoom(arrRooms[this.map.currentRoom.doors.getRoomTop() - 1]);
            updateStatus();
            if(this.map.currentRoom.getNumber() == 10)
            {
                if(this.map.currentRoom.enemy.getDamage() < this.character.getHp())
                {
                    this.character.setHp(this.character.getHp() - this.map.currentRoom.enemy.getDamage());
                    lblCurrentHP.setText((String.valueOf(this.character.getHp())));
                }
                else
                {
                    gameover();
                    this.map.currentRoom.setDescription("");
                }
            }
            this.txtAreaStory.appendText(this.map.currentRoom.getDescription() + "\n");
        }
        else
        {
            if(this.map.currentRoom.doors.getRoomTop() != 0)
            {
                this.txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }
    public void goleft(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isLeft())
        {
            if(this.map.currentRoom.doors.getRoomLeft() == 3)
            {
                room3.setItem(usableItem2);
            }
            else if(this.map.currentRoom.doors.getRoomLeft() == 9 || this.map.currentRoom.doors.getRoomLeft() == 14)
            {
                arrRooms[this.map.currentRoom.doors.getRoomLeft() - 1].setItem(usableItem4);
            }
            else if(this.map.currentRoom.doors.getRoomLeft() == 1)
            {
                txtAreaStory.appendText("it's a trap!\nfalling deep hitting the ground amidst a puddle of blood.\n");
                this.character.setHp(this.character.getHp() - 6);
            }
            this.map.setCurrentRoom(arrRooms[this.map.currentRoom.doors.getRoomLeft() - 1]);
            updateStatus();
            this.txtAreaStory.appendText(map.currentRoom.getDescription() + "\n");
        }
        else
        {
            if(this.map.currentRoom.doors.getRoomLeft() != 0)
            {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }
    public void goback(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isBottom())
        {
            if(this.map.currentRoom.doors.getRoomBottom() == 3)
            {
                room3.setItem(usableItem2);
            }
            else if(this.map.currentRoom.doors.getRoomBottom() == 9 || this.map.currentRoom.doors.getRoomBottom() == 14)
            {
                arrRooms[this.map.currentRoom.doors.getRoomBottom() - 1].setItem(usableItem4);
            }
            this.map.setCurrentRoom(arrRooms[this.map.currentRoom.doors.getRoomBottom() - 1]);
            updateStatus();
            if(this.map.currentRoom.getNumber() == 10)
            {
                if(this.map.currentRoom.enemy.getDamage() < this.character.getHp())
                {
                    this.character.setHp(this.character.getHp() - this.map.currentRoom.enemy.getDamage());
                    lblCurrentHP.setText((String.valueOf(this.character.getHp())));
                    updateStatus();
                }
                else
                {
                    gameover();
                    this.map.currentRoom.setDescription("");
                }
            }
            this.txtAreaStory.appendText(map.currentRoom.getDescription() + "\n");
        }
        else
        {
            if(this.map.currentRoom.doors.getRoomBottom() != 0)
            {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }
    public void goright(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isRight())
        {
            if(this.map.currentRoom.doors.getRoomRight() == 3)
            {
                room3.setItem(usableItem2);
            }
            else if(this.map.currentRoom.doors.getRoomRight() == 9 || this.map.currentRoom.doors.getRoomRight() == 14)
            {
                arrRooms[this.map.currentRoom.doors.getRoomRight() - 1].setItem(usableItem4);
            }
            this.map.setCurrentRoom(arrRooms[this.map.currentRoom.doors.getRoomRight() - 1]);
            updateStatus();
            this.txtAreaStory.appendText(map.currentRoom.getDescription() + "\n");
        }
        else
        {
            if(this.map.currentRoom.doors.getRoomRight() != 0)
            {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }

    public void fight(ActionEvent actionEvent) {
        //txtAreaStory.appendText(map.currentRoom.enemy.Text());
        if(map.currentRoom.getEnemy() == enemy0)
        {
            txtAreaStory.appendText("you can't fight without an enemy.\n\n");
        }
        else if (this.map.currentRoom.getEnemy() == enemy6)
        {
            txtAreaStory.appendText("trying to kill poor lizard.\nit quickly shambles away.\n\n");
            lblCurrentEnemy.setText("");
            lblEnemyHP.setText("");
        }
        else
        {
            character.setHp(map.currentRoom.enemy.Fight(character.getHp()));
            map.currentRoom.enemy.setHp(character.Fight(map.currentRoom.enemy.getHp()));

            if (map.currentRoom.enemy.hp == 0)
            {
                txtAreaStory.appendText("you killed the beast.\nit dropped something useful.\n\n");
                map.currentRoom.setItem(map.currentRoom.enemy.getItem());
                map.currentRoom.setDescription("nothing but an empty room.\n");
                map.currentRoom.setEnemy(enemy0);
            }

            if (character.hp == 0) gameover();
            else updateStatus();

            /*if(map.currentRoom.enemy.getDamage() < character.getHp())
            {
                character.setHp(character.getHp() - map.currentRoom.enemy.getDamage());
                lblCurrentHP.setText(String.valueOf(character.getHp()));
                if(character.getDamage() < map.currentRoom.enemy.getHp())
                {
                    map.currentRoom.enemy.setHp(map.currentRoom.enemy.getHp() - character.getDamage());
                    lblEnemyHP.setText(String.valueOf(map.currentRoom.enemy.getHp()));
                } else {
                    txtAreaStory.appendText("you killed the beast.\nit dropped something useful.\n\n");
                    map.currentRoom.setItem(map.currentRoom.enemy.getItem());
                    map.currentRoom.setDescription("nothing but an empty room.\n");
                    map.currentRoom.setEnemy(enemy0);
                    updateStatus();
                    //lblnewItem.setText(map.currentRoom.item.getName());
                    //lblCurrentEnemy.setText(map.currentRoom.enemy.getName());
                    //lblEnemyHP.setText("");
                }
            }
            else
            {
                gameover();
            }*/
        }
    }

    public void gameover() {
        FadeTransition t = new FadeTransition(new Duration(1000), gamepad);
        t.setFromValue(1);
        t.setToValue(0);
        t.play();
        /*txtAreaStory.setText("\n\n\nY O U   D I E D !");
        character.setHp(0);
        lblCurrentHP.setText(String.valueOf(character.getHp()));
        btnSelectUse.setDisable(true);
        btnFight.setDisable(true);
        btnBack.setDisable(true);
        btnCollectItem.setDisable(true);
        btnLeft.setDisable(true);
        btnMoveOn.setDisable(true);
        btnRight.setDisable(true);
        txtInterface.setDisable(true);*/
        //TODO: delete inventory
    }

    public void selectuse(ActionEvent actionEvent) {
        try {
            //select item from list to use
            Item selectItem = lstItems.getSelectionModel().getSelectedItem();
            int selectIndex = lstItems.getSelectionModel().getSelectedIndex();
            if (!selectItem.equals(item0)) {
                //key opens locked door
                if (selectItem.equals(usableItem1) && map.currentRoom.getNumber() != 15 && map.currentRoom.isLocked() /*&& !map.currentRoom.doors.isTop() || !map.currentRoom.doors.isRight() || !map.currentRoom.doors.isLeft() || !map.currentRoom.doors.isBottom() &&*/) {
                    if (map.currentRoom.doors.getRoomTop() != 0) {
                        map.currentRoom.doors.setTop(true);
                    } else if (map.currentRoom.doors.getRoomRight() != 0) {
                        map.currentRoom.doors.setRight(true);
                    } else if (map.currentRoom.doors.getRoomLeft() != 0) {
                        map.currentRoom.doors.setLeft(true);
                    } else if (map.currentRoom.doors.getRoomBottom() != 0) {
                        map.currentRoom.doors.setBottom(true);
                    }
                    txtAreaStory.appendText("door is unlocked now.\n\n");
                    lstItems.getItems().remove(selectIndex);
                //rubin, emerald and sapphire for gnome
                } else if ((selectItem.equals(item1) || selectItem.equals(item2) || selectItem.equals(item3)) && this.map.currentRoom.getNumber() == 15) {
                    counterGems++;
                    lstItems.getItems().remove(selectIndex);
                    txtAreaStory.appendText("those wrinkled eyes get wide and he nods excitedly.\n\n");
                    if (counterGems == 3) {
                        txtAreaStory.appendText("the gnome leaves. The door to freedom stands open.\n\n"); //and drops something useful.\n\n");
                        //map.currentRoom.setItem(map.currentRoom.enemy.getItem());
                        map.currentRoom.setDescription("nothing but an empty room.");
                        map.currentRoom.setEnemy(enemy0);
                        map.currentRoom.doors.setTop(true);
                        updateStatus();
                    }
                }
                //water or food recover hp
                else if (selectItem.equals(usableItem2) || selectItem.equals(usableItem4)) {
                    //TODO: call function from item?
                    if (character.getHp() + selectItem.getValue() > character.getMaxHP()) {
                        character.setHp(character.getMaxHP());
                    } else {
                        character.setHp(character.getHp() + selectItem.getValue());
                    }
                    updateStatus();
                    lstItems.getItems().remove(selectIndex);
                }
                //armor increases max hp
                else if (selectItem.equals(usableItem3)) {
                    //TODO: call function from item?
                    character.setMaxHP(character.getMaxHP() + selectItem.getValue());
                    character.setHp(character.getMaxHP());
                    lblCurrentArmor.setText(String.valueOf(lstItems.getSelectionModel().getSelectedItem()));
                    updateStatus();
                    lstItems.getItems().remove(selectIndex);
                }
                //weapon increases damage
                else if (selectItem.equals(weapon1) || selectItem.equals(weapon2)) { // || selectItem.equals(weapon3)) {
                    //TODO: call function from item?
                    lblCurrentWeapon.setText(String.valueOf(lstItems.getSelectionModel().getSelectedItem()));
                    int currDamage = character.getDamage() - 2;
                    character.setDamage(character.getDamage() - currDamage + selectItem.getValue());
                    if (selectItem.equals(weapon1)) {
                        character.setWeapon(weapon1);
                    } else if (selectItem.equals(weapon2)) {
                        character.setWeapon(weapon2);
                    } else {
                        //character.setWeapon(weapon3);
                    }
                    updateStatus();
                } else {
                    txtAreaStory.appendText("there is no use for this yet.\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectItem(ActionEvent actionEvent) {
        //TODO: add to inventory
        //add item to list
        if (map.currentRoom.getItem() != item0)
        {
            lstItems.getItems().add(map.currentRoom.getItem());
            //delete item from room
            map.currentRoom.setItem(item0);
            lblnewItem.setText("");
        }
        else
        {
            txtAreaStory.appendText("there is nothing you should take with you.\n\n");
        }
    }
}

package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller {
    public SplitPane gamepad;                   //Gamepad
    public Button btnMoveOn;                    //Movement
    public Button btnLeft;
    public Button btnBack;
    public Button btnRight;
    public Button btnFight;                     //Interaction
    public Button btnCollectItem;
    public Button btnSelectUse;
    public TextField txtInterface;              //no use yet
    public Label lblHP;                         //Labels
    public Label lblDamage;
    public Label lblWeapon;
    public Label lblItems;
    public Label lblnewItem;                    //Values
    public Label lblCurrentEnemy;
    public Label lblCurrentRoom;
    public Label lblCurrentHP;
    public Label lblMaxHP;
    public Label lblCurrentWeapon;
    public Label lblCurrentDamage;
    public Label lblCurrentArmor;
    public TextArea txtAreaStory;               //Story
    public ListView<Item> lstItems;             //Inventory //no use yet
    public Label lblR1;                         //Map
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
    public Label[] mapLabels = new Label[16];   //array for mini-map on the side
    public ImageView dungeon;
    public Button btnStartGame;

    private Character character;
    private Map map = new Map();
    //private Inventory inventory;  //no use yet
    private int counterGems = 0;    //3 needed to end game

    public void initialize() { //executed when program started
        map.CreateMap();
        character = new Character(20, map.weapon0,2);
        lblCurrentArmor.setText("");
        txtAreaStory.setText("you wake up. dizzy and shivering.\n");
        txtAreaStory.appendText(String.valueOf(map.currentRoom));
        mapLabels[0] = lblR1;   //fill array for mini-map on the side
        mapLabels[1] = lblR2;
        mapLabels[2] = lblR3;
        mapLabels[3] = lblR4;
        mapLabels[4] = lblR5;
        mapLabels[5] = lblR6;
        mapLabels[6] = lblR7;
        mapLabels[7] = lblR8;
        mapLabels[8] = lblR9;
        mapLabels[9] = lblR10;
        mapLabels[10] = lblR11;
        mapLabels[11] = lblR12;
        mapLabels[12] = lblR13;
        mapLabels[13] = lblR14;
        mapLabels[14] = lblR15;
        updateStatus();
    }

    public void startGame(ActionEvent actionEvent) {
        FadeTransition t = new FadeTransition(new Duration(6000), dungeon);
        t.setFromValue(1);
        t.setToValue(0.25);
        t.play();

        dungeon.setDisable(true);
        btnStartGame.setDisable(true);
        btnStartGame.setOpacity(0);
    }

    private void updateStatus() {
        if (character.getHp() <= 0) gameover();
        else {
            lblCurrentHP.setText(String.valueOf(character.getHp()));
            lblMaxHP.setText(String.valueOf(character.getMaxHP()));
            lblCurrentDamage.setText(String.valueOf(character.getDamage()));
            lblCurrentWeapon.setText(String.valueOf(character.getWeapon()));
            lblCurrentRoom.setText("Room: " + map.currentRoom.getNumber());
            lblnewItem.setText(String.valueOf(map.currentRoom.item));
            lblCurrentEnemy.setText(String.valueOf(map.currentRoom.enemy));
            mapLabels[map.currentRoom.getNumber() - 1].setText("@");
            mapLabels[map.currentRoom.getNumber() - 1].setTextFill(Color.RED);
        }
    }

    public void moveon(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isTop()) {
            txtAreaStory.appendText("you move on.\n\n");
            switch(map.currentRoom.doors.getRoomTop()) {
                case 3: //water
                    map.rooms[map.currentRoom.doors.getRoomTop()-1].setItem(map.usableItem2);
                    break;
                case 8:
                    txtAreaStory.appendText("going upstairs...\n");
                    break;
                case 9: //food
                case 14:
                    map.rooms[map.currentRoom.doors.getRoomTop()-1].setItem(map.usableItem4);
                    break;
                case 10: //lizard
                    map.rooms[map.currentRoom.doors.getRoomTop()-1].setEnemy(map.enemy6);
                    character.setHp(map.rooms[map.currentRoom.doors.getRoomTop()-1].enemy.Fight(character.getHp()));
                    //if(character.getHp() == 0) gameover();
                    break;
                default:
                    break;
            }
            mapLabels[map.currentRoom.getNumber()-1].setTextFill(Color.BLACK);
            map.setCurrentRoom(map.rooms[map.currentRoom.doors.getRoomTop()-1]);
            txtAreaStory.appendText(String.valueOf(map.currentRoom));
            updateStatus();
        } else {
            if(map.currentRoom.doors.getRoomTop() != 0) {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }

    public void goleft(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isLeft()) {
            txtAreaStory.appendText("you go left.\n\n");
            switch (map.currentRoom.doors.getRoomLeft()) {
                case 1: //trap door
                    txtAreaStory.appendText("it's a trap (door)!\nyou fall deep and hit the ground amidst a puddle of blood.\n");
                    character.setHp(character.getHp() - 6);
                    //if (character.getHp() == 0) gameover();
                    break;
                case 3: //water
                    map.rooms[map.currentRoom.doors.getRoomLeft()-1].setItem(map.usableItem2);
                    break;
                case 9: //food
                case 14:
                    map.rooms[map.currentRoom.doors.getRoomLeft()-1].setItem(map.usableItem4);
                    break;
                default:
                    break;
            }
            mapLabels[map.currentRoom.getNumber()-1].setTextFill(Color.BLACK);
            map.setCurrentRoom(map.rooms[map.currentRoom.doors.getRoomLeft()-1]);
            txtAreaStory.appendText(String.valueOf(map.currentRoom));
            updateStatus();
        } else {
            if(map.currentRoom.doors.getRoomLeft() != 0) {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }

    public void goback(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isBottom()) {
            txtAreaStory.appendText("you go back.\n\n");
            switch (map.currentRoom.doors.getRoomBottom()) {
                case 3: //water
                    map.rooms[map.currentRoom.doors.getRoomBottom()-1].setItem(map.usableItem2);
                    break;
                case 5:
                    txtAreaStory.appendText("going downstairs...\n");
                    break;
                case 9: //food
                case 14:
                    map.rooms[map.currentRoom.doors.getRoomBottom()-1].setItem(map.usableItem4);
                    break;
                case 10: //lizard
                    map.rooms[map.currentRoom.doors.getRoomBottom()-1].setEnemy(map.enemy6);
                    character.setHp(map.rooms[map.currentRoom.doors.getRoomBottom()-1].enemy.Fight(character.getHp()));
                    //if (character.getHp() == 0) gameover();
                    break;
                default:
                    break;
            }
            mapLabels[map.currentRoom.getNumber()-1].setTextFill(Color.BLACK);
            map.setCurrentRoom(map.rooms[map.currentRoom.doors.getRoomBottom()-1]);
            txtAreaStory.appendText(String.valueOf(map.currentRoom));
            updateStatus();
        } else {
            if(map.currentRoom.doors.getRoomBottom() != 0) {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }

    public void goright(ActionEvent actionEvent) {
        if(map.currentRoom.doors.isRight()) {
            txtAreaStory.appendText("you go right.\n\n");
            switch (map.currentRoom.doors.getRoomRight()) {
                case 3: //water
                    map.rooms[map.currentRoom.doors.getRoomRight()-1].setItem((map.usableItem2));
                    break;
                case 9: //food
                case 14:
                    map.rooms[map.currentRoom.doors.getRoomRight()-1].setItem(map.usableItem4);
                    break;
                default:
                    break;
            }
            mapLabels[map.currentRoom.getNumber()-1].setTextFill(Color.BLACK);
            map.setCurrentRoom(map.rooms[map.currentRoom.doors.getRoomRight()-1]);
            txtAreaStory.appendText(String.valueOf(map.currentRoom));
            updateStatus();
        } else {
            if(map.currentRoom.doors.getRoomRight() != 0) {
                txtAreaStory.appendText("door is locked.\n\n");
            }
        }
    }

    public void fight(ActionEvent actionEvent) {
        //no enemy
        if(map.currentRoom.getEnemy() == map.enemy0) {
            txtAreaStory.appendText("you can't fight without an enemy.\n\n");
        }
        //small lizard
        else if (map.currentRoom.getEnemy() == map.enemy6) {
            txtAreaStory.appendText("trying to kill poor lizard.\nit quickly shambles away.\n\n");
            map.currentRoom.setEnemy(map.enemy0);
        }
        //any other enemy
        else {
            character.setHp(map.currentRoom.enemy.Fight(character.getHp()));
            map.currentRoom.enemy.setHp(character.Fight(map.currentRoom.enemy.getHp()));
            if (map.currentRoom.enemy.hp == 0) {
                if (map.currentRoom.doors.getRoomRight() != 0) map.currentRoom.doors.setRight(true);
                if (map.currentRoom.doors.getRoomTop() != 0) map.currentRoom.doors.setTop(true);
                if (map.currentRoom.doors.getRoomLeft() != 0) map.currentRoom.doors.setLeft(true);
                if (map.currentRoom.doors.getRoomBottom() != 0) map.currentRoom.doors.setBottom(true);
                txtAreaStory.appendText("you killed the beast!\nit dropped something useful.\n\n");
                map.currentRoom.setItem(map.currentRoom.enemy.getItem());
                map.currentRoom.setDescription("nothing but an empty room.\n");
                map.currentRoom.setEnemy(map.enemy0);
            }
        }
        updateStatus();
    }

    public void selectuse(ActionEvent actionEvent) {
        try {
            //select item from list to use
            Item selectItem = lstItems.getSelectionModel().getSelectedItem();
            int selectIndex = lstItems.getSelectionModel().getSelectedIndex();

            if (selectItem != null) {
                //key opens locked door
                if (selectItem.equals(map.usableItem1) && map.currentRoom.getNumber() != 15 && map.currentRoom.isLocked()) {
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
                }
                //rubin, emerald and sapphire for gnome
                else if ((selectItem.equals(map.item1) || selectItem.equals(map.item2) || selectItem.equals(map.item3)) && map.currentRoom.getNumber() == 15) {
                    counterGems++;
                    lstItems.getItems().remove(selectIndex);
                    txtAreaStory.appendText("those wrinkled eyes get wide and he nods excitedly.\n\n");
                    if (counterGems == 3) {
                        txtAreaStory.appendText("the gnome leaves. The door to freedom stands open...\n\n"); //and drops something useful.\n\n");
                        //map.currentRoom.setItem(map.currentRoom.enemy.getItem());
                        map.currentRoom.setDescription("nothing but an empty room.\n\n");
                        map.currentRoom.setEnemy(map.enemy0);
                        map.currentRoom.doors.setTop(true);
                        updateStatus();
                    }
                }
                //water or food recover hp
                else if (selectItem.equals(map.usableItem2) || selectItem.equals(map.usableItem4)) {
                    character.setHp(selectItem.Recover(character.getHp(), character.getMaxHP()));
                    updateStatus();
                    lstItems.getItems().remove(selectIndex);
                }
                //armor increases max hp
                else if (selectItem.equals(map.usableItem3)) {
                    character.setMaxHP(character.getMaxHP() + selectItem.getValue());
                    character.setHp(character.getMaxHP());
                    lblCurrentArmor.setText(String.valueOf(selectItem));
                    map.currentRoom.setDescription("nothing but an empty room.\n\n");
                    updateStatus();
                    lstItems.getItems().remove(selectIndex);
                }
                //weapon increases damage
                else if (selectItem.equals(map.weapon1) || selectItem.equals(map.weapon2)) { // || selectItem.equals(weapon3)) {
                    character.setDamage(selectItem.value);
                    character.setWeapon((Weapon)selectItem);
                    updateStatus();
                    lstItems.getItems().remove(selectIndex);
                } else {
                    txtAreaStory.appendText("there is no use for this yet.\n\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectItem(ActionEvent actionEvent) {
        if (map.currentRoom.getItem() != map.item0) {
            //add item to list
            lstItems.getItems().add(map.currentRoom.getItem());
            //delete item from room
            map.currentRoom.setItem(map.item0);
            updateStatus();
        } else {
            txtAreaStory.appendText("there is nothing you should take with you.\n\n");
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
    }
}
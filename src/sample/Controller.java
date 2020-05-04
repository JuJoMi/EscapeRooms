package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.font.ImageGraphicAttribute;

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
    public Label lblItems;
    public Label lblnewItem;                    //Values
    public Label lblCurrentEnemy;
    public Label lblCurrentRoom;
    public Label lblCurrentHP;
    public Label lblMaxHP;
    public Label lblCurrentWeapon;
    public Label lblCurrentDamage;
    public Label lblCurrentArmor;
    public TextArea txtAreaStory;               //Storyboard
    public ListView<Item> lstItems;             //Inventory
    public Label lblR1;                         //mini-map
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
    public Label[] mapLabels = new Label[15];   //array for mini-map on the side
    public VBox vboxMenu;
    public Button btnStartGame;
    public Button btnNewGame;
    public ImageView igameover;                 //imageviews gamestate
    public ImageView iexit;
    public ImageView idungeon;
    public ImageView ivSword;                   //imageviews items
    public ImageView ivArmor;
    public ImageView ivKey;

    private Character character;
    private Map map = new Map();
    private int counterGems;    //3 needed to end game

    public void initialize() { //executed when program started
        map.CreateMap();
        character = new Character(20, map.weapon0,2);
        counterGems = 0;
        lblCurrentArmor.setText("");
        txtAreaStory.setText("you wake up. dizzy and shivering.\n");
        txtAreaStory.appendText(String.valueOf(map.currentRoom));
        //fill array for mini-map on the side
        mapLabels[0] = lblR1;
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
        //set up elements
        gamepad.setVisible(true);
        gamepad.setDisable(false);
        gamepad.setOpacity(1);
        idungeon.setDisable(true);  //opacity 0.25
        igameover.setDisable(true);
        igameover.setOpacity(0);
        iexit.setDisable(true);
        iexit.setOpacity(0);
        ivSword.setOpacity(0);
        ivArmor.setOpacity(0);
        ivKey.setOpacity(0);
        updateStatus();
    }

    public void startGame(ActionEvent actionEvent) {
        FadeTransition t = new FadeTransition(new Duration(6000), idungeon);
        t.setFromValue(1);
        t.setToValue(0.25);
        t.play();
        //disable elements
        vboxMenu.setDisable(true);
        btnStartGame.setDisable(true);
        btnStartGame.setOpacity(0);
    }

    public void newGame(ActionEvent actionEvent) {
        //delete mini map
        for (int i = 0; i <= 14; i++) {
            mapLabels[i].setText("");
            mapLabels[i].setTextFill(Color.BLACK);
        }
        //delete inventory
        lstItems.getItems().clear();
        //disable elements
        vboxMenu.setDisable(true);
        btnNewGame.setDisable(true);
        btnNewGame.setOpacity(0);
        idungeon.setOpacity(0.25);
        initialize();
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
            if (map.currentRoom.getNumber() <= 15) {
                mapLabels[map.currentRoom.getNumber() - 1].setText("@");
                mapLabels[map.currentRoom.getNumber() - 1].setTextFill(Color.RED);
            }
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
                    txtAreaStory.appendText("you're going upstairs...\n");
                    break;
                case 9: //food
                case 14:
                    map.rooms[map.currentRoom.doors.getRoomTop()-1].setItem(map.usableItem4);
                    break;
                case 10: //lizard
                    map.rooms[map.currentRoom.doors.getRoomTop()-1].setEnemy(map.enemy6);
                    character.setHp(map.rooms[map.currentRoom.doors.getRoomTop()-1].enemy.Fight(character.getHp()));
                    break;
                case 16:
                    //exit
                    FadeTransition t = new FadeTransition(new Duration(2000), iexit);
                    t.setFromValue(0);
                    t.setToValue(1);
                    t.play();

                    gamepad.setVisible(false);
                    vboxMenu.setDisable(false);
                    btnNewGame.setDisable(false);
                    btnNewGame.setOpacity(1);
                    idungeon.setOpacity(0);
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
                map.currentRoom.setDescription("maybe collect the " + map.currentRoom.item + "?!\n");
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
                    ivKey.setOpacity(0);
                    txtAreaStory.appendText("door is unlocked now.\n\n");
                    lstItems.getItems().remove(selectIndex);
                }
                //rubin, emerald and sapphire for gnome
                else if ((selectItem.equals(map.item1) || selectItem.equals(map.item2) || selectItem.equals(map.item3)) && map.currentRoom.getNumber() == 15) {
                    counterGems++;
                    lstItems.getItems().remove(selectIndex);
                    txtAreaStory.appendText("those wrinkled eyes get wide and he nods excitedly.\n\n");
                    if (counterGems == 3) {
                        txtAreaStory.appendText("the gnome leaves. The door to freedom stands open...\n\n");
                        map.currentRoom.setDescription("nothing but an empty room.\n");
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
                    ivArmor.setOpacity(1);
                    lblCurrentArmor.setText(String.valueOf(selectItem));
                    updateStatus();
                    lstItems.getItems().remove(selectIndex);
                }
                //weapon increases damage
                else if (selectItem.equals(map.weapon1) || selectItem.equals(map.weapon2)) { // || selectItem.equals(weapon3)) {
                    character.setDamage(selectItem.value);
                    character.setWeapon((Weapon)selectItem);
                    ivSword.setOpacity(1);
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
            //key icon visible
            if (map.currentRoom.getItem() == map.usableItem1) {
                ivKey.setOpacity(1);
                //map.currentRoom.setDescription("nothing but an empty room.\n");
            }
            //delete item from room
            if (map.currentRoom.getItem() != map.usableItem2 && map.currentRoom.getItem() != map.usableItem4) {
                map.currentRoom.setDescription("nothing but an empty room.\n");
            }
            map.currentRoom.setItem(map.item0);
            updateStatus();
        } else {
            txtAreaStory.appendText("there is nothing you should take with you.\n\n");
        }
    }
    public void gameover() {
        FadeTransition t = new FadeTransition(new Duration(1000), igameover);
        t.setFromValue(0);
        t.setToValue(1);
        t.play();

        gamepad.setVisible(false);
        gamepad.setDisable(true);
        vboxMenu.setDisable(false);
        btnNewGame.setDisable(false);
        btnNewGame.setOpacity(1);
    }

}
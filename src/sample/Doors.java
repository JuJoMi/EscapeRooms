package sample;

public class Doors {

    //Fields

    protected boolean top;
    protected boolean left;
    protected boolean right;
    protected boolean bottom;
    final protected int roomTop;
    final protected int roomLeft;
    final protected int roomRight;
    final protected int roomBottom;

    //Constructor
    public Doors(boolean top, boolean left, boolean right, boolean bottom, int roomTop, int roomLeft, int roomRight, int roomBottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.roomTop = roomTop;
        this.roomLeft = roomLeft;
        this.roomRight = roomRight;
        this.roomBottom = roomBottom;
    }

    //Methods
    //Getter
    public boolean isTop() {
        return top;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isBottom() {
        return bottom;
    }

    public int getRoomTop() {
        return roomTop;
    }

    public int getRoomLeft() {
        return roomLeft;
    }

    public int getRoomRight() {
        return roomRight;
    }

    public int getRoomBottom() {
        return roomBottom;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    //Function

}

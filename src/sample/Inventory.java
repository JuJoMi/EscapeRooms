package sample;

public class Inventory { //no use yet

    private Item item1, item2, item3;
    protected Item currentItem;

    public Inventory(String name1, int value1, String name2, int value2, String name3, int value3)
    {
        this.item1 = new Item(name1, value1);
        this.item2 = new Item(name2, value2);
        this.item3 = new Item(name3, value3);
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }
}

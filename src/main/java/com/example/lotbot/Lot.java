package com.example.lotbot;

public class Lot {
    private static int NUM_CREATED = 0;
    private int id;
    private Space[] spaces;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Space[] getSpaces() {
        return spaces;
    }

    public void setSpaces(Space[] spaces) {
        this.spaces = spaces;
    }

    /**
     * creates a new lot, setting that lot's spaces
     * to a random amount.
     * @return
     */
    public static Lot createLot() {
        Lot l = new Lot();
        l.setId(NUM_CREATED++);
        l.setSpaces(new Space[(int)(Math.random() * 50)]);

        return l;
    }
}

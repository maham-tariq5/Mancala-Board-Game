package mancala;

public class Player {
    private String name;
    private Store store;

    public Player() {
        name = "Player";
        store = new Store();
    }

    public Player(String name) {
        this.name = name;
        store = new Store();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getStoneCount() {
        return store.getTotalStones();
    }

    public Store getStore() {
        return store;
    }
}


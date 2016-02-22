package lt.arminai.streams.entities;

/**
 * Created by Vytautas on 2015-09-13.
 */
public class Transaction {
    private int id;
    private String name;

    public Transaction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

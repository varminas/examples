package lt.arminai.ws.streamingoutput;

public class Record {
    private final String name;
    private final byte[] content;

    public Record(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }
}

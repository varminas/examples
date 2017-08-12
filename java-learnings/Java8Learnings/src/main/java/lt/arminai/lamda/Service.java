package lt.arminai.lamda;

public class Service {
    public String getName(int i) {
        if (i == 9) {
            throw new RuntimeException("Illegal number");
        }

        return "Name " + 1;
    }
}

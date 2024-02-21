import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MonServer s = new MonServer(8081);
        s.start();
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class Server {
    private final int PORT = 8189;
    private List<ClientHandler> clients;

    public Server () {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

        } catch (IOException e) {

        }
    }

    public void broadcastMessage(String message) {

    }

    public void writeServerLog(String message) {

    }
}

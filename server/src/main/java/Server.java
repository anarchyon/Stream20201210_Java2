import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public Server () {
        clients = new ArrayList<>();
        authService = new DBAuthService();
        if (!DBAuthService.isConnected()) {
            authService = new BaseAuthService();
            System.out.println("DB is not connected, using BaseAuthService");
        }
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public synchronized void broadcastClientsList() {
        List<String> list = new ArrayList<>();
        for (ClientHandler client : clients) {
            list.add(client.getNick());
        }
        String stringList = "/list" + list.toString();
        broadcastMessage(stringList);
    }

    public void privateMessage(String nick, String message) {
        for (ClientHandler client : clients) {
            if (client.getNick().equals(nick)) {
                client.sendMessage(message);
                break;
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler client : clients) {
            if (client.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe (ClientHandler client) {
        clients.add(client);
        System.out.println(client.getNick() + " subscribed");
        broadcastClientsList();
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
        System.out.println(client.getNick() + " unsubscribed");
        broadcastClientsList();
    }
}

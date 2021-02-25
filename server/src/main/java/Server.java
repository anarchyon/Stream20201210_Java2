import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int PORT = 8189;
    private List<ClientHandler> clients;

    public Server () {
        clients = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
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
        System.out.println(client.getNick() + " subscribed");
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        System.out.println(client.getNick() + " unsubscribed");
        clients.remove(client);
    }
}

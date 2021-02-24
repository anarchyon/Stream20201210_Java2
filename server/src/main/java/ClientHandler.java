import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {

                }finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {

        }
    }

    public void authentication() throws IOException {

    }

    public void readMessages() throws IOException {
        while (true) {
            String incomingMessage = in.readUTF();
            server.writeServerLog("от " + nick + incomingMessage);
            if (incomingMessage.equalsIgnoreCase("/end")) {
                return;
            }
            server.broadcastMessage(incomingMessage);
        }

    }

    public void sendMessage(String message) {

    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

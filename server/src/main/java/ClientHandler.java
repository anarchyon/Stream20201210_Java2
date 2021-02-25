import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;

    public static final String DISCONNECT_SEQUENCE = "/end";

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
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authentication() throws IOException {
        //TODO
        while (true) {
            String incomingMessage = in.readUTF();
            if (incomingMessage.startsWith("/nick")) {
                String tNick = incomingMessage.split("\\s")[1];
                if (!server.isNickBusy(tNick)) {
                    out.writeUTF("/nickok");
                    nick = tNick;
                    server.broadcastMessage(nick + " присоединился к чату");
                    break;
                } else {
                    out.writeUTF("/nickbad");
                }
            }
        }
        server.subscribe(this);
    }

    public void readMessages() throws IOException {
        while (true) {
            String incomingMessage = in.readUTF();
            System.out.println(buildString(incomingMessage));
            if (incomingMessage.equalsIgnoreCase(DISCONNECT_SEQUENCE)) {
                return;
            }
            if (incomingMessage.startsWith("/w")) {
                String[] parts = incomingMessage.split("\\s");
                StringBuilder message = new StringBuilder();
                for (int i = 2; i < parts.length; i++) {
                    message.append(parts[i]);
                    message.append(" ");
                }
                server.privateMessage(parts[1], "(private)" + buildString(message.toString().trim()));
            } else {
                server.broadcastMessage(buildString(incomingMessage));
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildString (String str) {
        return String.format("%s (%s): %s",
                nick,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")),
                str);
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(nick + " вышел из чата");
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

    public String getNick() {
        return nick;
    }
}

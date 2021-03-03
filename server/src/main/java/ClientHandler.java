import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;
    private boolean isSubscribed;

    private static int num;

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
                } catch (SocketException se) {
                    System.out.println("Connection with client broken");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (isSubscribed) {
                        closeConnection();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authentication() throws IOException {
        while (!isSubscribed) {
            String incomingMessage = in.readUTF();
            if (incomingMessage.startsWith("/auth")) {
                String[] tokens = incomingMessage.split("\\s");
                nick = server.getAuthService().getNicknameByLoginAndPassword(tokens[1], tokens[2]);
                if (nick != null) {
                    System.out.println("nick is found");
                    out.writeUTF("/authok" + nick);
                    server.broadcastMessage(nick + " присоединился к чату");
                    server.subscribe(this);
                    isSubscribed = true;
                } else {
                    out.writeUTF("/authbad");
                }
            }
        }
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

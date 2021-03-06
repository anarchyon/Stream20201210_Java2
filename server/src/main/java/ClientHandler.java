import java.io.BufferedInputStream;
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
    private final long TIME_FOR_AUTHORIZATION_IN_MILLIS = 120000;
    private boolean isAuthTimeOut;

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
                    if (isAuthTimeOut) {
                        System.out.println("Authorization timeout");
                    }
                    System.out.println("Connection with client broken");
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
        long start = System.currentTimeMillis();
        while (!isSubscribed) {
            if (in.available() > 0) {
                String incomingMessage = in.readUTF();
                if (incomingMessage.equals("/end")) {
                    break;
                }
                if (incomingMessage.startsWith("/auth")) {
                    String[] tokens = incomingMessage.split("\\s");
                    nick = server.getAuthService().getNicknameByLoginAndPassword(tokens[1], tokens[2]);
                    if (nick != null) {
                        if (server.isNickBusy(nick)) {
                            System.out.println("Client is logged in already");
                            out.writeUTF("/authbusy");
                        } else {
                            System.out.println("Client logged in as " + nick);
                            out.writeUTF("/authok" + nick);
                            server.broadcastMessage(null,nick + " присоединился к чату");
                            server.subscribe(this);
                            isSubscribed = true;
                        }
                    } else {
                        out.writeUTF("/authbad");
                    }
                }
            }
            if (System.currentTimeMillis() - start > TIME_FOR_AUTHORIZATION_IN_MILLIS) {
                isAuthTimeOut = true;
                out.writeUTF("/authtimeout");
                closeConnection();
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
                server.privateMessage(this, parts[1], buildString(message.toString().trim()));
            } else {
                server.broadcastMessage(this, buildString(incomingMessage));
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

    private String buildString(String str) {
        return String.format("(%s): %s",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")),
                str);
    }

    public void closeConnection() {
        if (isSubscribed) {
            server.unsubscribe(this);
            server.broadcastMessage(null, nick + " вышел из чата");
        }
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

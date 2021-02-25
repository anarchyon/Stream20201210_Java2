import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final int PORT = 8189;
    private final String INET_ADDRESS = "localhost";

    public static final String DISCONNECT_SEQUENCE = "/end";

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;

    ChatWindow gui;

    public Client() {
        try {
            socket = new Socket(INET_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                nick = JOptionPane.showInputDialog(gui, "Введите ник:");
                if (nick == null) {
                    System.exit(0);
                }
                out.writeUTF("/nick " + nick);
                String answer = in.readUTF();
                if (answer.equals("/nickok")) {
                    break;
                }
            }
            gui = new ChatWindow(this);
            new Thread(this::readMessages).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(gui, "Unable to connect to server");
            System.exit(0);
        }
    }

    private void readMessages() {
        try {
            while (true) {
                String incomingMessage = in.readUTF();
                if (incomingMessage.startsWith(nick)) {
                    incomingMessage = incomingMessage.replace(nick, "Я");
                }
                gui.appendText("\n\n" + incomingMessage);
            }
        } catch (IOException e) {
            System.out.println("Socket was closed");
        }
    }

    public void sendMessage(String message) {
        try {
            if (!socket.isClosed()) {
                out.writeUTF(message);
                if (message.equalsIgnoreCase(DISCONNECT_SEQUENCE)) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}

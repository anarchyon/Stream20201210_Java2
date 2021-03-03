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

    private int num = 1;

    private boolean isConnectionOk;
    private boolean isAuthOk;
    private Callback<Boolean> isTConnectionOk;
    private Callback<Boolean> isTAuthOk;
    private Callback<String> callOnMsgReceived;
    private Callback<String> callOnChangeClientList;

    public void connect() {
        try {
            socket = new Socket(INET_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            isConnectionOk = true;
            Thread authAndReading = new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            authAndReading.start();
        } catch (IOException e) {
            System.out.println("Unable to connect to server");
            isConnectionOk = false;
        }
    }

    public void authentication() throws IOException{
        while (true) {
            String tNick = "client" + num++;
            out.writeUTF("/nick " + tNick);
            String answer = in.readUTF();
            if (answer.equals("/nickok")) {
                nick = tNick;
                break;
            } else if (answer.equals("/nickbad")) {
                System.out.println("Введённый вами ник уже используется");
                //JOptionPane.showMessageDialog(gui, "Введённый вами ник уже используется");
            }
        }
    }

    private void readMessages() {
        try {
            while (true) {
                String incomingMessage = in.readUTF();
                if (incomingMessage.startsWith("/list")) {
                    callOnChangeClientList.callback(incomingMessage.replaceFirst("/list", ""));
                }else if (incomingMessage.startsWith(nick)) {
                    incomingMessage = incomingMessage.replaceFirst(nick, "Я");
                    callOnMsgReceived.callback("\n\n" + incomingMessage);
                } else {
                    callOnMsgReceived.callback("\n\n" + incomingMessage);
                }
            }
        } catch (IOException e) {
            System.out.println("Socket was closed");
        }
        closeConnection();
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
        System.exit(0);
    }

    public void setCallOnMsgReceived(Callback<String> callOnMsgReceived) {
        this.callOnMsgReceived = callOnMsgReceived;
    }

    public void setCallOnChangeClientList(Callback<String> callOnChangeClientList) {
        this.callOnChangeClientList = callOnChangeClientList;
    }

    public void setIsTConnectionOk(Callback<Boolean> isTConnectionOk) {
        this.isTConnectionOk = isTConnectionOk;
    }

    public void setIsTAuthOk(Callback<Boolean> isTAuthOk) {
        this.isTAuthOk = isTAuthOk;
    }

    public boolean isConnectionOk() {
        return isConnectionOk;
    }

    public boolean isAuthOk() {
        return isAuthOk;
    }
}

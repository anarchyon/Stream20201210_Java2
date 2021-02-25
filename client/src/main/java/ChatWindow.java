import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow extends JFrame {
    public static final int MINIMUM_WIDTH = 800;
    public static final int MINIMUM_HEIGHT = 600;

    private JTextArea chatArea;
    private JTextField yourMessage;
    private Client client;

    public ChatWindow(Client client) {
        this.client = client;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        setTitle(client.getNick());
        setSize(windowSize);
        setMinimumSize(windowSize);
        setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.sendMessage(Client.DISCONNECT_SEQUENCE);
            }
        });

        add(chatPanel());
        add(writePanel(), BorderLayout.SOUTH);

        setVisible(true);
        yourMessage.grabFocus();
    }

    private JPanel chatPanel() {
        JPanel chatPanel = new JPanel();
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chat"));
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        chatPanel.setLayout(new BorderLayout());
        chatPanel.add(new JScrollPane(chatArea));

        return chatPanel;
    }

    private JPanel writePanel() {
        JPanel writePanel = new JPanel();
        yourMessage = new JTextField();
        JButton send = new JButton("Отправить");
        writePanel.setLayout(new BorderLayout());

        send.addActionListener(e -> sendMessage());
        yourMessage.addActionListener(e -> sendMessage());

        writePanel.add(yourMessage);
        writePanel.add(send, BorderLayout.EAST);
        return writePanel;
    }

    public void appendText(String message) {
        chatArea.append(message);
    }

    private void sendMessage() {
        yourMessage.grabFocus();
        if (yourMessage.getText().trim().equals("")) {
            return;
        }
        String message = yourMessage.getText().trim();
        client.sendMessage(message);
        yourMessage.setText("");
    }
}

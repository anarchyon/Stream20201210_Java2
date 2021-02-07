package lesson4.homework;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    public static final int MINIMUM_WIDTH = 800;
    public static final int MINIMUM_HEIGHT = 600;

    private JTextArea chatArea;
    private JTextField yourMessage;

    public ChatWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        setSize(windowSize);
        setMinimumSize(windowSize);
        setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

    private void sendMessage() {
        yourMessage.grabFocus();
        if (yourMessage.getText().trim().equals("")) {
            return;
        }
        chatArea.append(String.format("\n\nЯ(%s):\n%s",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")),
                yourMessage.getText()));
        yourMessage.setText("");
    }
}

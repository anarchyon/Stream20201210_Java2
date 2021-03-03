import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow extends JFrame {
    public static final int MINIMUM_WIDTH = 800;
    public static final int MINIMUM_HEIGHT = 600;
    public static final int LOGON_MINIMUM_WIDTH = 350;
    public static final int LOGON_MINIMUM_HEIGHT = 200;


    private JFrame registerLogon;
    private JTextArea listArea;
    private JTextArea chatArea;
    private JTextField yourMessage;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Client client;

    public ChatWindow() {
        this.client = new Client();

        setCallbacks();
        setMainWindow();

        client.connect();
        if (!client.isConnectionOk()) {
            JOptionPane.showMessageDialog(this, "Unable to connect to server");
            System.exit(0);
        }

        setRegisterLogonWindow();

        setTitle(client.getNick());
        yourMessage.grabFocus();
    }

    private void setCallbacks() {
        client.setCallOnChangeClientList(this::listMembers);
        client.setCallOnMsgReceived(this::appendText);
    }

    private void setMainWindow() {
        Dimension windowSize = new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        setSize(windowSize);
        setMinimumSize(windowSize);
        setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.sendMessage(Client.DISCONNECT_SEQUENCE);
                super.windowClosing(e);
            }
        });

        add(listPanel(), BorderLayout.NORTH);
        add(chatPanel());
        add(writePanel(), BorderLayout.SOUTH);
    }

    private JPanel listPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setBorder(BorderFactory.createTitledBorder("List"));
        listArea = new JTextArea();
        listArea.setEditable(false);

        listPanel.setLayout(new BorderLayout());
        listPanel.add(new JScrollPane(listArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        return listPanel;
    }

    private JPanel chatPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chat"));
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        //chatPanel.setLayout(new BorderLayout());
        chatPanel.add(new JScrollPane(chatArea));

        return chatPanel;
    }

    private JPanel writePanel() {
        JPanel writePanel = new JPanel(new BorderLayout());
        yourMessage = new JTextField();
        JButton send = new JButton("Отправить");
        //writePanel.setLayout(new BorderLayout());

        send.addActionListener(e -> sendMessage());
        yourMessage.addActionListener(e -> sendMessage());

        writePanel.add(yourMessage);
        writePanel.add(send, BorderLayout.EAST);
        return writePanel;
    }

    private void setRegisterLogonWindow() {
        registerLogon = new JFrame();
        Dimension logonSize = new Dimension(LOGON_MINIMUM_WIDTH, LOGON_MINIMUM_HEIGHT);
        registerLogon.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        registerLogon.setSize(logonSize);
        registerLogon.setResizable(false);
        registerLogon.setLocation((screenSize.width - logonSize.width) / 2, (screenSize.height - logonSize.height) / 2);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("register", registerPanel());
        tabbedPane.addTab("logon", logonPanel());
        registerLogon.add(tabbedPane);
        registerLogon.setVisible(true);
    }

    private JPanel logonPanel() {
        JPanel logonPanel = new JPanel();
        logonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton logonButton = new JButton("Вход");
        logonButton.setPreferredSize(new Dimension(150, 30));
        JLabel loginLabel = new JLabel("Login:", SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);

        logonPanel.add(loginLabel);
        logonPanel.add(loginField());
        logonPanel.add(passwordLabel);
        logonPanel.add(passwordField());
        logonPanel.add(logonButton);
        logonButton.addActionListener(str -> sendMessage());
        return logonPanel;
    }

    private JPanel registerPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JTextField nickField = new JTextField();
        nickField.setPreferredSize(new Dimension(220, 25));
        JButton registerButton = new JButton("Регистрация");
        JLabel loginLabel = new JLabel("Login:", SwingConstants.RIGHT);
        JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
        JLabel nickLabel = new JLabel("Nickname:", SwingConstants.RIGHT);

        registerButton.setPreferredSize(new Dimension(150, 30));
        registerPanel.add(loginLabel);
        registerPanel.add(loginField());
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordField());
        registerPanel.add(nickLabel);
        registerPanel.add(nickField);
        registerPanel.add(registerButton);
        return registerPanel;
    }

    private JTextField loginField() {
        JTextField loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(220, 25));
        return loginField;
    }

    private JPasswordField passwordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(220, 25));
        return passwordField;
    }

    public void appendText(String message) {
        chatArea.append(message);
    }

    private void listMembers(String list) {
        listArea.setText(list);
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

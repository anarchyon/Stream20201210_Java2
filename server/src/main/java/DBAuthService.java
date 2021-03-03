import java.sql.*;

public class DBAuthService implements AuthService{
    public static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/dbchat";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "s32a7Sdqg";
    public static Connection connection;
    private static boolean isConnected;

    static {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            isConnected = true;
        } catch (SQLException throwables) {
            isConnected = false;
        }
    }

    public static boolean isConnected() {
        return isConnected;
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT nick FROM chat_clients WHERE login='" + login + "' AND pass='" + password + "'");
             ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                return result.getString("nick");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

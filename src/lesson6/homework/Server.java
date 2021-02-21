package lesson6.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket socket;

    public static void main(String[] args) {
        serverStart();
    }

    private static void serverStart() {
        try (ServerSocket serverSocket = new ServerSocket(8200, 2)) {
            System.out.println("Server started");
            socket = serverSocket.accept();
            handleConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            System.out.println("Client connected");
            out.println("Hello, you connected to server.");
            out.flush();
            Thread messageToClient = new Thread(() -> {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while (!socket.isClosed()) {
                        if (br.ready()) {
                            String message = br.readLine();
                            out.println(message);
                            out.flush();
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageToClient.start();
            while (true) {
                String incomingMessage = in.readLine();
                System.out.println("Message from client: " + incomingMessage);
                if (incomingMessage.equalsIgnoreCase("stop")) {
                    System.out.println("Client disconnected");
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

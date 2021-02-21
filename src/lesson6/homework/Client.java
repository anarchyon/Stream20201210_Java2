package lesson6.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        startClient();
    }

    private static void startClient() {
        try (Socket socket = new Socket("localhost", 8200);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream());
             Scanner consoleText = new Scanner(System.in)) {
            Thread messageFromServer = new Thread(() -> {
                try {
                    while (!socket.isClosed()) {
                        String incomingMessage = in.readLine();
                        System.out.println("Message from server: " + incomingMessage);
                    }
                } catch (SocketException se) {
                    System.out.println("You disconnected from server");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            messageFromServer.start();
            String message;
            do {
                message = consoleText.nextLine();
                out.println(message);
                out.flush();
            } while (!message.equalsIgnoreCase("stop"));
        } catch (IOException e) {
            System.out.println("Unable to connect to server");
        }
    }
}

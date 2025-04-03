package multichat;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;

    public Client(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to the group chat!");
            startReading();
            startWriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        Runnable readerThread = () -> {
            try {
                String msg;
                while ((msg = br.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(readerThread).start();
    }

    public void startWriting() {
        Runnable writerThread = () -> {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                String content;
                while ((content = consoleReader.readLine()) != null) {
                    out.println(content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(writerThread).start();
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 8888);
    }
}


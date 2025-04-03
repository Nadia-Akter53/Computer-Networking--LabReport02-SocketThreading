package multichat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            clients.add(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = br.readLine()) != null) {
                if (msg.equalsIgnoreCase("Exit")) {
                    break;
                }
                sendToAll(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clients.remove(this);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToAll(String message) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.out.println(message);
            }
        }
    }
}

package multichat;

import java.net.*;

public class Server {
    private ServerSocket server;
int i=1;
    public Server() {
        try {
            server = new ServerSocket(8888);
            System.out.println("Server is ready..");
           for (int j=1; j!=0; j++){
                Socket socket = server.accept();
                System.out.println("client-"+i+ "connected!");
                i++;
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            System.out.println(" "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}

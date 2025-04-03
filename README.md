This project is a group chat application developed using socket programming, allowing multiple users to communicate in real time. Users can join the chat by running the client program multiple times, while the server manages all connections and ensures seamless message delivery. Whenever a client sends a message, it is broadcasted to all other connected users, creating an interactive chat environment. This project demonstrates practical applications of network communication, socket handling, and multithreading.


The Server Class manages client connections, listens for incoming messages, and broadcasts them to all other clients. It creates a new thread for each client and handles disconnections to keep the chat running smoothly.

The Client Class connects users to the server, allowing them to send and receive messages. It runs a separate thread for receiving messages so users can chat without interruptions while typing.

The ClientHandler Class runs on the server to manage individual client communication. It listens for messages from clients, forwards them to the server, and handles disconnections to keep everything organized.

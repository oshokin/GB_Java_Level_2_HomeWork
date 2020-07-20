package ru.geekbrains.core;

import ru.geekbrains.chat.common.MessageLibrary;
import ru.geekbrains.net.MessageSocketThreadListener;
import ru.geekbrains.net.ServerSocketThread;
import ru.geekbrains.net.ServerSocketThreadListener;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer implements ServerSocketThreadListener, MessageSocketThreadListener {

    private ServerSocketThread serverSocketThread;
    private ChatServerListener listener;
    private AuthController authController;
    private HashMap<Socket, ClientSessionThread> clientSessions; //Hashtable не стал использовать, т. к. сервер 1 всегда.
    private static final int MAX_CLIENT_CONNECTIONS = 100;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
    }

    public void start(int port) {
        if (serverSocketThread != null && serverSocketThread.isAlive()) {
            return;
        }
        serverSocketThread = new ServerSocketThread(this,"Chat-Server-Socket-Thread", port, 2000);
        serverSocketThread.start();
        authController = new AuthController();
        authController.init();
        clientSessions = new HashMap<>(MAX_CLIENT_CONNECTIONS);
    }

    public void stop() {
        if (serverSocketThread == null || !serverSocketThread.isAlive()) {
            return;
        }
        serverSocketThread.interrupt();
    }

    @Override
    public void onClientConnected() {
        logMessage("Client connected");
    }

    @Override
    public void onSocketAccepted(Socket socket) {
        if (!clientSessions.containsKey(socket)) {
            clientSessions.put(socket, new ClientSessionThread(this, "ClientSessionThread", socket));
        }
    }

    @Override
    public void onException(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onClientTimeout(Throwable throwable) {

    }

    @Override
    public void onSocketReady() {
        logMessage("Socket ready");
    }

    @Override
    public void onSocketClosed() {
        logMessage("Socket Closed");
    }

    @Override
    public void onMessageReceived(Socket socket, String msg) {
        ClientSessionThread clientSession = clientSessions.get(socket);
        if (clientSession instanceof ClientSessionThread) {
            if (clientSession.isAuthorized()) {
                processAuthorizedUserMessage(clientSession, msg);
            } else {
                processUnauthorizedUserMessage(clientSession, msg);
            }
        }
    }

    private void processAuthorizedUserMessage(ClientSessionThread currentSession, String msg) {
        logMessage(msg);
        Map<String, Object> decodedMessage = MessageLibrary.decodeMessage(msg);
        if (decodedMessage == null) {
            return;
        }
        String messageType = (String) (decodedMessage.get("type"));
        String message = (String) (decodedMessage.get("message"));
        if (messageType.equals(MessageLibrary.COMMON_MESSAGE_TYPE)) {
            if (message.startsWith("/all")) {
                clientSessions.forEach((key, clientSession) -> {
                    clientSession.sendMessage(msg);
                });
            } else currentSession.sendMessage(msg);
        }

    }

    private void processUnauthorizedUserMessage(ClientSessionThread clientSession, String msg) {
        Map<String, Object> decodedMessage = MessageLibrary.decodeMessage(msg);
        if (decodedMessage == null) {
            clientSession.authError("Incorrect request: " + msg);
            return;
        }
        String nickname = authController.getNickname((String) (decodedMessage.get("login")), (String) (decodedMessage.get("password")));
        if (nickname == null) {
            clientSession.authDeny();
            return;
        }
        clientSession.authAccept(nickname);
     }

    public void disconnectAll() {
    }

    private void logMessage(String msg) {
        listener.onChatServerMessage(msg);
    }
}

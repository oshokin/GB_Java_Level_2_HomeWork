package ru.geekbrains.net;

import java.net.Socket;

public interface MessageSocketThreadListener {

    void onSocketReady();
    void onSocketClosed();
    void onMessageReceived(Socket socket, String msg);
    void onException(Throwable throwable);
}

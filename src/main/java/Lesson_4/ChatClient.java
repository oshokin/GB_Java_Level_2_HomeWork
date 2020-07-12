package Lesson_4;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    private InetAddress serverHost;
    private int serverPort = 8181;
    private String login;
    private Socket socket;

    public ChatClient(String login) throws UnknownHostException {
        this.serverHost = InetAddress.getLocalHost();
        this.socket = null;
        this.login = login;
    }

    public String getServerHost() {
        return serverHost.getHostAddress();
    }

    public void setServerHost(String serverHost) throws UnknownHostException {
        this.serverHost = InetAddress.getByName(serverHost);
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Message createMessage(String messageText) {
        return new Message(login, messageText);
    }

    public boolean sendMessage(Message message) throws IOException {
        //отправить сообщение через сокет на сервер и вернуть результат отправки
        return true;
    }
}

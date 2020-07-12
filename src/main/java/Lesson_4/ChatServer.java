package Lesson_4;

public class ChatServer {
    private int serverPort;
    private boolean isAlive;

    public ChatServer() {
        serverPort = 8181;
        //открыть ини файл и прочесть номер порта
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void start() {
        if (isAlive) System.out.println("Server is already running!");
        else {
            isAlive = true;
            System.out.println("Server started on port: " + getServerPort());
        }
    }

    public void stop() {
        if (!isAlive) System.out.println("Server is already stopped!");
        else {
            isAlive = false;
            System.out.println("Server stopped");
        }
    }
}

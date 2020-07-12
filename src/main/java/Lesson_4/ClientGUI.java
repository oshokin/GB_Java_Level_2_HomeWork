package Lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private ChatClient chatClient;
    private final JTextArea chatArea = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField ipAddressField = new JTextField();
    private final JTextField portField = new JTextField();
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField("123");
    private final JButton buttonLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton buttonDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField messageField = new JTextField();
    private final JButton buttonSend = new JButton("Send");

    private final JList<String> listUsers = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientGUI::new);
    }


    ClientGUI () {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setSize(WIDTH, HEIGHT);
        setAlwaysOnTop(true);

        try {
            chatClient = new ChatClient("javinator");
        } catch (UnknownHostException e) {
            System.out.println("Couldn't connect to the server: " + e.getMessage());
        }

        ipAddressField.setText(chatClient.getServerHost());
        portField.setText(Integer.toString(chatClient.getServerPort()));
        loginField.setText(chatClient.getLogin());

        listUsers.setListData(new String[]{"user1", "user2", "user3", "user4",
                "user5", "user6", "user7", "user8", "user9", "user-with-too-long-name-in-this-chat"});
        JScrollPane scrollPaneUsers = new JScrollPane(listUsers);
        JScrollPane scrollPaneChatArea = new JScrollPane(chatArea);
        scrollPaneUsers.setPreferredSize(new Dimension(100, 0));

        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);

        panelTop.add(ipAddressField);
        panelTop.add(portField);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(loginField);
        panelTop.add(passwordField);
        panelTop.add(buttonLogin);
        panelBottom.add(buttonDisconnect, BorderLayout.WEST);
        panelBottom.add(messageField, BorderLayout.CENTER);
        panelBottom.add(buttonSend, BorderLayout.EAST);

        add(scrollPaneChatArea, BorderLayout.CENTER);
        add(scrollPaneUsers, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        cbAlwaysOnTop.addActionListener(this);
        buttonSend.addActionListener(this);
        messageField.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == buttonSend || src == messageField) {
            String curText = messageField.getText();
            if (!curText.trim().isEmpty()) passMessageToClient(curText);
        } else {
            throw new RuntimeException("Unsupported action: " + src);
        }
    }

    //1. Отправлять сообщения в чат по нажатию кнопки или по нажатию клавиши Enter - должно отобразиться в chatArea.
    //Сама схема работы чата мне представляется так (как бы я делал это в 1С)
    //Если регистрация - скидываем серверу хеш пароля и логин, чтобы он внес их в базу в таблицу users,
    //А далее залогинивает пользователя.
    //Если это логин, чат клиент создает клиентский сокет, который стучится в сокет на стороне сервера
    //По отправке сообщения - формируется объект типа Message, у него есть логин, универсальное UNIX время и текст,
    //этот объект отправляется в сокет на сервер, тем временем сервер в бесконечном цикле опрашивает потоки клиентских сокетов.
    //При получении сообщения, сервер в потоке сокета клиента делает INSERT через JDBC в СУБД, и отдает ОК, если все прошло хорошо.
    //Клиент чата получает этот ответ и только если все хорошо, выводит сообщение в GUI, или ошибку.

    //2. Создать лог в файле (показать комментарием, где и как Вы планируете писать сообщение в файловый журнал - открытие файла, вывод и закрытие файла).
    //Текстовый лог, как мне кажется не нужен, только если как дополнительный элемент журналирования сообщений на сервере,
    //Но опять же, в 2020-м году, хранить незашифрованные логи на сервере - моветон.
    //По поводу реализации, на предыдущем месте работы я делал универсальную систему логгирования:
    //Сначала создается класс Logger, в котором прописаны IO операции для протоколирования в разные места.
    //Потом создается класс LogMessage, в котором по сути есть конструктор сообщения и несколько фабричных методов для каждой системы хранения логов.
    //Это для универсальности, чтобы у каждой системы всегда были необходимые данные для записи сообщений,
    //при смене выходного потока логов в настройках (у меня это были текст или Kafka, которая потом опрашивается через ELK)
    //В конструкторе класса Logger указываются настройки логгирования:
    //Система хранения сообщения (куда скидывать логи), и размер буффера логов, т. к. каждый раз делать операцию ввода/вывода затратно.
    //Делается 2 метода - boolean putMessage(logMessage) и boolean flush().
    //Оба метода возвращают true, если все хорошо
    //Текущих знаний/времени не хватает, чтобы самостоятельно реализовать подобную штуку в Java.

    private void passMessageToClient(String curText) {
        boolean messageSent;
        Message curMsg = chatClient.createMessage(curText);
        try {
            messageSent = chatClient.sendMessage(curMsg);
        } catch (IOException error) {
            messageSent = false;
        }
        if (messageSent) {
            chatArea.append(curMsg.toString() + String.format("%n"));
            messageField.setText("");
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        String msg = String.format("Exception in \"%s\": %s %s%n\t %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception!", JOptionPane.ERROR_MESSAGE);
    }
}

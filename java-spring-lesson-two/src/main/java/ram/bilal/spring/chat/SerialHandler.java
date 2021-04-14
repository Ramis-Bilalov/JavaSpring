package ram.bilal.spring.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SerialHandler implements Closeable, Runnable {

    private static int cnt = 0;
    private String userName;
    private final ObjectInputStream is;
    private final ObjectOutputStream os;
    private boolean running;
    EchoServer server;

    public SerialHandler(Socket socket, EchoServer server) throws IOException {
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
        cnt++;
        userName = "username" + cnt;
        running = true;
        this.server = server;
        os.writeObject(Message.of(userName, "OK"));
        os.flush();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Message message = (Message) is.readObject();
                if (message.getMessage().startsWith("/changeNick")) {
                    String[] data = message.getMessage().split(" ");
                    String oldName = userName;
                    userName = data[1];
                    String msg = String.format("User %s change name to %s", oldName, userName);
                    sendMessage(Message.of(userName, msg));
                    continue;
                }
                if (message.getMessage().startsWith("/private")) {
                    String[] data = message.getMessage().split(" ");
                    String nick = data[1];
                    String msg = data[2];
                    sendMessage(message);
                    server.sendMessageTo(userName, nick, msg);
                    continue;
                }
                message.setAuthor(userName);
                System.out.println(message);
                server.broadCast(message);
            } catch (IOException | ClassNotFoundException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Exception while read");
                break;
            }
        }
    }

    public void sendMessage(Message message) throws IOException {
        os.writeObject(message);
        os.flush();
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void close() throws IOException {
        os.close();
        is.close();
    }
}
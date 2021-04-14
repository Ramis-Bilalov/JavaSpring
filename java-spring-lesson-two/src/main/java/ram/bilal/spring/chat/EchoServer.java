package ram.bilal.spring.chat;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class EchoServer {

    private boolean running;
    private ConcurrentLinkedDeque<SerialHandler> clients = new ConcurrentLinkedDeque<>();
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class);

    public EchoServer() {
        running = true;
        try(ServerSocket server = new ServerSocket(8180)) {
            String log4jConfPath = "java-spring-lesson-two/src/main/resources/log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            String log = "Server started!";
            LOG.info(log);
            while (running) {
                System.out.println("Server is waiting connection");
                Socket socket = server.accept();
                log = "Client accepted!";
                LOG.info(log);
                SerialHandler handler = new SerialHandler(socket, this);
                clients.add(handler);
                //new Thread(handler).start();
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(handler);

                System.out.println("Client info: " + socket.getInetAddress());
            }
        } catch (Exception e) {
            String log = "Server crashed";
            LOG.info(log);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void broadCast(Message msg) throws IOException {
        for (SerialHandler client : clients) {
            client.sendMessage(msg);
            String log = "Сообщение: " + msg.getMessage();
            LOG.info(log);
        }
    }

    public void sendMessageTo(String from, String nick, String message) throws IOException {
        for (SerialHandler client : clients) {
            if (client.getUserName().equals(nick)) {
                client.sendMessage(Message.of(from, message));
            }
        }
    }

    public void kickMe(SerialHandler client) {
        clients.remove(client);
    }

    public static void main(String[] args) {
        new EchoServer();
    }
}
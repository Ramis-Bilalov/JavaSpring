package ram.bilal.spring.chat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {

    private Date sendAt;
    private String author;
    private String message;
    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public static Message of(String author, String message) {
        Message m = new Message();
        m.setAuthor(author);
        m.setMessage(message);
        m.setSendAt(new Date());
        return m;
    }

    public Message() {
    }

    public Date getSendAt() {
        return sendAt;
    }

    public void setSendAt(Date sendAt) {
        this.sendAt = sendAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
//        if(author.endsWith("1")) {
//            return String.format("\t\t\t\t\t\t" + "%s %s: %s\n", format.format(sendAt), author, message);
//        }
        return String.format("%s %s: %s\n", format.format(sendAt), author, message);
    }
}
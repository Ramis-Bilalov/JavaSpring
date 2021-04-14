package ram.bilal.spring.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

@Component
public class UsersSQLiteDao implements UserDao, Closeable {

    private Connection connection;
    private PreparedStatement getNicknameStatement;
    private PreparedStatement updateNickStatement;
    private PreparedStatement userExistsStatement;
    private String nick;

    @Autowired
    public UsersSQLiteDao() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:chatDB");
        try {
            getNicknameStatement = connection.prepareStatement("SELECT NAME FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
            updateNickStatement = connection.prepareStatement("INSERT INTO USERS(login, password, name, email) VALUES(?, ?, ?, ?)");
            userExistsStatement = connection.prepareStatement("SELECT NAME FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String setAuth(String login, String password) throws SQLException {
        getNicknameStatement.setString(1, login);
        getNicknameStatement.setString(2, password);
        nick = getNicknameStatement.executeQuery().getString("NAME");
        return nick;
    }

    @Override
    public boolean userExists(String login, String password) throws SQLException {
        userExistsStatement.setString(1, login);
        userExistsStatement.setString(2, password);
        ResultSet resultSet = userExistsStatement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void updateUser(String login, String password, String nickname, String email) throws SQLException {
        try {
            updateNickStatement.setString(1, login);
            updateNickStatement.setString(2, password);
            updateNickStatement.setString(3, nickname);
            updateNickStatement.setString(4, email);
            updateNickStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getNickname(String login, String password) {
        try {
            getNicknameStatement.setString(1, login);
            getNicknameStatement.setString(2, password);
            getNicknameStatement.execute();
            nick = getNicknameStatement.executeQuery().getString("NAME");
            return nick;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return null;
    }

    @Override
    public void close() throws IOException {
        try {
            getNicknameStatement.close();
            updateNickStatement.close();
            userExistsStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

package quiz.dao.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
/*
            statement.executeUpdate("insert into category(name) values (" +
                    "'cat1'), ('cat2')");
*/
/*
            statement.executeUpdate("insert into question(text, questionType, questionDifficulty, category_id) values " +
                    "('text1', 'OPEN', 'LOW', 536)," +
                    "('text2', 'OPEN', 'MEDIUM', 536)," +
                    "('text3', 'OPEN', 'HIGH', 536)," +
                    "('text4', 'CHOICE', 'LOW', 537)," +
                    "('text5', 'CHOICE', 'MEDIUM', 537)," +
                    "('text6', 'CHOICE', 'HIGH', 537)" );
*/
            statement.executeUpdate("insert into answer(text, value, question_id) values " +
                    "('text1', 'TRUE', 7)," +
                    "('text2', 'TRUE', 8)," +
                    "('text3', 'TRUE', 9)," +
                    "('text4', 'FALSE', 10)," +
                    "('text5', 'FALSE', 11)," +
                    "('text6', 'FALSE', 12)");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tryToCloseConnection(connection);
        }
    }

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost/companie";
        String user = "root";
        String password = "root";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Nu exista conexiune.");
        }
    }

    private static void tryToCloseConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

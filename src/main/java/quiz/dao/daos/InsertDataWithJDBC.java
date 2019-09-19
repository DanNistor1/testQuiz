package quiz.dao.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataWithJDBC {
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
                    "('question1', 'OPEN', 'LOW', 536)," +
                    "('question2', 'OPEN', 'MEDIUM', 536)," +
                    "('question3', 'OPEN', 'HIGH', 536)," +
                    "('question4', 'CHOICE', 'LOW', 537)," +
                    "('question5', 'CHOICE', 'MEDIUM', 537)," +
                    "('question6', 'CHOICE', 'HIGH', 537)" );
*/
            statement.executeUpdate("insert into answer(text, value, question_id) values " +
                    "('answer1', 'TRUE', 7)," +
                    "('answer2', 'TRUE', 8)," +
                    "('answer3', 'TRUE', 9)," +
                    "('answer4', 'TRUE', 10)," +
                    "('answer5', 'TRUE', 11)," +
                    "('answer6', 'TRUE', 12)");

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

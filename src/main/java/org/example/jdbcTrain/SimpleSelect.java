package org.example.jdbcTrain;

import java.sql.*;

public class SimpleSelect {
    public static void main(String[] args) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/codekitchen",
                    "codekitchen", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM records");

            while (results.next()) {
                int id = results.getInt(1);
                int status = results.getInt(2);
                String text=results.getString(3);
                System.out.println(results.getRow() + ". " + id + "\t"+ status+"\t"+ text);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

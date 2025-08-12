package org.example.jdbcTrain;

import java.sql.*;


public class PreparedStatementEx {
    public static void main(String[] args) throws SQLException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/codekitchen",
                    "codekitchen", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String firstName = "Harry";
        String lastName = "Potter";
        int age = 16;
        String middleName = "James";

// Запрос с указанием мест для параметров в виде знака "?"
        String sql = "INSERT INTO students (age,first_name, last_name, middle_name) VALUES (?, ?, ?, ?)";

// Создание запроса. Переменная con — это объект типа Connection
        PreparedStatement stmt = connection.prepareStatement(sql);

// Установка параметров
        stmt.setInt(1,age);
        stmt.setString(2, firstName);
        stmt.setString(3, lastName);
        stmt.setString(4, middleName);

// Выполнение запроса
        stmt.executeUpdate();
        connection.close();
    }
}

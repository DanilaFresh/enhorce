package org.example.jdbcTrain;

import java.sql.*;

public class BatchEx {
    public static void main(String[] args) throws SQLException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/codekitchen",
                    "codekitchen", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql="INSERT INTO students (age,first_name, last_name, middle_name) VALUES (?, ?, ?, ?)";
        PreparedStatement statement=connection.prepareStatement(sql);

        for (int i = 0; i < 10; i++) {
            // Заполняем параметры запроса
            statement.setInt(1, 20 + i);
            statement.setString(2, "First_name_" + i);
            statement.setString(3, "Second_name_" + i);
            statement.setString(4, "Middle_name_ "+ i);
            statement.addBatch();//скалыдваем запросы в пачку
        }
        statement.executeBatch();//отправляем запросы пачкой
        connection.close();
    }
}

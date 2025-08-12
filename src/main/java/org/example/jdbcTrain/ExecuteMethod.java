package org.example.jdbcTrain;

import java.sql.*;

public class ExecuteMethod {
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
            Statement statement=connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Count(*) FROM records");
            results.next();
            int count = results.getInt(1);
            System.out.println("Количество записей в records "+count);

            int rowsCount = statement.executeUpdate("UPDATE records SET status = 0 "+"where id=1 ");
            System.out.println("Количество обновленных записей "+rowsCount);

            boolean hasResults = statement.execute("SELECT Count(*) FROM records");
            if ( hasResults ) {
                ResultSet results2 =  statement.getResultSet();
                results2.next();
                int count2 = results2.getInt(1);
                System.out.println("Количество записей в records "+count2);
            }

            boolean hasResults2 = statement.execute("UPDATE records SET status = 0 "+"where id=1 ");
            if ( !hasResults2 ) {
                int count3 = statement.getUpdateCount();
                System.out.println("Количество обновленных записей "+count3);
            }
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

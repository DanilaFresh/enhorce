package org.example.jdbcTrain;

import java.sql.*;

public class Transactions {
    public static void main(String[] args) throws SQLException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/codekitchen",
                    "codekitchen", "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            int rowsCount1 = statement.executeUpdate("INSERT INTO records (status,title) VALUES(0,'text1');");
            System.out.println(rowsCount1);
            int rowsCount2 = statement.executeUpdate("INSERT INTO records (status,title) VALUES(0,'text2')");
            Savepoint save = connection.setSavepoint();
            try{
                int rowsCount3 = statement.executeUpdate("UPDATE  несколько опечаток приведут к исключению");
            }
            catch (Exception e) {
                connection.rollback(save);
            }
            connection.commit();
        }
        catch (Exception e) {
            connection.rollback();
        }finally {
            connection.close();
        }
    }
}

package org.example.jdbcTrain;
import java.sql.*;
public class ManageRows {
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
            ResultSet results =
                    statement.executeQuery("SELECT * FROM addresses" +
                                        " inner join students ON (student_id=students.id)");
            ResultSetMetaData metaData = results.getMetaData();//получаем данные о структуре ответа

            System.out.println(metaData.getTableName(1));
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++)
            {
                String name = metaData.getColumnName(column);
                String className = metaData.getColumnClassName(column);
                String typeName = metaData.getColumnTypeName(column);
                int type = metaData.getColumnType(column);

                System.out.println(name + "\t" + className + "\t" + typeName + "\t" + type);
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

package org.example.jdbcTrain;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.sql.*;


public class WorkWithBlobs {
    public static void main(String[] args) throws SQLException, IOException {
       Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/codekitchen",
                "codekitchen", "12345678");


        connection.setAutoCommit(false);
        Savepoint save=connection.setSavepoint();
        try {
            String insertQuery = "INSERT INTO blobs (object) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            Path photo = Paths.get("C:\\Users\\user\\Pictures\\Screenshots\\test.png");
            InputStream inputStream=Files.newInputStream(photo);

            statement.setBinaryStream(1,inputStream);
            inputStream.close();
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            connection.rollback(save);
            System.out.println(e);
        }finally {
            connection.close();
        }
        //считываем объект

       connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/codekitchen",
                "codekitchen", "12345678");
        Statement statement=connection.createStatement();
        ResultSet results =statement.executeQuery("SELECT object FROM blobs WHERE id = 1");
        results.next();
        InputStream in=results.getBinaryStream(1);
        Path path=Paths.get("C:\\Users\\user\\Pictures\\Screenshots\\testFromDB.png");
        FileChannel fileChannel=FileChannel.open(path,StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        ByteBuffer buff=ByteBuffer.wrap(in.readAllBytes());
        fileChannel.write(buff);
        fileChannel.close();
        connection.close();
    }
}

package org.example.IOandNIO;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NIO {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("text.txt");
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);  // буфер на 1024 байта
            while (channel.read(buffer) > 0) {
                buffer.flip();  // переключаем буфер в режим чтения
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.print((char) b);  // выводим байты как символы
                }
                buffer.clear(); // очищаем буфер для следующей порции данных
            }
        }
        File file=new File("output.txt");
        if(!file.exists())
            file.createNewFile();
        String text = "Hello, world!\nПривет, мир!\n";
        ByteBuffer buffer = ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8));
        // Открываем канал на запись (с созданием файла или заменой)
        try (FileChannel channel = FileChannel.open(Path.of("output.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            channel.write(buffer);
        }
    }
}


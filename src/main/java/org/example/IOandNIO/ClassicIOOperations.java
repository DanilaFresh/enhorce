package org.example.IOandNIO;

import java.io.*;
import java.nio.charset.Charset;

public class ClassicIOOperations {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("First string\n");
        stringBuilder.append("Second string\n");
        stringBuilder.append("Third string\n");
        byte[] bytes = stringBuilder.toString().getBytes();
        //Запись в файл без буферов
        File fileText = new File("text.txt");
        if (!fileText.exists()) {
            try {
                fileText.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            fileOutputStream.write(bytes);
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Запись в файл с буфером

        File fileTextWithBuffer = new File("textBuffer.txt");
        if (!fileTextWithBuffer.exists()) {
            try {
                fileTextWithBuffer.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            fileOutputStream = new FileOutputStream(fileTextWithBuffer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        try {
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Запись в файл с ByteArray

        File fileTextWithByteArray = new File("textByteAray.txt");
        if (!fileTextWithByteArray.exists()) {
            try {
                fileTextWithByteArray.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            fileOutputStream = new FileOutputStream(fileTextWithByteArray);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            byteArrayOutputStream.writeTo(fileOutputStream);
//            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Запись в файл с DataOutput

        File fileTextWithData = new File("textData.txt");
        if (!fileTextWithData.exists()) {
            try {
                fileTextWithData.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            fileOutputStream = new FileOutputStream(fileTextWithData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        try {
            dataOutputStream.writeUTF("String\n");
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeDouble(2.4);
            dataOutputStream.writeFloat(3.55F);
            dataOutputStream.writeLong(1111111111);
            dataOutputStream.write(123);
            dataOutputStream.writeShort(2222);
            dataOutputStream.writeInt(213124124);
            dataOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ЧТЕНИЕ

        //Чтение из FileInputStream
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bytes = fileInputStream.readAllBytes();
            fileOutputStream.close();
            String str = new String(bytes, Charset.defaultCharset());
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Чтение из BufferedInputStream
        try {
            fileInputStream = new FileInputStream(fileTextWithBuffer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try {
            bytes = bufferedInputStream.readAllBytes();
            System.out.println(new String(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Чтение из ByteArrayInputStream
        try {
            fileInputStream = new FileInputStream(fileTextWithByteArray);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(fileInputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new String(byteArrayInputStream.readAllBytes()));

        //Чтение из FileInputStream
        try {
            fileInputStream = new FileInputStream(fileTextWithData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(dataInputStream.readUTF());
            stringBuilder.append(dataInputStream.readBoolean()).append("\n");
            stringBuilder.append(dataInputStream.readDouble()).append("\n");
            stringBuilder.append(dataInputStream.readFloat()).append("\n");
            stringBuilder.append(dataInputStream.readLong()).append("\n");
            stringBuilder.append(dataInputStream.read()).append("\n");
            stringBuilder.append(dataInputStream.readShort()).append("\n");
            stringBuilder.append(dataInputStream.readInt()).append("\n");
            System.out.println(stringBuilder.toString());
            dataInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Reader и Writer

        //Запись
        stringBuilder = new StringBuilder();
        stringBuilder.append("First string Writer\n");
        stringBuilder.append("Second string Writer\n");
        stringBuilder.append("Third string Writer\n");

        //Запись в файл FileWriter
        File fileTextWriter = new File("textWriter.txt");
        if (!fileTextWriter.exists()) {
            try {
                fileTextWriter.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileTextWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Запись в файл BufferedWriter
        File fileTextBufferedWriter = new File("textBufferedWriter.txt");
        if (!fileTextBufferedWriter.exists()) {
            try {
                fileTextBufferedWriter.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        BufferedWriter bufferedWriter;
        try {
            fileWriter = new FileWriter(fileTextWriter);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Запись в файл PrintWriter
        File fileTextPrintWriter = new File("textfileTextPrintWriter.txt");
        if (!fileTextPrintWriter.exists()) {
            try {
                fileTextPrintWriter.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(fileTextPrintWriter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        printWriter.print(stringBuilder.toString());
        printWriter.close();

        //Чтение
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileText);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        stringBuilder = new StringBuilder();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                stringBuilder.append((char) i);
            }
            System.out.println(stringBuilder.toString());
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileInputStream = new FileInputStream(fileText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        i = -1;
        try {
            while ((i = inputStreamReader.read()) != -1) {
                System.out.print((char) i);
            }
            inputStreamReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n");
        //BufferdReader
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileText));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String s;

        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
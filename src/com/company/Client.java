package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1020)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите число: ");
            int userInput = scanner.nextInt();
            OutputStream outputStream = socket.getOutputStream();
            // отправка числа серверу
            outputStream.write(userInput);
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            // получение ответа от сервера и вывод
            int response = inputStream.read();
            System.out.println("Ответ сервера: " + response);
        }
    }
}

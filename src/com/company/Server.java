package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    private static final Logger LOG = Logger.getLogger(Server.class.getName());
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1020)) {
            // цикл для ожидания подключений от клиентов
            while (true) {
                Socket socket = serverSocket.accept();
                serveClient(socket);
            }
        }
    }
    // метод для работы клиента
    private static void serveClient(Socket socket) throws IOException {
        LOG.info("Клиент" + socket.getInetAddress());
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        // цикл для обработки запросов от клиента
        while (true) {
            int request = inputStream.read();
            if (request == -1) {
                break;
            }
            LOG.info("Запрос:" + request);
            // отправление квадрата полученного числа обратно клиенту
            outputStream.write(request * request);
            outputStream.flush();
        }
    }
}
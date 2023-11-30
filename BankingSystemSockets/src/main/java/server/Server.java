package server;

import bank.BankHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket servSock = new ServerSocket(PORT);

            while (true) {
                Socket socket = servSock.accept();
                System.out.println("Conexão estabelecida com o cliente " + socket.getInetAddress().getHostAddress());
                Thread bank = new Thread(new BankHandler(socket));
                bank.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

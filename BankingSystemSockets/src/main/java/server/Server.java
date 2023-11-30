package server;

import bank.BankHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private static final int BUFSIZE = 32; // Size of receive buffer
    public static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket servSock = new ServerSocket(PORT);
            int recvSize; // Size of receive message
            byte[] byteBuffer = new byte[BUFSIZE]; // Receive buffer


            while (true){
                Socket socket = servSock.accept();
                System.out.println("Conexão estabelecida com o cliente " + socket.getInetAddress().getHostAddress());
//                InputStream in = socket.getInputStream();
//                OutputStream out = socket.getOutputStream();
//                while ((recvSize = in.read(byteBuffer)) != -1) {
//                    out.write(byteBuffer, 0, recvSize);
//                }
                Thread bank = new Thread(new BankHandler(socket));
                bank.start();

//                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

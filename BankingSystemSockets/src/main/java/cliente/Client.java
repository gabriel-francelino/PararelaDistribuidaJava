package cliente;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_NAME, SERVER_PORT);
        System.out.println("Connected to server... sending echo string");

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String input = "-99";

        do {
            System.out.print("Escolha uma opção: ");
            input = userInput.readLine();
            out.println(input);

            String accountNumber;
            String amount;

            switch (input) {
                case "1":
                    System.out.println("Digite seu nome:");
                    String nameAccount = userInput.readLine();
                    out.println(input);
                    break;
                case "2", "3":
                    accountNumber = userInput.readLine();
                    amount = userInput.readLine();
                    out.println(input);
                    break;
                case "4":
                    String accountSendId = userInput.readLine();
                    String accountRecvId = userInput.readLine();
                    amount = userInput.readLine();
                    out.println(input);
                    break;
                case "5":
                    accountNumber = userInput.readLine();
                    out.println(input);
                    break;
                default:
//                    String returnFailed = in.readLine();
            }

            System.out.println("Resposta do servidor: \n" + in.readLine());
        } while(!input.equals("0"));
        // Convert input String to bytes using default character encoding
//        byte[] byteBuffer = args[1].getBytes();
//        int servPort = Integer.parseInt(args[1]);
//        System.out.println("Antes de conectar " + server + " " + servPort);
//
//        // Create socket that is connected to server on specified port
//        Socket socket = new Socket(server, servPort);
//        System.out.println("Connected to server... sending echo string");
//        InputStream in = socket.getInputStream();
//        OutputStream out = socket.getOutputStream();
//        out.write(byteBuffer); // Send the encoded string to the server
//        // Receive the same string back from the server
//        int totalBytesRcvd = 0;
//        int bytesRcvd;
//        while (totalBytesRcvd < byteBuffer.length) {
//            if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) {
//                socket.close();
//                throw new SocketException("Connection closed prematurely");
//            }
//            totalBytesRcvd += bytesRcvd;
//        }
//        System.out.println("Received: " + new String(byteBuffer));
        socket.close();
    }
}
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

        while (true) {
            System.out.print("Escolha uma opção: ");
            input = userInput.readLine();
            if (input == null || input.equals("0")) {
                break;
            }
            out.println(input);
            System.out.println("Server response: " + in.readLine());
        }
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
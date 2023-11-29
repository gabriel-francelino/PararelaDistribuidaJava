package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length != 2)
            throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
        String server = args[0];

        // Convert input String to bytes using default character encoding
        byte[] byteBuffer = args[1].getBytes();
        int servPort = Integer.parseInt(args[1]);
        System.out.println("Antes de conectar " + server + " " + servPort);

        // Create socket that is connected to server on specified port
        Socket socket = new Socket(server, servPort);
        System.out.println("Connected to server... sending echo string");
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(byteBuffer); // Send the encoded string to the server
        // Receive the same string back from the server
        int totalBytesRcvd = 0;
        int bytesRcvd;
        while (totalBytesRcvd < byteBuffer.length) {
            if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) {
                socket.close();
                throw new SocketException("Connection closed prematurely");
            }
            totalBytesRcvd += bytesRcvd;
        }
        System.out.println("Received: " + new String(byteBuffer));
        socket.close();
    }
}
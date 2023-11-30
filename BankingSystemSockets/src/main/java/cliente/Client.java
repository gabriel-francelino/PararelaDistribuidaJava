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
            showOptions();
            input = userInput.readLine();
            out.println(input);

            String accountNumber;
            String amount;

            switch (input) {
                case "1":
                    System.out.println("Digite seu nome:");
                    String nameAccount = userInput.readLine();
                    out.println(nameAccount);
                    break;
                case "2", "3":
                    System.out.println("Digite o número da conta:");
                    accountNumber = userInput.readLine();
                    System.out.println("Digite o valor:");
                    amount = userInput.readLine();
                    out.println(accountNumber);
                    out.println(amount);
                    break;
                case "4":
                    System.out.println("Digite o número da conta de origem:");
                    String accountSendId = userInput.readLine();
                    System.out.println("Digite o número da sua conta de destino:");
                    String accountRecvId = userInput.readLine();
                    System.out.println("Digite o valor a ser transferido:");
                    amount = userInput.readLine();
                    out.println(accountSendId);
                    out.println(accountRecvId);
                    out.println(amount);
                    break;
                case "5":
                    System.out.println("Digite o número da conta:");
                    accountNumber = userInput.readLine();
                    out.println(accountNumber);
                    break;
                default: ;
            }

            System.out.println(in.readLine());
        } while(!input.equals("0"));

        socket.close();
    }

    private static void showOptions() {
        System.out.println("=============== Bem vindos ao Banco ===============");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Saldo");
        System.out.println("6 - Sair");
        System.out.println("===================================================");
        System.out.print("Digite a opção desejada: ");
    }
}
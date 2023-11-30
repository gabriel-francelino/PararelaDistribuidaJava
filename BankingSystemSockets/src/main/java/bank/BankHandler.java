package bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class BankHandler extends Bank implements Runnable {
    // Variáveis de comunicação
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
    private BufferedReader in;
    private PrintWriter out;

    // Váriaveis do banco
    private int accountNumber;
    private ArrayList<Account> accounts;

    public BankHandler(Socket socket) {
        this.accountNumber = 1000;
        this.accounts = new ArrayList<>();

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            String operation;
            double amount;
            int accountNumber;
            while ((operation = in.readLine()) != null) {

                switch (operation) {
                    case "1":
                        String nameAccount = in.readLine();
                        out.println(createAccount(nameAccount));
                        break;
                    case "2":
                        accountNumber = Integer.parseInt(in.readLine());
                        amount = Double.parseDouble(in.readLine());
                        out.println(deposit(accountNumber, amount));
                        break;
                    case "3":
                        accountNumber = Integer.parseInt(in.readLine());
                        amount = Double.parseDouble(in.readLine());
                        out.println(withdraw(accountNumber, amount));
                        break;
                    case "4":
                        int accountSendId = Integer.parseInt(in.readLine());
                        int accountRecvId = Integer.parseInt(in.readLine());
                        amount = Double.parseDouble(in.readLine());
                        out.println(transfer(accountSendId, accountRecvId, amount));
                        break;
                    case "5":
                        accountNumber = Integer.parseInt(in.readLine());
                        out.println(getBalance(accountNumber));
                        break;
                    case "0":
                        out.println("Saindo...");
                        break;
                    default:
                        out.println("Operação inválida!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String brazilianNumberFormat(double amount) {
        Locale brazil = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(brazil);
        return numberFormat.format(amount);
    }

    private static Account findAccount(ArrayList<Account> accounts, int accountNumber) {
        Account account = null;

        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                account = acc;
                break;
            }
        }

        return account;
    }

    @Override
    String createAccount(String name) {
        this.accountNumber++;
        Account account = new Account(this.accountNumber, name);
        this.accounts.add(account);
        return "Conta criada com sucesso! " + account.info();
    }

    @Override
    String deposit(int accountNumber, double amount) {
        Account account = findAccount(this.accounts, accountNumber);

        if (amount > 0) {
            account.setBalance(amount);
            return "Depósito realizado com sucesso!";
        } else {
            return "O valor deve ser maior que 0 para ser depositado!";
        }
    }

    @Override
    String withdraw(int accountNumber, double amount) {
        Account account = findAccount(this.accounts, accountNumber);

        if (amount < 0) {
            return "O valor do saque deve ser maior que 0!";
        } else if (amount > account.getBalance() || 0 >= account.getBalance()) {
            return "Operação inválida! Não há saldo suficiente!";
        } else {
            account.setBalance(-amount);
            return "Saque realizado com sucesso!";
        }
    }

    @Override
    String transfer(int accountSendId, int accountRecvId, double amount) {
        Account accountSend = findAccount(this.accounts, accountSendId);
        Account accountRecv = findAccount(this.accounts, accountRecvId);

        if (amount < 0) {
            return "O valor do saque deve ser positivo!";
        } else if (amount > accountSend.getBalance() || 0 >= accountSend.getBalance()) {
            return "Operação inválida! Não há saldo suficiente!";
        } else {
            accountSend.setBalance(-amount);
            accountRecv.setBalance(amount);

//            String proofOfTransfer =
//                    "============================\n" +
//                            "Comprovante de transferência\n" +
//                            "Conta de origem: " + accountSend.getName() + "\n" +
//                            "Conta de destino: " + accountRecv.getName() + "\n" +
//                            "Valor: " + brazilianNumberFormat(amount) + "\n" +
//                            "============================\n";

            String response = "Transferência realizada com sucesso! [" +
                    "Origem: " + accountSend.getName() + ", " +
                    "Destino: " + accountRecv.getName() + ", " +
                    "Valor: " + brazilianNumberFormat(amount) + "]";
                    ;

            return response;
        }
    }

    @Override
    String getBalance(int accountNumber) {
        Account account = findAccount(this.accounts, accountNumber);

        if (account == null) {
            return "Conta não encontrada!";
        }

        return account.info();
    }
}

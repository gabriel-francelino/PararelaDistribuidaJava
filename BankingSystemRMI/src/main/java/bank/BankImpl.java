package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BankImpl extends UnicastRemoteObject implements Bank {
    private int accountNumber;
    private ArrayList<Account> accounts = new ArrayList<>();

    public BankImpl() throws RemoteException {
        super();
        accountNumber = 1000;
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
    public String getBalance(int accountNumber) {
        Account account = findAccount(this.accounts, accountNumber);

        if (account == null) {
            return "Conta não encontrada!";
        }

        return account.info();
    }

    @Override
    public String createAccount(String name) {
        this.accountNumber++;
        Account account = new Account(this.accountNumber, name);
        this.accounts.add(account);
        return "Conta criada com sucesso!\n" + account.info();
    }

    @Override
    public String deposit(int accountNumber,double amount) {
        Account account = findAccount(this.accounts, accountNumber);

        if (amount > 0) {
            account.setBalance(amount);
            return "Depósito realizado com sucesso!";
        } else {
            return "O valor deve ser maior que 0 para ser depositado!";
        }
    }

    @Override
    public String withdraw(int accountNumber, double amount) {
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
    public String transfer(int accountSendId, int accountRecvId, double amount) {
        Account accountSend = findAccount(this.accounts, accountSendId);
        Account accountRecv = findAccount(this.accounts, accountRecvId);

        if (amount < 0) {
            return "O valor do saque deve ser positivo!";
        } else if (amount > accountSend.getBalance() || 0 >= accountSend.getBalance()) {
            return "Operação inválida! Não há saldo suficiente!";
        } else {
            accountSend.setBalance(-amount);
            accountRecv.setBalance(amount);

            String proofOfTransfer =
                    "============================\n" +
                    "Comprovante de transferência\n" +
                    "Conta de origem: " + accountSend.getName() + "\n" +
                    "Conta de destino: " + accountRecv.getName() + "\n" +
                    "Valor: " + brazilianNumberFormat(amount) + "\n" +
                    "============================\n";

            return proofOfTransfer;
        }
    }


}

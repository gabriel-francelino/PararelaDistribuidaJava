package bank;

import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private int accountNumber;
    private String name;
    private double balance;

    public Account(int number, String name) {
        this.accountNumber = number;
        this.name = name;
        this.balance = 0.0;
    }

    private static String brazilianNumberFormat(double amount) {
        Locale brazil = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(brazil);
        return numberFormat.format(amount);
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double amount) {
        this.balance += amount;
    }

    public String info() {
        return "============================\n" +
                "Nome: " + this.name + "\n" +
                "Número da conta: " + this.accountNumber + "\n" +
                "Saldo: " + brazilianNumberFormat(this.balance) + "\n" +
                "============================\n";
    }
}
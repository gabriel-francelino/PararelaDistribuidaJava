package bank;

public abstract class Bank {
    abstract String createAccount(String name);

    abstract String deposit(int accountNumber,double amount);

    abstract String withdraw(int accountNumber, double amount);

    abstract String transfer(int accountSendId, int accountRecvId, double amount);

    abstract String getBalance(int accountNumber);
}

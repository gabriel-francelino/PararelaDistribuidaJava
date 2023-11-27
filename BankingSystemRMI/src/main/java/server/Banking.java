package server;

public interface Banking {
    void createAccount(int id, String name);
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(int idAccount1, int idAccount2, double amount);
    double getBalance();
}

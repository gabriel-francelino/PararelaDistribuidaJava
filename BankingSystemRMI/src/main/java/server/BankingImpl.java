package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankingImpl extends UnicastRemoteObject implements Banking {
    private int id;
    private String name;
    private double balance;


    protected BankingImpl() throws RemoteException {
        super();
    }

    @Override
    public void createAccount(int id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
    }

    @Override
    public void deposit(double amount) {

    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public void transfer(int idAccount1, int idAccount2, double amount) {

    }

    @Override
    public double getBalance() {
        return 0;
    }
}

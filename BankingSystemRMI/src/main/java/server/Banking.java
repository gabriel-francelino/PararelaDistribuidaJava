package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banking extends Remote {
    void createAccount(int id, String name) throws RemoteException;

    void deposit(double amount) throws RemoteException;

    void withdraw(double amount) throws RemoteException;

    void transfer(Banking account, double amount) throws RemoteException;

    double getBalance() throws RemoteException;
}

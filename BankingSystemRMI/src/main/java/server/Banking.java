package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banking extends Remote {
    void createAccount(int id, String name) throws RemoteException;
    void deposit(double amount) throws RemoteException;
    void withdraw(double amount) throws RemoteException;
    void transfer(int idAccount1, int idAccount2, double amount) throws RemoteException;
    double getBalance() throws RemoteException;
}

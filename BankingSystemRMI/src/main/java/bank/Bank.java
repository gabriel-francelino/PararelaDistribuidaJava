package bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {
    String createAccount(String name) throws RemoteException;

    String deposit(int accountNumber,double amount) throws RemoteException;

    String withdraw(int accountNumber, double amount) throws RemoteException;

    String transfer(int accountSendId, int accountRecvId, double amount) throws RemoteException;

    String getBalance(int accountNumber) throws RemoteException;
}

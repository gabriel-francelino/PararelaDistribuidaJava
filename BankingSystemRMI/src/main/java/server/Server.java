package server;

import bank.BankImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1234);
            Naming.rebind("rmi://localhost:1234/Banking", new BankImpl());
            Naming.rebind("rmi://localhost:1234/Banking1", new BankImpl());
            Naming.rebind("rmi://localhost:1234/Banking2", new BankImpl());
            System.out.println("The server starting...");
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

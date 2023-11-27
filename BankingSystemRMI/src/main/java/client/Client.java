package client;

import server.Banking;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try{
            Banking account1 = (Banking) Naming.lookup("rmi://localhost:1234/Banking");

            System.out.println("Account 1 balance: " + account1.getBalance());

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

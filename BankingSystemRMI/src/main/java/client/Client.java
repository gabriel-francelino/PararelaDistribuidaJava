package client;

import server.Banking;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            Banking account1 = (Banking) Naming.lookup("rmi://localhost:1234/Banking1");
            Banking account2 = (Banking) Naming.lookup("rmi://localhost:1234/Banking2");

            account1.createAccount(1, "John");
            account2.createAccount(2, "Mary");

            account1.deposit(3000);
            account2.deposit(5000);

            System.out.println("Account 1 balance: " + account1.getBalance());
            System.out.println("Account 2 balance: " + account2.getBalance());

            account1.transfer(account2, 1520);

            System.out.println("Account 1 balance: " + account1.getBalance());
            System.out.println("Account 2 balance: " + account2.getBalance());


            System.out.println("Account 1 balance: " + account1.getBalance());

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

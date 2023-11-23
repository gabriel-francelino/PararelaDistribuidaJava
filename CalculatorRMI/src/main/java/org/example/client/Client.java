package org.example.client;

import org.example.server.Calculator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            Calculator stub = (Calculator) Naming.lookup("rmi://localhost:1234/Calculator");

            System.out.println("Sum 4 + 5 : " + stub.sum(4,5));
            System.out.println("Sub 7 - 8 - 9 - 5 : " + stub.sub(7, 8, 9, 5));
            System.out.println("Mul 12 * 2 * -1 : " + stub.mul(12, 2 , -1));
            System.out.println("Div 160 / 2: " + stub.div(160, 2));
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

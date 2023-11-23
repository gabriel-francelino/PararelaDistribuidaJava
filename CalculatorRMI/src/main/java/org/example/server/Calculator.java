package org.example.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    double sum(double... numbers) throws RemoteException;
    double sub(double... numbers) throws RemoteException;
    double mul(double... numbers) throws RemoteException;
    double div(double number, double divider) throws RemoteException;
}

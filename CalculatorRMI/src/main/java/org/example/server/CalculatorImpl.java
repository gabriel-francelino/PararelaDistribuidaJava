package org.example.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    protected CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double sum(double... numbers) throws RemoteException {
        double sumValues = 0;
        for (double number : numbers) {
            sumValues += number;
        }
        return sumValues;
    }

    @Override
    public double sub(double... numbers) throws RemoteException {
        double subValues = 0;
        for (double number : numbers) {
            subValues -= number;
        }
        return subValues;
    }

    @Override
    public double mul(double... numbers) throws RemoteException {
        double mulValues = 1;
        for (double number : numbers) {
            mulValues *= number;
        }
        return mulValues;
    }

    @Override
    public double div(double number, double divider) throws RemoteException {
        return number / divider;
    }
}
